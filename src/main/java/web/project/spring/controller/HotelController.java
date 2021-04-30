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
		logger.info("indexGET() ȣ��");
	}

	@GetMapping("/login")
	public void loginGET() {
		logger.info("loginGET() ȣ��");
	}

	@PostMapping("/login")
	public String loginPOST(String id, String pw, RedirectAttributes reAttr, HttpSession session) {
		logger.info("indexPOST() ȣ��");
		logger.info("id : " + id);
		logger.info("pw : " + pw);
		LoginVO vo = loginService.read(id);
		logger.info("vo.id : " + vo.getId());
		logger.info("vo.pw : " + vo.getPw());
		if (id.equals(vo.getId()) && pw.equals(vo.getPw())) {
			logger.info("�α��� ����");
			session.setAttribute("userid", id);
			return "redirect:/hotel/index";
		} else {
			logger.info("�α��� ����");
			return "redirect:/hotel/login";
		}
	}

	@GetMapping("/register")
	public void registerGET() {
		logger.info("registerGET() ȣ��");
	}

	@PostMapping("/register")
	public String registerPOST(LoginVO vo, RedirectAttributes reAttr, HttpSession session) {
		// RedirectAttributes
		// - ���� ��ġ�� �Ӽ����� �����ϴ� ��ü
		session.removeAttribute("userid");
		logger.info("registerPOST() ȣ��");
		logger.info(vo.toString());
		int result = loginService.create(vo);
		logger.info(result + "�����");
		if (result == 1) {
			// "insert_result"�� Ű�̸��� ���� ������ ����
			reAttr.addFlashAttribute("insert_result", "success");
			return "redirect:/hotel/index"; // /board/list ��η� �̵�. get���
		} else {
			reAttr.addFlashAttribute("insert_result", "fail");
			return "redirect:/hotel/register"; // /board/list ��η� �̵�. get���
		}
		// request response ��ü�� �����ؼ� ��� �� �� �ִ�. ���� redirect����� response request ��ü�� ������
		// �Ұ��� ������ ���� ���� ������δ� ����
	}

	@GetMapping("/mypage")
	public void mypageGET(Model model, String id) {
		logger.info("mypageGET() ȣ�� id = " + id);
		LoginVO vo = loginService.read(id);
		model.addAttribute("vo", vo);

	}

	@GetMapping("/mypageupdate")
	public void mypageupdateGET(Model model, String id) {
		logger.info("mypageupdateGET() ȣ��");
		LoginVO vo = loginService.read(id);
		model.addAttribute("vo", vo);
	}

	@PostMapping("/mypageupdate")
	public String mypageupdatePOST(LoginVO vo, RedirectAttributes reAttr, HttpSession session) {
		logger.info("mypageupdatePOST() ȣ��");
		logger.info(vo.toString());
		int result = loginService.update(vo);
		logger.info(result + "������Ʈ");
		if (result == 1) {
			// "insert_result"�� Ű�̸��� ���� ������ ����
			session.setAttribute("userid", vo.getId());
			reAttr.addFlashAttribute("update_result", "success");
			return "redirect:/hotel/mypage?id=" + vo.getId(); // /board/list ��η� �̵�. get���
		} else {
			reAttr.addFlashAttribute("update_result", "fail");
			return "redirect:/hotel/mypageupdate"; // /board/list ��η� �̵�. get���
		}
	}

	@GetMapping("/mypagedelete")
	public String delete(String id, RedirectAttributes reAttr, HttpSession session) {
		logger.info("delete()ȣ�� : id = " + id);
		int result = loginService.delete(id);
		session.removeAttribute("userid");
		if (result == 1) {
			// "insert_result"�� Ű�̸��� ���� ������ ����
			reAttr.addFlashAttribute("delete_result", "success");
			return "redirect:/hotel/index"; // /board/list ��η� �̵�. get���
		} else {
			reAttr.addFlashAttribute("delete_result", "fail");
			return "redirect:/hotel/index"; // /board/list ��η� �̵�. get���
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
        
        String subject = "test ����";
        String content = "���� �׽�Ʈ ����";
        String from = "ehddnoops@naver.com";
        String to = "ehddnoops@naver.com";
        
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail,true,"UTF-8");
            // true�� ��Ƽ��Ʈ �޼����� ����ϰڴٴ� �ǹ�
            
            /*
             * �ܼ��� �ؽ�Ʈ �޼����� ���ÿ� �Ʒ��� �ڵ嵵 ��� ���� 
             * MimeMessageHelper mailHelper = new MimeMessageHelper(mail,"UTF-8");
             */
            
            mailHelper.setFrom(from);
            // �� ���̵� ������ ���� �ܼ��� smtp ������ �ޱ� ���� ��� ���� ��������(setFrom())�ݵ�� �ʿ�
            // �������̿� �����ּҸ� �����ϴ��̰� ���� ��� ǥ�� �ǰ� ���ϽŴٸ� �Ʒ��� �ڵ带 ����Ͻø� �˴ϴ�.
            //mailHelper.setFrom("�������� �̸� <�������� ���̵�@�������ּ�>");
            mailHelper.setTo(to);
            mailHelper.setSubject(subject);
            mailHelper.setText(content, true);
            // true�� html�� ����ϰڴٴ� �ǹ��Դϴ�.
            
            /*
             * �ܼ��� �ؽ�Ʈ�� ����ϽŴٸ� ������ �ڵ带 ����ϼŵ� �˴ϴ�. mailHelper.setText(content);
             */
            
            mailSender.send(mail);
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }

}