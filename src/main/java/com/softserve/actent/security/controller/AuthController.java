package com.softserve.actent.security.controller;

import com.softserve.actent.model.dto.IdDto;
import com.softserve.actent.model.entity.Role;
import com.softserve.actent.model.entity.User;
import com.softserve.actent.security.JwtTokenProvider;
import com.softserve.actent.security.model.dto.request.SignInDto;
import com.softserve.actent.security.model.dto.request.SignUpDto;
import com.softserve.actent.security.model.dto.response.JwtAuthResponseDto;
import com.softserve.actent.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

import static com.softserve.actent.constant.UrlConstants.API_V1;
import static com.softserve.actent.constant.UrlConstants.AUTH;

@RestController
@RequestMapping(API_V1 + AUTH)
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.OK)
    public JwtAuthResponseDto authenticateUser(@Validated @RequestBody SignInDto signInDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInDto.getUsernameOrEmail(), signInDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = tokenProvider.generateToken(authentication);
        return new JwtAuthResponseDto(jwtToken);
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public IdDto registerUser(@Validated @RequestBody SignUpDto signUpDto) {

        User user = modelMapper.map(signUpDto, User.class);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = Role.USER;

        user.setRoleset(Collections.singleton(userRole));

        user = userService.add(user);

        return new IdDto(user.getId());
    }
}
