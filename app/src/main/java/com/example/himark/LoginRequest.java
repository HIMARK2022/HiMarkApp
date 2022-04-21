package com.example.himark;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
import com.android.volley.toolbox.StringRequest;

public class LoginRequest extends StringRequest {

    // 서버 URL 설정 ( PHP 파일 연동 )
    final static private String URL = "http://192.168.1.56/login.php";
    private Map<String, String> map;


    public LoginRequest(String userId, String userPwd, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        System.out.println("로그인");
        map = new HashMap<>();
        map.put("user_id",userId);
        map.put("user_pwd", userPwd);
        System.out.println("map : "+map);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}