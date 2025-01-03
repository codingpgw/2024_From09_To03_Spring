package com.pcwk.ehr.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pcwk.ehr.cmn.SearchVO;
import com.pcwk.ehr.cmn.StringUtil;
import com.pcwk.ehr.community.domain.CommunityVO;
import com.pcwk.ehr.community.service.CommunityService;
import com.pcwk.ehr.hospital.domain.HospitalVO;
import com.pcwk.ehr.hospital.service.HospitalService;
import com.pcwk.ehr.member.domain.MemberVO;
import com.pcwk.ehr.member.service.MemberService;

@Controller
@RequestMapping("admin")
public class AdminController {
	final Logger log = LogManager.getLogger(getClass());
	
	@Qualifier("hospitalServiceImpl")
	@Autowired
	private HospitalService hospitalService;
	
	@Qualifier("memberServiceImpl")
	@Autowired
	private MemberService memberService;
	
	@Qualifier("communityServiceImpl")
	@Autowired
	private CommunityService communityService;

	public AdminController() {
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ AdminController()                     │");
		log.debug("└───────────────────────────────────────┘");
	}
	
	private boolean isSessionValid(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
	    return session != null && session.getAttribute("member") != null;
	}
	
	@GetMapping("/admin.do")
	public String adminIndex(HttpServletRequest request, Model model) {
		String viewName = "admin/admin";
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **adminIndex()**                      │");
		log.debug("└───────────────────────────────────────┘");
		
		if (!isSessionValid(request)) {
	        // 세션이 유효하지 않은 경우 로그인 페이지로 리다이렉트
	        //return "redirect:/login/login_index.do?alert=needLogin";
	        
	        model.addAttribute("loginMessage", "로그인이 필요합니다.");
	        return "login/login";
	    }
		
		return viewName;
	}
	
	@GetMapping("/adminRetrieveHospital.do")
	public String doRetrieveHospital(HttpServletRequest req, Model model) throws Exception {
	    String viewName = "admin/hospital/admin_hospital_list";
	    SearchVO search = new SearchVO();
	    log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **doRetrieveHospital()**              │");
		log.debug("└───────────────────────────────────────┘");

		if (!isSessionValid(req)) {
			model.addAttribute("loginMessage", "로그인이 필요합니다.");
	        return "login/login";
	    }

		String pageNoString = StringUtil.nvl(req.getParameter("pageNo"), "1");
	    String pageSizeString = StringUtil.nvl(req.getParameter("pageSize"), "10");

	    int pageNo = Integer.parseInt(pageNoString);
	    int pageSize = Integer.parseInt(pageSizeString);

	    String searchDiv = StringUtil.nvl(req.getParameter("searchDiv"), "");
		String searchWord = StringUtil.nvl(req.getParameter("searchWord"), "");
		log.debug("searchDiv: {}", searchDiv);
		log.debug("searchWord: {}", searchWord);
		
		search.setPageNo(pageNo);
		search.setPageSize(pageSize);
		search.setSearchDiv(searchDiv);
		search.setSearchWord(searchWord);
		
		log.debug("search: {}", search);

	    List<HospitalVO> list = hospitalService.doRetrieve(search);

	    int totalCnt = 0;
	    if (null != list && list.size() > 0) {
    		HospitalVO vo = list.get(0);
    		totalCnt = vo.getTotalCnt();
	    }

	    model.addAttribute("totalCnt", totalCnt);
	    model.addAttribute("list", list);
	    model.addAttribute("search", search);

	    return viewName;
	}
	
