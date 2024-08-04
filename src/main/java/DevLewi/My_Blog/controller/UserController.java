package DevLewi.My_Blog.controller;

import DevLewi.My_Blog.bean.User;
import DevLewi.My_Blog.bean.UserLogin;
import DevLewi.My_Blog.config.JwtUtils;
import DevLewi.My_Blog.dto.AuthenticationRequest;
import DevLewi.My_Blog.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestMapping("/users")
@RestController
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(AuthenticationManager authenticationManager, UserService userService, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            final UserDetails userDetails = new UserLogin(userService.getUserByEmail(request.getEmail()));
            if (userDetails != null) {
                return ResponseEntity.ok(jwtUtils.generateToken(userDetails));
            } else {
                return ResponseEntity.status(404).body("User not found");
            }
        } catch (BadCredentialsException ex) {
            logger.error("Authentication failed: {}", ex.getMessage());
            return ResponseEntity.status(401).body("Authentication failed: " + ex.getMessage());
        } catch (ExpiredJwtException ex) {
            logger.error("Authentication failed: {}", ex.getMessage());
            return ResponseEntity.status(402).body("Authentication failed: " + ex.getMessage());
        } catch (Exception e) {
            logger.error("Some error has occurred: {}", e.getMessage(), e);
            return ResponseEntity.status(500).body("Some error has occurred: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
            userService.registerUser(user);
            return ResponseEntity.status(201).body("User registered successfully");
        } catch (IllegalArgumentException e) {
            logger.error("Registration failed: {}", e.getMessage());
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Some error has occurred: {}", e.getMessage(), e);
            return ResponseEntity.status(500).body("Some error has occurred: " + e.getMessage());
        }
    }

    @GetMapping("/token")
    public User getUserByToken(@RequestHeader("Authorization") String jwtToken) {
        return userService.getUserByToken(jwtToken);
    }
}
