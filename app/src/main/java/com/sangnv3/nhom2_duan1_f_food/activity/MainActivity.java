//
//package chienncph29246.fpoly.example.nhom2_duan1_happyfoo.activity;
//
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.activity.EdgeToEdge;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.ActionBarDrawerToggle;
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.drawerlayout.widget.DrawerLayout;
//import androidx.fragment.app.Fragment;
//
//import com.google.android.material.navigation.NavigationView;
//
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.Frag_ADM.DonHang_Fragment;
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.Frag_ADM.HomeFragment;
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.Frag_ADM.K_DMK_ADM;
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.Frag_ADM.K_THEMUSER_ADM;
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.Frag_ADM.QL_DH_ADM;
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.Frag_ADM.QL_DonHang_Fragment;
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.Frag_ADM.QL_SP_ADM;
//
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.Frag_ADM.QL_USER_ADM;
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.Frag_ADM.TK_DOANHTHU_ADM;
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.Frag_ADM.TK_TOP10_ADM;
//
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.R;
//
//public class MainActivity extends AppCompatActivity {
//    DrawerLayout drawer_adm;
//    Toolbar toolbar_adm;
//    NavigationView navi_adm;
//
//    ActionBarDrawerToggle drawerToggle;
//    private ImageView imgAvatar_header, imgMuiTen_header;
//    int loaiTaiKhoan;
//    private TextView tvName_header, tvPhoneNumber_header, tvEmail_header;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);
//        drawer_adm = findViewById(R.id.drawerLayout_frame4);
//        toolbar_adm = findViewById(R.id.toolbar_frame4);
//        navi_adm = findViewById(R.id.navigationView_frame4);
//
//        View headerLayout = navi_adm.getHeaderView(0);
//        imgAvatar_header = headerLayout.findViewById(R.id.imgAvatar_header);
//        imgMuiTen_header = headerLayout.findViewById(R.id.imgMuiTen_header);
//        tvName_header = headerLayout.findViewById(R.id.tvName_header);
//        tvPhoneNumber_header = headerLayout.findViewById(R.id.tvPhoneNumber_header);
//        tvEmail_header = headerLayout.findViewById(R.id.tvEmail_header);
//
//
//        setSupportActionBar(toolbar_adm);
//        getSupportActionBar().setTitle("Happy Food");
//        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
//        loaiTaiKhoan = sharedPreferences.getInt("loaiTaiKhoan",-1);
//
//        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawer_adm, toolbar_adm, R.string.open, R.string.close);
//        drawerToggle.setDrawerIndicatorEnabled(true);
//        drawerToggle.syncState();
//        drawer_adm.addDrawerListener(drawerToggle);
//
//        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_frame4, new QL_SP_ADM()).commit();
//
//        navi_adm.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int itemId = item.getItemId();
//                showMenu(itemId);
//                return true;
//            }
//        });
//    }
//
//    private boolean showMenu(int itemId){
//        Fragment fragment = null;
//        String title = "";
//        try {
//            if(itemId == R.id.menu_home) {
//                fragment = new HomeFragment();
//                title = "San pham";
//            } else if(itemId == R.id.menu_donhang) {
//                fragment = new DonHang_Fragment();
//                title = "Đơn Hàng";
//            } else if(itemId == R.id.menu_ql_donhang) {
//                if(loaiTaiKhoan == 1) {
//                    fragment = new QL_DonHang_Fragment();
//                    title = "QL Đơn Hàng";
//                }else{
//
//                }
//            } else if(itemId == R.id.menu_ql_sanpham) {
//                if(loaiTaiKhoan == 1) {
//                    fragment = new QL_SP_ADM();
//                    title = "Quản lý sản phẩm";
//                }else{}
//            } else if(itemId == R.id.menu_ql_nguoidung) {
//                if(loaiTaiKhoan == 1) { // Phân quyền chỉ hiển thị cho admin
//                    fragment = new QL_USER_ADM();
//                    title = "Quản lý người dùng";
//                } else {
//                    // Xử lý khi người dùng không có quyền truy cập
//                    // Hiển thị thông báo hoặc chuyển hướng tùy theo yêu cầu
//                }
//            } else if(itemId == R.id.menu_ql_donhang) {
//                if(loaiTaiKhoan == 1) {
//                    fragment = new QL_DH_ADM();
//                    title = "Quản lý đơn hàng";
//                }else{
//
//                }
//            } else if(itemId == R.id.menu_tk_top10) {
//                fragment = new TK_TOP10_ADM();
//                title = "Top 10 sản phẩm";
//            } else if(itemId == R.id.menu_tk_doanhthu) {
//                fragment = new TK_DOANHTHU_ADM();
//                title = "Doanh thu";
//            } else if(itemId == R.id.menu_nd_themnd) {
//                fragment = new K_THEMUSER_ADM();
//                title = "Thêm người dùng";
//            } else if(itemId == R.id.menu_nd_dmk) {
//                fragment = new K_DMK_ADM();
//                title = "Đổi mật khẩu";
//            } else if(itemId == R.id.menu_nd_exit) {
//                showExit();
//                return true;
//            }
//
//            if (fragment != null) {
//                drawer_adm.close();
//                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_frame4, fragment).commit();
//                getSupportActionBar().setTitle(title);
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        return  true;
//    }
//
//    private void showExit(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//        builder.setTitle("Exit !");
//        builder.setIcon(R.drawable.thongbao);
//        builder.setMessage("Bạn muốn đăng xuất ?");
//        builder.setCancelable(false);
//
//        builder.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                startActivity(new Intent(MainActivity.this, DangNhapActivity.class));
//                finish();
//            }
//        });
//        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        AlertDialog dialog =builder.create();
//        dialog.show();
//    }
//}
//
package com.sangnv3.nhom2_duan1_f_food.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;
import com.sangnv3.nhom2_duan1_f_food.Frag_ADM.DonHang_Fragment;
import com.sangnv3.nhom2_duan1_f_food.Frag_ADM.HomeFragment;
import com.sangnv3.nhom2_duan1_f_food.Frag_ADM.K_THEMUSER_ADM;
import com.sangnv3.nhom2_duan1_f_food.Frag_ADM.QL_DonHang_Fragment;
import com.sangnv3.nhom2_duan1_f_food.Frag_ADM.QL_SP_ADM;
import com.sangnv3.nhom2_duan1_f_food.Frag_ADM.QL_USER_ADM;
import com.sangnv3.nhom2_duan1_f_food.Frag_ADM.QuanLy_BL_Fragment;
import com.sangnv3.nhom2_duan1_f_food.Frag_ADM.ThongKeADMFragment;
import com.sangnv3.nhom2_duan1_f_food.Frag_ADM.ThongKeFragment;
import com.sangnv3.nhom2_duan1_f_food.R;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer_adm;
    Toolbar toolbar_adm;
    NavigationView navi_adm;
    private Fragment fragment;
    private DrawerLayout drawerLayout_frame4;
    private NavigationView navigationView_frame4;

    ActionBarDrawerToggle drawerToggle;
    private ImageView imgAvatar_header, imgMuiTen_header;
    private FragmentManager fragmentManager;
    int loaiTaiKhoan;
    private TextView tvName_header, tvPhoneNumber_header, tvEmail_header;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        drawer_adm = findViewById(R.id.drawerLayout_frame4);
        toolbar_adm = findViewById(R.id.toolbar_frame4);
        navi_adm = findViewById(R.id.navigationView_frame4);

        View headerLayout = navi_adm.getHeaderView(0);
        imgAvatar_header = headerLayout.findViewById(R.id.imgAvatar_header);
        imgMuiTen_header = headerLayout.findViewById(R.id.imgMuiTen_header);
        tvName_header = headerLayout.findViewById(R.id.tvName_header);
        tvPhoneNumber_header = headerLayout.findViewById(R.id.tvPhoneNumber_header);
        tvEmail_header = headerLayout.findViewById(R.id.tvEmail_header);


        setSupportActionBar(toolbar_adm);
        getSupportActionBar().setTitle("Happy Food");
        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        loaiTaiKhoan = sharedPreferences.getInt("loaiTaiKhoan",-1);



        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawer_adm, toolbar_adm, R.string.open, R.string.close);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
        drawer_adm.addDrawerListener(drawerToggle);

        fragmentManager = getSupportFragmentManager();
        fragment = new HomeFragment();
        fragmentManager.beginTransaction().replace(R.id.frameLayout_frame4, fragment).commit();


        if (loaiTaiKhoan != 1){
            navi_adm.getMenu().findItem(R.id.menu_ql_sanpham).setVisible(false);
            navi_adm.getMenu().findItem(R.id.menu_ql_donhang).setVisible(false);
            navi_adm.getMenu().findItem(R.id.menu_ql_doanhthu).setVisible(false);
            navi_adm.getMenu().findItem(R.id.menu_ql_binhluan).setVisible(false);
            navi_adm.getMenu().findItem(R.id.menu_ql_nguoidung).setVisible(false);
        }

