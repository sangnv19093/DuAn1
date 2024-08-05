package com.sangnv3.nhom2_duan1_f_food.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sangnv3.nhom2_duan1_f_food.Dao.GioHangDAO;
import com.sangnv3.nhom2_duan1_f_food.Dao.SanPham_DAO;
import com.sangnv3.nhom2_duan1_f_food.Model.GioHang;
import com.sangnv3.nhom2_duan1_f_food.Model.SanPham;
import com.sangnv3.nhom2_duan1_f_food.R;
import com.sangnv3.nhom2_duan1_f_food.activity.ChiTietSanPhamActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SanPhamAdapter1 extends RecyclerView.Adapter<SanPhamAdapter1.MyViewHolder> {
    private Context context;
    private ArrayList<SanPham> list;


    public SanPhamAdapter1(Context context, ArrayList<SanPham> list) {
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_san_pham2, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        String srcImg = list.get(position).getAnhSP();

        // Kiểm tra xem ảnh có phải là đường dẫn URI hay không
        boolean isUri = srcImg.startsWith("content://");


            // Nếu là đường dẫn URI, sử dụng Picasso để tải ảnh từ đường dẫn URI
            Picasso.get().load(srcImg).into(holder.imgSanPham_itemGrid);
//        } else {
//            // Nếu không phải là đường dẫn URI, sử dụng cách khác để hiển thị ảnh (ví dụ: từ nguồn drawable)
//            int resourceId = context.getResources().getIdentifier(srcImg, "drawable", context.getPackageName());
//            holder.imgSanPham_itemGrid.setImageResource(resourceId);
//        }
        double giaSanPham = list.get(position).getGiaSP();
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        holder.tvGiaSanPham_itemGrid.setText(String.valueOf(list.get(position).getGiaSP()) + " vnd");
        holder.imgGioHang_itemGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Chưa code chức năng mua hàng", Toast.LENGTH_SHORT).show();
            }
        });
        holder.tv_tensp.setText(list.get(position).getTenSP());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SanPham sanPham = list.get(holder.getAdapterPosition());
                Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("sanPham",sanPham);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        holder.imgGioHang_itemGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonMua(list.get(holder.getAdapterPosition()).getMaSP());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list.size() != 0) {
            return list.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imgSanPham_itemGrid, imgGioHang_itemGrid;
        TextView tvGiaSanPham_itemGrid, tv_tensp;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgSanPham_itemGrid = itemView.findViewById(R.id.imgSanPham_itemGrid);
            imgGioHang_itemGrid = itemView.findViewById(R.id.imgGioHang_itemGrid);
            tvGiaSanPham_itemGrid = itemView.findViewById(R.id.tvGiaSanPham_itemGrid);
            tv_tensp = itemView.findViewById(R.id.tv_tensp);
            cardView = itemView.findViewById(R.id.cardView);
        }

    }
//    public void chonMua(int sanPham_id){
//        SanPham_DAO sanPhamDAO = new SanPham_DAO(context);
//        SanPham sanPham = sanPhamDAO.getSanPham(sanPham_id);
//
//        GioHangDAO gioHangDAO = new GioHangDAO(context);
//        GioHang gioHang = new GioHang();
//        SharedPreferences sharedPreferences = context.getSharedPreferences("User",context.MODE_PRIVATE);
//        int getNguoiDung_id = sharedPreferences.getInt("maUser", 0);
//        gioHang.setNguoiDung_id(getNguoiDung_id);
//        gioHang.setSanPham_id(sanPham_id);
//        gioHang.setSoLuong(1);
//        gioHang.setTrangThaiMua(0);
//        if(gioHangDAO.themVaoGioHang(gioHang) > 0){
//            Toast.makeText(context, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
//            Log.d("SanPhamAdapter1", "Đã thêm vào giỏ hàng: " + gioHang.toString());
//
//        }else{
//            Toast.makeText(context, "Mặt hàng này đã có trong giỏ hàng", Toast.LENGTH_SHORT).show();
//        }
//    }
public void chonMua(int sanPham_id) {
    GioHangDAO gioHangDAO = new GioHangDAO(context);

    // Kiểm tra xem mặt hàng đã tồn tại trong giỏ hàng chưa
    ArrayList<GioHang> gioHangList = gioHangDAO.getDsGioHang(sanPham_id);
    if (gioHangList.size() > 0) {
        Toast.makeText(context, "Mặt hàng này đã có trong giỏ hàng", Toast.LENGTH_SHORT).show();
        return; // Thoát khỏi phương thức nếu mặt hàng đã tồn tại trong giỏ hàng
    }

    // Thêm mặt hàng vào giỏ hàng
    SanPham_DAO sanPhamDAO = new SanPham_DAO(context);
    SanPham sanPham = sanPhamDAO.getSanPham(sanPham_id);

    GioHang gioHang = new GioHang();
    SharedPreferences sharedPreferences = context.getSharedPreferences("User", context.MODE_PRIVATE);
    int userId = sharedPreferences.getInt("maUser", 0);

    gioHang.setNguoiDung_id(userId);
    gioHang.setSanPham_id(sanPham_id);
    gioHang.setSoLuong(1);
    gioHang.setTrangThaiMua(0);

    long result = gioHangDAO.themVaoGioHang(gioHang);
    if (result > 0) {
        Toast.makeText(context, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
        Log.d("SanPhamAdapter1", "Đã thêm vào giỏ hàng: " + gioHang.toString());
    } else {
        Toast.makeText(context, "Thêm vào giỏ hàng không thành công", Toast.LENGTH_SHORT).show();
    }
}

}
