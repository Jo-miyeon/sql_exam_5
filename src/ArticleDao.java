import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArticleDao {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/t1?serverTimezone=UTC";
	String user = "sbsst";
	String pass = "sbs123414";
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
