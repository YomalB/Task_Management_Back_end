package com.task.task.user.service.controller;

import com.task.task.user.service.config.JwtProvider;
import com.task.task.user.service.modal.User;
import com.task.task.user.service.repository.UserRepository;
import com.task.task.user.service.request.LogingRequest;
import com.task.task.user.service.response.AuthResponse;
import com.task.task.user.service.service.CustomerUserServiceImplementation;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLOutput;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerUserServiceImplementation customUserDetails;

   @PostMapping("/signup")
    public ResponseEntity<AuthResponse>createUserHandler(
           @RequestBody User user)throws Exception{

       String email=user.getEmail();
       String password=user.getPassword();
       String fullName =user.getFullName();
       String role=user.getRole();

       User isEmailExist =userRepository.findByEmail(email);

       if (isEmailExist!=null){
           throw new Exception("Email is Already Used with Another Account");
       }
       //Create new user
       User createdUser =new User();
       createdUser.setEmail(email);
       createdUser.setFullName(fullName);
       createdUser.setRole(role);
       createdUser.setPassword(passwordEncoder.encode(password));



       Authentication authentication=new UsernamePasswordAuthenticationToken(email,password);
       SecurityContextHolder.getContext().setAuthentication(authentication);

       String token = JwtProvider.generateToken(authentication);

       AuthResponse authResponse=new AuthResponse();
       authResponse.setJwt(token);
       authResponse.setMessage("Register Success");
       authResponse.setStatus(true);

       return new ResponseEntity<>(authResponse, HttpStatus.OK);

   }
   @PostMapping("/signin")
    public ResponseEntity<AuthResponse>signin(@RequestBody LogingRequest logingRequest){
       String username =logingRequest.getEmail();
       String password =logingRequest.getPassword();

       System.out.println(username+"-----"+password);

       Authentication authentication = authenticate(username,password);
       SecurityContextHolder.getContext().setAuthentication(authentication);

       String token =JwtProvider.generateToken(authentication);
       AuthResponse authResponse =new AuthResponse();

       authResponse.setMessage("Login Success");
       authResponse.setJwt(token);
       authResponse.setStatus(true);

       return new ResponseEntity<>(authResponse,HttpStatus.OK);


   }
   private Authentication authenticate(String username,String password){
       UserDetails userDetails =customUserDetails.loadUserByUsername(username);

       System.out.println("sing in userDetails-"+userDetails);

       if(userDetails==null){
           System.out.println("sing in Details -null"+userDetails);
           throw new BadCredentialsException("Invalid user name or password");
       }
       if(!passwordEncoder.matches(password,userDetails.getPassword())){
           System.out.println("sing in userDetails -password not match"+userDetails);
           throw new BadCredentialsException("Invalid user name or password");
       }
       return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());


   }




}
