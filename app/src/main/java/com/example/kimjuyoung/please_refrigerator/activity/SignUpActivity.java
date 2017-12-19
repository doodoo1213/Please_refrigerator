package com.example.kimjuyoung.please_refrigerator.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kimjuyoung.please_refrigerator.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Kim juyoung on 2017-12-18.
 */

public class SignUpActivity extends AppCompatActivity {

    private static final String url = "jdbc:mysql://165.132.221.47:3307/2017_2_kjy"; //서버 역할을 할 ip주소의 사용할 데이터베이스(it_refrigerator)
    private static final String user = "2017_2_kjy"; //it_refrigerator 데이터베이스 user
    private static final String pass = "selabkjy"; //it_refrigerator 데이터베이스 비밀번호

    EditText phone, refrigerator;

    String query1 = "";
    String query2 = "";
    String query3 = "";

    ArrayList<String> refrigerator_list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        phone = (EditText)findViewById(R.id.phone);
        refrigerator = (EditText)findViewById(R.id.refrigerator);

    }

    public void Insert(View v){
        Send objSend = new Send();
        objSend.execute("");
    }

    private class Send extends AsyncTask<String, String, String>
    {
        String msg = ""; //알림 띄울 문자열 초기화
        String msg1 = "";
        Boolean result = false;
        String text_phone = phone.getText().toString();
        String text_refrigerator = refrigerator.getText().toString(); //데이터베이스에 들어갈 입력받은 날짜 string으로 text_date 에 저장

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        } //save 누르기 전에 실행할 부분, 우리는 없으니까 딱히 뭐 안적음.

        @Override
        protected String doInBackground(String... strings) {
            try{
                Class.forName("com.mysql.jdbc.Driver"); //mysql 연결
                conn = DriverManager.getConnection(url, user, pass); //서버 url로 user와 password사용하여 접속
                if(conn==null)
                {
                    msg = "Connection goes wrong"; // 연결이 실패했을 때 에러 문자열 msg로 저장
                }
                else {
                        query1 = "SELECT REFRIGERATOR FROM USER WHERE PHONE = '"+text_phone+"'";
                        query2 = "INSERT INTO USER VALUES ('"+text_phone+"', '"+text_refrigerator+"')";
                        query3 = "CREATE TABLE "+text_refrigerator+
                                "(space varchar(20) not null," +
                                "type varchar(20) not null," +
                                "name varchar(50) not null," +
                                "life date not null," +
                                "amount int(11) default 1," +
                                "memo varchar(300) default null," +
                                "primary key (name, life))";
                        // 입력받은 데이터 데이터베이스에 넣기 위한 쿼리문 작성
                        stmt = conn.createStatement(); // 쿼리 넣을 준비 함수
                        rs = stmt.executeQuery(query1);
                        while (rs.next()){
                            refrigerator_list.add(rs.getString(1));
                        }
                        for(int i = 0; i<refrigerator_list.size(); i++) {
                                if (text_refrigerator.equals(refrigerator_list.get(i))){
                                    result = true;
                                }
                            }
                        if(result){
                            msg="이미 존재하는 냉장고입니다.";
                        }
                        else {
                            stmt.executeUpdate(query2); // 쿼리 실행
                            stmt.executeUpdate(query3);
                            msg = "등록되었습니다."; // 데이터베이스에 데이터가 잘 들어갔을 때 성공 문자열 msg로 저장
                        }
                }
            } catch (Exception e) {
                msg = "입력을 확인해 주세요."; // 연결이 안됐거나, 쿼리문 실행에서 오류가 났을 경우 에러 문자열 msg로 저장, 나머지 부분 오류는 앞에서 이미 처리
                e.printStackTrace();
            }finally {
                try {
                    if (conn != null) conn.close();
                    if (stmt != null) stmt.close();
                    if (rs != null) rs.close();
                } catch (SQLException e) {

                }
            }
            return msg;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(SignUpActivity.this, msg, Toast.LENGTH_SHORT).show();
            if(msg.equals("등록되었습니다.")) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
            else{
                Intent intent = new Intent(SignUpActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        } // 실행 후 msg로 저장한 문자열 알림창으로 보여주기(실패인지, 성공인지)
    }
}
