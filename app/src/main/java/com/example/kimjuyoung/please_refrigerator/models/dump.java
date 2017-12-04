package com.example.kimjuyoung.please_refrigerator.models;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.kimjuyoung.please_refrigerator.activity.InputData;
import com.example.kimjuyoung.please_refrigerator.activity.MainActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by SELab on 2017-11-29.
 */

public class dump extends AppCompatActivity{

    ArrayList<String> name = new ArrayList<String>(0);
    ArrayList<String> date = new ArrayList<String>(0);
    int tuple = 0;

    public dump(String space){
        /*String DB_name= "asd";
        name.add(DB_name);*/
        String url = "jdbc:mysql://165.132.221.47:3307/2017_2_kjy"; //서버 역할을 할 ip주소의 사용할 데이터베이스
        String user = "2017_2_kjy"; // 데이터베이스 user
        String pass = "selabkjy"; //데이터베이스 비밀번호
        String query = "";

        try{
            Class.forName("com.mysql.jdbc.Driver"); //mysql 연결
            Connection conn = DriverManager.getConnection(url, user, pass); //서버 url로 user와 password사용하여 접속
            if(conn==null)
            {
                Toast.makeText(this, "Connection goes wrong", Toast.LENGTH_LONG).show(); // 연결이 실패했을 때 에러 문자열 msg로 저장
            }
            else {
                query = "SELECT * FROM STORAGE ORDER BY LIFE";
                // 입력받은 데이터 데이터베이스에 넣기 위한 쿼리문 작성
                Statement stmt = conn.createStatement(); // 쿼리 넣을 준비 함수
                ResultSet rs = stmt.executeQuery(query);// 쿼리 실행
                rs.last();
                tuple = rs.getRow();
                rs.beforeFirst();
                while(rs.next()) {

                    Toast.makeText(this, "Connect", Toast.LENGTH_LONG).show();// 데이터베이스에 데이터가 잘 들어갔을 때 성공 문자열 msg로 저장
                }
                rs.close();
                stmt.close();
            }
            conn.close();
        } catch (Exception e) {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show(); // 연결이 안됐거나, 쿼리문 실행에서 오류가 났을 경우 에러 문자열 msg로 저장, 나머지 부분 오류는 앞에서 이미 처리
            e.printStackTrace();
        }
    }
    public ArrayList<String> getName_list(){
        return name;
    }

    public int getTuple(){
        return tuple;
    }

}
