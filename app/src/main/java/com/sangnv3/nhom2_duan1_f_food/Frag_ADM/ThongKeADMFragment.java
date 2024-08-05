package com.sangnv3.nhom2_duan1_f_food.Frag_ADM;



import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sangnv3.nhom2_duan1_f_food.Dao.ThongKeDAO;
import com.sangnv3.nhom2_duan1_f_food.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ThongKeADMFragment extends Fragment {

    private EditText edTuNgay, edDenNgay;
    private Button btnTimKiem;
    private TextView tvSoTienTrongKhoang,tvtile;
    private SimpleDateFormat dateFormat;
    private ThongKeDAO thongKeDAO;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_ke, container, false);

        thongKeDAO = new ThongKeDAO(getContext());

        // Ánh xạ view từ layout XML
        edTuNgay = view.findViewById(R.id.edTu_ngay);
        edDenNgay = view.findViewById(R.id.edDen_ngay);
        btnTimKiem = view.findViewById(R.id.btnTimKiem);
        tvSoTienTrongKhoang = view.findViewById(R.id.tvTong_tien);
        tvtile=view.findViewById(R.id.tvtile);

        tvtile.setText("DOANH THU :");

        // Khởi tạo đối tượng SimpleDateFormat
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        // Thiết lập sự kiện click cho trường nhập liệu "Từ ngày"
        edTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hiển thị hộp thoại chọn ngày khi nhấn vào trường "Từ ngày"
                showDatePickerDialog(edTuNgay);
            }
        });

        // Thiết lập sự kiện click cho trường nhập liệu "Đến ngày"
        edDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hiển thị hộp thoại chọn ngày khi nhấn vào trường "Đến ngày"
                showDatePickerDialog(edDenNgay);
            }
        });

        // Thiết lập sự kiện click cho nút tìm kiếm
        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gọi phương thức updateData() khi nhấn nút tìm kiếm
                updateData();
            }
        });

        return view;
    }

    // Phương thức để hiển thị hộp thoại chọn ngày
    private void showDatePickerDialog(final EditText editText) {
        // Lấy ngày hiện tại
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // Tạo hộp thoại chọn ngày
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // Thiết lập ngày được chọn vào trường nhập liệu
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, month, dayOfMonth);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                editText.setText(sdf.format(selectedDate.getTime()));
            }
        }, year, month, dayOfMonth);

        // Hiển thị hộp thoại chọn ngày
        datePickerDialog.show();
    }

//    private void updateData() {
//        try {
//            // Lấy ngày từ EditText và chuyển đổi sang định dạng Date
//            Date tuNgay = dateFormat.parse(edTuNgay.getText().toString());
//            Date denNgay = dateFormat.parse(edDenNgay.getText().toString());
//
//            // Kiểm tra xem ngày có hợp lệ không
//            if (tuNgay != null && denNgay != null) {
//                int tongTien = thongKeDAO.getTongTienTheoThang(tuNgay, denNgay);
//                tvSoTienTrongKhoang.setText(String.valueOf(tongTien));
//
//            } else {
//                Toast.makeText(getContext(), "Vui lòng chọn ngày hợp lệ", Toast.LENGTH_SHORT).show();
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//            Toast.makeText(getContext(), "Đã xảy ra lỗi khi đọc ngày", Toast.LENGTH_SHORT).show();
//        }
//    }
private void updateData() {
    try {
        // Lấy ngày từ EditText và chuyển đổi sang định dạng Date
        Date tuNgay = dateFormat.parse(edTuNgay.getText().toString());
        Date denNgay = dateFormat.parse(edDenNgay.getText().toString());
        // Lấy ID của người dùng từ SharedPreferences



        // Kiểm tra xem ngày có hợp lệ không
        if (tuNgay != null && denNgay != null) {
            // Lấy tổng tiền từ cơ sở dữ liệu
            int tongTien = thongKeDAO.getTongTienTheoThanga(tuNgay, denNgay);
            // Hiển thị tổng tiền lên TextView
            tvSoTienTrongKhoang.setText(String.valueOf(tongTien));

            // Log ra tổng tiền
            Log.d("ThongKeFragment", "Tổng tiền trong khoảng: " + tongTien);
        } else {
            Toast.makeText(getContext(), "Vui lòng chọn ngày hợp lệ", Toast.LENGTH_SHORT).show();
        }
    } catch (ParseException e) {
        e.printStackTrace();
        Toast.makeText(getContext(), "Đã xảy ra lỗi khi đọc ngày", Toast.LENGTH_SHORT).show();
    }
}

}

