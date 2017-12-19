package com.example.kimjuyoung.please_refrigerator.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.kimjuyoung.please_refrigerator.activity.LoginActivity;
/**
 * Created by Kim juyoung on 2017-12-06.
 */

public class Select {

                        private final String url = "jdbc:mysql://165.132.221.47:3307/2017_2_kjy"; //서버 역할을 할 ip주소의 사용할 데이터베이스
                        private final String user = "2017_2_kjy"; // 데이터베이스 user
                        private final String pass = "selabkjy"; //데이터베이스 비밀번호

                        LoginActivity refrigerator = new LoginActivity();
                        final boolean result = refrigerator.getResult();
                        String table = "";

                        public ArrayList<Select_info> get() {

                            final ArrayList<Select_info> storage_list = new ArrayList<>();

                            Thread thread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Class.forName("com.mysql.jdbc.Driver"); //mysql 연결
                                        Connection conn = DriverManager.getConnection(url, user, pass); //서버 url로 user와 password사용하여 접속

                                        if (conn == null) {

                                        } else {

                                            if(result) {
                                               final String table = refrigerator.getRefrigerator();
                                            }
                                            String query = "SELECT * FROM '"+table+"' ORDER BY LIFE ASC, AMOUNT DESC";
                        // 입력받은 데이터 데이터베이스에 넣기 위한 쿼리문 작성

                        Statement stmt = conn.createStatement(); // 쿼리 넣을 준비 함수
                        ResultSet rs = stmt.executeQuery(query);// 쿼리 실행


                        while (rs.next()) {
                            storage_list.add(new Select_info(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6)));
                        }
                        rs.close();
                        stmt.close();
                    }
                    conn.close();
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

        return storage_list;
    }
}