package web.project.spring.controller;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import web.project.spring.domain.LoginVO;
import web.project.spring.service.LoginService;

@Controller
@RequestMapping(value = "/hotel")
public class HotelController {
	private static final Logger logger = LoggerFactory.getLogger(HotelController.class);

	@Autowired
	private LoginService loginService;

	@GetMapping("/index")
	public void indexGET() {
		logger.info("indexGET() 호출");
	}

	@GetMapping("/login")
	public void loginGET() {
		logger.info("loginGET() 호출");
	}

	@PostMapping("/login")
	public String loginPOST(String id, String pw, RedirectAttributes reAttr, HttpSession session) {
		logger.info("indexPOST() 호출");
		logger.info("id : " + id);
		logger.info("pw : " + pw);
		LoginVO vo = loginService.read(id);
		logger.info("vo.id : " + vo.getId());
		logger.info("vo.pw : " + vo.getPw());
		if (id.equals(vo.getId()) && pw.equals(vo.getPw())) {
			logger.info("로그인 성공");
			session.setAttribute("userid", id);
			return "redirect:/hotel/index";
		} else {
			logger.info("로그인 실패");
			return "redirect:/hotel/login";
		}
	}

	@GetMapping("/register")
	public void registerGET() {
		logger.info("registerGET() 호출");
	}

	@PostMapping("/register")
	public String registerPOST(LoginVO vo, RedirectAttributes reAttr, HttpSession session) {
		// RedirectAttributes
		// - 재경로 위치에 속성값을 전송하는 객체
		session.removeAttribute("userid");
		logger.info("registerPOST() 호출");
		logger.info(vo.toString());
		int result = loginService.create(vo);
		logger.info(result + "행삽입");
		if (result == 1) {
			// "insert_result"의 키이름을 가진 데이터 전송
			reAttr.addFlashAttribute("insert_result", "success");
			return "redirect:/hotel/index"; // /board/list 경로로 이동. get방식
		} else {
			reAttr.addFlashAttribute("insert_result", "fail");
			return "redirect:/hotel/register"; // /board/list 경로로 이동. get방식
		}
		// request response 객체를 전달해서 사용 할 수 있다. 기존 redirect방식을 response request 객체를 재사용이
		// 불가능 하지만 위와 같은 방식으로는 가능
	}

	@GetMapping("/mypage")
	public void mypageGET(Model model, String id) {
		logger.info("mypageGET() 호출 id = " + id);
		LoginVO vo = loginService.read(id);
		model.addAttribute("vo", vo);

	}

	@GetMapping("/mypageupdate")
	public void mypageupdateGET(Model model, String id) {
		logger.info("mypageupdateGET() 호출");
		LoginVO vo = loginService.read(id);
		model.addAttribute("vo", vo);
	}

	@PostMapping("/mypageupdate")
	public String mypageupdatePOST(LoginVO vo, RedirectAttributes reAttr, HttpSession session) {
		logger.info("mypageupdatePOST() 호출");
		logger.info(vo.toString());
		int result = loginService.update(vo);
		logger.info(result + "업데이트");
		if (result == 1) {
			// "insert_result"의 키이름을 가진 데이터 전송
			session.setAttribute("userid", vo.getId());
			reAttr.addFlashAttribute("update_result", "success");
			return "redirect:/hotel/mypage?id=" + vo.getId(); // /board/list 경로로 이동. get방식
		} else {
			reAttr.addFlashAttribute("update_result", "fail");
			return "redirect:/hotel/mypageupdate"; // /board/list 경로로 이동. get방식
		}
	}

	@GetMapping("/mypagedelete")
	public String delete(String id, RedirectAttributes reAttr, HttpSession session) {
		logger.info("delete()호출 : id = " + id);
		int result = loginService.delete(id);
		session.removeAttribute("userid");
		if (result == 1) {
			// "insert_result"의 키이름을 가진 데이터 전송
			reAttr.addFlashAttribute("delete_result", "success");
			return "redirect:/hotel/index"; // /board/list 경로로 이동. get방식
		} else {
			reAttr.addFlashAttribute("delete_result", "fail");
			return "redirect:/hotel/index"; // /board/list 경로로 이동. get방식
		}
	}

	@GetMapping("/logout")
	public String delete(String id, HttpSession session) {
		session.removeAttribute("userid");
		return "redirect:/hotel/index";
	}
	
	@Autowired
	private JavaMailSender mailSender;
	
	@RequestMapping(value = "/sendMail", method = RequestMethod.GET)
    public void sendMailTest() throws Exception{
        
        String subject = "test 메일";
        String content = "메일 테스트 내용";
        String from = "ehddnoops@naver.com";
        String to = "ehddnoops@naver.com";
        
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail,true,"UTF-8");
            // true는 멀티파트 메세지를 사용하겠다는 의미
            
            /*
             * 단순한 텍스트 메세지만 사용시엔 아래의 코드도 사용 가능 
             * MimeMessageHelper mailHelper = new MimeMessageHelper(mail,"UTF-8");
             */
            
            mailHelper.setFrom(from);
            // 빈에 아이디 설정한 것은 단순히 smtp 인증을 받기 위해 사용 따라서 보내는이(setFrom())반드시 필요
            // 보내는이와 메일주소를 수신하는이가 볼때 모두 표기 되게 원하신다면 아래의 코드를 사용하시면 됩니다.
            //mailHelper.setFrom("보내는이 이름 <보내는이 아이디@도메인주소>");
            mailHelper.setTo(to);
            mailHelper.setSubject(subject);
            mailHelper.setText(content, true);
            // true는 html을 사용하겠다는 의미입니다.
            
            /*
             * 단순한 텍스트만 사용하신다면 다음의 코드를 사용하셔도 됩니다. mailHelper.setText(content);
             */
            
            mailSender.send(mail);
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }

}