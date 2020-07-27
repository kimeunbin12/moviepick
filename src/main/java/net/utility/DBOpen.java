package net.utility;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.stereotype.Component;

//스프링컨테이너(웹서버)가 자동으로 객체 생성함
@Component		//annotation
public class DBOpen {
	
	public DBOpen() {
		System.out.println("--DBOpen()객체 생성됨..");
	}
	public Connection getConnection() {
	    /*
		//1)오라클
	    String url     ="jdbc:oracle:thin:@localhost:1521:xe";
	    String user    ="system";
	    String password="1234";
	    String driver  ="oracle.jdbc.driver.OracleDriver"; // ojdbc6.jar
	   */
	    //2)My-SQL
	    
	    String url="jdbc:mysql://localhost:3306/moviepick?useUnicode=true&characterEncoding=utf8";
	    String user="root";
	    String password="1234";
	    String driver="org.gjt.mm.mysql.Driver";
	    
	   
	    Connection con=null;
	    try {
	      Class.forName(driver);
	      con=DriverManager.getConnection(url, user, password);
	    }catch(Exception e){
	      System.out.println("DB연결실패: "+e);
	    }
	   
	    return con;   
	   
	  }//getConnection() end
}//class end
