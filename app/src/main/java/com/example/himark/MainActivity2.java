package com.example.himark;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    private TextView user,authority,username,duty,dept;
    private Button rBtn,fBtn,bBtn,btnPieChart;
    PieChart pieChart;
    int[] colorArray = new int[]{Color.RED,Color.BLUE,Color.GREEN};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        pieChart = findViewById(R.id.pieChart);

        PieDataSet pieDataSet = new PieDataSet(data1(),"전자결재");
        pieDataSet.setColors(colorArray);
        PieData pieData = new PieData(pieDataSet);
        pieChart.setDrawEntryLabels(true);
        pieChart.setUsePercentValues(true);
        pieData.setValueTextSize(30);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("전자 결재");
        pieChart.setCenterTextSize(25);
        pieChart.setHoleRadius(30);
        pieChart.setData(pieData);
        pieChart.invalidate();

        user = findViewById(R.id.user);
        authority = findViewById(R.id.authority);
        username = findViewById(R.id.username);
        duty = findViewById(R.id.duty);
        dept = findViewById(R.id.dept);
        rBtn = findViewById(R.id.rBtn);

        Intent intent = getIntent();
        intent.getSerializableExtra("mlist");
        MemberVO mvo = (MemberVO) intent.getSerializableExtra("mvo");
        System.out.println("사용자 이름 : "+mvo.getUsername());
        user.setText(mvo.getUsername());
        authority.setText(mvo.getAuthority());
        username.setText(mvo.getUsername());
        duty.setText(mvo.getDuty());
        dept.setText(mvo.getHead()+" "+mvo.getDepart()+" " +mvo.getTeam());


        rBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditText에 현재 입력되어있는 값을 get(가져온다)해온다.
               /* String id = mID.getText().toString().trim();
                String pwd = mPWD.getText().toString().trim();*/
                String id = mvo.getUserId();


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
                                Intent intent = new Intent(MainActivity2.this, RequestActivity.class);
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
                RequestQueue queue = Volley.newRequestQueue(MainActivity2.this);
                queue.add(paymentRequest);
            }
        });

    }

    private ArrayList<PieEntry>data1(){
        ArrayList<PieEntry> datavalue = new ArrayList<>();

        datavalue.add(new PieEntry(30,"대기"));
        datavalue.add(new PieEntry(50,"완료"));
        datavalue.add(new PieEntry(20,"반려"));

        return datavalue;
    }
}