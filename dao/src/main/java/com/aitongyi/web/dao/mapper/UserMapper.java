package com.aitongyi.web.dao.mapper;

import com.aitongyi.web.bean.User;
import com.aitongyi.web.bean.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户数据映射
 * Created by admin on 16/8/8.
 */
public interface UserMapper {

    @Select(value="select username,password,enabled from users where username = #{username}")
    User loadUserByUsername(@Param("username") String username);

    @Insert(value="insert into users (username, password, enabled, create_date) value(#{username},#{password},#{enabled},#{createDate})")
    void saveUser(User user);


    Users getUserByUsername(String username);
    @Select(value="select password from users where username = #{username}")
    String getPasswordByUsername(String username);

    List<String> loadUserAuthoritiesByName(String username);

    @Select(value="select id, username, password,enabled,create_date as createDate from users limit #{offset} , #{count}")
    List<User> getUserList(@Param("offset") int offset, @Param("count")int count);

    @Select(value="select count(id) from users")
    Integer getUserListCount();
}
