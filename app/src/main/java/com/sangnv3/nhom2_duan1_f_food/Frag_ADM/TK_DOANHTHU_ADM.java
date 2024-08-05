//package chienncph29246.fpoly.example.nhom2_duan1_happyfoo.Frag_ADM;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Locale;
//
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.Dao.ThongKeDAO;
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.R;
//
//public class TK_DOANHTHU_ADM extends Fragment {
//    private EditText editNgayBatDau, editNgayKetThuc;
//    private Button btnTinhDoanhThu;
//    private TextView txtDoanhThuThangNay, txtDoanhThuThangTruoc;
//
//    private ThongKeDAO thongKeDAO;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.frag_tk_doanhthu_adm, container, false);
//
//        editNgayBatDau = view.findViewById(R.id.edit_ngay_bat_dau);
//        editNgayKetThuc = view.findViewById(R.id.edit_ngay_ket_thuc);
//        btnTinhDoanhThu = view.findViewById(R.id.btn_tinh_doanh_thu);
//        txtDoanhThuThangNay = view.findViewById(R.id.txt_doanh_thu_thang_nay);
//        txtDoanhThuThangTruoc = view.findViewById(R.id.txt_doanh_thu_thang_truoc);
//
//        thongKeDAO = new ThongKeDAO(getContext());
//
//        btnTinhDoanhThu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String ngayBatDau = editNgayBatDau.getText().toString();
//                String ngayKetThuc = editNgayKetThuc.getText().toString();
//
//                // Kiểm tra xem các trường nhập liệu có rỗng không
//                if (ngayBatDau.isEmpty() || ngayKetThuc.isEmpty()) {
//                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ ngày bắt đầu và ngày kết thúc", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                // Chuyển đổi chuỗi ngày thành định dạng Date
//                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
//                Date startDate, endDate;
//                try {
//                    startDate = sdf.parse(ngayBatDau);
//                    endDate = sdf.parse(ngayKetThuc);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                    Toast.makeText(getContext(), "Định dạng ngày không hợp lệ", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                // Lấy doanh thu theo tháng
//                int doanhThuThangNay = thongKeDAO.getTongTienTheoThang(startDate, endDate);
//                txtDoanhThuThangNay.setText("Doanh thu từ " + ngayBatDau + " đến " + ngayKetThuc + ": " + doanhThuThangNay + " VNĐ");
//
//
//            }
//        });
//
//        return view;
//    }
//}

package com.sangnv3.nhom2_duan1_f_food.Frag_ADM;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sangnv3.nhom2_duan1_f_food.Dao.ThongKeDAO;
import com.sangnv3.nhom2_duan1_f_food.R;

import java.util.Calendar;

public class TK_DOANHTHU_ADM extends Fragment {
    private Button btnNgayBatDau, btnNgayKetThuc;
    private Button btnTinhDoanhThu;
    private TextView txtDoanhThuThangNay;

    private ThongKeDAO thongKeDAO;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_tk_doanhthu_adm, container, false);

        btnNgayBatDau = view.findViewById(R.id.btn_ngay_bat_dau);
        btnNgayKetThuc = view.findViewById(R.id.btn_ngay_ket_thuc);
        btnTinhDoanhThu = view.findViewById(R.id.btn_tinh_doanh_thu);
        txtDoanhThuThangNay = view.findViewById(R.id.txt_doanh_thu_thang_nay);

        thongKeDAO = new ThongKeDAO(getContext());

        btnNgayBatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(btnNgayBatDau);
            }
        });

        btnNgayKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(btnNgayKetThuc);
            }
        });

        btnTinhDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ngayBatDau = btnNgayBatDau.getText().toString();
                String ngayKetThuc = btnNgayKetThuc.getText().toString();

                // Kiểm tra xem các trường đã chọn ngày chưa
                if (ngayBatDau.equals("Chọn ngày") || ngayKetThuc.equals("Chọn ngày")) {
                    Toast.makeText(getContext(), "Vui lòng chọn ngày bắt đầu và kết thúc", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Tiến hành tính toán và hiển thị doanh thu
                // ...
            }
        });

        return view;
    }

    private void showDatePickerDialog(final Button button) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        button.setText(selectedDate);
                    }
                },
                year, month, dayOfMonth);
        datePickerDialog.show();
    }
}

