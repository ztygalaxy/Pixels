package top.tyzhang.Dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.tyzhang.Entity.Ver;

@Repository
public interface VerDao {
    @Select("select * from ver")
    public abstract Ver find() throws Exception;
}