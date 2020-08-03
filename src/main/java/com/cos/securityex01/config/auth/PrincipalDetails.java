package com.cos.securityex01.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.management.relation.Role;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.securityex01.model.User;

import lombok.Data;

//얘가유저임
//Authentication 객체에 저장할수있는 유일한 타입
@Data
public class PrincipalDetails implements UserDetails{


	private User user;
	private UserDetails principal;


	public PrincipalDetails(User user) {

		super();
		System.out.println("여긴 auth.principalDetails.java ");
		this.user=user;
	}
	@Override
	public String getPassword() {

		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() { //너지금게정만료 되지않았니?
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { //너계정 락걸렸니?
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {//비번ㅁ ㅏㄴ료 안됫니
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() { // 계정 활성화 됫니?
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//여기서틀리면 manager로 접속등 ㅈ같이안된다.
		ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		System.out.println("PrincipalDetails.java의 getAuthorities가 발동");
		 auth.add(new SimpleGrantedAuthority(user.getRore()));

		 return auth;




	}


}

