package com.sangnv3.nhom2_duan1_f_food.Frag_User;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.sangnv3.nhom2_duan1_f_food.Dao.SanPham_DAO;
import com.sangnv3.nhom2_duan1_f_food.Model.SanPham;
import com.sangnv3.nhom2_duan1_f_food.R;

import java.util.ArrayList;

public class Frag_user_trangchu extends Fragment {
    RecyclerView mrecyclerView;
    ArrayList<SanPham> list;
    SanPham_DAO sanPhamDao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_user_trangchu, container, false);
        mrecyclerView = view.findViewById(R.id.id_recyFood);

        sanPhamDao = new SanPham_DAO(getContext());
        list = sanPhamDao.getSP();

        return view;
    }
}
