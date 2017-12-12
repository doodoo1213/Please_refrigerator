package com.example.kimjuyoung.please_refrigerator.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Kim juyoung on 2017-12-12.
 */

public class Delete {

    private final String url = "jdbc:mysql://165.132.221.47:3307/2017_2_kjy"; //서버 역할을 할 ip주소의 사용할 데이터베이스
    private final String user = "2017_2_kjy"; // 데이터베이스 user
    private final String pass = "selabkjy"; //데이터베이스 비밀번호

    public void delete_item(final String space, final String name, final String life) {


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver"); //mysql 연결
                    Connection conn = DriverManager.getConnection(url, user, pass); //서버 url로 user와 password사용하여 접속

                    PreparedStatement preparedStatement = null;

                    if (conn == null) {

                    } else {
                        String query = "DELETE FROM STORAGE WHERE SPACE=? AND NAME=? AND LIFE=?";
                        // 입력받은 데이터 데이터베이스에 넣기 위한 쿼리문 작성

                        preparedStatement = conn.prepareStatement(query);
                        preparedStatement.setString(1, space);
                        preparedStatement.setString(2, name);
                        preparedStatement.setString(3, life);

                        preparedStatement.executeUpdate();
                    }

                    if(preparedStatement != null){
                        preparedStatement.close();
                    }

                    if(preparedStatement != null){
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
