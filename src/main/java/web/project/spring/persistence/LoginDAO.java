package web.project.spring.persistence;

import org.apache.ibatis.annotations.Param;

import web.project.spring.domain.LoginVO;

public interface LoginDAO {

	public abstract int insert(LoginVO vo);

	public abstract LoginVO select(String id);

	public abstract int update(LoginVO vo);

	public abstract int delete(String id);
//	int GetKey(String id, String user_key); // ���� ����Ű ���� �޼���
//	int alter_userKey(String user_id, String key); // ���� ����Ű Y�� �ٲ��ִ� �޼���
//	int searchPassword(String user_id, String user_email, String key); // ȸ�� �ӽ� ��й�ȣ ���� �޼���
//	LoginVO loginUser(@Param("user_id")String user_id);// ���� �α��� �޼���

}