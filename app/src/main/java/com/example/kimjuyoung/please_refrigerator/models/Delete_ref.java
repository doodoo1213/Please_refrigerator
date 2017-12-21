package com.example.kimjuyoung.please_refrigerator.models;

import com.example.kimjuyoung.please_refrigerator.activity.LoginActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * Created by Kim juyoung on 2017-12-21.
 */

public class Delete_ref {
    private final String url = "jdbc:mysql://165.132.221.47:3307/2017_2_kjy"; //서버 역할을 할 ip주소의 사용할 데이터베이스
    private final String user = "2017_2_kjy"; // 데이터베이스 user
    private final String pass = "selabkjy"; //데이터베이스 비밀번호

    public void delete_ref() {


        final String phone = LoginActivity.login_p;
        final String refrigerator = LoginActivity.login_ref;

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver"); //mysql 연결
                    Connection conn = DriverManager.getConnection(url, user, pass); //서버 url로 user와 password사용하여 접속

                    Statement stmt = null;
                    Statement statement = null;

                    if (conn == null) {

                    } else {
                        String query = "DELETE FROM STORAGE WHERE PHONE='"+phone+"' AND REFRIGERATOR = '"+refrigerator+"'";
                        String query1 = "DELETE FROM USER WHERE PHONE='"+phone+"' AND REFRIGERATOR = '"+refrigerator+"'";
                        // 입력받은 데이터 데이터베이스에 넣기 위한 쿼리문 작성

                        stmt = conn.createStatement();
                        statement = conn.createStatement();
                        stmt.executeUpdate(query);
                        statement.executeUpdate(query1);
                    }

                    if(stmt != null){
                        stmt.close();
                    }

                    if(statement != null){
                        stmt.close();
                    }

                    if(conn != null){
                        conn.close();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        try {
            synchronized (thread) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
