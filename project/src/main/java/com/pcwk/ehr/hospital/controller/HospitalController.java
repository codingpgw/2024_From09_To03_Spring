package com.pcwk.ehr.hospital.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pcwk.ehr.cmn.SearchVO;
import com.pcwk.ehr.cmn.StringUtil;
import com.pcwk.ehr.user.domain.HospitalVO;
import com.pcwk.ehr.user.service.HospitalService;

@Controller
@RequestMapping("hospital")
public class HospitalController {
	final Logger log = LogManager.getLogger(getClass());

	@Autowired
	private HospitalService hospitalService;

	public HospitalController() {
		log.debug("┌───────────────────────────────────┐");
		log.debug("│ **HospitalController**            │");
		log.debug("└───────────────────────────────────┘");
	}

	@RequestMapping(value = "/doRetrieve.do", method = RequestMethod.GET)
	public String doRetrieve(HttpServletRequest req, Model model) throws Exception {
		String viewName = "hospital/hos_test";
		SearchVO search = new SearchVO();

		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **doRetrieve()**                      │");
		log.debug("└───────────────────────────────────────┘");

		String pageNoString = StringUtil.nvl(req.getParameter("pageNo"), "1");
		String pageSizeString = StringUtil.nvl(req.getParameter("pageSize"), "10");

		int pageNo = Integer.parseInt(pageNoString);
		int pageSize = Integer.parseInt(pageSizeString);
		log.debug("pageNo:{}", pageNo);
		log.debug("pageSize:{}", pageSize);
		
		String searchDiv = StringUtil.nvl(req.getParameter("searchDiv"), "");
		String searchWord = StringUtil.nvl(req.getParameter("searchWord"), "");
		log.debug("searchDiv:{}", searchDiv);
		log.debug("searchWord:{}", searchWord);

		search.setPageNo(pageNo);
		search.setPageSize(pageSize);
		search.setSearchDiv(searchDiv);
		search.setSearchWord(searchWord);

		log.debug("search:{}", search);

		List<HospitalVO> list = hospitalService.doRetrieve(search);

		int totalCnt = 0;
		for (int i = 0; i < list.size(); i++) {
			if (i == 0) {
				HospitalVO vo = list.get(0);
				totalCnt = vo.getTotalCnt();
				break;
			}
		}

		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("list", list);
		model.addAttribute("search", search);

		return viewName;
	}

}
