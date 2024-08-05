package com.sangnv3.nhom2_duan1_f_food.Frag_ADM;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sangnv3.nhom2_duan1_f_food.R;

public class QL_DH_ADM extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_ql_donhang_adm, container, false);
        return view;
    }
}
