package com.sangnv3.nhom2_duan1_f_food.Frag_ADM;
//
//import android.app.DatePickerDialog;
//import android.content.SharedPreferences;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.DatePicker;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
//
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.GregorianCalendar;
//
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.R;
//
//
//public class ThongKeFragment extends Fragment {
//    SharedPreferences sharedPreferences;
//    int nguoiDung_id;
//    TextView tvTong_tien, tvSo_tien_trong_khoang, tvTu_ngay, tvDen_ngay;
//    ThongKeDAO thongKeDAO;
//    BarChart barChart;
//    ArrayList list;
//    int mYear, mMonth, mDay;
//    EditText edTu_ngay, edDen_ngay;
//    LinearLayout layout;
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//    boolean check;
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//
//        return inflater.inflate(R.layout.fragment_thong_ke, container, false);
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        tvTong_tien = view.findViewById(R.id.tvTong_tien);
//        tvTu_ngay = view.findViewById(R.id.tvTu_ngay);
//        tvDen_ngay = view.findViewById(R.id.tvDen_ngay);
//        tvSo_tien_trong_khoang = view.findViewById(R.id.tvSo_tien_trong_khoang);
//        edTu_ngay = view.findViewById(R.id.edTu_ngay);
//        edDen_ngay = view.findViewById(R.id.edDen_ngay);
//        layout = view.findViewById(R.id.linear);
//        barChart = view.findViewById(R.id.barChart);
//        thongKeDAO = new ThongKeDAO(getContext());
//        sharedPreferences = getContext().getSharedPreferences("NGUOIDUNG", getContext().MODE_PRIVATE);
//        nguoiDung_id = sharedPreferences.getInt("nguoiDung_id", -1);
//        tvTong_tien.setText(""+thongKeDAO.getSoTienDaMua(nguoiDung_id)+" vnd");
//
//        muaHangTrongKhoang();
//
//        getData();
//        BarDataSet barDataSet = new BarDataSet(list,"Hàng tháng");
//        BarData barData = new BarData(barDataSet);
//        barChart.setData(barData);
//        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
//        barDataSet.setValueTextColor(Color.BLACK);
//        barDataSet.setValueTextSize(18f);
//        barChart.getDescription().setEnabled(true);
//
//    }
//
//    private void getData(){
//        list = new ArrayList();
//        Calendar calendar = Calendar.getInstance();
//        int year = calendar.get(Calendar.YEAR);
//        list.add(new BarEntry(1f, thongKeDAO.getDataInMonth(nguoiDung_id, 1, year)));
//        list.add(new BarEntry(2f, thongKeDAO.getDataInMonth(nguoiDung_id, 2, year)));
//        list.add(new BarEntry(3f, thongKeDAO.getDataInMonth(nguoiDung_id, 3, year)));
//        list.add(new BarEntry(4f, thongKeDAO.getDataInMonth(nguoiDung_id, 4, year)));
//        list.add(new BarEntry(5f, thongKeDAO.getDataInMonth(nguoiDung_id, 5, year)));
//        list.add(new BarEntry(6f, thongKeDAO.getDataInMonth(nguoiDung_id, 6, year)));
//        list.add(new BarEntry(7f, thongKeDAO.getDataInMonth(nguoiDung_id, 7, year)));
//        list.add(new BarEntry(8f, thongKeDAO.getDataInMonth(nguoiDung_id, 8, year)));
//        list.add(new BarEntry(9f, thongKeDAO.getDataInMonth(nguoiDung_id, 9, year)));
//        list.add(new BarEntry(10f, thongKeDAO.getDataInMonth(nguoiDung_id, 10, year)));
//        list.add(new BarEntry(11f, thongKeDAO.getDataInMonth(nguoiDung_id, 11, year)));
//        list.add(new BarEntry(12f, thongKeDAO.getDataInMonth(nguoiDung_id, 12, year)));
//    }
//
//    private void muaHangTrongKhoang(){
//        edTu_ngay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Calendar c = Calendar.getInstance();
//                mDay = c.get(Calendar.DAY_OF_MONTH);
//                mMonth = c.get(Calendar.MONTH);
//                mYear = c.get(Calendar.YEAR);
//                DatePickerDialog d = new DatePickerDialog(getContext(), 0, tuNgay, mYear, mMonth, mDay);
//                d.show();
//            }
//        });
//
//        edDen_ngay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Calendar c = Calendar.getInstance();
//                mDay = c.get(Calendar.DAY_OF_MONTH);
//                mMonth = c.get(Calendar.MONTH);
//                mYear = c.get(Calendar.YEAR);
//                DatePickerDialog d = new DatePickerDialog(getContext(), 0, denNgay, mYear, mMonth, mDay);
//                d.show();
//            }
//        });
//
//
//
//    }
//
//    DatePickerDialog.OnDateSetListener tuNgay = new DatePickerDialog.OnDateSetListener() {
//        @Override
//        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//            mDay = dayOfMonth;
//            mMonth = month;
//            mYear = year;
//            GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
//            edTu_ngay.setText(sdf.format(c.getTime()));
//            check = true;
//            if(edTu_ngay.getText().length() != 0 || edDen_ngay.getText().length() != 0){
//                layout.setVisibility(View.VISIBLE);
//                tvTu_ngay.setText(edTu_ngay.getText().toString());
//                tvDen_ngay.setText(edDen_ngay.getText().toString());
//                int tienTrongkhoang = thongKeDAO.tuNgayDenNgay(edTu_ngay.getText().toString(), edDen_ngay.getText().toString(), nguoiDung_id);
//                if(tienTrongkhoang > 0){
//                    tvSo_tien_trong_khoang.setText(""+tienTrongkhoang+" vnd");
//                }else{
//                    tvSo_tien_trong_khoang.setText("0_0");
//                }
//            }
//        }
//    };
//
//    DatePickerDialog.OnDateSetListener denNgay = new DatePickerDialog.OnDateSetListener() {
//        @Override
//        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//            mDay = dayOfMonth;
//            mMonth = month;
//            mYear = year;
//            GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
//            edDen_ngay.setText(sdf.format(c.getTime()));
//            if(check){
//                if(edTu_ngay.getText().length() != 0 || edDen_ngay.getText().length() != 0){
//                    layout.setVisibility(View.VISIBLE);
//                    tvTu_ngay.setText(edTu_ngay.getText().toString());
//                    tvDen_ngay.setText(edDen_ngay.getText().toString());
//                    int tienTrongkhoang = thongKeDAO.tuNgayDenNgay(edTu_ngay.getText().toString(), edDen_ngay.getText().toString(), nguoiDung_id);
//                    if(tienTrongkhoang > 0){
//                        tvSo_tien_trong_khoang.setText(""+tienTrongkhoang+" vnd");
//                    }else{
//                        tvSo_tien_trong_khoang.setText("0_0");
//                    }
//                }
//            }else{
//                edDen_ngay.setText("");
//            }
//
//        }
//    };
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        muaHangTrongKhoang();
//    }
//}



import android.app.DatePickerDialog;
import android.content.SharedPreferences;
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

public class ThongKeFragment extends Fragment {

    private EditText edTuNgay, edDenNgay;
    private Button btnTimKiem;
    private TextView tvSoTienTrongKhoang;
    private SimpleDateFormat dateFormat;
    private ThongKeDAO thongKeDAO;

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
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("User", getContext().MODE_PRIVATE);
        int nguoiDungId = sharedPreferences.getInt("maUser", -1); // -1 là giá trị mặc định nếu không tìm thấy ID


        // Kiểm tra xem ngày có hợp lệ không
        if (tuNgay != null && denNgay != null) {
            // Lấy tổng tiền từ cơ sở dữ liệu
            int tongTien = thongKeDAO.getTongTienTheoThang(tuNgay, denNgay,nguoiDungId);
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

