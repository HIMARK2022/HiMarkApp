package com.example.himark;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class PaymentRequest extends StringRequest {

    // 서버 URL 설정 ( PHP 파일 연동 )
    final static private String URL = "http://192.168.1.56/payment.php";
    private Map<String, String> map;


    public PaymentRequest(String userId, Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);

        System.out.println("요청가져오기");
        map = new HashMap<>();
        map.put("user_id",userId);
        System.out.println("map : "+map);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
