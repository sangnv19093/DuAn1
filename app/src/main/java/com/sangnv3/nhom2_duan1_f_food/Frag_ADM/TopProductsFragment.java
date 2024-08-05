package com.sangnv3.nhom2_duan1_f_food.Frag_ADM;//package chienncph29246.fpoly.example.nhom2_duan1_happyfoo.Frag_ADM;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.Dao.SanPham_DAO;
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.Model.SanPham;
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.R;
//
//public class TopProductsFragment extends Fragment {
//
//    private ListView listView;
//    private ArrayAdapter<String> adapter;
//    private List<String> productList;
//    private SanPham_DAO sanPhamDAO;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_top_products, container, false);
//
//        // Khởi tạo DAO để truy vấn cơ sở dữ liệu
//        sanPhamDAO = new SanPhamDAO(getContext());
//
//        // Ánh xạ các thành phần UI
//        listView = view.findViewById(R.id.listView_top_products);
//
//        // Khởi tạo danh sách sản phẩm
//        productList = new ArrayList<>();
//
//        // Lấy danh sách top 10 sản phẩm bán chạy nhất từ cơ sở dữ liệu
//        List<SanPham> topProducts = sanPhamDAO.getTop10SanPhamBanNhieuNhat();
//
//        // Chuyển đổi danh sách sản phẩm thành danh sách chuỗi để hiển thị trên ListView
//        for (SanPham sanPham : topProducts) {
//            String productInfo = sanPham.getMaSP() + ": " + sanPham.getTenSP(); // Thay đổi cách hiển thị nếu cần
//            productList.add(productInfo);
//        }
//
//        // Khởi tạo và thiết lập adapter cho ListView
//        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, productList);
//        listView.setAdapter(adapter);
//
//        return view;
//    }
//}
