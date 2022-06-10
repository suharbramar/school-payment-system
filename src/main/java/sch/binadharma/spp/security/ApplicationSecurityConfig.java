package sch.binadharma.spp.security;

import io.bramcode.movie.moviecategoryservices.auth.ApplicationUserService;
import io.bramcode.movie.moviecategoryservices.jwt.JwtConfig;
import io.bramcode.movie.moviecategoryservices.jwt.JwtSecretKey;
import io.bramcode.movie.moviecategoryservices.jwt.JwtTokenVerifier;
import io.bramcode.movie.moviecategoryservices.jwt.JwtUsernameAndPasswordFilter;
import io.bramcode.movie.moviecategoryservices.jwt.JwtSecretKey.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import static io.bramcode.movie.moviecategoryservices.security.ApplicationUserRole.ADMIN;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserService applicationUserService;
    private final JwtSecretKey jwtSecretKey;
    private final JwtConfig jwtConfig;

   // @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, ApplicationUserService applicationUserService,
                                     JwtSecretKey jwtSecretKey, JwtConfig jwtConfig) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserService = applicationUserService;
        this.jwtSecretKey = jwtSecretKey;
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //won't store the server into database
                .and()
                .addFilter(new JwtUsernameAndPasswordFilter(authenticationManager(), jwtConfig, jwtSecretKey.secretKey())) //authenticationManager come from WebSecurirtConfigAdapter
                .addFilterAfter(new JwtTokenVerifier(jwtSecretKey.secretKey(), jwtConfig), JwtUsernameAndPasswordFilter.class)
                .authorizeRequests() // wants authorize request
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll() // whitelist some urls
             //   .antMatchers("/admin/**").hasRole(ADMIN.name())
                .anyRequest() //any request
                .authenticated(); //must be authenticated
    }

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
