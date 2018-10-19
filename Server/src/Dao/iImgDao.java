package Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Utils.*;
import Entity.*;

public class iImgDao implements ImgDao {
	@Override
	public int insert(String url,String name) throws Exception {
		int R=0;
//		if (find(url)!=null) return 0;
		String sql="insert into image(name,author) values('"+url+"','"+name+"')";
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
	public Ima find(String name) throws Exception {
		// TODO Auto-generated method stub
		String sql="select * from image where name='"+name+"'";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		Ima image=new Ima();
		try{
		conn=DBConnect.getDbconnection();
		pstmt=conn.prepareStatement(sql);
		rs=pstmt.executeQuery();
		if (rs.next()){
			image.setName(rs.getString("author"));
			image.setUrl(rs.getString("name"));
		}
		}catch(Exception e){
		}finally{
		DBConnect.closeDB(conn, pstmt,rs);}
		return image;
	}


	@Override
	public ArrayList<Ima> getAll() throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				ArrayList<Ima> al_Ima=new ArrayList<Ima>();
				String sql="select * from image";
				
				Connection conn=null;
				PreparedStatement pstmt=null;
				ResultSet rs=null;
				try{
					conn=DBConnect.getDbconnection();
					pstmt=conn.prepareStatement(sql);
					rs=pstmt.executeQuery();
					while (rs.next()){
						Ima ima=new Ima();
						ima.setUrl(rs.getString("name"));
						ima.setName(rs.getString("author"));
						al_Ima.add(ima);
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				finally
				{
					DBConnect.closeDB(conn, pstmt,rs);
				}
			return al_Ima;
		}
}
