package com.example.himark;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RequestActivity extends AppCompatActivity {
    private RecyclerView recyclerView =null;
    private RequestAdapter requestAdapter=null;
    private List<PaymentVO> dataList=null;
    private TextView total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        total = findViewById(R.id.totalrequest);

        Intent intent = getIntent();
        intent.getSerializableExtra("plist");
        ArrayList<PaymentVO> plist = (ArrayList<PaymentVO>) intent.getSerializableExtra("plist");
        System.out.println(plist.size());
        int listTotal = plist.size();
        init();
        total.setText(String.valueOf(listTotal));
        getPayment(plist);
        setRecyclerView();
    }

    private void init(){
        recyclerView=findViewById(R.id.recyclerView);
        dataList=new ArrayList<PaymentVO>();
    }

    private void getPayment(ArrayList<PaymentVO> plist){

        for(int i=0;i<plist.size();i++){
            dataList.add(new PaymentVO(plist.get(i)));
            System.out.println(dataList.get(i).getCategory());
        }

/*
        dataList.add(new RecyclerModel("흥부와 놀부","지은이"));
        dataList.add(new RecyclerModel("금도끼 은도끼","지은이"));
        dataList.add(new RecyclerModel("콩쥐팥쥐","지은이"));
        dataList.add(new RecyclerModel("이솝 우화","지은이"));
        dataList.add(new RecyclerModel("헨젤과 그레텔","지은이"));
        dataList.add(new RecyclerModel("개구리 왕자","지은이"));*/
    }

    private void setRecyclerView(){
        requestAdapter=new RequestAdapter(getApplicationContext(),R.layout.item_request,dataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        //LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(this);
        //horizontalLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //recyclerView.setLayoutManager(horizontalLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(requestAdapter);
    }
}
