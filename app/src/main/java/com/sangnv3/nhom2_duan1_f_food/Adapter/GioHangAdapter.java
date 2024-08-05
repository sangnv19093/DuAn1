package com.sangnv3.nhom2_duan1_f_food.Adapter;


import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.sangnv3.nhom2_duan1_f_food.Dao.GioHangDAO;
import com.sangnv3.nhom2_duan1_f_food.Dao.SanPham_DAO;
import com.sangnv3.nhom2_duan1_f_food.Model.GioHang;
import com.sangnv3.nhom2_duan1_f_food.Model.SanPham;
import com.sangnv3.nhom2_duan1_f_food.R;
import com.sangnv3.nhom2_duan1_f_food.activity.GioHangActivity;
import com.squareup.picasso.Picasso;

import java.util.List;


public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder> {
    List<GioHang> list;
    List<GioHang> listMuaHang;
    GioHangActivity context;
    GioHangDAO gioHangDAO;
    SharedPreferences sharedPreferences;
    SanPham_DAO sanPhamDAO;
    int nguoiDung_id;
    int price;
    public GioHangAdapter(List<GioHang> list, GioHangActivity context) {
        this.list = list;
        this.context = context;
                gioHangDAO = new GioHangDAO( context);
        sharedPreferences = context.getSharedPreferences("User", Context.MODE_PRIVATE);
        nguoiDung_id = sharedPreferences.getInt("maUser", -1);
        sanPhamDAO = new SanPham_DAO(context);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_gio_hang, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GioHang gioHang = list.get(position);
//        String srcImg = list.get(position).getAnhSanPham();
//        int resourceId = context.getResources().getIdentifier(srcImg, "drawable", context.getPackageName());
//        if (resourceId != 0) {
//            Picasso.get().load(resourceId).into(holder.imgAnh_san_pham);
//        } else {
//            // Sử dụng hình ảnh mặc định hoặc không có gì
//            holder.imgAnh_san_pham.setImageResource(R.drawable.food); // Thay 'default_image' bằng ID của hình ảnh mặc định của bạn
//        }
        Picasso.get().load(list.get(position).getAnhSanPham()).into(holder.imgAnh_san_pham);

        holder.tvTen_san_pham.setText(list.get(position).getTenSanPham());
        holder.tvSo_luong_mua.setText(""+list.get(position).getSoLuong());
        holder.tvGia_san_pham.setText(""+list.get(position).getGiaSanPham());
        if(list.get(holder.getAdapterPosition()).getTrangThaiMua() == 0){
            holder.ckbMua_hang.setChecked(false);
        }else{
            holder.ckbMua_hang.setChecked(true);
        }
        holder.imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(R.drawable.thongbao);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có chắc chắn muốn xóa  không ?");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try{
                            if(gioHangDAO.xoaKhoiGioHang(list.get(holder.getAdapterPosition()).getSanPham_id(), list.get(holder.getAdapterPosition()).getNguoiDung_id()) > 0){
                                Toast.makeText(context, "Đã xóa khỏi giỏ hàng", Toast.LENGTH_SHORT).show();
                                list.remove(holder.getAdapterPosition());
                                notifyDataSetChanged();
                                price = 0;
                                for(GioHang gioHang1: list){
                                    if(gioHang1.getTrangThaiMua()==1){
                                        price += (gioHang1.getGiaSanPham()) * (gioHang1.getSoLuong());
                                    }
                                }
                                context.tvTotal.setText(""+price);
                            }else{
                                Toast.makeText(context, "Chưa xóa khỏi giỏ hàng", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e){
                            Log.e(TAG, "Lỗi khi xóa sản phẩm", e);
                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Hủy", null);
                builder.show();
            }
        });

        holder.imgPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SanPham sanPham = sanPhamDAO.getSanPham(list.get(holder.getAdapterPosition()).getSanPham_id());
//                if(Integer.parseInt(holder.tvSo_luong_mua.getText().toString()) == sanPham.get()
//                || sanPham.getSoLuongConLai() == 0){
//                    return;
//                }
                holder.tvSo_luong_mua.setText(""+(Integer.parseInt(holder.tvSo_luong_mua.getText().toString())+1));
                gioHang.setSoLuong(Integer.parseInt(holder.tvSo_luong_mua.getText().toString()));
                gioHangDAO.suaSoLuong(gioHang);
                    price = 0;
                    for(GioHang gioHang1: list){
                        if(gioHang1.getTrangThaiMua()==1){
                            price += (gioHang1.getGiaSanPham()) * (gioHang1.getSoLuong());
                        }
                }
                context.tvTotal.setText(""+price);
            }
        });

        holder.imgMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.tvSo_luong_mua.getText().toString().matches("1")){
                    return;
                }
                holder.tvSo_luong_mua.setText(""+(Integer.parseInt(holder.tvSo_luong_mua.getText().toString())-1));
                gioHang.setSoLuong(Integer.parseInt(holder.tvSo_luong_mua.getText().toString()));
                gioHangDAO.suaSoLuong(gioHang);
                price = 0;
                for(GioHang gioHang1: list){
                    if(gioHang1.getTrangThaiMua()==1){
                        price += (gioHang1.getGiaSanPham()) * (gioHang1.getSoLuong());
                    }
                }
                context.tvTotal.setText(""+price);
            }
        });

        holder.ckbMua_hang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SanPham sanPham = sanPhamDAO.getSanPham(list.get(holder.getAdapterPosition()).getSanPham_id());
