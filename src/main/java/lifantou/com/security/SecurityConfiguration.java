package lifantou.com.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "lifantou.com")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        	.antMatchers("/","/logon/**","/resetPassword").permitAll()        	
        	.antMatchers("/User/**").access("hasRole('ADMIN')")         
        	.antMatchers("/Custumer/**","/Admin/Gestions/Detail-produit/**").access("hasRole('ADMIN') or hasRole('ABONNE')") 
        	.antMatchers("/Admin/**").access("hasRole('ADMIN')") 
            .and()
            .exceptionHandling().accessDeniedPage("/Access_Denied/")
            .and()
            .formLogin().loginPage("/login").loginProcessingUrl("/login")
            .successHandler(myAuthenticationSuccessHandler());
    }

    @Override
    public void configure(AuthenticationManagerBuilder builder)
            throws Exception {
    	builder.jdbcAuthentication()
    	.dataSource(dataSource)
    	.usersByUsernameQuery("SELECT username as principal, password as credentials, actived FROM acces_app WHERE username=?")
    	.authoritiesByUsernameQuery("SELECT username as principal, type FROM acces_app,roles WHERE acces_app.id_role = roles.id and username=?")
    	.passwordEncoder(new BCryptPasswordEncoder())
    	.rolePrefix("ROLE_");
    }
    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new MyAuthenticationSuccessHandler();
    }
}