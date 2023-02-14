package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.AdminDao;
import model.CartDao;
import model.CategoryDao;
import model.OrderDao;
import model.OrderDetailDao;
import model.ProductDao;
import model.UserDao;
import model.domain.CartVo;
import model.domain.UserVo;


//@WebServlet("/CustomerServlet")

@Controller
@SessionAttributes({"id", "uvo", "cart", "cartNum"})
@RequestMapping("cccc")
public class cccc {       
	
	@Autowired
	private UserDao udao;
	@Autowired
	private AdminDao adao;
	@Autowired
	private CartDao cdao;
	@Autowired
	private CategoryDao ctgdao;
	@Autowired
	private OrderDao odao;
	@Autowired
	private OrderDetailDao oddao;
	@Autowired
	private ProductDao pdao;

	
//	@ModelAttribute("cart")
//	public ArrayList<CartVo> cart() {
//		return new ArrayList<CartVo>();
//	}
	
//	<a href="9pang/test1">회원가입</a>
//	<a href="9pang/test2">로그인</a>
//	<a href="9pang/test3">상품보기</a>
//	<a href="9pang/test4">회원보기</a>
//	<a href="9pang/test5">장바구니 보기</a>
//	<a href="9pang/test6">카테고리 보기</a>
	
	
//	@GetMapping("test1")
//	public ModelAndView m1() {
//		System.out.println("m1()");
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("insertUserForm");
//		
//		return mv; 
//	}
	@PostMapping("step03")
	public @ResponseBody ArrayList<CartVo> m3( ArrayList<CartVo> all) {
		System.out.println("m3()");
//		System.out.println(sessionData.getAttribute("cart"));
//		System.out.println(sessionData.getAttribute("id"));
		System.out.println(all);
		return all;
	}
//	@GetMapping("step03")
//	public @ResponseBody ArrayList<CartVo> m3( @ModelAttribute("cart") ArrayList<CartVo> all) {
//		System.out.println("m3()");
//		System.out.println(all);
//		return all;
//	}
	
	
	@GetMapping("test1")
	public String m1() {
		System.out.println("m1()1");
		//ModelAndView mv = new ModelAndView();
		//mv.setViewName("insertUserForm");
		
		return "check"; 
	}
	
	@GetMapping("callCart")
	public String callCart() {
		System.out.println("m1()1");
		//ModelAndView mv = new ModelAndView();
		//mv.setViewName("insertUserForm");
		
		return "check"; 
	}
	
	//카트로 보내기 지금은 세션처리 여기서 했지만 원래는 로그인하면 그때 같이 처리하는거임 오케? ㅇㅋㅇㅋ
//	@GetMapping("cart")
//	public String cartView( Model sessionData, ArrayList<CartVo> all) throws SQLException {
//		System.out.println("cartView");
//		//ArrayList<CartVo> all = null;
//		
//		if(sessionData.getAttribute("id")==null) {
//			System.out.println("카트정보 없어요");
//			return "redirect:/login.html";
//		}else {
//			all = cdao.getCarts((String)sessionData.getAttribute("id"));
//			
//			sessionData.addAttribute("cart", all);//카트 리스트 세션저장
//			System.out.println("카트정보:"+  sessionData.getAttribute("cart"));
//		}
//		return "redirect:step03"; //redirect:step03 이렇게하면 또 되네..
//	}
	@GetMapping("cart2")
	public String cartView2( Model sessionData, ArrayList<CartVo> all) throws SQLException {
		System.out.println("야호2");
		System.out.println(sessionData.getAttribute("cart"));
		System.out.println(sessionData.getAttribute("id"));
		
		return "redirect:/cart.html";
	}
	
//	@GetMapping("cart2")
//	public String cartView3() throws SQLException {
//		
//		return "redirect:cart2";
//	}
	
	//aixos용 장바구니 데이터 전달
	@ResponseBody
	@PostMapping("cart")
	public ArrayList<CartVo> cartView( Model sessionData, ArrayList<CartVo> all) throws SQLException {
		all = (ArrayList<CartVo>) sessionData.getAttribute("cart");
		return all;
	}
	
	//장바구니 수정시 세션 업데이트 db적용은 로그아웃시 반영
	@ResponseBody
	@PostMapping("updatesess")
	public String cartUpdate(Model sessionData, @RequestBody ArrayList<CartVo> all) throws SQLException {
		sessionData.addAttribute("cart", all);
		System.out.println("갱신된세션"+sessionData.getAttribute("cart"));
		return "cart update";
	}
	
