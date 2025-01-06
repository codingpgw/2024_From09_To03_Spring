package com.pcwk.ehr.qna.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
import com.pcwk.ehr.member.domain.MemberVO;
import com.pcwk.ehr.qna.domain.QnaVO;
import com.pcwk.ehr.qna.service.QnaService;

@Controller
@RequestMapping("qna")
public class QnaController {
	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	private QnaService qnaService;
	
	public QnaController() {
		log.debug("┌─────────────────────────────────┐");
		log.debug("│ *QnaController()*               │");
		log.debug("└─────────────────────────────────┘");		
	}
	
	@PostMapping(value = "/doSave.do", produces = "text/plain;charset=UTF-8" )
	@ResponseBody
	public String doSave(QnaVO param) throws SQLException {
		String jsonString = "";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **doSave()**                          │");
		log.debug("└───────────────────────────────────────┘");			
		log.debug("│ param                          │"+param);
		
		
		int flag = this.qnaService.doSave(param);
		
		String message = "";
		if(flag == 1) {
			message = "질문이 등록 되었습니다.";
		} else {
			message = "질문 등록 실패.";
		}
		
		MessageVO messageVO = new MessageVO(flag, message);
		
		jsonString = new Gson().toJson(messageVO);
		log.debug("jsonString:\n{}",jsonString);  
		
		return jsonString;
	}
	
	@GetMapping(value = "/doDelete.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doDelete(@RequestParam(name = "qna_no", required = true, defaultValue = "0") int qnaNo) throws SQLException {
		String jsonString = "";
		
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **doDelete()**                        │");
		log.debug("└───────────────────────────────────────┘");				
		
		log.debug("│ cmnNo                   │" + qnaNo);
		
		QnaVO inVO = new QnaVO();
		inVO.setQnaNo(qnaNo);
		
		log.debug("│ inVO                   │" + inVO);
		
		int flag = this.qnaService.doDelete(inVO);
		
		String message = "";
		
		if(flag == 1) {
			message = "질문이 삭제 되었습니다.";
		} else {
			message = "질문 삭제 실패.";
		}
		
		jsonString=new Gson().toJson(new MessageVO(flag, message));
		log.debug("jsonString:\n{}",jsonString);
		
		return jsonString;
	}
	
	@PostMapping(value = "/doUpdate.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doUpdate(HttpServletRequest req, QnaVO param) throws SQLException {
		String jsonString = "";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **doUpdate()**                        │");
		log.debug("└───────────────────────────────────────┘");
		log.debug(" │ param                           │" + param);
		
		int flag = qnaService.doUpdate(param);
		
		String message = "";
		
		if (flag == 1) {
			message = "질문 번호" + param.getQnaNo() + "의 정보가 수정되었습니다.";
		} else {
			message = "질문 번호" + param.getQnaNo() + "의 정보 수정이 실패했습니다.";
		}
		
		jsonString = new Gson().toJson(new MessageVO(flag, message));
		log.debug("jsonString:\n{}", jsonString);
		
		return jsonString;
	}
	
	@RequestMapping(value = "/doRetrieve.do", method = RequestMethod.GET)
	public String doRetrieve(HttpServletRequest req, Model model) throws Exception {
		String viewName = "qna/qna_list";
		SearchVO search = new SearchVO();
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **doRetrieve()**                      │");
		log.debug("└───────────────────────────────────────┘");
		
		String pageNoString = StringUtil.nvl(req.getParameter("pageNo"), "1");
		String pageSizeString = StringUtil.nvl(req.getParameter("pageSize"), "5");
		
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
		
		List<QnaVO> list = qnaService.doRetrieve(search);
		
		// 총 글수
		int totalCnt = 0;
		
		if (null != list && list.size() > 0) {
			QnaVO vo = list.get(0);
			totalCnt = vo.getTotalCnt();
		}
		
		if (list == null) {
		    list = new ArrayList<QnaVO>(); // 빈 리스트로 초기화
		}
		
		
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("list", list);
		model.addAttribute("search", search);
		
		return viewName;
	}
	
	@RequestMapping(value = "/doSelectOne.do", method = RequestMethod.GET)
	public String doSelectOne(@RequestParam(name = "qna_no", required = true, defaultValue = "0") int inQnaNo,
			Model model) throws Exception {
		String viewName = "qna/qna_mng";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **doSelectOne()**                     │");
		log.debug("└───────────────────────────────────────┘");
		QnaVO inVO = new QnaVO();
		
		inVO.setQnaNo(inQnaNo);
		
		QnaVO outVO = qnaService.doSelectOne(inVO);
		
		model.addAttribute("vo", outVO);
		
		return viewName;
	}
}
