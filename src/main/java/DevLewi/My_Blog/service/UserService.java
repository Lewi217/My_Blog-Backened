package DevLewi.My_Blog.service;

import DevLewi.My_Blog.bean.User;
import DevLewi.My_Blog.config.JwtUtils;
import DevLewi.My_Blog.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final JwtUtils jwtUtils;

    @Autowired
    public UserService(UserMapper userMapper, JwtUtils jwtUtils){
        this.userMapper = userMapper;
        this.jwtUtils = jwtUtils;
    }

    public User verifyUserLogin(User user){}
    User userResult = userMapper.getUserByUsernameAndPassword(user.getUserName);
}
