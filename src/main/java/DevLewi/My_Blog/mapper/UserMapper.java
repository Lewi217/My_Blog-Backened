package DevLewi.My_Blog.mapper;

import DevLewi.My_Blog.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    // Existing methods

    @Select("SELECT * FROM users WHERE username = #{username}")
    User getUserByUsername(String username);

    @Select("SELECT * FROM users WHERE username = #{username} AND password = #{password}")
    User getUserByUsernameAndPassword(String username, String password);

    @Select("SELECT * FROM users WHERE email = #{email}")
    User getUserByEmail(String email);

    @Insert("INSERT INTO login_history (user_id, login_time) VALUES (#{userId}, NOW())")
    Integer insertLoginHistory(Long userId);

    @Insert("INSERT INTO users (username, password, email) VALUES (#{username}, #{password}, #{email})")
    void insertUser(User user);
}
