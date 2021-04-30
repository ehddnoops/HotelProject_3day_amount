package web.project.spring.persistence;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import web.project.spring.domain.LoginVO;

@Repository
public class LoginDAOImple implements LoginDAO {
	private static final Logger logger = LoggerFactory.getLogger(LoginDAOImple.class);
	private static final String NAMESPACE = "web.project.spring.HotelMapper";

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(LoginVO vo) {
		logger.info("insert() 호출");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public LoginVO select(String id) {
		logger.info("select_By_ID() 호출");
		return sqlSession.selectOne(NAMESPACE + ".select_By_Id", id);
	}

	@Override
	public int update(LoginVO vo) {
		logger.info("update() 호출");
		return sqlSession.update(NAMESPACE + ".update", vo);
	}

	@Override
	public int delete(String id) {
		logger.info("delete() 호출");
		return sqlSession.delete(NAMESPACE + ".delete_all", id);
	}
	

}