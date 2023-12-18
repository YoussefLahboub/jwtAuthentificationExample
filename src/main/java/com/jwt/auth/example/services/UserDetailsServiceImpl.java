package com.jwt.auth.example.services;

import com.jwt.auth.example.dtos.UserRequest;
import com.jwt.auth.example.dtos.UserResponse;
import com.jwt.auth.example.models.CustomUserDetails;
import com.jwt.auth.example.models.UserInfos;
import com.jwt.auth.example.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.debug("Entering in loadUserByUsername Method...");

        UserInfos userInfos = userRepository.findByUsername(username)
                                            .orElseThrow(() -> new UsernameNotFoundException("could not found user..!!"));

        log.info("User Authenticated Successfully..!!!");
        return new CustomUserDetails(userInfos);
    }

    public UserResponse saveUser(UserRequest user){
        UserInfos userInfos = modelMapper.map(user, UserInfos.class);
        UserInfos userInfosSaved = userRepository.save(userInfos);
        return modelMapper.map(userInfosSaved, UserResponse.class);
    }

    public List<UserInfos> finAll(){
        return userRepository.findAll();
    }
}