//        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_frame4, new HomeFragment()).commit();
        imgMuiTen_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChiTietNguoiDung.class);
                startActivity(intent);
            }
        });
        navi_adm.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                showMenu(itemId);
                return true;
            }
        });
    }

    private boolean showMenu(int itemId){
        Fragment fragment = null;
        String title = "";
        try {
            if (itemId == R.id.menu_home) {
                fragment = new HomeFragment();
                title = "San pham";
            } else if (itemId == R.id.menu_donhang) {
                fragment = new DonHang_Fragment();
                title = "Đơn Hàng";
            }
            else if (itemId == R.id.menu_thongke) {
                fragment = new ThongKeFragment();
                title = "Thống Kê";
            }else if (itemId == R.id.menu_ql_sanpham) {
                if (loaiTaiKhoan == 1) {
                    fragment = new QL_SP_ADM();
                    title = "Quản lý sản phẩm";
                } else {
                    Toast.makeText(this, "Chỉ dành cho Admin", Toast.LENGTH_SHORT).show();
                }
            } else if (itemId == R.id.menu_ql_nguoidung) {
                if (loaiTaiKhoan == 1) { // Phân quyền chỉ hiển thị cho admin
                    fragment = new QL_USER_ADM();
                    title = "Quản lý người dùng";
                } else {
                    Toast.makeText(this, "Chỉ dành cho Admin", Toast.LENGTH_SHORT).show();
                }
            } else if (itemId == R.id.menu_ql_donhang) {
                if (loaiTaiKhoan == 1) {
                    fragment = new QL_DonHang_Fragment();
                    title = "Quản lý đơn hàng";
                } else {
                    Toast.makeText(this, "Chỉ dành cho Admin", Toast.LENGTH_SHORT).show();
                }
            }else if (itemId == R.id.menu_ql_binhluan) {
                if (loaiTaiKhoan == 1) {
                    fragment = new QuanLy_BL_Fragment();
                    title = "Quản lý Bình Luận";
                } else {
                    Toast.makeText(this, "Chỉ dành cho Admin", Toast.LENGTH_SHORT).show();
                }
            }
            else if (itemId == R.id.menu_ql_doanhthu) {
                if (loaiTaiKhoan == 1) { // Phân quyền chỉ hiển thị cho admin
                    fragment = new ThongKeADMFragment();
                    title = "Quản lý Doanh Thu";
                } else {
                    Toast.makeText(this, "Chỉ dành cho Admin", Toast.LENGTH_SHORT).show();
                }
            } else if (itemId == R.id.menu_nd_themnd) {
                fragment = new K_THEMUSER_ADM();
                title = "Liên Hệ";
            } else if (itemId == R.id.menu_nd_exit) {
                showExit();
                return true;
            }

            if (fragment != null) {
                drawer_adm.close();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_frame4, fragment).commit();
                getSupportActionBar().setTitle(title);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }


    private void showExit(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Exit !");
        builder.setIcon(R.drawable.thongbao);
        builder.setMessage("Bạn muốn đăng xuất ?");
        builder.setCancelable(false);

        builder.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(MainActivity.this, DangNhapActivity.class));
                finish();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
