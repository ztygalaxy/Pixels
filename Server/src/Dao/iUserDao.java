package Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Entity.User;
import Utils.DBConnect;

public class iUserDao implements UserDao {
	@Override
	public int insert(String name,String pwd) throws Exception {
		int R=0;
		String sql="insert into user(name,pwd) values('"+name+"','"+pwd+"')";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=DBConnect.getDbconnection();
			pstmt=conn.prepareStatement(sql);
			R=pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBConnect.closeDB(conn, pstmt,rs);
		}
		return R;
	}
	

	@Override
	public int find(User user) throws Exception {
		// TODO Auto-generated method stub
		int R;
		Connection conn=null;
		PreparedStatement prep=null;
		ResultSet rSet=null;
		conn=DBConnect.getDbconnection();
		String sql="select * from user where (name=? and pwd=?)";
		prep=conn.prepareStatement(sql);
		prep.setString(1, user.getName());
		prep.setString(2, user.getPwd());
		rSet=prep.executeQuery();
			if (rSet.next()) R=1;
			else R=0;
			if(rSet!=null) rSet.close();
			if(prep!=null) prep.close();
			if(conn!=null) conn.close();
			return R;
	}
}
