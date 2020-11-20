import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class DBTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		ArticleDao articleDao = new ArticleDao();
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			System.out.println("명령어를 입력해주세요.");
			String cmd = sc.nextLine();
			if (cmd.equals("list")) {
				ArrayList<Article> articles = articleDao.getArticles();
				for(int i = 0; i<articles.size(); i++) {
					System.out.println("제목:"+articles.get(i).getTitle());
					System.out.println("내용:"+articles.get(i).getBody());
				}
				
			} else if (cmd.equals("add")) {
				Article a = new Article();
				System.out.println("제목 : ");
				String title = sc.nextLine();
				a.setTitle(title);
				System.out.println("내용 : ");
				String body = sc.nextLine();
				a.setBody(body);
				
//				String sql2 = "INSERT INTO article SET title = ? ,`body` = ?, nickname = '홍길동', hit = 10";
//				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
//
//				pstmt2.setString(1, title);
//				pstmt2.setString(2, body);
//				pstmt2.executeUpdate();
			} else if (cmd.equals("read")) {
				Article a = new Article();
				ArrayList<Article> articles = articleDao.getArticles();
				System.out.println("게시물번호를 입력해주세요 : ");
				int targetNumb = Integer.parseInt(sc.nextLine());
				for(int i=0;i<articles.size();i++) {
					if(targetNumb==a.getId()) {
						System.out.println("======="+a.getId()+"번 게시물=====");
						System.out.println("제목:"+articles.get(i).getTitle());
						System.out.println("내용:"+articles.get(i).getBody());
					}else {
						System.out.println("게시물이 존재하지 않습니다.");
					}
				}
				
			} else if (cmd.equals("update")) {
//				System.out.println("수정할 게시물 번호 :");
//				int id = Integer.parseInt(sc.nextLine());
//
//				String sql2 = "SELECT * FROM article WHERE id =?";
//				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
//				pstmt2.setInt(1, id);
//				ResultSet rs = pstmt2.executeQuery();
//
//				if (rs.next()) {
//					String sql = "UPDATE article SET title = ?, `body` = ?, WHERE id = ?";
//					PreparedStatement pstmt = conn.prepareStatement(sql);
//
//					System.out.println("제목:");
//					String newTitle = sc.nextLine();
//					System.out.println("내용:");
//					String newBody = sc.nextLine();
//					System.out.println("수정이 완료되었습니다.");
//
//					pstmt.setString(1, newTitle);
//					pstmt.setString(2, newBody);
//					pstmt.setInt(3, id);
//					pstmt.executeUpdate();
//
//				} else {
//					System.out.println("없는 게시물 번호입니다.");
//				}
			} else if (cmd.equals("delete")) {
//				System.out.println("삭제할 게시물 번호 :");
//				int id = Integer.parseInt(sc.nextLine());
//
//				String sql2 = "SELECT * FROM article WHERE id =?";
//				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
//				pstmt2.setInt(1, id);
//				ResultSet rs = pstmt2.executeQuery();
//
//				if (rs.next()) {
//					String sql = "DELETE FROM article WHERE id = ?";
//					PreparedStatement pstmt = conn.prepareStatement(sql);
//					System.out.println("삭제가 완료되었습니다.");
//
//					pstmt.setInt(1, id);
//					pstmt.executeUpdate();
//
//				} else {
//					System.out.println("없는 게시물 번호입니다.");
//				}

			}
		}

	}

}