package DevLewi.My_Blog.service;

import DevLewi.My_Blog.bean.User;
import DevLewi.My_Blog.mapper.UserMapper;
import DevLewi.My_Blog.config.JwtUtils; // Import JwtUtils
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils; // Add JwtUtils as a dependency

    @Autowired
    public UserService(UserMapper userMapper, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils; // Initialize JwtUtils
    }

    public User verifyUserLogin(User user) {
        User userResult = userMapper.getUserByUsernameAndPassword(user.getUserName(), user.getPassword());
        if (userResult == null) return null;
        Integer affectedRow = insertLoginHistory(userResult.getUserId());
        if (affectedRow == 0) return null;
        return userResult;
    }

    public Integer insertLoginHistory(Long userId) {
        return userMapper.insertLoginHistory(userId);
    }

    public User getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    public User getUserByToken(String token) {
        String jwt = token.substring(7); // Remove "Bearer " prefix
        String email = jwtUtils.extractUsername(jwt); // Convert JWT token into email
        return userMapper.getUserByEmail(email);
    }

    public User registerUser(User user) {
        User existingUser = userMapper.getUserByEmail(user.getEmail());
        if (existingUser != null) {
            throw new IllegalArgumentException("User with email already exists.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.insertUser(user);
        return user;
    }
}
