import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArticleDao {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/t1?serverTimezone=UTC";
	String user = "sbsst";
	String pass = "sbs123414";
	
	public Connection getConnection() {
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
		return conn;
	}
	public void close() {
		try {
			if(rs != null) {
				rs.close();
			}
			if(rs != null) {
				pstmt.close();		
			}
			if(rs != null) {
				conn.close();
			}
		}catch(SQLException e) {
				e.printStackTrace();
			}
	}
	public ArrayList<Article> getRows(String sql,String[] params) { //sql에서 어떤 select문을 다 처리할 수있어야함
		ArrayList<Article> articles = new ArrayList<>();
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pass);
			pstmt = conn.prepareStatement(sql);
			
			String[] fields = sqlCheck(sql);
			if(fields != null) {
				for(int i=0; i<fields.length; i++) {
					if(fields.equals("hit") || fields.equals("id")) {
						pstmt.setInt(i+1, Integer.parseInt(params[i]));
					}else {
						pstmt.setString(i+1, params[i]);
					}
				}
			}
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String title = rs.getString("title");
				int id = rs.getInt("id");
				String body = rs.getString("body");
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
			}
		return articles;
	}
	public void setData(String sql) {
		try {
			conn=getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
	}
	public String[] sqlCheck(String sql) {
		String[] stringBits = null;
		if(sql.startsWith("select")||sql.startsWith("delete")) {
			int index = sql.indexOf("where");
			if(index != -1) {
				sql = sql.substring(index+7);
				stringBits = sql.split("and");
				
				for(int i=0;i<stringBits.length;i++) {
					stringBits[i] = stringBits[i].replace(" ","");
				}
				System.out.println("33");
				for(int i=0;i<stringBits.length;i++) {
					String[] tmp = stringBits[i].split("=");
					stringBits[i] = tmp[0].replace(" ", "");
				}
			}
		}
		return stringBits;
	}
	public ArrayList<Article> getReadArticles() {
		ArrayList<Article> articles = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM article WHERE id = ?";
		
		return articles;
		
	}
	public ArrayList<Article> getArticles() {
		ArrayList<Article> articles = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pass);
			
			String sql = "SELECT * FROM article";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String title = rs.getString("title");
				String body = rs.getString("body");
				int id = rs.getInt("id");
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
			try {
				if(rs != null) {
					rs.close();
				}
				if(rs != null) {
					pstmt.close();		
				}
				if(rs != null) {
					conn.close();
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return articles;
	}
}
