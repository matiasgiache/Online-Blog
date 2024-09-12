package com.OnlineBlog.online_blog.controllers.sec;

import com.OnlineBlog.online_blog.models.dtos.AuthLoginRequestDTO;
import com.OnlineBlog.online_blog.models.dtos.AuthResponseDTO;
import com.OnlineBlog.online_blog.services.sec.UserDetailsServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@PreAuthorize("permitAll()")
public class AuthenticationController {

    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid AuthLoginRequestDTO authLoginRequestDTO){
        return new ResponseEntity<>(this.userDetailsServiceImp.loginUser(authLoginRequestDTO), HttpStatus.OK);
    }
}
