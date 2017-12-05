package com.example.kimjuyoung.please_refrigerator.models;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.kimjuyoung.please_refrigerator.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by SELab on 2017-11-29.
 */

public class dump extends AppCompatActivity {

    private final String url = "jdbc:mysql://165.132.221.47:3307/2017_2_kjy"; //서버 역할을 할 ip주소의 사용할 데이터베이스
    private final String user = "2017_2_kjy"; // 데이터베이스 user
    private final String pass = "selabkjy"; //데이터베이스 비밀번호

    ArrayList<String> name = new ArrayList<String>();
    ArrayList<String> date = new ArrayList<String>();
    ArrayList<Integer> amount = new ArrayList<Integer>();
    ArrayList<String> memo = new ArrayList<String>();

    int tuple = 0;

    String space = new String();
    String type = new String();

    /*@Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
    }*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Select objSelect = new Select();
        objSelect.execute("");
    }

    public dump(String item_space, String item_type) {
}

private class Select extends AsyncTask<String, String, String> {

        String msg = "";
        String query = "";

        public Select(){

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                Class.forName("com.mysql.jdbc.Driver"); //mysql 연결
                Connection conn = DriverManager.getConnection(url, user, pass); //서버 url로 user와 password사용하여 접속

                if (conn == null) {
                    msg = "Connection goes wrong"; // 연결이 실패했을 때 에러 문자열 msg로 저장
                } else {
                    query = "SELECT * FROM STORAGE WHERE SPACE='" + "냉장" + "' AND TYPE='" + "고기" + "' ORDER BY LIFE ASC, AMOUNT DESC";
                    // 입력받은 데이터 데이터베이스에 넣기 위한 쿼리문 작성

                    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); // 쿼리 넣을 준비 함수
                    ResultSet rs = stmt.executeQuery(query);// 쿼리 실행

                    rs.last();
                    tuple = rs.getRow();
                    rs.beforeFirst();

                    while (rs.next()) {
                        name.add(rs.getString(3));
                        date.add(rs.getString(4));
                        amount.add(rs.getInt(5));
                        memo.add(rs.getString(6));
                    }
                    msg = "Connect";// 데이터베이스에 데이터가 잘 들어갔을 때 성공 문자열 msg로 저장
                    rs.close();
                    stmt.close();
                }
                conn.close();
            } catch (Exception e) {
                msg = "error"; // 연결이 안됐거나, 쿼리문 실행에서 오류가 났을 경우 에러 문자열 msg로 저장, 나머지 부분 오류는 앞에서 이미 처리
                e.printStackTrace();
            }
            return msg;
        }

        @Override
        protected void onPostExecute(String s) {
        }

    }

    public ArrayList<String> getName_list(){
        return name;
    }

    public ArrayList<String> getDate_list(){
        return date;
    }

    public ArrayList<Integer> getAmount_list(){
        return amount;
    }

    public ArrayList<String> getMemo_ist(){return memo;}

    public int getTuple(){
        return tuple;
    }

}
