package com.pcwk.ehr.board.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pcwk.ehr.board.domain.BoardVO;
import com.pcwk.ehr.board.service.BoardService;
import com.pcwk.ehr.cmn.MessageVO;
import com.pcwk.ehr.cmn.SearchVO;
import com.pcwk.ehr.cmn.StringUtil;
import com.pcwk.ehr.markdown.service.MarkdownService;
import com.pcwk.ehr.user.domain.UserVO;

@Controller
@RequestMapping("board")
class BoardController {
	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private MarkdownService markdownService;

	public BoardController() {
		log.debug("┌───────────────────────────────────┐");
		log.debug("│ **BoardController**               │");
		log.debug("└───────────────────────────────────┘");
	}
	
	@GetMapping("/board_reg_index.do")
	public String boardRegIndex(@RequestParam(name="div", defaultValue="10")String div, 
			Model model) {
		String viewName = "board/board_reg";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **boardRegIndex()**                   │");
		log.debug("└───────────────────────────────────────┘");
		
		log.debug("div:{}",div);
		model.addAttribute("board_div", div);
		
		return viewName;
	}
	
	
	@GetMapping("/doSelectOne.do")
	public String doSelectOne(@RequestParam(name="div", defaultValue="10")String div,
			Model model, @RequestParam(name="seq", defaultValue="0") int seq,
			HttpSession session) throws NullPointerException, SQLException {
		
		String viewName = "board/board_mng";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **doSelectOne()**                     │");
		log.debug("└───────────────────────────────────────┘");
		
		BoardVO inVO = new BoardVO();
		inVO.setDiv(div);
		inVO.setSeq(seq);
		log.debug("inVO:"+inVO);
		
		String userId = "";
		
		if(null != session.getAttribute("user")) {
			UserVO user = (UserVO) session.getAttribute("user");
			userId = user.getUserId();
		}
		inVO.setRegId(userId);
		
		BoardVO outVO = this.boardService.doSelectOne(inVO);
		
		log.debug("markdownService:{}",markdownService);
		
		String markdownHtml = markdownService.convertMarkdownToHtml(outVO.getContents());
		
		model.addAttribute("vo", outVO);
		model.addAttribute("markdownToHtml", markdownHtml);
		
		return viewName;
	}
	
	@PostMapping(value="/doUpdate.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doUpdate(BoardVO param, HttpSession session)throws SQLException{
		String jsonString = "";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **doUpdate()**                        │");
		log.debug("└───────────────────────────────────────┘");
		
		if(null != session.getAttribute("user")) {
			UserVO user = (UserVO) session.getAttribute("user");
			param.setModId(user.getUserId());
		}
		
		log.debug("param:{}", param);
		
		int flag = this.boardService.doUpdate(param);
		
		String message = "";
		if(1 == flag) {
			message = "게시글이 수정되었습니다.";
		}else {
			message = "게시글 등록되었습니다.";
		}
		
		MessageVO messageVO = new MessageVO(flag, message);

		jsonString = new Gson().toJson(messageVO);
		log.debug("jsonString:{}", jsonString);
		
		return jsonString;
	}
	
	
	@GetMapping(value = "/doDelete.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doDelete(@RequestParam(name="seq", required = true, defaultValue = "0")int seq) throws SQLException {
		String jsonString = "";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **doDelete()**                        │");
		log.debug("└───────────────────────────────────────┘");
		
		log.debug("seq : "+seq);
		
		BoardVO inVO = new BoardVO();
		inVO.setSeq(seq);
		
		log.debug("inVO:{}",inVO);
		
		int flag = this.boardService.doDelete(inVO);
		
		String message = "";
		if(1 == flag) {
			message = "게시글이 삭제되었습니다.";
		}else {
			message = "게시글 삭제 실패했습니다.";
		}

		jsonString = new Gson().toJson(new MessageVO(flag, message));
		log.debug("jsonString:{}", jsonString);
		
		return jsonString;
	}
	
	@PostMapping(value = "/doSave.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doSave(HttpServletRequest req, BoardVO param) throws SQLException {
		String jsonString = "";
		
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **doSave()**                          │");
		log.debug("└───────────────────────────────────────┘");

		log.debug("param:{}", param);
		
		int flag = this.boardService.doSave(param);
		
		String message = "";
		if(1 == flag) {
			message = "게시글이 등록되었습니다.";
		}else {
			message = "게시글 등록에 실패했습니다..";
		}
		
		MessageVO messageVO = new MessageVO(flag, message);

		jsonString = new Gson().toJson(messageVO);
		log.debug("jsonString:{}", jsonString);
		
		return jsonString;
	}
	
	
	@GetMapping("/doRetrieve.do")
	public String doRetrieve(HttpServletRequest req, Model model){
		
		String viewName = "board/board_list";
		
		SearchVO search = new SearchVO();
		
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **doRetrieve()**                      │");
		log.debug("└───────────────────────────────────────┘");
		
		String pageNoString  = StringUtil.nvl(req.getParameter("pageNo"),"1");
		String pageSizeString  = StringUtil.nvl(req.getParameter("pageSize"),"10");
		
		int pageNo = Integer.parseInt(pageNoString);
		int pageSize = Integer.parseInt(pageSizeString);
		log.debug("pageNo:{}",pageNo);
		log.debug("pageSize:{}",pageSize);
		//데이터가 null이면, ""
		String searchDiv  = StringUtil.nvl(req.getParameter("searchDiv"),"");
		String searchWord = StringUtil.nvl(req.getParameter("searchWord"),"");
		log.debug("searchDiv:{}",searchDiv);
		log.debug("searchWord:{}",searchWord);
		
		search.setPageNo(pageNo);
		search.setPageSize(pageSize); 
		search.setSearchDiv(searchDiv);
		search.setSearchWord(searchWord);  
		
		//10(공지사항), 20(자유게시판)
		String div = StringUtil.nvl(req.getParameter("div"), "10");
		log.debug("div:{}",div);
		Map<String, String> option = new HashMap<String, String>();
		option.put("div",div);
		
		search.setOptionSearch(option);
		log.debug("search:{}",search);
		List<BoardVO> list = boardService.doRetrieve(search);
		
		//총 글수
		int totalCnt = 0;
		
		if(null != list && list.size() > 0) {
			BoardVO vo = list.get(0);
			
			totalCnt = vo.getTotalCnt();
		}
		
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("list",list);
		model.addAttribute("search", search);
		
		model.addAttribute("board_div", div);
		
		return viewName;
	}
	
}
