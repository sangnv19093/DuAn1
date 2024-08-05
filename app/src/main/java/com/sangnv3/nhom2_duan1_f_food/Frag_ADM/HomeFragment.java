//package chienncph29246.fpoly.example.nhom2_duan1_happyfoo.Frag_ADM;
//
//import android.annotation.SuppressLint;
//import android.app.SearchManager;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.widget.SearchView;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.android.material.textfield.TextInputEditText;
//
//import java.text.Normalizer;
//import java.util.ArrayList;
//import java.util.regex.Pattern;
//
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.Adapter.SanPhamAdapter1;
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.Dao.SanPham_DAO;
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.Model.SanPham;
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.R;
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.activity.GioHangActivity;
//
//public class HomeFragment extends Fragment {
//    private RecyclerView recyclerView_frame4;
//    private SanPham_DAO sanPhamDAO;
//    private ImageView giohang,home;
//    ArrayList<SanPham> listtemporary;
//    ArrayList<SanPham> oriList;
//    private TextInputEditText edTimkiem;
//    private SanPhamAdapter1 searchResultsAdapter;
//
//    @SuppressLint("MissingInflatedId")
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_home, container, false);
//
//        giohang = view.findViewById(R.id.giohang);
//        home=view.findViewById(R.id.home);
//        recyclerView_frame4 = view.findViewById(R.id.recyclerView_frame4);
//        sanPhamDAO = new SanPham_DAO(getContext());
//        edTimkiem = view.findViewById(R.id.edTimKiem);
//
//        oriList = sanPhamDAO.getSP();
//
//        ImageView searchButton = view.findViewById(R.id.imgTimKiem);
//        searchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onSearchButtonClick();
//            }
//        });
//
//        giohang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getContext(), GioHangActivity.class));
//            }
//        });
//        home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//
//        loadDataGridLayout(recyclerView_frame4);
//        return view;
//
//    }
//
//
//    public void loadDataGridLayout(RecyclerView recyclerView) {
//        int numColumns = 2;
//        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numColumns));
//        ArrayList<SanPham> list = sanPhamDAO.getSP();
//        SanPhamAdapter1 adapter = new SanPhamAdapter1(getContext(), list);
//        recyclerView.setAdapter(adapter);
//    }
//
//    public void onSearchButtonClick() {
//        String searchText = edTimkiem.getText().toString().toLowerCase();
//
//        ArrayList<SanPham> searchResults = new ArrayList<>();
//        for (SanPham sanPham : oriList) {
//            String tenSanPham = sanPham.getTenSP().toLowerCase();
//            String normalizedTenSanPham = normalizeString(tenSanPham);
//
//            if (tenSanPham.contains(searchText) || normalizedTenSanPham.contains(searchText)) {
//                searchResults.add(sanPham);
//            }
//        }
//
//        SanPhamAdapter1 searchAdapter = new SanPhamAdapter1(getContext(), searchResults);
//        recyclerView_frame4.setAdapter(searchAdapter);
//    }
//
//    private String normalizeString(String input) {
//        String normalizedString = Normalizer.normalize(input, Normalizer.Form.NFD);
//        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
//        return pattern.matcher(normalizedString).replaceAll("").toLowerCase();
//    }
//
//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        inflater.inflate(R.menu.timkiem, menu);
//        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
//        MenuItem menuItem = menu.findItem(R.id.idmenu);
//        SearchView searchView = (SearchView) menuItem.getActionView();
//        searchView.setIconified(true);
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
//        searchView.setMaxWidth(Integer.MAX_VALUE);
//
//        listtemporary = sanPhamDAO.getSP();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                oriList.clear();
//                for (SanPham sanPham : listtemporary) {
//                    String tenSanPham = sanPham.getTenSP().toLowerCase();
//                    String normalizedTenSanPham = normalizeString(tenSanPham);
//
//                    if (tenSanPham.contains(newText) || normalizedTenSanPham.contains(newText)) {
//                        oriList.add(sanPham);
//                    }
//                }
//
//                SanPhamAdapter1 adapter = new SanPhamAdapter1(getContext(), oriList);
//                recyclerView_frame4.setAdapter(adapter);
//                return true;
//            }
//        });
//
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        setHasOptionsMenu(true);
//        super.onCreate(savedInstanceState);
//    }
//}
//
package com.sangnv3.nhom2_duan1_f_food.Frag_ADM;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.sangnv3.nhom2_duan1_f_food.Adapter.SanPhamAdapter1;
import com.sangnv3.nhom2_duan1_f_food.Dao.SanPham_DAO;
import com.sangnv3.nhom2_duan1_f_food.Model.SanPham;
import com.sangnv3.nhom2_duan1_f_food.R;
import com.sangnv3.nhom2_duan1_f_food.activity.GioHangActivity;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView_frame4;
    private SanPham_DAO sanPhamDAO;
    private ImageView giohang, home;
    ArrayList<SanPham> listtemporary;
    ArrayList<SanPham> oriList;
    private TextInputEditText edTimkiem;
    private SanPhamAdapter1 searchResultsAdapter;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        giohang = view.findViewById(R.id.giohang);
        home = view.findViewById(R.id.home);
        recyclerView_frame4 = view.findViewById(R.id.recyclerView_frame4);
        sanPhamDAO = new SanPham_DAO(getContext());
        edTimkiem = view.findViewById(R.id.edTimKiem);

        oriList = sanPhamDAO.getSP();

        ImageView searchButton = view.findViewById(R.id.imgTimKiem);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearchButtonClick();
            }
        });

        giohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), GioHangActivity.class));
            }
        });
//        home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Reload fragment
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
//            }
//        });

        loadDataGridLayout(recyclerView_frame4);
        return view;
    }


    public void loadDataGridLayout(RecyclerView recyclerView) {
        int numColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numColumns));
        ArrayList<SanPham> list = sanPhamDAO.getSP();
        SanPhamAdapter1 adapter = new SanPhamAdapter1(getContext(), list);
        recyclerView.setAdapter(adapter);
    }

    public void onSearchButtonClick() {
        String searchText = edTimkiem.getText().toString().toLowerCase();

        ArrayList<SanPham> searchResults = new ArrayList<>();
        for (SanPham sanPham : oriList) {
            String tenSanPham = sanPham.getTenSP().toLowerCase();
            String normalizedTenSanPham = normalizeString(tenSanPham);

            if (tenSanPham.contains(searchText) || normalizedTenSanPham.contains(searchText)) {
                searchResults.add(sanPham);
            }
        }

        SanPhamAdapter1 searchAdapter = new SanPhamAdapter1(getContext(), searchResults);
        recyclerView_frame4.setAdapter(searchAdapter);
    }

    private String normalizeString(String input) {
        String normalizedString = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalizedString).replaceAll("").toLowerCase();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.timkiem, menu);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        MenuItem menuItem = menu.findItem(R.id.idmenu);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setIconified(true);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        listtemporary = sanPhamDAO.getSP();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                oriList.clear();
                for (SanPham sanPham : listtemporary) {
                    String tenSanPham = sanPham.getTenSP().toLowerCase();
                    String normalizedTenSanPham = normalizeString(tenSanPham);

                    if (tenSanPham.contains(newText) || normalizedTenSanPham.contains(newText)) {
                        oriList.add(sanPham);
                    }
                }

                SanPhamAdapter1 adapter = new SanPhamAdapter1(getContext(), oriList);
                recyclerView_frame4.setAdapter(adapter);
                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }
}
