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

public class LoginActivity extends AppCompatActivity {

    private static final String url = "jdbc:mysql://165.132.221.47:3307/2017_2_kjy"; //서버 역할을 할 ip주소의 사용할 데이터베이스(it_refrigerator)
    private static final String user = "2017_2_kjy"; //it_refrigerator 데이터베이스 user
    private static final String pass = "selabkjy"; //it_refrigerator 데이터베이스 비밀번호

    EditText phone, refrigerator;
    String query = "";
    String msg = ""; //알림 띄울 문자열 초기화
    Boolean result = false;

    ArrayList<String> refrigerator_list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phone = (EditText)findViewById(R.id.login_phone);
        refrigerator = (EditText)findViewById(R.id.login_name);
    }

    public void SignUp(View v){
        Intent signup = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(signup);
        finish();
    }

    public void Login(View v){
        Send objSend = new Send();
        objSend.execute("");
    }

    private class Send extends AsyncTask<String, String, String>
    {
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
                    query = "SELECT REFRIGERATOR FROM USER WHERE PHONE = '"+text_phone+"'";

                    // 입력받은 데이터 데이터베이스에 넣기 위한 쿼리문 작성
                    stmt = conn.createStatement(); // 쿼리 넣을 준비 함수
                    rs = stmt.executeQuery(query);
                    while (rs.next()){
                        refrigerator_list.add(rs.getString(1));
                    }
                    for(int i = 0; i<refrigerator_list.size(); i++) {
                        if (text_refrigerator.equals(refrigerator_list.get(i))){
                            result = true;
                        }
                    }
                    if(result){
                        msg="로그인 성공";
                    }
                    else {
                        if(refrigerator_list.size()==0){
                            msg = "핸드폰 번호를 확인해 주세요.";
                        }
                        else {
                            msg = "냉장고 이름을 확인해 주세요."; // 데이터베이스에 데이터가 잘 들어갔을 때 성공 문자열 msg로 저장
                        }
                    }
                }
            } catch (Exception e) {
                msg = "error"; // 연결이 안됐거나, 쿼리문 실행에서 오류가 났을 경우 에러 문자열 msg로 저장, 나머지 부분 오류는 앞에서 이미 처리
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
            Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
            if(msg.equals("로그인 성공")) {
                Intent intent = new Intent(LoginActivity.this, activity_homeview.class);
                startActivity(intent);
                finish();
            }
            else{
                Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        } // 실행 후 msg로 저장한 문자열 알림창으로 보여주기(실패인지, 성공인지)
    }

    public String getRefrigerator(){
            return refrigerator.getText().toString();
    }

    public boolean getResult(){
        return result;
    }

}
