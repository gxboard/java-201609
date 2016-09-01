/**
 * 
 */
package kr.co.saramin.emaillist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.co.saramin.emaillist.vo.EmailListVo;

/**
 * @author user1
 *
 */
public class EmailListDao {
	public List<EmailListVo> getList()
	{
		List<EmailListVo> list = new ArrayList<EmailListVo>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			// 1. 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");
			String dbUrl = "jdbc:mysql://localhost/webdb";
			
			// 2. 디비 연결하기
			conn = DriverManager.getConnection(dbUrl, "webdb", "webdb");
			
			stmt = conn.createStatement();
			String sql = "select * from emaillist";
			rs = stmt.executeQuery(sql);
			
			// 5 결과
			while (rs.next()) {
				Long no = rs.getLong(1);
				String firstName = rs.getString(2);
				String lastName  = rs.getString(3);
				String email     = rs.getString(4);
				EmailListVo vo = new EmailListVo();
				vo.setNo(no);;
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				vo.setEmail(email);
				
				list.add(vo);
			}
			
			return list;
			
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			System.out.println("Driver Loading Fail");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException: " + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public void insert(EmailListVo vo )
	{
		PreparedStatement stmt = null;
		
		try {
			// 1. 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");
			String dbUrl = "jdbc:mysql://localhost/webdb";
			
			// 2. 디비 연결하기
			Connection conn = DriverManager.getConnection(dbUrl, "webdb", "webdb");
			
			String sql = "insert into emaillist values(null, ?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, vo.getFirstName());
			stmt.setString(2, vo.getLastName());
			stmt.setString(3, vo.getEmail());
			
			stmt.executeUpdate();
			
			
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			System.out.println("Driver Loading Fail");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException: " + e);
		}
	}
	
	
}
