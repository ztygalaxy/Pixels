package top.tyzhang.Dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.tyzhang.Entity.User;

@Repository
public interface UserDao {
    @Select("select count(*) from user where name = #{name} and pwd = #{pwd}")
    public abstract int find(User user) throws Exception;

    @Insert("insert into user (name, pwd) values (#{name}, #{pwd})")
    public abstract int insert(User user) throws Exception;
}