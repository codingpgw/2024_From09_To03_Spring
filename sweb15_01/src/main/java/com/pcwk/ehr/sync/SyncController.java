package com.pcwk.ehr.sync;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SyncController {
	
	@RequestMapping(value = "/sync/sync_index.do")
	public String handlerSyncIndex() {
		System.out.println("handlerSyncIndex");
		
		return "sync/sync_index";
	}
	
	@GetMapping("/sync/sync_result.do")
	public String syncResult(HttpServletRequest req, Model model) {
		System.out.println("syncResult");
		
		String name = req.getParameter("name");
		System.out.println("name : "+name);
		
		model.addAttribute("req_name", name);
		
		return "sync/sync_index";
	}
}
