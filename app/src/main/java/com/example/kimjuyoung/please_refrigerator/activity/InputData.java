package com.example.kimjuyoung.please_refrigerator.activity;

/**
 * Created by Kim juyoung on 2017-11-30.
 */

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kimjuyoung.please_refrigerator.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by Kim juyoung on 2017-11-28.
 */

public class InputData extends AppCompatActivity implements View.OnClickListener{

    private static final String url = "jdbc:mysql://165.132.221.47:3307/2017_2_kjy"; //서버 역할을 할 ip주소의 사용할 데이터베이스(it_refrigerator)
    private static final String user = "2017_2_kjy"; //it_refrigerator 데이터베이스 user
    private static final String pass = "selabkjy"; //it_refrigerator 데이터베이스 비밀번호

    CheckBox type_refrigerated, type_frozen, type_etc;
    EditText name, date, amount;
    MultiAutoCompleteTextView memo;
    Spinner spinner;
    String space = "";
    String query="";
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        type_refrigerated = (CheckBox) findViewById(R.id.checkBox1);
        type_frozen = (CheckBox) findViewById(R.id.checkBox2);
        type_etc = (CheckBox) findViewById(R.id.checkBox3);
        spinner = (Spinner)findViewById(R.id.spinner);
        name = (EditText) findViewById(R.id.editText);
        date = (EditText) findViewById(R.id.editText2);
        amount = (EditText)findViewById(R.id.editText3);
        memo = (MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView);
        // 각 부분에서 값들을 저장하기 위한 변수들

        type_refrigerated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Checked(view);
            }
        });
        type_frozen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Checked(view);
            }
        });
        type_etc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Checked(view);
            }
        });
    }
   public String Checked(View view) {
        count = 0;
        if(type_refrigerated.isChecked()) {
            space = "refrigerated";
            count++;
        }
        if(type_frozen.isChecked()) {
            space = "frozen";
            count++;
        }
        if(type_etc.isChecked()) {
            space = "etc";
            count++;
        }
        return space;
    }

    public void onClick(View view) {
        btnConn(view);
    }

    public void btnConn(View view)
    {
        Send objSend = new Send();
        objSend.execute("");
    } //저장 눌렀을 때 실행

    private class Send extends AsyncTask<String, String, String>
    {
        String msg = ""; //알림 띄울 문자열 초기화
        String text_name = name.getText().toString(); //데이터베이스에 들어갈 입력받은 이름 string으로 text_name 에 저장
        String text_date = date.getText().toString(); //데이터베이스에 들어갈 입력받은 날짜 string으로 text_date 에 저장
        String text_memo = memo.getText().toString(); //데이터베이스에 들어갈 입력받은 이름 string으로 text_name 에 저장
        String item_type = spinner.getSelectedItem().toString();
        int item_amount = Integer.parseInt(amount.getText().toString());

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
                    if (count > 1) {
                        msg = "저장공간을 하나만 선택해주세요.";
                    }
                    else if(count < 1) {
                        msg = "저장공간을 선택해 주세요";
                    }
                    else
                    {
                        if (space.equals("refrigerated")) {
                            query = "INSERT INTO COLDSTORAGE (type, fname, amount, shelflife, etc) VALUES ('beef', '" + text_name + "', '" + item_amount + "', '" + text_date + "', '" + text_memo + "')";
                            // 입력받은 데이터 데이터베이스에 넣기 위한 쿼리문 작성
                        }
                        else if (space.equals("frozen")) {
                            query = "INSERT INTO COLDSTORAGE (type, fname, amount, shelflife, etc) VALUES ('vegetable', '" + text_name + "', '" + item_amount + "', '" + text_date + "', '" + text_memo + "')";
                            // 입력받은 데이터 데이터베이스에 넣기 위한 쿼리문 작성
                        }
                        else if (space.equals("etc")) {
                            query = "INSERT INTO COLDSTORAGE (type, fname, amount, shelflife, etc) VALUES ('etc', '" + text_name + "', " + item_amount + ", '" + text_date + "', '" + text_memo + "')";
                            // 입력받은 데이터 데이터베이스에 넣기 위한 쿼리문 작성
                        }
                        Statement stmt = conn.createStatement(); // 쿼리 넣을 준비 함수
                        stmt.executeUpdate(query); // 쿼리 실행
                        msg = "Success"; // 데이터베이스에 데이터가 잘 들어갔을 때 성공 문자열 msg로 저장
                    }
                }
                conn.close();
            } catch (Exception e) {
                msg = "입력을 확인 해 주세요."; // 연결이 안됐거나, 쿼리문 실행에서 오류가 났을 경우 에러 문자열 msg로 저장
                e.printStackTrace();
            }

            return msg;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(InputData.this, msg, Toast.LENGTH_LONG).show();
        } // 실행 후 msg로 저장한 문자열 알림창으로 보여주기(실패인지, 성공인지)
    }

    /*    public void onClick(View v) {
            if(type_refrigerated.isChecked() == true)
                ;
            if(type_frozen.isChecked() == true)
                ;
            if(type_etc.isChecked() == true)
                ;
        }
    */
    public void Return(View v){//+버튼의 동작
        Intent home = new Intent(InputData.this, MainActivity.class);
        startActivity(home);
        finish();
    }
}
