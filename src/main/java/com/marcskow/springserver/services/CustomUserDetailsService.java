package com.marcskow.springserver.services;

import com.marcskow.springserver.model.UserModel;
import com.marcskow.springserver.repositories.UserModelRepository;
import com.marcskow.springserver.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserModelRepository userModelRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws
            UsernameNotFoundException {

        Optional<UserModel> userModel = userModelRepository.findOneByUsername(username);
        if (!userModel.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserDetails(userModel.get());
    }

    private Collection<? extends GrantedAuthority> getGrantedAuthorities(UserModel userModel) {
        final List<GrantedAuthority> authorities = new ArrayList<>();
        for (final String privilege : userModel.getPrivilages()) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}