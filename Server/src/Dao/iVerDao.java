*package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Entity.Ver;
import Utils.DBConnect;

public class iVerDao implements VerDao{

	@Override
	public Ver find() throws Exception {
		// TODO Auto-generated method stub
		String sql="select * from ver";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		Ver ver=new Ver();
		try{
			conn=DBConnect.getDbconnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if (rs.next()){
				
				ver.setVer(rs.getString("ver"));
				ver.setDes(rs.getString("des"));
				
			
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
		return ver;
	}

}
