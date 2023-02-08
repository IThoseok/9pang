package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import model.UserDao;
import model.domain.UserVo;


//@WebServlet("/CustomerServlet")
@RestController
public class CustomerServlet {       
	
	@Autowired
	private UserDao dao;
	
	@GetMapping("step03")
	public ArrayList<UserVo> m3() {
		System.out.println("m3()");
		ArrayList<UserVo> all = null;
		try {
			all = dao.getUsers();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return all; 
	}
	
	
	
	//logout - 세션 삭제 후 login.html로 이동
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String insert(SessionStatus sess) {
		System.out.println("로그아웃....");
		sess.setComplete();
		sess = null;
				
		return "redirect:/login.html";
	}
	
	//로그인 인증 메소드
	/* 유효한 user인 경우
	 * 	-세션에 id 데이터 저장
	 * 	- 모두 보기(allView)화면으로 redirect 이동
	 *  무효한 user라면 로그인 웹페이지로 이동
	 * 
	 * http://ip:port/context/CustomerServlet/login
	 * 	- post방식으로 id/pw값 웹쿼리스트링으로 조합
	 * 
	 * http://ip:port/context/CustomerSevlet/login
	 * 	- id=man01&pw=11
	 * 
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model sessionData, @RequestParam String id, @RequestParam String pw) throws SQLException {
		boolean validate = dao.userLogin(id, pw);
		//로그인폼은 포스트맨으로 대체했음
		if(validate) {
			sessionData.addAttribute("id", id);
			return "redirect:allView";			
		}else {
			return "redirect:/login.html";			
			
		}
	}
	
	
	
	//http:ip:port/context/CustomerServlet/insert
	//http:ip:port/context/CustomerServlet/view.jsp X
	//http:ip:port/context/view.jsp O
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert( Model sessionData, UserVo cvo) throws SQLException {
		System.out.println("insert메소드 호출");
				
		dao.insert(cvo);
		sessionData.addAttribute("cvo", cvo);
		
				
			return "forward:/view.jsp"; //슬래쉬를 빼면 CustomerServlet하위로 인식 슬래시 넣으면 context/view.jsp로 인식
		}
		
		
	
	/*
	 * delete후 모든 검색
	 * 메소드 반환값? 모든 검색 요청 url요청 /getCustomer() 로직과 동일한 코드 복붙
	 * 
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("id") String deleteId) throws SQLException {
	// String deleteId = request.getParameter("id");
			
		
				boolean result = dao.delete(deleteId);
				
		
		return "redirect:allView";
	}	
	
	
	/**
	 * 가입된 회원 정보 update 메소드
	 * 1. update.jsp로 호출되는 메소드로 pw와 email값만 업데이트 가능하다.
	 * 2. 처리 결과에 따라 
		* 	2-1. 입력에 실패하면 error.html로 수행을 넘긴다.
		*  2-2. 정상적으로 삭제 성공했으면 단순 성공 메세지가 출력되는 JSP(updateSuccess.jsp)가 실행 되도록 한다.
	 * @param request
	 * @param response
	 * @throws SQLException 
	 * @throws ServletException
	 * @throws IOException
	 */
	
	//http://ip:port/context/CustomerServlet
	//post 방식으로 email과 password값 적용
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@RequestParam("password") String pw, @RequestParam("email") String email, @ModelAttribute("cvo") UserVo cvo, SessionStatus status) throws SQLException {
		System.out.println("업데이트 실행확인");
		System.out.println("cvo받은거 확인"+cvo);
		
		
			//CustomerVo cvo = (CustomerVo)(request.getSession().getAttribute("cvo"));		
			//cvo.setPassword(pw);
			//cvo.setEmail(email);	
			dao.update(cvo);			
			status.setComplete();//세션 무효화	
			status = null;
		return "forward:/updateSuccess.jsp";
	}
		
	
	
	/**
	 * 가입된 모든 회원 정보 검색 메소드
	 * 1. 다수의 jsp로 호출되는 메소드로 모든 고객 정보를 DAO로 부터 받아온다
	 * 2. 처리 결과에 따라 
		*  2-1. 입력에 실패하면 error.jsp로 수행을 넘긴다.
		*  2-2. 정상적으로 Dao로부터 받아온 값이 있다면 JSP(list.jsp)가 실행되도록 한다
	 * @param request
	 * @param response
	 * @throws SQLException 
	 * @throws ServletException
	 * @throws IOException
	 */
	
	@RequestMapping(value = "/allView", method = RequestMethod.GET)
	public ModelAndView getCustomers() throws SQLException {
		String url = "error.jsp";
		ModelAndView mv = new ModelAndView();
		System.out.println("겟커스토머");
			
			//ArrayList<CustomerVo> allData = dao.getCustomers();
			mv.addObject("allData", dao.getUsers());
			mv.setViewName("list");
		
		return mv;
		
	}
	
	//예외 처리에 대한 중복 코드를 분리해서 예외처리 전담 메소드
	@ExceptionHandler(SQLException.class) //이렇게하는게 원칙이긴함
	public String totalEx(SQLException e, HttpServletRequest req) {
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
	
	
	@Test
	public void test() {
		try {
			if(dao.userLogin("1", "1")) {
				System.out.println("유저로그인체크 정상작동");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	public void test2() {
		try {
			System.out.println(dao.getUsers());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}