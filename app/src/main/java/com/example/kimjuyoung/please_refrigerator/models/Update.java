package com.example.kimjuyoung.please_refrigerator.models;

import com.example.kimjuyoung.please_refrigerator.activity.LoginActivity;
import com.example.kimjuyoung.please_refrigerator.activity.UpdateActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Created by Kim juyoung on 2017-12-20.
 */

public class Update {

    private final String url = "jdbc:mysql://165.132.221.47:3307/2017_2_kjy"; //서버 역할을 할 ip주소의 사용할 데이터베이스
    private final String user = "2017_2_kjy"; // 데이터베이스 user
    private final String pass = "selabkjy"; //데이터베이스 비밀번호

    public void update_item(final String space, final String life, final int amount, final String memo) {

        final String name = UpdateActivity.old_name;
        final String phone = LoginActivity.login_p;
        final String refrigerator = LoginActivity.login_ref;

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver"); //mysql 연결
                    Connection conn = DriverManager.getConnection(url, user, pass); //서버 url로 user와 password사용하여 접속

                    PreparedStatement preparedStatement = null;

                    if (conn == null) {

                    } else {
                        String query = "UPDATE STORAGE SET SPACE=?, LIFE=?, AMOUNT=?, MEMO=? WHERE NAME=? AND PHONE=? AND REFRIGERATOR=?";
                        // 입력받은 데이터 데이터베이스에 넣기 위한 쿼리문 작성

                        preparedStatement = conn.prepareStatement(query);
                        preparedStatement.setString(1, space);
                        preparedStatement.setString(2, life);
                        preparedStatement.setInt(3, amount);
                        preparedStatement.setString(4, memo);
                        preparedStatement.setString(5, name);
                        preparedStatement.setString(6, phone);
                        preparedStatement.setString(7, refrigerator);

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
