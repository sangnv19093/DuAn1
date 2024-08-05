package com.sangnv3.nhom2_duan1_f_food.Main;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.sangnv3.nhom2_duan1_f_food.Frag_User.Frag_user_donhang;
import com.sangnv3.nhom2_duan1_f_food.Frag_User.Frag_user_giohang;
import com.sangnv3.nhom2_duan1_f_food.Frag_User.Frag_user_taikhoan;
import com.sangnv3.nhom2_duan1_f_food.Frag_User.Frag_user_trangchu;
import com.sangnv3.nhom2_duan1_f_food.R;

public class MainUser extends AppCompatActivity {
    DrawerLayout drawerLayout;
    FrameLayout frameLayout;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_user);
        drawerLayout = findViewById(R.id.drawer_user);
        frameLayout = findViewById(R.id.frame_user);
        bottomNavigationView = findViewById(R.id.btn_navi);




        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                int item = menuItem.getItemId() ;
                try {
                    if ( item == R.id.menu_user_home ){
                        fragment = new Frag_user_trangchu();
                    } else if ( item == R.id.menu_user_giohang ) {
                        fragment = new Frag_user_giohang();
                    } else if (item == R.id.menu_user_donhang) {
                        fragment = new Frag_user_donhang();
                    } else if (item == R.id.menu_user_thongtin) {
                        fragment = new Frag_user_taikhoan();
                    }
                    if (fragment != null){
//                        drawerLayout.close();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_user, fragment).commit();
                    }
                    return true;
                } catch (Exception e){
                    e.printStackTrace();
                }
                return false;
            }
        });
    }


}