package com.airhacks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class testbd {
	
	public static ArrayList<String> connect() throws SQLException, ClassNotFoundException {
	    String url, user, password, req1;

	    Class.forName("com.mysql.cj.jdbc.Driver");

	    url = "jdbc:mysql://tijger.o2switch.net:3306/vmvo1438_mediaweb";
	    user = "vmvo1438_mediaweb";
	    password = "mediaweb4568";
	    Connection conn = DriverManager.getConnection(url, user, password);

	    ArrayList<String> utilisateurs = new ArrayList<>();
	    req1 = "SELECT `login` FROM utilisateur";
	    Statement st1 = conn.createStatement();
	    ResultSet RS1 = st1.executeQuery(req1);

	    while(RS1.next()) {
	        String login = RS1.getString(1);
	        System.out.println(login);
	        utilisateurs.add(login);
	    }
	    

	    RS1.close();
	    st1.close();

	    return utilisateurs;
	}	
}
