package com.pcwk.ehr.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pcwk.ehr.cmn.SearchVO;
import com.pcwk.ehr.cmn.StringUtil;
import com.pcwk.ehr.community.domain.CommunityVO;
import com.pcwk.ehr.community.service.CommunityService;

@Controller
@RequestMapping("main")
public class MainController {
	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	private CommunityService communityService;

	// 필요한 Service
	public MainController() {
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ MainController()                      │");
		log.debug("└───────────────────────────────────────┘");
	}
	
	@GetMapping("/main.do")
	public String mainIndex(HttpServletRequest req, Model model) {
		String viewName = "main/main";
		SearchVO search = new SearchVO();
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **mainIndex()**                       │");
		log.debug("└───────────────────────────────────────┘");
		
		String pageNoString  = StringUtil.nvl(req.getParameter("pageNo"),"1");
		String pageSizeString  = StringUtil.nvl(req.getParameter("pageSize"),"10");
		String searchDiv  = StringUtil.nvl(req.getParameter("searchDiv"),"");
		String searchWord = StringUtil.nvl(req.getParameter("searchWord"),"");
		
		int pageNo = Integer.parseInt(pageNoString);
		int pageSize = Integer.parseInt(pageSizeString);
		
		search.setPageNo(pageNo);
		search.setPageSize(pageSize);
		search.setSearchDiv(searchDiv);
		search.setSearchWord(searchWord);
		
		//10(공지사항),20(자유게시판)
		String cmnDivNotice = StringUtil.nvl(req.getParameter("cmn_div"), "10");
		String cmnDivBoard = StringUtil.nvl(req.getParameter("cmn_div"), "20");
		Map<String,String> optionNotice = new HashMap<String, String>();
		Map<String,String> optionBoard = new HashMap<String, String>();
		optionNotice.put("cmn_div", cmnDivNotice);
		
		search.setOptionSearch(optionNotice);
		
		List<CommunityVO> list = communityService.doRetrieve(search);
		
		int totalCnt = 0;
		
		if(null != list && list.size() > 0) {
			CommunityVO vo=list.get(0);
			
			totalCnt = vo.getTotalCnt();
		}
		
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("list", list);
		model.addAttribute("search", search);	
		model.addAttribute("cmn_div", cmnDivNotice);
		
		return viewName;
	}
}