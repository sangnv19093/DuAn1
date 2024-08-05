package com.sangnv3.nhom2_duan1_f_food.Frag_ADM.childFrag_of_DonHangFrag;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sangnv3.nhom2_duan1_f_food.Adapter.DonHangAdapter;
import com.sangnv3.nhom2_duan1_f_food.Dao.HoaDonChiTietDAO;
import com.sangnv3.nhom2_duan1_f_food.Model.HoaDonChiTiet;
import com.sangnv3.nhom2_duan1_f_food.R;

import java.util.ArrayList;


public class DangGiao_Fragment extends Fragment {
    private RecyclerView ryc_dangGiao;
    private HoaDonChiTietDAO hoaDonChiTietDAO;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dang_giao, container, false);
        ryc_dangGiao = view.findViewById(R.id.ryc_dangGiao);
        loadData(ryc_dangGiao);
        return view;
    }
    private void loadData(RecyclerView ryc_dangGiao){
        hoaDonChiTietDAO = new HoaDonChiTietDAO(getContext());
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("User", Context.MODE_PRIVATE);
        int nguoiDung_id = sharedPreferences.getInt("maUser", -1);
        ArrayList<HoaDonChiTiet> list = hoaDonChiTietDAO.getDonHangByHDCT(2,nguoiDung_id);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        ryc_dangGiao.setLayoutManager(linearLayoutManager);
        DonHangAdapter adapter = new DonHangAdapter(getContext(),list);
        ryc_dangGiao.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData(ryc_dangGiao);
    }
}
