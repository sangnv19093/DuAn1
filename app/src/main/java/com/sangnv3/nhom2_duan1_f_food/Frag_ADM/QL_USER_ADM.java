package com.sangnv3.nhom2_duan1_f_food.Frag_ADM;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sangnv3.nhom2_duan1_f_food.Adapter.NguoiDungAdapter;
import com.sangnv3.nhom2_duan1_f_food.Dao.NguoiDungDAO;
import com.sangnv3.nhom2_duan1_f_food.Model.NguoiDung;
import com.sangnv3.nhom2_duan1_f_food.R;

import java.util.ArrayList;

public class QL_USER_ADM extends Fragment {
    RecyclerView rc_ql_user_adm;
    NguoiDungAdapter nguoiDungAdapter;
    NguoiDungDAO nguoiDungDAO;
    ArrayList<NguoiDung> list;
    private Uri selectedImageUri;
    private static final int PICK_IMAGE_REQUEST = 1;
    ImageView imvUser;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_ql_user_adm, container, false);
        rc_ql_user_adm = view.findViewById(R.id.rc_ql_user_adm);

        nguoiDungDAO = new NguoiDungDAO(getContext());
        Load();

        view.findViewById(R.id.fab_User).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialogAddNguoiDung();
            }
        });
        return view;
    }
    private void Load(){
        list = nguoiDungDAO.getDsNguoiDung();
        nguoiDungAdapter = new NguoiDungAdapter(getContext(), list);
        rc_ql_user_adm.setLayoutManager(new LinearLayoutManager(getContext()));
        rc_ql_user_adm.setAdapter(nguoiDungAdapter);
    }
    private void ShowDialogAddNguoiDung() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = ((Activity)getContext()).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_tk_qlndung,null);


        builder.setView(view);

        EditText  edtAddname = view.findViewById(R.id.edtName_Add_qlnd);
        EditText  edtAddemail = view.findViewById(R.id.edtEmail_U_qlnd);
        EditText  edtAddPhone = view.findViewById(R.id.edtPhoneNumber_U_qlnd);
        EditText  edtAddUserName = view.findViewById(R.id.edtUsername_U_qlnd);
        EditText  edtAddPass = view.findViewById(R.id.edtPassword_U_qlnd);
        EditText edtLoaiTK = view.findViewById(R.id.edtLoaiTK);
        imvUser = view.findViewById(R.id.imgAvatar_U_qlnd);

        imvUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(intent, 1);
                openGallery();
            }
        });

        edtLoaiTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] phanloai = {"0", "1"};
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                builder1.setIcon(R.drawable.thongbao);
                builder1.setTitle("Chọn phân loại sản phẩm: 0 là User, 1 là Admin ");
                builder1.setItems(phanloai, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edtLoaiTK.setText(phanloai[which]);
                    }
                });
                AlertDialog alertDialog = builder1.create();
                alertDialog.show();
            }
        });


        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                String imgSrc = selectedImageUri != null ? selectedImageUri.toString() : "avatar_mac_dinh";
                String imgSrc = String.valueOf(selectedImageUri);
                String edtAddName = edtAddname.getText().toString();
                String edtAddEmail = edtAddemail.getText().toString();
                String edtAddphone = edtAddPhone.getText().toString();
                String edtAdduserName = edtAddUserName.getText().toString();
                String edtAddpass = edtAddPass.getText().toString();
                String edtLTK =  edtLoaiTK.getText().toString();

                if (validateForm(imgSrc, edtAddName, edtAddphone, edtAddEmail, edtAdduserName, edtAddpass)) {
                    NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(getContext());
                    NguoiDung nguoiDung = new NguoiDung(imgSrc, edtAddName, edtAddphone, edtAddEmail, edtAdduserName, edtAddpass,Integer.parseInt(edtLTK), 0);
                    if(nguoiDungDAO.themTaiKhoan(nguoiDung)){

                        Toast.makeText(getContext(), "Thêm tài khoản thành công!", Toast.LENGTH_SHORT).show();
                        // Xóa danh sách người dùng hiện tại
                        list.clear();
                        // Cập nhật danh sách người dùng sau khi thêm mới
                        list.addAll(nguoiDungDAO.getDsNguoiDung());
                        // Thông báo cho adapter rằng dữ liệu đã thay đổi
                        reloadData();


                    }else {
                        Toast.makeText(getContext(), "Thêm tài khoản thất bại, hãy kiểm tra lại!", Toast.LENGTH_SHORT).show();
                    }
                }


            }

        });



        Dialog dialog = builder.create();
        dialog.show();

    }
    public boolean validateForm(String selectedImageUri, String name, String phoneNumber, String email, String username, String password) {
        NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(getContext());
        boolean checkTonTai = nguoiDungDAO.checkTaiKhoanTonTai(username);
        if (name.isEmpty() || phoneNumber.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            Toast.makeText(getContext(), "Không được bỏ trống!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.endsWith("@gmail.com")) {
            Toast.makeText(getContext(), "Email không hợp lệ!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!Patterns.PHONE.matcher(phoneNumber).matches()) {
            Toast.makeText(getContext(), "Số điện thoại không hợp lệ!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (selectedImageUri == null) {
            Toast.makeText(getContext(), "Vui lòng thêm ảnh của bạn", Toast.LENGTH_SHORT).show();
            return false;
        } else if (checkTonTai) {
            Toast.makeText(getContext(), "Tên tài khoản đã tồn tại!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    public void reloadData() {
        // Xóa danh sách người dùng hiện tại
        list.clear();
        // Lấy lại danh sách người dùng từ database
        list.addAll(nguoiDungDAO.getDsNguoiDung());
        // Thông báo cho adapter rằng dữ liệu đã thay đổi
        rc_ql_user_adm.getAdapter().notifyDataSetChanged();
    }
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            imvUser.setImageURI(selectedImageUri);
        }
    }



}

