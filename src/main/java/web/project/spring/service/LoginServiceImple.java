package web.project.spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.project.spring.domain.LoginVO;
import web.project.spring.persistence.LoginDAO;

@Service
public class LoginServiceImple implements LoginService {
	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImple.class);

	@Autowired
	private LoginDAO dao;

	@Override
	public int create(LoginVO vo) {
		logger.info("create() ȣ�� : vo = " + vo.toString());
		return dao.insert(vo);
	}

	@Override
	public LoginVO read(String id) {
		logger.info("read() ȣ�� : id = " + id);
		return dao.select(id);
	}

	public int update(LoginVO vo) {
		logger.info("update() ȣ�� : vo = " + vo.toString());
		return dao.update(vo);
	}

	@Override
	public int delete(String id) {
		logger.info("delete() ȣ�� : id = " + id);
		return dao.delete(id);
	}

}