package com.pcwk.ehr.user.controller;

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
import com.pcwk.ehr.user.domain.Level;
import com.pcwk.ehr.user.domain.UserVO;
import com.pcwk.ehr.user.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	final Logger log = LogManager.getLogger(getClass());

	@Qualifier("userServiceImpl")
	@Autowired
	private UserService userService;

	public UserController() {
		log.debug("┌───────────────────────────────────┐");
		log.debug("│ **UserController**                │");
		log.debug("└───────────────────────────────────┘");
	}
	
	@GetMapping("/user_reg_index.do")
	public String userRegIndex() {
		String viewName = "user/user_reg";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **userRegIndex()**                    │");
		log.debug("└───────────────────────────────────────┘");
		
		return viewName;
	}
	
	@RequestMapping(value="/doRetrieve.do",
					method = RequestMethod.GET)
	public String doRetrieve(HttpServletRequest req,
			Model model) throws Exception {
		String viewName = "user/user_list";
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
		
		log.debug("search:{}",search);
		
		List<UserVO> list = userService.doRetrieve(search);
		
		int totalCnt = 0;
		for(int i=0; i<list.size(); i++) {
			if(i == 0) {
				UserVO vo = list.get(0);
				totalCnt = vo.getTotalCnt();
				break;
			}
		}
		
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("list",list);
		model.addAttribute("search", search);
		
		return viewName;
	}

	@RequestMapping(value = "/doSelectOne.do", method = RequestMethod.GET)
	public String doSelectOne(
			@RequestParam(name = "userId", required = true, defaultValue = "Unknown Id") String inUserId, Model model)
			throws Exception {
		String viewName = "user/user_mng";
		log.debug("┌───────────────────────────────────────────────────────────┐");
		log.debug("│ ***doSelectOne()***                                       │");
		log.debug("└───────────────────────────────────────────────────────────┘");

		UserVO inVO = new UserVO();

		log.debug("userId: {}", inUserId);

		inVO.setMem_id(inUserId);

		UserVO outVO = userService.doSelectOne(inVO);

		model.addAttribute("vo", outVO);

		return viewName;
	}

	@RequestMapping(value = "/doUpdate.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doUpdate(HttpServletRequest req, UserVO param) throws SQLException {
		String jsonString = "";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **doUpdate()**                        │");
		log.debug("└───────────────────────────────────────┘");

		log.debug("param:{}", param);

		int flag = userService.doUpdate(param);

		String message = "";

		if (1 == flag) {
			message = param.getMem_name() + "님 정보가 수정되었습니다.";
		} else {
			message = param.getMem_name() + "님 정보가 수정 실패했습니다.";
		}

		jsonString = new Gson().toJson(new MessageVO(flag, message));
		log.debug("jsonString:{}", jsonString);

		return jsonString;
	}

	@RequestMapping(value = "/doDelete.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doDelete(HttpServletRequest req,
			@RequestParam(name = "userId", required = true, defaultValue = "Unkown Id") String inUserId)
			throws SQLException {
		String jsonString = "";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **doDelete()**                        │");
		log.debug("└───────────────────────────────────────┘");
		UserVO inVO = new UserVO();
		
		String userId = req.getParameter("userId");
		//String userId = inUserId;
		//log.debug("inUserId:{}", inUserId);
		inVO.setMem_id(userId);
		log.debug("inVO:{}", inVO);

		int flag = userService.doDelete(inVO);

		String message = "";
		if (1 == flag) {
			message = inVO.getMem_id() + "님이 삭제되었습니다.";
		} else {
			message = inVO.getMem_id() + "님이 실패 했습니다.";
		}

		jsonString = new Gson().toJson(new MessageVO(flag, message));
		log.debug("jsonString:\n{}", jsonString);

		return jsonString;
	}

	@RequestMapping(value = "/doSave.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String doSave(HttpServletRequest req, UserVO param) throws SQLException {
		String jsonString = "";

		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **doSave()**                          │");
		log.debug("└───────────────────────────────────────┘");

		log.debug("param:{}", param);

		int flag = userService.doSave(param);

		String message = "";

		if (1 == flag) {
			message = param.getMem_name() + "님 등록 성공하였습니다.";
		} else {
			message = param.getMem_name() + "님 등록 실패하였습니다.";
		}

		MessageVO messageVO = new MessageVO(flag, message);

		jsonString = new Gson().toJson(messageVO);
		log.debug("jsonString:{}", jsonString);

		return jsonString;
	}
}
