package com.pcwk.ehr.community.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pcwk.ehr.cmn.MessageVO;
import com.pcwk.ehr.cmn.SearchVO;
import com.pcwk.ehr.cmn.StringUtil;
import com.pcwk.ehr.community.domain.CommunityVO;
import com.pcwk.ehr.community.service.CommunityService;
import com.pcwk.ehr.member.domain.MemberVO;

@Controller
@RequestMapping("community")
public class CommunityController {
	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	private CommunityService communityService;
	
	public CommunityController() {
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ *CommunityController()*               │");
		log.debug("└───────────────────────────────────────┘");		
	}
	
	@GetMapping("/community_reg_index.do")
	public String communityRegIndex(@RequestParam(name = "cmn_div", defaultValue = "10") String div, Model model) {
		String viewName = "community/community_reg";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **boardRegIndex()**                   │");
		log.debug("└───────────────────────────────────────┘");		
		log.debug("│ div                   │"+div);
		
		model.addAttribute("cmn_div", div);
		
		return viewName;
	}
	
	@RequestMapping(value = "/doUpdate.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doUpdate(HttpServletRequest req, CommunityVO param) throws SQLException {
		String jsonString = "";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **doUpdate()**                        │");
		log.debug("└───────────────────────────────────────┘");
		log.debug(" │ param                           │" + param);

		int flag = communityService.doUpdate(param);
		
		String message = "";

		if (flag == 1) {
			message = "글 번호" + param.getCmnNo() + "의 정보가 수정 되었습니다.";
		} else {
			message = "글 번호" + param.getCmnNo() + "님의 정보 수정이 실패했습니다.";
		}
		
		jsonString = new Gson().toJson(new MessageVO(flag, message));
		log.debug("jsonString:\n{}", jsonString);
		
		return jsonString;
	}
	
	@GetMapping(value = "/doDelete.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doDelete(@RequestParam(name = "cmn_no", required = true, defaultValue = "0")int cmnNo) throws SQLException {
		String jsonString = "";
		
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **doDelete()**                        │");
		log.debug("└───────────────────────────────────────┘");				
		
		log.debug("│ cmnNo                   │" + cmnNo);
		
		CommunityVO inVO = new CommunityVO();
		inVO.setCmnNo(cmnNo);
		
		log.debug("│ inVO                   │" + inVO);
		
		int flag = this.communityService.doDelete(inVO);
		
		String message = "";
		
		if(flag == 1) {
			message = "글이 삭제 되었습니다.";
		} else {
			message = "글 삭제 실패.";
		}
		
		jsonString=new Gson().toJson(new MessageVO(flag, message));
		log.debug("jsonString:\n{}",jsonString);
		
		return jsonString;
	}
	
	@GetMapping("/doSelectOne.do")
	public String doSelectOne(@RequestParam(name = "cmn_category" ,defaultValue = "10") String cmnCategory,
			@RequestParam(name = "cmn_div" ,defaultValue = "10") String cmnDiv,
			@RequestParam(name = "cmn_no" ,defaultValue = "0" ) int cmnNo,
			Model model, HttpSession session) throws NullPointerException, SQLException {
		String viewName = "community/community_mng";
		
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **doSelectOne()**                     │");
		log.debug("└───────────────────────────────────────┘");			
		
		CommunityVO inVO = new CommunityVO();
		inVO.setCategory(cmnCategory);
		inVO.setDiv(cmnDiv);
		inVO.setCmnNo(cmnNo);
		
		String memId = "";
		
		if(null != session.getAttribute("user")) {
			MemberVO member = (MemberVO)session.getAttribute("member");
			memId = member.getMemId();
		}
		
		inVO.setMemId(memId);
		
		
		log.debug("│ inVO                     │" + inVO);
		

		CommunityVO outVO = this.communityService.doSelectOne(inVO);
		model.addAttribute("vo", outVO);
		
		return viewName;
	}
	
	@PostMapping(value = "/doSave.do", produces = "text/plain;charset=UTF-8" )
	@ResponseBody
	public String doSave(CommunityVO param) throws SQLException {
		String jsonString = "";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **doSave()**                          │");
		log.debug("└───────────────────────────────────────┘");			
		log.debug("│ param                          │"+param);
		
		
		int flag = this.communityService.doSave(param);
		
		String message = "";
		if(flag == 1) {
			message = "글이 등록 되었습니다.";
		} else {
			message = "글 등록 실패.";
		}
		
		MessageVO messageVO = new MessageVO(flag, message);
		
		jsonString = new Gson().toJson(messageVO);
		log.debug("jsonString:\n{}",jsonString);  
		
		return jsonString;
	}
	
	@GetMapping("/doRetrieve.do")
	public String doRetrieve(HttpServletRequest req, Model model){
		String viewName = "community/community_list";
		SearchVO search=new SearchVO();
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **doRetrieve()**                      │");
		log.debug("└───────────────────────────────────────┘");		
		
		String pageNoString  = StringUtil.nvl(req.getParameter("pageNo"),"1");
		String pageSizeString  = StringUtil.nvl(req.getParameter("pageSize"),"10");
		
		int pageNo = Integer.parseInt(pageNoString);
		int pageSize = Integer.parseInt(pageSizeString);
		log.debug("pageNo:{}",pageNo);
		log.debug("pageSize:{}",pageSize);
		
		String searchDiv  = StringUtil.nvl(req.getParameter("searchDiv"),"");
		String searchWord = StringUtil.nvl(req.getParameter("searchWord"),"");
		log.debug("searchDiv:{}",searchDiv);
		log.debug("searchWord:{}",searchWord);	
		
		search.setPageNo(pageNo);
		search.setPageSize(pageSize);
		search.setSearchDiv(searchDiv);
		search.setSearchWord(searchWord);
		
		
		//10(공지사항),20(자유게시판)
		String cmnDiv = StringUtil.nvl(req.getParameter("cmn_div"), "10");
		log.debug("div:{}",cmnDiv);	
		Map<String,String> option=new HashMap<String, String>();
		option.put("cmn_div", cmnDiv);
		
		search.setOptionSearch(option);
		log.debug("search:{}",search);
		
		
		List<CommunityVO> list = communityService.doRetrieve(search);
		
		//총 글수
		int totalCnt = 0;
		
		if(null != list && list.size() > 0) {
			CommunityVO vo=list.get(0);
			
			totalCnt = vo.getTotalCnt();
		}
		
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("list", list);
		model.addAttribute("search", search);	
		
		model.addAttribute("cmn_div", cmnDiv);
		
		return viewName;
	}
}
