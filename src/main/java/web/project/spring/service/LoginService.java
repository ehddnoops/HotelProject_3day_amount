package web.project.spring.service;

import web.project.spring.domain.LoginVO;

public interface LoginService {
	public abstract int create(LoginVO vo);

	public abstract LoginVO read(String id);

	public abstract int update(LoginVO vo);

	public abstract int delete(String id);

	
}