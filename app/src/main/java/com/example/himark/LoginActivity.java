package com.example.himark;

import android.content.Intent;

import android.os.Bundle;
import android.widget.TextView;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    EditText mID, mPWD;
    Button mLoginBtn;
    private MemberVO mvo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mID = findViewById(R.id.ID);
        mPWD = findViewById(R.id.PWD);
        mLoginBtn = findViewById(R.id.LoginBtn);

      /*  mLoginBtn.setOnClickListener(v -> {
            String id = mID.getText().toString().trim();
            String pwd = mPWD.getText().toString().trim();

            if (TextUtils.isEmpty(id)) {
                mID.setError("Email is Required.");
                return;
            }

            if (TextUtils.isEmpty(pwd)) {
                mPWD.setError("Password is Required.");
                return;
            }*/

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditText에 현재 입력되어있는 값을 get(가져온다)해온다.
               /* String id = mID.getText().toString().trim();
                String pwd = mPWD.getText().toString().trim();*/
                String id = mID.getText().toString();
                String pwd =mPWD.getText().toString();

                System.out.println("아이디: "+id+ " 비밀번호: "+pwd);

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            // TODO : 인코딩 문제때문에 한글 DB인 경우 로그인 불가
                            System.out.println("사용자정보" + response);
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if (success) { // 성공한 경우
                                System.out.println("사용자정보 가져오기 성공");

                                    String num=Integer.toString(0);
                                    System.out.println(num);
                                    String user_id = jsonObject.getJSONObject(num).getString("userId");
                                    System.out.println(user_id);

                                    String username = jsonObject.getJSONObject(num).getString("username");
                                    String duty = jsonObject.getJSONObject(num).getString("duty");
                                    String team = jsonObject.getJSONObject(num).getString("team");
                                    String depart = jsonObject.getJSONObject(num).getString("depart");
                                    String head = jsonObject.getJSONObject(num).getString("head");
                                    String authority = jsonObject.getJSONObject(num).getString("authority");
                                    String currentstate = jsonObject.getJSONObject(num).getString("currentstate");

                                    mvo = new MemberVO(user_id,username,duty,team,depart,head,authority,currentstate);
                                    System.out.println(mvo);



                                Toast.makeText(getApplicationContext(),"사용자정보 가져오기에 성공하였습니다.",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity2.class);
                                intent.putExtra("mvo", mvo);
                                startActivity(intent);
                            } else { // 로그인에 실패한 경우
                                Toast.makeText(getApplicationContext(),"로그인에 실패하였습니다.",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(id, pwd, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });


    }
}



