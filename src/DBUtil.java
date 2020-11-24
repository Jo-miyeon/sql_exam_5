import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBUtil {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/t1?serverTimezone=UTC";
	String user = "sbsst";
	String pass = "sbs123414";
	Connection conn = null;
	
	public PreparedStatement getPreparedStatement(String sql,Object[] params)throws SQLException {
		PreparedStatement pstmt = null;
		conn = getConnection();
		pstmt = conn.prepareStatement(sql);
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pstmt;
	}
//	public void close() {
//		try {
//			if(rs != null) {
//				rs.close();
//			}
//			if(rs != null) {
//				pstmt.close();		
//			}
//			if(rs != null) {
//				conn.close();
//			}
//		}catch(SQLException e) {
//				e.printStackTrace();
//			}
//	}
	public ArrayList<Article> getRows(String sql,Object...params) { //sql에서 어떤 select문을 다 처리할 수있어야함
		if(params.length != 0 && params[0] instanceof Object[]) {
			params = (Object[])params[0];
		}
		ArrayList<Article> articles = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
	
			while(rs.next()) {
				String title = rs.getString("title");
				int id = rs.getInt("id");
				String body = rs.getString("body");
				String nickname = rs.getString("nickname");
				int hit = rs.getInt("hit");
				
				Article article = new Article();
				article.setTitle(title);
				article.setBody(body);
				article.setNickname(nickname);
				article.setId(id);
				article.setHit(hit);
				articles.add(article);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs,pstmt,conn);
			}
		return articles;
	}
	public Article getRow(String sql,Object...params) {
		return getRows(sql,params).get(0);
	}
	public int updateQuery(String sql,Object...params) {
		if(params.length != 0 && params[0] instanceof Object[]) {
			params = (Object[])params[0]; 
		}
		int rst = 0;
		PreparedStatement pstmt = null;
		System.out.println(sql);
		pstmt = getPrepareStatement(sql,params);
		rst = pstmt.executeUpdate();
		
		return 0;
		
	}
}
