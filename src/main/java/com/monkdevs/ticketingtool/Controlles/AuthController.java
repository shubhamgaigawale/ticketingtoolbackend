package com.monkdevs.ticketingtool.Controlles;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.monkdevs.ticketingtool.Message.Request.LoginRequest;
import com.monkdevs.ticketingtool.Message.Request.SignupRequest;
import com.monkdevs.ticketingtool.Message.Response.JwtResponse;
import com.monkdevs.ticketingtool.Message.Response.ResponseMessage;
import com.monkdevs.ticketingtool.Models.Role;
import com.monkdevs.ticketingtool.Models.RoleName;
import com.monkdevs.ticketingtool.Models.User;
import com.monkdevs.ticketingtool.Repositories.RoleRepository;
import com.monkdevs.ticketingtool.Repositories.UserRepository;
import com.monkdevs.ticketingtool.Security.jwt.JwtProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest){
        if(userRepository.existsByUsername(signupRequest.getUsername())){
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken."), HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signupRequest.getEmail())){
            return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in exist"), HttpStatus.BAD_REQUEST);
        }

        User user = new User(signupRequest.getFirstName(), 
        signupRequest.getLastName(), 
        signupRequest.getUsername(), 
        signupRequest.getEmail(), 
        passwordEncoder.encode(signupRequest.getPassword()));

        LocalDateTime createdDate = LocalDateTime.now();

        user.setCreatedDate(createdDate);
        user.setIsActive(true);

        Set<Role> roles = new HashSet<>();
        if(signupRequest.getRole() != null){
            Set<String> strRoles = signupRequest.getRole();

            strRoles.forEach(role -> {
                switch(role){
                case "admin":
                    Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Fail! Cause: User Role not found"));
                    roles.add(adminRole);
                    break;
                case "projectManager":
                    Role projectManagerRole = roleRepository.findByName(RoleName.ROLE_PM).orElseThrow(() -> new RuntimeException("Fail! Cause: User Role not found"));
                    roles.add(projectManagerRole);
                    break;
                case "tester":
                    Role testerRole = roleRepository.findByName(RoleName.ROLE_TESTER).orElseThrow(() -> new RuntimeException("Fail! Cause: User Role not found"));
                    roles.add(testerRole);
                    break;
                case "developer":
                    Role developerRole = roleRepository.findByName(RoleName.ROLE_DEVELOPER).orElseThrow(() -> new RuntimeException("Fail! Cause: User Role not found"));
                    roles.add(developerRole);
                    break;
                default:
					Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
					roles.add(userRole);
				}
                });
            }else{
                Role userRole = roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new RuntimeException("Fail! -> Cause: User not found"));
                roles.add(userRole);
            }

            user.setRoles(roles);
            userRepository.save(user);

            return new ResponseEntity<>(new ResponseMessage("User "+ signupRequest.getFirstName() + " is registered successfully"), HttpStatus.OK);
        }
    }

