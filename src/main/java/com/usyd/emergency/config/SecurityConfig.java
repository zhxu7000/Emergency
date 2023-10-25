package com.usyd.emergency.config;

import com.usyd.emergency.exception.AccessDeniedHandlerImpl;
import com.usyd.emergency.exception.AuthenticationEntryPointImpl;
import com.usyd.emergency.filter.JwtAuthenticationTokenFilter;
import com.usyd.emergency.utils.XUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //由于是前后端分离项目，所以要关闭csrf
                .csrf().disable()
                //由于是前后端分离项目，所以session是失效的，我们就不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //指定让spring security放行登录接口的规则
                .authorizeRequests()
                // 对于登录接口 anonymous表示允许匿名访问
                .antMatchers("/user/login").anonymous();
                // 除上面外的所有请求全部需要鉴权认证

        http.exceptionHandling().accessDeniedHandler(new AccessDeniedHandlerImpl());
        http.exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPointImpl());

        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

//    @Override
//    @Bean
//    public UserDetailsService userDetailsService() {
//        List<UserDetails> users= new ArrayList<UserDetails>();
//        users.add(User.withDefaultPasswordEncoder().username("admin").password("nimda").roles("USER","ADMIN").build());
//        users.add(User.withDefaultPasswordEncoder().username("Spring").password("Security").roles("USER").build());
//        return new InMemoryUserDetailsManager(users);
//    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
