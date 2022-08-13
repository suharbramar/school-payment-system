package sch.binadharma.spp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import sch.binadharma.spp.auth.ApplicationUserService;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserService applicationUserService;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, ApplicationUserService applicationUserService) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserService = applicationUserService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.csrf().disable()
                .authorizeRequests() // wants authorize request
                .antMatchers("index","/bootstrap/css/**","/bootstrap/js/**","/bootstrap/**", "/images/**").permitAll() // whitelist some urls
                .anyRequest() //any request
                .authenticated() //must be authenticated
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
//                .and()
//                .sessionManagement()
//                .invalidSessionUrl("/login")
//                .and()
//                .rememberMe()//default two weeks
//                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
//                .key("somethingveryseecure")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) // using this if disable csrf
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me")
                .logoutSuccessUrl("/login")
                .and()
                .httpBasic();
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests() // wants authorize request
//                .antMatchers( "index", "/css/*", "/js/*").permitAll() // whitelist some urls
//                //.antMatchers("/admin/**").hasRole(ADMIN.name())
//                //========using has authority instead of hasRole :
////                //.antMatchers(HttpMethod.POST,"/admin/movie/**").hasAuthority(MOVIE_WRITE.getPermission())
////                //.antMatchers(HttpMethod.PUT,"/admin/movie/**").hasAuthority(MOVIE_WRITE.getPermission())
////                //.antMatchers(HttpMethod.DELETE,"/admin/movie/**").hasAuthority(MOVIE_WRITE.getPermission())
////                //.antMatchers(HttpMethod.GET,"/admin/movie/**").hasAnyRole(ADMIN.name(), ADMINTRAINEE.name())
////                //.antMatchers(HttpMethod.GET,"/admin/movie/**").hasAuthority(MOVIE_READ.getPermission())
//                .anyRequest() //any request
//                .authenticated() //must be authenticated
//                .and()
//                .formLogin()
//                    .loginPage("/login").permitAll()
//                .defaultSuccessUrl("/category?message=Hello there !!", true)
//                .and()
//                .sessionManagement()
//                .invalidSessionUrl("/login")
//                .and()
//                .rememberMe()//default two weeks
//                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
//                .key("somethingveryseecure")
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) // using this if disable csrf
//                .clearAuthentication(true)
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID", "remember-me")
//                .logoutSuccessUrl("/login")
//                .and()
//                .httpBasic();
//    }

    //Using DAO Authentification Provider
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);

        return provider;
    }

}
