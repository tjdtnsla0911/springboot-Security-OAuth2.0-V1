package com.cos.securityex01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Configuration //Ioc에 빈(Bean)을 등록   빈 = 객체
@EnableWebSecurity //필터 체인 관리 시작
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) //특정 주소 접근시 권한 및 인증을 위한 어노테이션 활성
public class SecurityCofig  extends WebSecurityConfigurerAdapter{ //무조껀 다관리할 필요가없으니까 인터페이스가아니라 상속개념임
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		System.out.println("auth.SecurityCofig.java 전");
		return new BCryptPasswordEncoder();
	}

	//한번만뜨면되서 여기둠 암테나도도댐

	@Override //요놈으로인해 http에서 오는요청을 다막아버린다
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("auth.SecurityConfig.java 의 configure");
			http.csrf().disable(); //disable로 끄고 어차피 javaScript로 할꺼임 이거요샌안검
			http.authorizeRequests()
			.antMatchers("/user/**").authenticated() //user로 가는건 인증필요
//			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")        //일케 적으면 ROLE_ADMIN이란 권한을 가진놈만 들어갈수있음
//			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') and hasRole('ROLE_USER')")        //일케 적으면 ROLE_ADMIN이란 권한을 가진놈만 들어갈수있음
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")        //일케 적으면 ROLE_ADMIN이란 권한을 가진놈만 들어갈수있음
			.anyRequest().permitAll()
		.and()
			.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/loginProc")
			.defaultSuccessUrl("/");
			System.out.println("auth.SecurityConfig.java 의 configure는 문제없는듯하오");

	}
}
