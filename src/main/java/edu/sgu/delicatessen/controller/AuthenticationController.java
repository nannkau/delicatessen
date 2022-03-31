package edu.sgu.delicatessen.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.sgu.delicatessen.dto.request.LoginRequest;
import edu.sgu.delicatessen.dto.response.BaseResponse;
import edu.sgu.delicatessen.dto.response.JwtDTO;
import edu.sgu.delicatessen.security.MyUserDetails;
import edu.sgu.delicatessen.security.jwt.JwtUtils;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
        private final static String tokenType = "bearer";
        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private JwtUtils jwtUtils;

        @PostMapping("/signin")
        public ResponseEntity<BaseResponse<JwtDTO>> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

                Authentication authentication = authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                                                loginRequest.getPassword()));

                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = jwtUtils.generateJwtToken(authentication);

                MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
                List<String> roles = userDetails.getAuthorities().stream()
                                .map(item -> item.getAuthority())
                                .collect(Collectors.toList());
                JwtDTO result = new JwtDTO();
                result.setJwt(jwt);
                result.setType(tokenType);
                result.setUsername(userDetails.getUsername());
                result.setRoles(roles);
                return ResponseEntity.ok(new BaseResponse<JwtDTO>(result));
        }

}