	//장바구니 상품삭제 //CartVo로 파라미터 설정해서도 체크해보기
	@RequestMapping(value = "deleteCart", method = RequestMethod.DELETE)
	public String deleteCart(Model sessionData, @RequestBody CartVo cartNum) throws SQLException {
		ArrayList<CartVo> all = (ArrayList<CartVo>) sessionData.getAttribute("cart");
//		System.out.println("받아온 넘버확인"+cartNum);
//		System.out.println("받아온 올확인"+all);
		all.removeIf(list -> list.getCartNum()==cartNum.getCartNum());//세션수정용
		if(cdao.delete(cartNum)) {//카트 db수정
			System.out.println("장바구니 아이템삭제");
		}
//		System.out.println("수정한 올확인"+all);
		//System.out.println("갱신된세션"+sessionData.getAttribute("cart"));
//		sessionData.addAttribute("cart", all);
		return "redirect:/cart.html";
	}
	
	//로그아웃 장바구니 세션 db적용후 세션지우고 로그아웃처리
	@GetMapping("logout")
	public String logout(Model sessionData, SessionStatus sess) throws SQLException {
		System.out.println("로그아웃....");
		ArrayList<CartVo> all = (ArrayList<CartVo>) sessionData.getAttribute("cart");
		all.forEach(c -> {
			try {
				cdao.update(c);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
		sess.setComplete();
		sess = null;
		
		return "redirect:/index.html"; 
	}

	
	//로그인후 아이디랑 카트 세션에 저장
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(Model sessionData, ArrayList<CartVo> all, @RequestParam String id, @RequestParam String pw, HttpServletRequest req) throws SQLException {
		System.out.println(id+pw);
		boolean validate = udao.userLogin(id, pw);
		System.out.println("여기실행?");
		//로그인폼은 포스트맨으로 대체했음
		if(validate) {
			sessionData.addAttribute("id", id);
			all = cdao.getCarts(id);
			sessionData.addAttribute("cart", all);//카트 리스트 세션저장
			return "redirect:/index.html";			
		}else {
			req.setAttribute("errorMsg","아이디 또는 비밀번호를 확인해주세요");
			return "error";			
			
		}
	}
	
	//회원가입폼 이동
	@RequestMapping(value = "insert", method = RequestMethod.GET)
	public String m2() {
		return "insertUserForm"; 
	}
	
	@RequestMapping("test4")
	public ArrayList<UserVo> m4() {
		System.out.println("m3()");
		ArrayList<UserVo> all = null;
		try {
			all = udao.getUsers();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return all; 
	}
	
	
//////////////////////위는 테스트존////////////////////////////////////////////////////////////	
	
//	//logout - 세션 삭제 후 login.html로 이동
//	@RequestMapping(value = "/logout", method = RequestMethod.GET)
//	public String insert(SessionStatus sess) {
//		System.out.println("로그아웃....");
//		sess.setComplete();
//		sess = null;
//				
//		return "redirect:/login.html";
//	}
//	
//	//로그인 인증 메소드
//	/* 유효한 user인 경우
//	 * 	-세션에 id 데이터 저장
//	 * 	- 모두 보기(allView)화면으로 redirect 이동
//	 *  무효한 user라면 로그인 웹페이지로 이동
//	 * 
//	 * http://ip:port/context/CustomerServlet/login
//	 * 	- post방식으로 id/pw값 웹쿼리스트링으로 조합
//	 * 
//	 * http://ip:port/context/CustomerSevlet/login
//	 * 	- id=man01&pw=11
//	 * 
//	 */
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public String login1(Model sessionData, @RequestParam String id, @RequestParam String pw) throws SQLException {
//		boolean validate = dao.userLogin(id, pw);
//		//로그인폼은 포스트맨으로 대체했음
//		if(validate) {
//			sessionData.addAttribute("id", id);
//			return "redirect:allView";			
//		}else {
//			return "redirect:/login.html";			
//			
//		}
//	}
//	
//	
//	
//	//http:ip:port/context/CustomerServlet/insert
//	//http:ip:port/context/CustomerServlet/view.jsp X
//	//http:ip:port/context/view.jsp O
//	@RequestMapping(value = "/insert", method = RequestMethod.POST)
//	public String insert( Model sessionData, UserVo cvo) throws SQLException {
//		System.out.println("insert메소드 호출");
//				
//		dao.insert(cvo);
//		sessionData.addAttribute("cvo", cvo);
//		
//				
//			return "forward:/view.jsp"; //슬래쉬를 빼면 CustomerServlet하위로 인식 슬래시 넣으면 context/view.jsp로 인식
//		}
//		
//		
//	
//	/*
//	 * delete후 모든 검색
//	 * 메소드 반환값? 모든 검색 요청 url요청 /getCustomer() 로직과 동일한 코드 복붙
//	 * 
//	 */
//	@RequestMapping(value = "/delete", method = RequestMethod.GET)
//	public String delete(@RequestParam("id") String deleteId) throws SQLException {
//	// String deleteId = request.getParameter("id");
//			
//		
//				boolean result = dao.delete(deleteId);
//				
//		
//		return "redirect:allView";
//	}	
//	
//	
//	/**
//	 * 가입된 회원 정보 update 메소드
//	 * 1. update.jsp로 호출되는 메소드로 pw와 email값만 업데이트 가능하다.
//	 * 2. 처리 결과에 따라 
//		* 	2-1. 입력에 실패하면 error.html로 수행을 넘긴다.
//		*  2-2. 정상적으로 삭제 성공했으면 단순 성공 메세지가 출력되는 JSP(updateSuccess.jsp)가 실행 되도록 한다.
//	 * @param request
//	 * @param response
//	 * @throws SQLException 
//	 * @throws ServletException
//	 * @throws IOException
//	 */
//	
//	//http://ip:port/context/CustomerServlet
//	//post 방식으로 email과 password값 적용
//	@RequestMapping(value = "/update", method = RequestMethod.POST)
//	public String update(@RequestParam("password") String pw, @RequestParam("email") String email, @ModelAttribute("cvo") UserVo cvo, SessionStatus status) throws SQLException {
//		System.out.println("업데이트 실행확인");
//		System.out.println("cvo받은거 확인"+cvo);
//		
//		
//			//CustomerVo cvo = (CustomerVo)(request.getSession().getAttribute("cvo"));		
//			//cvo.setPassword(pw);
//			//cvo.setEmail(email);	
//			dao.update(cvo);			
//			status.setComplete();//세션 무효화	
//			status = null;
//		return "forward:/updateSuccess.jsp";
//	}
//		
//	
//	
//	/**
//	 * 가입된 모든 회원 정보 검색 메소드
//	 * 1. 다수의 jsp로 호출되는 메소드로 모든 고객 정보를 DAO로 부터 받아온다
//	 * 2. 처리 결과에 따라 
//		*  2-1. 입력에 실패하면 error.jsp로 수행을 넘긴다.
//		*  2-2. 정상적으로 Dao로부터 받아온 값이 있다면 JSP(list.jsp)가 실행되도록 한다
//	 * @param request
//	 * @param response
//	 * @throws SQLException 
//	 * @throws ServletException
//	 * @throws IOException
//	 */
//	
//	@RequestMapping(value = "/allView", method = RequestMethod.GET)
//	public ModelAndView getCustomers() throws SQLException {
//		String url = "error.jsp";
//		ModelAndView mv = new ModelAndView();
//		System.out.println("겟커스토머");
//			
//			//ArrayList<CustomerVo> allData = dao.getCustomers();
//			mv.addObject("allData", dao.getUsers());
//			mv.setViewName("list");
//		
//		return mv;
//		
//	}
	
	//예외 처리에 대한 중복 코드를 분리해서 예외처리 전담 메소드
	@ExceptionHandler //이렇게하는게 원칙이긴함
	public String totalEx(Exception e, HttpServletRequest req) {
		System.out.println("예외전담");
		e.printStackTrace();
		req.setAttribute("errorMsg", e.getMessage());
		return "forward:/error.jsp"; //기존엔 슬래시 안붙였는데 왜 붙이란거 갑자기?
	}
	
	@ExceptionHandler
	public String totalEx2(NullPointerException e) {  // 예외중 NullPointerException 만 처리 하는 핸들러 메소드
		System.out.println("예외 처리 전담");
		e.printStackTrace();
		return "forward:error.jsp?";
	}
	
	
	
	
}
