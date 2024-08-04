package DevLewi.My_Blog.mapper;

import DevLewi.My_Blog.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    // Existing methods

    @Select("SELECT * FROM user WHERE username = #{username}")
    User getUserByUsername(String username);

    @Select("SELECT * FROM user WHERE username = #{username} AND password = #{password}")
    User getUserByUsernameAndPassword(String username, String password);

    @Select("SELECT * FROM user WHERE email = #{email}")
    User getUserByEmail(String email);

    @Insert("INSERT INTO login_history (user_id, login_time) VALUES (#{userId}, NOW())")
    Integer insertLoginHistory(Long userId);

    @Insert("INSERT INTO user (username, password, email) VALUES (#{username}, #{password}, #{email})")
    void insertUser(User user);
}
