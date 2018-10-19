package Servlet;

import Dao.iUserDao;
import Dao.iVerDao;
import Entity.User;

public class StaticData {
	public static void main(String[] args) throws Exception {
		iVerDao iDao=new iVerDao();
		System.out.println(iDao.find().getVer());
	}
}
