package DevLewi.My_Blog.mapper;

import DevLewi.My_Blog.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    User getUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    Integer insertLoginHistory(@Param("userId") Long userId);

    User getUserByEmail(@Param("email") String email);

    @Insert("INSERT INTO users (username, email, password, name) VALUES (#{username}, #{email}, #{password}, #{name})")
    void save(User user);
}