	@RequestMapping(value = "/adminHospitalSelectOne.do", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public ResponseEntity<HospitalVO> doHospitalSelectOne(@RequestParam(name = "hospital_id", required = true, defaultValue = "Unknown Id") String inHpId,
							  Model model, HttpServletRequest request) throws Exception {
		
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **doSelectOne()**                     │");
		log.debug("└───────────────────────────────────────┘");
		
		if (!isSessionValid(request)) {
			model.addAttribute("loginMessage", "로그인이 필요합니다.");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	    }
		
		log.debug("inHpId: {}", inHpId);

		HospitalVO inVO = new HospitalVO();
	    inVO.setHospital_id(inHpId);

	    // 회원 정보 조회
	    HospitalVO outVO = hospitalService.doSelectOne(inVO);

	    if (outVO == null) {
	        // 회원 정보가 없으면 404 Not Found 응답
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 회원 정보를 찾을 수 없을 경우 null 반환
	    }

	    // 조회된 회원 정보를 JSON 형식으로 반환
	    return ResponseEntity.status(HttpStatus.OK).body(outVO);
	}
	
	@GetMapping("/adminRetrieveMember.do")
	public String doRetrieveMember(HttpServletRequest req, Model model) throws Exception {
		String viewName = "admin/member/admin_member_list";
		SearchVO search = new SearchVO();
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **doRetrieveMember()**                │");
		log.debug("└───────────────────────────────────────┘");
		
		if (!isSessionValid(req)) {
			model.addAttribute("loginMessage", "로그인이 필요합니다.");
	        return "login/login";
	    }
		
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
	
	@RequestMapping(value = "/adminMemberSelectOne.do", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public ResponseEntity<MemberVO> doMemberSelectOne(@RequestParam(name = "memId", required = true, defaultValue = "Unknown Id") String inMemId,
							  Model model, HttpServletRequest request) throws Exception {
		
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **doSelectOne()**                     │");
		log.debug("└───────────────────────────────────────┘");
		
		if (!isSessionValid(request)) {
			model.addAttribute("loginMessage", "로그인이 필요합니다.");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	    }
		
		log.debug("userId: {}", inMemId);

	    MemberVO inVO = new MemberVO();
	    inVO.setMemId(inMemId);

	    // 회원 정보 조회
	    MemberVO outVO = memberService.doSelectOne(inVO);

	    if (outVO == null) {
	        // 회원 정보가 없으면 404 Not Found 응답
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 회원 정보를 찾을 수 없을 경우 null 반환
	    }

	    // 조회된 회원 정보를 JSON 형식으로 반환
	    return ResponseEntity.status(HttpStatus.OK).body(outVO);
	}
	
	@GetMapping("/adminRetrieveCommunity.do")
	public String doRetrieveCommunity(HttpServletRequest req, Model model) {
		String viewName = "admin/community/admin_community_list";
		SearchVO search = new SearchVO();
		
		if (!isSessionValid(req)) {
			model.addAttribute("loginMessage", "로그인이 필요합니다.");
	        return "login/login";
	    }
		
		String pageNoString = StringUtil.nvl(req.getParameter("pageNo"), "1");
		String pageSizeString = StringUtil.nvl(req.getParameter("pageSize"), "10");
		
		int pageNo = Integer.parseInt(pageNoString);
		int pageSize = Integer.parseInt(pageSizeString);
		
		log.debug("pageNo: {}", pageNo);
		log.debug("pageSize: {}", pageSize);
		
		String searchDiv = StringUtil.nvl(req.getParameter("searchDiv"), "");
		String searchWord = StringUtil.nvl(req.getParameter("searchWord"), "");
		log.debug("searchDiv: {}", searchDiv);
		log.debug("searchWord: {}", searchWord);
		
		search.setPageNo(pageNo);
		search.setPageSize(pageSize);
		search.setSearchDiv(searchDiv);
		search.setSearchWord(searchWord);
		
		//10(공지사항),20(자유게시판)
		String div = StringUtil.nvl(req.getParameter("cmn_div"), "10");
		String cat = StringUtil.nvl(req.getParameter("cmn_category"), "10");
		log.debug("div: {}", div);
		log.debug("cat: {}", cat);
		
		Map<String,String> option = new HashMap<String, String>();
		option.put("cmn_div", div);
		option.put("cmn_category", cat);
		
		search.setOptionSearch(option);
		log.debug("search: {}", search);
		
		List<CommunityVO> list = communityService.doRetrieve(search);
		
		log.debug(list);
		
		int totalCnt = 0;
		if (null != list && list.size() > 0) {
			CommunityVO vo = list.get(0);
			totalCnt = vo.getTotalCnt();
		}
		
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("list", list);
		model.addAttribute("search", search);
		model.addAttribute("cmn_div", div);
		model.addAttribute("cmn_category", cat);
		
		return viewName;
	}
	
	@RequestMapping(value = "/adminCommunitySelectOne.do", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public ResponseEntity<CommunityVO> doCommunitySelectOne(@RequestParam(name = "cmnNo", required = true, defaultValue = "0") int inCmnNo,
							  Model model, HttpServletRequest request) throws Exception {
		if (!isSessionValid(request)) {
			model.addAttribute("loginMessage", "로그인이 필요합니다.");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	    }
		
		log.debug("inHpId: {}", inCmnNo);

		CommunityVO inVO = new CommunityVO();
	    inVO.setCmnNo(inCmnNo);

	    // 회원 정보 조회
	    CommunityVO outVO = communityService.doSelectOne(inVO);

	    if (outVO == null) {
	        // 회원 정보가 없으면 404 Not Found 응답
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 회원 정보를 찾을 수 없을 경우 null 반환
	    }

	    // 조회된 회원 정보를 JSON 형식으로 반환
	    return ResponseEntity.status(HttpStatus.OK).body(outVO);
	}
	
//	@GetMapping("/adminCommunityReg.do")
//	public String adminCommunityReg(@RequestParam(name = "cmn_div", defaultValue = "10") String div, Model model) {
//		String viewName = "admin/community/admin_community_reg";
//		log.debug("┌───────────────────────────────────────┐");
//		log.debug("│ **boardRegIndex()**                   │");
//		log.debug("└───────────────────────────────────────┘");		
//		log.debug("│ div                   │"+div);
//		
//		model.addAttribute("cmn_div", div);
//		
//		return viewName;
//	}
}