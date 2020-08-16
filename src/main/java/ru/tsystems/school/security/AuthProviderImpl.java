package ru.tsystems.school.security;

import ru.tsystems.school.config.ApplicationContextHolder;
import ru.tsystems.school.dao.impl.PassengerDaoImpl;
import ru.tsystems.school.model.Passenger;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
//@AllArgsConstructor
public class AuthProviderImpl implements AuthenticationProvider {


	@Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String userName = authentication.getName();
        PassengerDaoImpl passengerDao1=(PassengerDaoImpl)ApplicationContextHolder.getContext().getBean("passengerDaoImpl");
        
        Passenger passenger = passengerDao1.findByUserName(userName);
           

        if (passenger == null) {
            throw new UsernameNotFoundException("User not found"); 
            
        }

        String password = authentication.getCredentials().toString();
        if (!password.equals(passenger.getPassword())) {      
        	 
            throw new BadCredentialsException("Bad credentials");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(passenger.getRoles().iterator().next().toString()));

        return new UsernamePasswordAuthenticationToken(passenger, null, authorities);

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
