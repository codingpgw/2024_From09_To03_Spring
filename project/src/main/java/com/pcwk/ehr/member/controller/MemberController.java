package com.pcwk.ehr.member.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pcwk.ehr.cmn.MessageVO;
import com.pcwk.ehr.cmn.SearchVO;
import com.pcwk.ehr.cmn.StringUtil;
import com.pcwk.ehr.member.dao.MemberDao;
import com.pcwk.ehr.member.dao.MemberDaoJdbc;
import com.pcwk.ehr.member.domain.MemberVO;
import com.pcwk.ehr.member.service.MemberService;

@Controller
@RequestMapping("member")
public class MemberController {
	final Logger log = LogManager.getLogger(MemberDaoJdbc.class);
	
	@Qualifier("memberServiceImpl")
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberDao memberDao;
	
	public MemberController() {
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ MemberController()                    │");
		log.debug("└───────────────────────────────────────┘");
	}
	
	@GetMapping("/member_reg_index.do")
	public String memberRegIndex() {
		String viewName = "member/member_reg";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **memberRegIndex()**                  │");
		log.debug("└───────────────────────────────────────┘");
		
		return viewName;
	}
	
	@RequestMapping(value = "/doRetrieve.do", method = RequestMethod.GET)
	public String doRetrieve(HttpServletRequest req, Model model) throws Exception {
		String viewName = "member/member_list";
		SearchVO search = new SearchVO();
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **doRetrieve()**                      │");
		log.debug("└───────────────────────────────────────┘");
		
		String pageNoString = StringUtil.nvl(req.getParameter("pageNo"), "1");
		String pageSizeString = StringUtil.nvl(req.getParameter("pageSize"), "10");
		
		int pageNo = Integer.parseInt(pageNoString);
		int pageSize = Integer.parseInt(pageSizeString);
		
		log.debug("pageNo: {}", pageNo);
		log.debug("pageSize: {}", pageSize);
		
		// 데이터가 NULL이면 ""
		String searchDiv = StringUtil.nvl(req.getParameter("searchDiv"), "");
		String searchWord = StringUtil.nvl(req.getParameter("searchWord"), "");
		log.debug("searchDiv: {}", searchDiv);
		log.debug("searchWord: {}", searchWord);
		
		search.setPageNo(pageNo);
		search.setPageSize(pageSize);
		search.setSearchDiv(searchDiv);
		search.setSearchWord(searchWord);
		
		log.debug("search: {}", search);
		
		List<MemberVO> list = memberService.doRetrieve(search);
		
		// 총 글수
		int totalCnt = 0;
		
		if (null != list && list.size() > 0) {
			MemberVO vo = list.get(0);
			totalCnt = vo.getTotalCnt();
		}
		
		
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("list", list);
		model.addAttribute("search", search);
		
		return viewName;
	}
	
	@RequestMapping(value = "/doSelectOne.do", method = RequestMethod.GET)
	public String doSelectOne(@RequestParam(name = "memId", required = true, defaultValue = "Unknown Id") String inUserId,
							  Model model) throws Exception {
		String viewName = "member/member_mng";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **doSelectOne()**                     │");
		log.debug("└───────────────────────────────────────┘");
		MemberVO inVO = new MemberVO();
		
		log.debug("userId: {}", inUserId);
		
		inVO.setMemId(inUserId);
		
		MemberVO outVO = memberService.doSelectOne(inVO);
		
		model.addAttribute("vo", outVO);
		
		return viewName;
	}
	
	@RequestMapping(value = "/doUpdate.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doUpdate(HttpServletRequest req, MemberVO param) throws SQLException {
		String jsonString = "";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **doUpdate()**                        │");
		log.debug("└───────────────────────────────────────┘");
		log.debug(" │ param                           │" + param);

		int flag = memberService.doUpdate(param);
		
		String message = "";

		if (flag == 1) {
			message = param.getName() + "님의 정보가 수정 되었습니다.";
		} else {
			message = param.getName() + "님의 정보 수정이 실패했습니다.";
		}
		
		jsonString = new Gson().toJson(new MessageVO(flag, message));
		log.debug("jsonString:\n{}", jsonString);
		
		return jsonString;
	}
	
	@RequestMapping(value = "/doDelete.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doDelete(HttpServletRequest req,
			@RequestParam(name = "memId", required = true, defaultValue = "Unknown Id") String inUserId) throws SQLException {
		String jsonString = "";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **doDelete()**                        │");
		log.debug("└───────────────────────────────────────┘");
		MemberVO inVO = new MemberVO();
		
		String memId = req.getParameter("memId");
		inVO.setMemId(memId);
		log.debug("inVO: {}", inVO);
		
		int flag = memberService.doDelete(inVO);
		
		String message = "";
		
		if (flag == 1) {
			message = inVO.getMemId() + "님이 삭제 되었습니다.";
		} else {
			message = inVO.getMemId() + "님이 삭제 실패 했습니다..";
		}
		
		jsonString = new Gson().toJson(new MessageVO(flag, message));
		log.debug("jsonString:\n{}", jsonString);
		
		return jsonString;
	}
	
	@RequestMapping(value = "/doSave.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doSave(HttpServletRequest req, MemberVO param) throws SQLException {
		String jsonString = "";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **doSave()**                          │");
		log.debug("└───────────────────────────────────────┘");
		
		log.debug(" │ param                           │" + param);
		String messageIdCheck = "";
		
		int idCheck = memberDao.idCheck(param);
		
		if (idCheck == 1) {
			messageIdCheck = "중복된 ID입니다.";
			
			MessageVO messageVO = new MessageVO(idCheck + 1, messageIdCheck);
			
			jsonString = new Gson().toJson(messageVO);
		} else {
			int flag = memberService.doSave(param);
			
			String message = "";
			
			if (flag == 1) {
				message = param.getName() + "님 등록 성공했습니다.";
			} else {
				message = param.getName() + "님 등록 실패했습니다.";
			}
			
			MessageVO messageVO = new MessageVO(flag, message);
			
			jsonString = new Gson().toJson(messageVO);
			log.debug("jsonString:\n{}", jsonString);
		}
		
		return jsonString;
	}
}