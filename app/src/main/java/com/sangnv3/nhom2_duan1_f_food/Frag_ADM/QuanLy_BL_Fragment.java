package com.sangnv3.nhom2_duan1_f_food.Frag_ADM;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.sangnv3.nhom2_duan1_f_food.Adapter.QL_BinhLuan_Adapter;
import com.sangnv3.nhom2_duan1_f_food.Dao.BinhLuanDAO;
import com.sangnv3.nhom2_duan1_f_food.Model.BinhLuan;
import com.sangnv3.nhom2_duan1_f_food.R;

import java.util.ArrayList;


public class QuanLy_BL_Fragment extends Fragment {
    ArrayList<BinhLuan> list = new ArrayList<>();
    RecyclerView recyclerView;

    public QuanLy_BL_Fragment() {
        // Required empty public constructor
    }


    public static QuanLy_BL_Fragment newInstance() {
        QuanLy_BL_Fragment fragment = new QuanLy_BL_Fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quan_ly__b_l_, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycQL_BinhLuan);
        // Tạo instance của Adapter


        loatDate();

    }


    public void loatDate() {
        BinhLuanDAO binhLuanDAO = new BinhLuanDAO(getContext());
        ArrayList<BinhLuan> list = binhLuanDAO.getDSBinhLuanCach1();
        QL_BinhLuan_Adapter adapter = new QL_BinhLuan_Adapter(getContext(), list, new QL_BinhLuan_Adapter.OnitemClick() {
            @Override
            public void onItemClickDelete(int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Thông Báo !");
                builder.setMessage("Bạn Có Muốn Xoá Không");
                builder.setNegativeButton("Huỷ", null).setPositiveButton("Xoá", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                                    BinhLuanDAO binhLuanDAO = new BinhLuanDAO(getContext());
                                    int postion = list.get(position).getBinhLuan_id();
                                    binhLuanDAO.xoaBinhLuanTheoBinhLuan_id(postion);
                                    loatDate();
                                    Toast.makeText(getContext(), "Xoá Thành Công", Toast.LENGTH_SHORT).show();

                                    dialog.dismiss();

                    }
                });



                Dialog dialog = builder.create();
                dialog.show();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        loatDate();
    }


}