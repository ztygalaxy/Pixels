package top.tyzhang.Dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.tyzhang.Entity.Ima;

import java.util.ArrayList;

@Repository
public interface ImgDao {
    @Select("select * from image where name= #{name}")
    public abstract Ima find(Ima ima) throws Exception;

    @Insert("insert into image(name,url) values(#{name}, #{url})")
    public abstract int insert(Ima ima) throws Exception;

    @Select("select * from image")
    public abstract ArrayList<Ima> getAll() throws Exception;
}