//                if(sanPham.getSoLuongConLai() == 0){
//                    Toast.makeText(context, "Sản phẩm đã hết hàng", Toast.LENGTH_SHORT).show();
//                    holder.ckbMua_hang.setChecked(false);
//                    return;
//                }
                if(gioHang.getTrangThaiMua()==0){
                    gioHang.setTrangThaiMua(1);
                    gioHangDAO.suaTrangThaiMua(gioHang);
                    price = 0;
                    for(GioHang gioHang1: list){
                        if(gioHang1.getTrangThaiMua()==1){
                            price += (gioHang1.getGiaSanPham()) * (gioHang1.getSoLuong());
                        }
                    }
                    context.tvTotal.setText(""+price);
                }else{
                    gioHang.setTrangThaiMua(0);
                    gioHangDAO.suaTrangThaiMua(gioHang);
                    price = 0;
                    for(GioHang gioHang1: list){
                        if(gioHang1.getTrangThaiMua()==1){
                            price += (gioHang1.getGiaSanPham()) * (gioHang1.getSoLuong());
                        }
                    }
                    context.tvTotal.setText(""+price);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if(list != null)
            return list.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvSo_luong_mua, tvTen_san_pham, tvGia_san_pham;
        ImageView imgAnh_san_pham, imgMinus, imgPlus, imgCancel;
        CheckBox ckbMua_hang;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSo_luong_mua = itemView.findViewById(R.id.tvSo_luong_mua);
            tvTen_san_pham = itemView.findViewById(R.id.tvTen_san_pham);
            tvGia_san_pham = itemView.findViewById(R.id.tvGia_san_pham);
            imgAnh_san_pham = itemView.findViewById(R.id.imgAnh_san_pham);
            imgMinus = itemView.findViewById(R.id.imgMinus);
            imgPlus = itemView.findViewById(R.id.imgPlus);
            imgCancel = itemView.findViewById(R.id.imgCancel);
            ckbMua_hang = itemView.findViewById(R.id.ckbMua_hang);
        }
    }

    public void xoaSanPhamKhoiGioHang(int viTri, TextView tvSoluong){
        if(gioHangDAO.xoaKhoiGioHang(list.get(viTri).getSanPham_id(), list.get(viTri).getNguoiDung_id()) > 0){
            Toast.makeText(context, "Da xoa khoi gio hang", Toast.LENGTH_SHORT).show();
            price = 0;
            for(GioHang gioHang1: list){
                if(gioHang1.getTrangThaiMua()==1){
                    price += (gioHang1.getGiaSanPham()) * (gioHang1.getSoLuong());
                }
            }
            context.tvTotal.setText(""+price);
            list.remove(viTri);
            notifyDataSetChanged();
        }else{
            Toast.makeText(context, "Chua xoa khoi gio hang", Toast.LENGTH_SHORT).show();
        }
    }


}
