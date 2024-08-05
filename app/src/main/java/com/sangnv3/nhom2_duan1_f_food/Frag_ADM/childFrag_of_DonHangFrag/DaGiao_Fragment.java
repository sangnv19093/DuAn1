package com.sangnv3.nhom2_duan1_f_food.Frag_ADM.childFrag_of_DonHangFrag;

import android.annotation.SuppressLint;
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


public class DaGiao_Fragment extends Fragment {
    private RecyclerView ryc_daGiao;
    private HoaDonChiTietDAO hoaDonChiTietDAO;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_da_giao, container, false);
        ryc_daGiao = view.findViewById(R.id.ryc_daGiao);
        loadData(ryc_daGiao);
        return view;
    }
    private void loadData(RecyclerView ryc_daGiao){
        hoaDonChiTietDAO = new HoaDonChiTietDAO(getContext());
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("User", Context.MODE_PRIVATE);
        int nguoiDung_id = sharedPreferences.getInt("maUser", -1);
        ArrayList<HoaDonChiTiet> list = hoaDonChiTietDAO.getDonHangByHDCT(3,nguoiDung_id);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        ryc_daGiao.setLayoutManager(linearLayoutManager);
        DonHangAdapter adapter = new DonHangAdapter(getContext(),list);
        ryc_daGiao.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData(ryc_daGiao);
    }
}
