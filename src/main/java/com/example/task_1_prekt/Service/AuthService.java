package com.example.task_1_prekt.Service;


import com.example.task_1_prekt.Entity.RoleName;
import com.example.task_1_prekt.Entity.User;
import com.example.task_1_prekt.Payload.ReqSignUp;
import com.example.task_1_prekt.Repository.RoleRepository;
import com.example.task_1_prekt.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (userRepository.existsByUsername(username)){
           return userRepository.findByUsername(username).orElseThrow(() -> new IllegalStateException("username yoq"));
        }else if(userRepository.existsByPhoneNumber(username)){
            return userRepository.findByPhoneNumber(username).orElseThrow(() -> new IllegalStateException("phoneNumber yoq"));
        }
        throw new UsernameNotFoundException(username);
    }

    public String register(ReqSignUp reqSignUp){
        Optional<User> byPhoneNumber = userRepository.findByPhoneNumber(reqSignUp.getPhoneNumber());
        if (byPhoneNumber.isPresent()){
            return "Bunday telefon raqam royhattan o'tgan";
        }else{
            userRepository.save(
                    new User(
                            passwordEncoder.encode(reqSignUp.getPassword()),
                            reqSignUp.getUsername(),
                            reqSignUp.getPhoneNumber(),
                            roleRepository.findAllByName(RoleName.ADMIN)));
            return "Royhatga olindingiz!!";
        }
    }

    public String login(String phoneNumber, String password){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                phoneNumber, password));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return authenticate.getPrincipal().toString();


    }
}
