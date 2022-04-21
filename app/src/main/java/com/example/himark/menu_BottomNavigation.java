package com.example.himark;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
public class menu_BottomNavigation extends AppCompatActivity {
    FrameLayout frameLayout;
    BottomNavigationView bottomNavigationView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    menu_BottomNavi_Fragment_1 menu_bottomNavi_fragment_1 = new menu_BottomNavi_Fragment_1();
    menu_BottomNavi_Fragment_2 menu_bottomNavi_fragment_2 = new menu_BottomNavi_Fragment_2();
    menu_BottomNavi_Fragment_3 menu_bottomNavi_fragment_3 = new menu_BottomNavi_Fragment_3();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        bottomNavigationView = findViewById(R.id.bottomNavi);
        frameLayout = findViewById(R.id.Main_Frame);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.Main_Frame,menu_bottomNavi_fragment_1);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                changeFragment(item.getItemId());
                return true;
            }
        });
    }
    public void changeFragment(int title_id) {
        fragmentTransaction = fragmentManager.beginTransaction();

        switch (title_id) {
            case R.id.action_write :
                fragmentTransaction.replace(R.id.Main_Frame,menu_bottomNavi_fragment_1);
                fragmentTransaction.commit();
                break;
            case R.id.action_home :
                fragmentTransaction.replace(R.id.Main_Frame,menu_bottomNavi_fragment_2);
                fragmentTransaction.commit();
                break;
            case R.id.action_list :
                fragmentTransaction.replace(R.id.Main_Frame,menu_bottomNavi_fragment_3);
                fragmentTransaction.commit();
                break;
        }
        return;
    }

}
