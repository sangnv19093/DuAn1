package com.sangnv3.nhom2_duan1_f_food.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sangnv3.nhom2_duan1_f_food.Dao.NguoiDungDAO;
import com.sangnv3.nhom2_duan1_f_food.Model.NguoiDung;
import com.sangnv3.nhom2_duan1_f_food.R;


public class DangKyTaiKhoanActivity extends AppCompatActivity {
    private ImageView imgAvatar_frame3, imv_back;
    private EditText  edtName_frame3, edtPhoneNumber_frame3, edtEmail_frame3, edtUsername_frame3, edtPassword_frame3;
    private Button btnSignUp_frame3;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri selectedImageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_tai_khoan);
        imgAvatar_frame3 = findViewById(R.id.imgAvatar_frame3);
        imv_back = findViewById(R.id.imv_back);
        edtName_frame3 = findViewById(R.id.edtName_frame3);
        edtPhoneNumber_frame3 = findViewById(R.id.edtPhoneNumber_frame3);
        edtEmail_frame3 = findViewById(R.id.edtEmail_frame3);
        edtUsername_frame3 = findViewById(R.id.edtUsername_frame3);
        edtPassword_frame3 = findViewById(R.id.edtPassword_frame3);
        btnSignUp_frame3 = findViewById(R.id.btnSignUp_frame3);

        btnSignUp_frame3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themTaiKhoan();
            }
        });

        //Mở thư viện ảnh và thêm ảnh
        imgAvatar_frame3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        imv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangKyTaiKhoanActivity.this, DangNhapActivity.class));
            }
        });
    }

    public void themTaiKhoan() {
        String imgSrc = String.valueOf(selectedImageUri);
        String hoTen = edtName_frame3.getText().toString();
        String soDienThoai = edtPhoneNumber_frame3.getText().toString();
        String email = edtEmail_frame3.getText().toString();
        String taiKhoan = edtUsername_frame3.getText().toString();
        String matKhau = edtPassword_frame3.getText().toString();

        if (validateForm(selectedImageUri, hoTen, soDienThoai, email, taiKhoan, matKhau)) {
            NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(this);
            NguoiDung nguoiDung = new NguoiDung(imgSrc, hoTen, soDienThoai, email, taiKhoan, matKhau, 0, 0);
            if(nguoiDungDAO.themTaiKhoan(nguoiDung)){
                Toast.makeText(this, "Thêm tài khoản thành công!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DangKyTaiKhoanActivity.this, DangNhapActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(this, "Thêm tài khoản thất bại, hãy kiểm tra lại!", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            imgAvatar_frame3.setImageURI(selectedImageUri);
        }
    }
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    public boolean validateForm(Uri selectedImageUri, String name, String phoneNumber, String email, String username, String password) {
        NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(getApplicationContext());
        boolean checkTonTai = nguoiDungDAO.checkTaiKhoanTonTai(username);
        if (name.isEmpty() || phoneNumber.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Không được bỏ trống!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.endsWith("@gmail.com")) {
            Toast.makeText(this, "Email không hợp lệ!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!Patterns.PHONE.matcher(phoneNumber).matches()) {
            Toast.makeText(this, "Số điện thoại không hợp lệ!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (selectedImageUri == null) {
            Toast.makeText(this, "Vui lòng thêm ảnh của bạn", Toast.LENGTH_SHORT).show();
            return false;
        } else if (checkTonTai) {
            Toast.makeText(this, "Tên tài khoản đã tồn tại!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    //Tham khảo code này để thay đổi loaiTaiKhoan danh cho quyền admin
    public void setDataSpnTypeAccount_frame3(Spinner spinner) {
        String[] data = {"Customer", "Admin"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}