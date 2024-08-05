package com.sangnv3.nhom2_duan1_f_food.Frag_User;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sangnv3.nhom2_duan1_f_food.R;


public class Frag_user_giohang extends Fragment {



    public Frag_user_giohang() {
        // Required empty public constructor
    }

    public static Frag_user_giohang newInstance(String param1, String param2) {
        Frag_user_giohang fragment = new Frag_user_giohang();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_frag_user_giohang, container, false);
    }
}