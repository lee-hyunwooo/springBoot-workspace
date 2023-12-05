package com.kh.common;

import java.sql.Connection;
import java.sql.SQLException;

//common 으로 끝나는 패키지명은 여러 코드에서 공통으로 사용되는 기능을 
// 담당하는 역할로 패키지명을 쓰기도 함 
// 또는 common 대신 util 이라는 이름을 사용하기도 함
public class JDBCTemplate {
	// Connection 객체를 생성
	 public static Connection getConnection() throws SQLException {
		 return OracleConnectionProvider.getConnection();
	 }
}
