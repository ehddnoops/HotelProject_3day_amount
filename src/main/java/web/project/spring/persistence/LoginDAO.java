package web.project.spring.persistence;

import org.apache.ibatis.annotations.Param;

import web.project.spring.domain.LoginVO;

public interface LoginDAO {

	public abstract int insert(LoginVO vo);

	public abstract LoginVO select(String id);

	public abstract int update(LoginVO vo);

	public abstract int delete(String id);
//	int GetKey(String id, String user_key); // 유저 인증키 생성 메서드
//	int alter_userKey(String user_id, String key); // 유저 인증키 Y로 바꿔주는 메서드
//	int searchPassword(String user_id, String user_email, String key); // 회원 임시 비밀번호 변경 메서드
//	LoginVO loginUser(@Param("user_id")String user_id);// 유저 로그인 메서드

}