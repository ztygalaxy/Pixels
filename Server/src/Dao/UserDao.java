package Dao;
import Entity.User;

public interface UserDao {
	public abstract int find(User user) throws Exception;
	public abstract int insert(String newpwd,String userName) throws Exception;
}