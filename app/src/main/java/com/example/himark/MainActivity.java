package com.example.himark;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    private TextView tv_id, tv_pass;
    private Button requestBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_id = findViewById(R.id.tv_id);
        tv_pass = findViewById(R.id.tv_pass);
        requestBtn = findViewById(R.id.requestBtn);

        Intent intent = getIntent();
        String userID = intent.getStringExtra("user_id");
        String userPass = intent.getStringExtra("user_password");

        System.out.println("userID : "+userID+"userPass : "+userPass);
        tv_id.setText(userID);
        tv_pass.setText(userPass);

        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditText에 현재 입력되어있는 값을 get(가져온다)해온다.
               /* String id = mID.getText().toString().trim();
                String pwd = mPWD.getText().toString().trim();*/
                String id = userID;


                System.out.println("아이디: "+id);

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ArrayList<PaymentVO> plist=new ArrayList<>();
                        try {
                            // TODO : 인코딩 문제때문에 한글 DB인 경우 로그인 불가
                            System.out.println("요청함" + response);
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if (success) { // 성공한 경우
                                System.out.println("요청가져오기성공");
                                for (int i=0; i< jsonObject.length()-1;i++){
                                    String num=Integer.toString(i);
                                    System.out.println(num);
                                    String user_id = jsonObject.getJSONObject(num).getString("user_id");
                                    System.out.println(user_id);

                                    String manager = jsonObject.getJSONObject(num).getString("manager");
                                    String title = jsonObject.getJSONObject(num).getString("title");
                                    String category = jsonObject.getJSONObject(num).getString("category");
                                    String rdate = jsonObject.getJSONObject(num).getString("rdate");
                                    String state = jsonObject.getJSONObject(num).getString("state");

                                    plist.add(new PaymentVO(user_id,manager,title,category,rdate,state));
                                    System.out.println(plist);
                                }


                                Toast.makeText(getApplicationContext(),"요청가져오기에 성공하였습니다.",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, RequestActivity.class);
                                intent.putExtra("plist", plist);


                                startActivity(intent);

                            } else { // 로그인에 실패한 경우
                                Toast.makeText(getApplicationContext(),"요청가져오기에 실패하였습니다.",Toast.LENGTH_SHORT).show();
                               return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                PaymentRequest paymentRequest = new PaymentRequest(id, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(paymentRequest);
            }
        });

    }
}