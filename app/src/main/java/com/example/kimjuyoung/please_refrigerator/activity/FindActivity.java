package com.example.kimjuyoung.please_refrigerator.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kimjuyoung.please_refrigerator.R;
import com.example.kimjuyoung.please_refrigerator.models.Select_info;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import butterknife.OnClick;

/**
 * Created by Kim juyoung on 2017-12-20.
 */

public class FindActivity extends AppCompatActivity {

    private static final String url = "jdbc:mysql://165.132.221.47:3307/2017_2_kjy"; //서버 역할을 할 ip주소의 사용할 데이터베이스(it_refrigerator)
    private static final String user = "2017_2_kjy"; //it_refrigerator 데이터베이스 user
    private static final String pass = "selabkjy"; //it_refrigerator 데이터베이스 비밀번호
    String query = "";

    EditText phone;

    ArrayList<String> findlist = new ArrayList<>();

    Findlist_Adapter adapter;
    ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

        phone = (EditText) findViewById(R.id.search_phone);

    }

    @OnClick(R.id.find_ref)
    public void btnsearch(View v) {
        FindActivity.Select objSelect = new FindActivity.Select();
        objSelect.execute("");
    }

    private class Select extends AsyncTask<String, String, String> {
        String msg = ""; //알림 띄울 문자열 초기화
        String search_phone = phone.getText().toString(); //데이터베이스에 들어갈 입력받은 이름 string으로 text_name 에 저장

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        } //save 누르기 전에 실행할 부분, 우리는 없으니까 딱히 뭐 안적음.

        @Override
        protected String doInBackground(String... strings) {
            try {
                Class.forName("com.mysql.jdbc.Driver"); //mysql 연결
                Connection conn = DriverManager.getConnection(url, user, pass); //서버 url로 user와 password사용하여 접속
                if (conn == null) {
                    msg = "Connection goes wrong"; // 연결이 실패했을 때 에러 문자열 msg로 저장
                } else {
                    if (TextUtils.isEmpty(phone.getText())) {
                        msg = "핸드폰 번호를 입력해주세요";
                    } else {
                        query = "SELECT REFRIGERATOR FROM USER WHERE PHONE='" + search_phone + "'";
                        // 입력받은 데이터 데이터베이스에 넣기 위한 쿼리문 작성
                        Statement stmt = conn.createStatement(); // 쿼리 넣을 준비 함수
                        ResultSet rs = stmt.executeQuery(query);// 쿼리 실행

                        while (rs.next()) {
                            findlist.add(rs.getString(1));
                        }
                        rs.close();
                        stmt.close();
                    }
                    conn.close();
                }
            } catch (Exception e) {
                msg = ""; // 연결이 안됐거나, 쿼리문 실행에서 오류가 났을 경우 에러 문자열 msg로 저장, 나머지 부분 오류는 앞에서 이미 처리
                e.printStackTrace();
            }
            return msg;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(FindActivity.this, msg, Toast.LENGTH_LONG).show();
            adapter = new Findlist_Adapter();
            // 리스트뷰 참조 및 Adapter달기
            listview = (ListView) findViewById(R.id.cart_list);
            listview.setAdapter(adapter);
            input();
        }
    }
        //리스트뷰 데이터 보여주기
        public void input() {
        for(int i = 0; i<findlist.size(); i++) {
            adapter.addList(findlist.get(i));
        }
        }
}
