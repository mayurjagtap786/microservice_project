package com.example.auth.serviceimpl;


import com.example.auth.entity.Users;
import com.example.auth.exception.UserNotFoundException;
import com.example.auth.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginUserServiceImpl implements UserDetailsService {

    private final Logger LOG = LoggerFactory.getLogger(LoginUserServiceImpl.class);
    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        StackTraceElement element = Thread.currentThread().getStackTrace()[2];
        LOG.info(element.getClassName() ,"{} <<<<<<<<<<<>>>>>>>>>>>> {}:{}",element.getMethodName(),element.getLineNumber());
        Users user = usersRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found...."));
        List<GrantedAuthority> authorityList = List.of(new SimpleGrantedAuthority(user.getRole()));
        return new User(user.getUsername(),user.getPwd(),authorityList);
    }
}
