package com.pcwk.ehr.hospital.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pcwk.ehr.cmn.MessageVO;
import com.pcwk.ehr.cmn.SearchVO;
import com.pcwk.ehr.cmn.StringUtil;
import com.pcwk.ehr.hospital.dao.HospitalDao;
import com.pcwk.ehr.hospital.domain.HospitalVO;
import com.pcwk.ehr.hospital.service.HospitalService;

@Controller
@RequestMapping("hospital")
public class HospitalController {
	
	final Logger log = LogManager.getLogger(getClass());
	
	@Qualifier("hospitalServiceImpl")
	@Autowired
	private HospitalService hospitalService;

	public HospitalController(){
		log.debug("┌─────────────────────────────────────────┐");
		log.debug("│ **HospitalController**                  │");
		log.debug("└─────────────────────────────────────────┘");
	}
	
	@GetMapping("hospital_reg_index")
	public String hospitalRegIndex() {
		log.debug("hospitalRegIndex method called");
		return "hospital/hospital_reg";
	}
		
	
	@RequestMapping(value = "/doSelectOne.do", method = RequestMethod.GET)
	public String doSelectOne(@RequestParam(name = "hospital_id", required = true, defaultValue = "Unknown Id") String inHpId,
			  Model model) throws Exception{
		String viewName = "hospital/hospital_mng";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **doSelectOne()**                     │");
		log.debug("└───────────────────────────────────────┘");
		HospitalVO inVO = new HospitalVO();
		
		inVO.setHospital_id(inHpId);
		
		HospitalVO outVO = hospitalService.doSelectOne(inVO);
		
		model.addAttribute("vo", outVO);
		
		return viewName;
	}
	
	@RequestMapping(value = "/doUpdate.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doUpdate(@Validated @RequestBody HospitalVO param) throws SQLException {
		String jsonString = "";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **doUpdate()**                        │");
		log.debug("└───────────────────────────────────────┘");
		log.debug(" │ param                           │" + param);
		
		int flag = hospitalService.doUpdate(param);
		
		String message = "";
		
		if (flag == 1) {
			message = param.getHospital_name() + "의 정보가 수정 되었습니다.";
		} else {
			message = param.getHospital_name() + "의 정보 수정이 실패했습니다.";
		}
		
		jsonString = new Gson().toJson(new MessageVO(flag, message));
		log.debug("jsonString:\n{}", jsonString);
		
		return jsonString;
	}
	
	
	@RequestMapping(value = "/doDelete.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doDelete(HttpServletRequest req,
			@RequestParam(name = "hospital_id", required = true, defaultValue = "Unknown Id") String inHpId) throws Exception{
		String jsonString = "";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **doDelete()**                        │");
		log.debug("└───────────────────────────────────────┘");
		HospitalVO inVO = new HospitalVO();
		
		String hpId = req.getParameter("hospital_id");
		inVO.setHospital_id(hpId);
		
		int flag = hospitalService.doDelete(inVO);
		
		String message = "";
		
		if (flag == 1) {
			message = inVO.getHospital_id() + "가 삭제 되었습니다.";
		} else {
			message = inVO.getHospital_id() + " 삭제 실패했습니다.";
		}
		
		jsonString = new Gson().toJson(new MessageVO(flag, message));
		return jsonString;
	}
	
	@RequestMapping(value = "/doSave.do", method = RequestMethod.POST)
	@ResponseBody
	public String doSave(HttpServletRequest req, HospitalVO param) throws SQLException{
		String jsonString = "";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **doSave()**                          │");
		log.debug("└───────────────────────────────────────┘");
		
		log.debug(" │ param                           │" + param);
		
		int flag = hospitalService.doSave(param);
		
		String message = "";
		
		if (flag == 1) {
			message = param.getHospital_name() + " 등록을 성공했습니다.";
		} else {
			message = param.getHospital_name() + " 등록을 실패했습니다.";
		}
		
		MessageVO messageVO = new MessageVO(flag, message);
		
		jsonString = new Gson().toJson(messageVO);
		log.debug("jsonString:\n{}", jsonString);
		
		
		return jsonString;
	}
	
	@GetMapping("/hospital/list")
	public String doRetrieve(HttpServletRequest req, Model model) throws Exception {
	    String viewName = "hospital/hospital_list";
	    SearchVO search = new SearchVO();
	    log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **doRetrieve()**                      │");
		log.debug("└───────────────────────────────────────┘");

		String pageNoString = StringUtil.nvl(req.getParameter("pageNo"), "1");
	    String pageSizeString = StringUtil.nvl(req.getParameter("pageSize"), "10");
	    String searchDiv = StringUtil.nvl(req.getParameter("searchDiv"), "");
		String searchWord = StringUtil.nvl(req.getParameter("searchWord"), "");
		
	    int pageNo = Integer.parseInt(pageNoString);
	    int pageSize = Integer.parseInt(pageSizeString);

	    search.setPageNo(pageNo);
		search.setPageSize(pageSize);
		search.setSearchDiv(searchDiv);
		search.setSearchWord(searchWord);

	    log.debug("search: {}", search);

	    List<HospitalVO> list = hospitalService.doRetrieve(search);

	    int totalCnt = 0;
    	if(null != list && list.size() > 0) {
    		HospitalVO vo = list.get(0);
    		totalCnt = vo.getTotalCnt();
    	}

	    model.addAttribute("totalCnt", totalCnt);
	    model.addAttribute("list", list);
	    model.addAttribute("search", search);

	    return viewName;
	}
}