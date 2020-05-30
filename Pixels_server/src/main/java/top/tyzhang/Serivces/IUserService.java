package top.tyzhang.Serivces;

import org.springframework.stereotype.Service;
import top.tyzhang.Entity.User;
public interface IUserService {
    public abstract int find(User user) throws Exception;
    public abstract int insert(String newpwd,String userName) throws Exception;
}
