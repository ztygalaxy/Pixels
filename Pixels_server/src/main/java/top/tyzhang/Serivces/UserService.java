package top.tyzhang.Serivces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.tyzhang.Dao.UserDao;
import top.tyzhang.Entity.User;

/**
 * @Description TODO
 * @Author Zhang Tianyu
 * @Date 2020-05-30 13:45
 * @Version 1.0
 **/
@Service("userService")
public class UserService implements IUserService {
    @Autowired
    private UserDao userDao;

    @Override
    public int find(User user) throws Exception {
        System.out.println("业务层OK");
        return userDao.find(user);
    }

    @Override
    public int insert(String userName, String newpwd) {
        System.out.println("业务层OK");
        try {
            return userDao.insert(new User(userName, newpwd));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}