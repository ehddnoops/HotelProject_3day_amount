package web.project.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import web.project.spring.domain.LoginVO;
import web.project.spring.persistence.LoginDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@WebAppConfiguration
public class LoginDAOTest {
   private static final Logger logger = 
         LoggerFactory.getLogger(LoginDAOTest.class);
   
   @Autowired
   private LoginDAO dao;
   
   @Test
   public void testDAO() {
      logger.info("1234");
      //testInsert();
      testSelcet();
   }

   private void testSelcet() {
      LoginVO vo = dao.select("test3");
      logger.info("vo : " + vo);
      
   }

   private void testInsert() {
      
      LoginVO vo = new LoginVO("test3","1234","test","010-0000-0000","test@test.com",2021-01-01,4);
      int result = dao.insert(vo);
      logger.info(result + "Çà »ðÀÔ");
   }
}