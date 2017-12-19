package com.example.kimjuyoung.please_refrigerator.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kimjuyoung.please_refrigerator.R;
import com.example.kimjuyoung.please_refrigerator.models.Select_cart;
import com.example.kimjuyoung.please_refrigerator.models.Select_cart_info;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

import butterknife.OnClick;

/**
 * Created by Kim juyoung on 2017-12-16.
 */

public class CartActivity extends AppCompatActivity implements View.OnClickListener{
    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    private static final String url = "jdbc:mysql://165.132.221.47:3307/2017_2_kjy"; //서버 역할을 할 ip주소의 사용할 데이터베이스(it_refrigerator)
    private static final String user = "2017_2_kjy"; //it_refrigerator 데이터베이스 user
    private static final String pass = "selabkjy"; //it_refrigerator 데이터베이스 비밀번호

    ArrayList<Select_cart_info> carttable_list = new ArrayList<>();
    Cartlist_Adapter adapter;
    ListView listview ;

    EditText cname, camount;
    //ImageButton totaldelete;

    String query="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);

        cname = (EditText) findViewById(R.id.input_cartname);
        camount = (EditText)findViewById(R.id.input_cartamount);

        //ImageButton totaldelete = (ImageButton)findViewById(R.id.all_delete);

//        totaldelete.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//
//                }
//            });
//        };

        // 각 부분에서 값들을 저장하기 위한 변수들

    }

    public void onClick(View view) {
        btnplus(view);
        //  btnremove(view);
    }
    // 카트테이블의 데이터 삭제
//    private void btnremove(View view) {
//        String msg = ""; //알림 띄울 문자열 초기화
//
//        try{
//            Class.forName("com.mysql.jdbc.Driver"); //mysql 연결
//            Connection conn = DriverManager.getConnection(url, user, pass); //서버 url로 user와 password사용하여 접속
//            if(conn==null)
//            {
//                msg = "Connection goes wrong"; // 연결이 실패했을 때 에러 문자열 msg로 저장
//            }
//            else {
//                if (TextUtils.isEmpty(cname.getText())){
//                    msg = "이름을 입력해주세요";
//                }
//                else {
//                    query = "DELECT FROM CART";
//                    // 입력받은 데이터 데이터베이스에 넣기 위한 쿼리문 작성
//                    Statement stmt = conn.createStatement(); // 쿼리 넣을 준비 함수
//                    stmt.executeUpdate(query); // 쿼리 실행
//                    msg = "입력되었습니다."; // 데이터베이스에 데이터가 잘 들어갔을 때 성공 문자열 msg로 저장
//                }
//            }
//            conn.close();
//        } catch (Exception e) {
//            msg = ""; // 연결이 안됐거나, 쿼리문 실행에서 오류가 났을 경우 에러 문자열 msg로 저장, 나머지 부분 오류는 앞에서 이미 처리
//            e.printStackTrace();
//        }
//        return msg;
//    }

    @OnClick(R.id.data_plus)
    public void btnplus(View view)
    {
        Send objSend = new Send();
        objSend.execute("");
    } //저장 눌렀을 때 실행

    private class Send extends AsyncTask<String, String, String>
    {
        String msg = ""; //알림 띄울 문자열 초기화
        String text_name = cname.getText().toString(); //데이터베이스에 들어갈 입력받은 이름 string으로 text_name 에 저장
        int item_amount = Integer.parseInt(camount.getText().toString());

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        } //save 누르기 전에 실행할 부분, 우리는 없으니까 딱히 뭐 안적음.

        @Override
        protected String doInBackground(String... strings) {
            try{
                Class.forName("com.mysql.jdbc.Driver"); //mysql 연결
                Connection conn = DriverManager.getConnection(url, user, pass); //서버 url로 user와 password사용하여 접속
                if(conn==null)
                {
                    msg = "Connection goes wrong"; // 연결이 실패했을 때 에러 문자열 msg로 저장
                }
                else {
                    if (TextUtils.isEmpty(cname.getText())){
                        msg = "이름을 입력해주세요";
                    }
                    else {
                        query = "INSERT INTO CART (name, amount) VALUES ('" +  text_name + "', '"  + item_amount + "')";
                        // 입력받은 데이터 데이터베이스에 넣기 위한 쿼리문 작성
                        Statement stmt = conn.createStatement(); // 쿼리 넣을 준비 함수
                        stmt.executeUpdate(query); // 쿼리 실행
                        msg = "입력되었습니다."; // 데이터베이스에 데이터가 잘 들어갔을 때 성공 문자열 msg로 저장
                    }
                }
                conn.close();
            } catch (Exception e) {
                msg = ""; // 연결이 안됐거나, 쿼리문 실행에서 오류가 났을 경우 에러 문자열 msg로 저장, 나머지 부분 오류는 앞에서 이미 처리
                e.printStackTrace();
            }
            return msg;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(CartActivity.this, msg, Toast.LENGTH_LONG).show();
            if(msg.equals("입력되었습니다.")) {

                adapter = new Cartlist_Adapter();

                // 리스트뷰 참조 및 Adapter달기
                listview = (ListView) findViewById(R.id.cart_list);
                listview.setAdapter(adapter);
                input();
            }
        } // 실행 후 msg로 저장한 문자열 알림창으로 보여주기(실패인지, 성공인지)
    }
    //리스트뷰 데이터 보여주기
    public void input() {

        Select_cart Info_cart = new Select_cart();
        carttable_list = Info_cart.get();

        for(int i = 0 ; i < carttable_list.size() ; i ++){
            adapter.addcartItem( carttable_list.get(i).getName(),carttable_list.get(i).getAmount());
        }
        Toast.makeText(this, carttable_list.get(1).getName()+carttable_list.get(1).getAmount(), Toast.LENGTH_SHORT).show();

    }

    @OnClick(R.id.gotohome)
    public void Return(View v){//버튼의 동작 홈뷰로 보내버리기
        Intent gotohome = new Intent(CartActivity.this, activity_homeview.class);
        startActivity(gotohome);
        finish();
    }


    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if(0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime)
        {
            super.onBackPressed();
        }
        else
        {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "한번 더 누르면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    }

}
