package com.example.kimjuyoung.please_refrigerator.models;

import android.text.TextUtils;
import android.widget.Toast;

import com.example.kimjuyoung.please_refrigerator.activity.CartActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by Kim juyoung on 2017-12-20.
 */

public class Delete_cart {
    private final String url = "jdbc:mysql://165.132.221.47:3307/2017_2_kjy"; //서버 역할을 할 ip주소의 사용할 데이터베이스
    private final String user = "2017_2_kjy"; // 데이터베이스 user
    private final String pass = "selabkjy"; //데이터베이스 비밀번호

    public void delete_item() {


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Class.forName("com.mysql.jdbc.Driver"); //mysql 연결
                    Connection conn = DriverManager.getConnection(url, user, pass); //서버 url로 user와 password사용하여 접속
                    Statement stmt = null;

                    if (conn == null) {

                    } else {

                    String query = "DELETE FROM CART";
                    // 입력받은 데이터 데이터베이스에 넣기 위한 쿼리문 작성
                    stmt = conn.createStatement(); // 쿼리 넣을 준비 함수
                    stmt.executeUpdate(query); // 쿼리 실행

                    }

                    if(stmt != null){
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
