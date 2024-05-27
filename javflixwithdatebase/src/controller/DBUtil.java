package controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class DBUtil {
	public static Scanner sc = new Scanner(System.in);

	// 데이터베이스 연결
	public static Connection makeConnection() throws FileNotFoundException, IOException {
		// 오라클 데이터베이스 정보(주소, 유저, 패스워드)
		String filePath = "D:/doitjava/githubjavflix/javflixwithdatebase/db.properties";
		Properties properties = new Properties();
		properties.load(new FileReader(filePath));

		String url = properties.getProperty("url");
		String user = properties.getProperty("user");
		String password = properties.getProperty("password");
		// 오라클 데이터베이스 객체 참조 변수
		Connection con = null;
		// oracle jdbc library class load
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
//			System.out.println("jdbc driver load success");
			con = DriverManager.getConnection(url, user, password);
//			System.out.println("database connection success");
		} catch (ClassNotFoundException e) {
			System.out.println("loading failure");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("connection fail");
			e.printStackTrace();
		}
		return con;
	}

	// 로그인
	public static Connection loginConnection() throws FileNotFoundException, IOException {
		// 오라클 데이터베이스 정보(주소, 유저, 패스워드)
		String filePath = "D:/doitjava/githubjavflix/javflixwithdatebase/db.properties";
		Properties properties = new Properties();
		properties.load(new FileReader(filePath));
		String url = properties.getProperty("url");
		String user = properties.getProperty("user");
		String password = properties.getProperty("password");
		Connection con = null;
		for (;;) {
			System.out.print("ID를 입력하세요: ");
			String id = sc.nextLine();
			System.out.print("비밀번호를 입력하세요: ");
			String pw = sc.nextLine();
			if (id.equals(user) && pw.equals(password)) {
				// 오라클 데이터베이스 객체 참조 변수
				// oracle jdbc library class load
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
//			System.out.println("jdbc driver load success");
					con = DriverManager.getConnection(url, user, password);
//			System.out.println("database connection success");
					System.out.println("로그인 성공!");
				} catch (ClassNotFoundException e) {
					System.out.println("loading failure");
					e.printStackTrace();
				} catch (SQLException e) {
					System.out.println("connection fail");
					e.printStackTrace();
				}
				break;
			} else {
				System.out.println("로그인 정보가 일치하지 않습니다.");
			}
		}
		return con;
	}

	public static void closeResource(ResultSet rs, PreparedStatement pstmt, Connection con) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Connection con = makeConnection();
		Connection login = loginConnection();
	}

	public static void closeResource(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeResource(PreparedStatement pstmt, Connection con) {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
