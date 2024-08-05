package com.sangnv3.nhom2_duan1_f_food.Adapter;


import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.sangnv3.nhom2_duan1_f_food.Dao.SanPham_DAO;
import com.sangnv3.nhom2_duan1_f_food.Interface.ItemClickListener;
import com.sangnv3.nhom2_duan1_f_food.Model.SanPham;
import com.sangnv3.nhom2_duan1_f_food.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SanPham_ADT extends RecyclerView.Adapter<SanPham_ADT.SanPhamViewHolder> {
    Context context;
    ArrayList<SanPham> list_sp;
    SanPham_DAO sanPhamDao;
    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener listener){
        this.itemClickListener = listener;
    }

    public SanPham_ADT(Context context, ArrayList<SanPham> list_sp, SanPham_DAO sanPhamDao) {
        this.context = context;
        this.list_sp = list_sp;
        this.sanPhamDao = sanPhamDao;
    }

    @NonNull
    @Override
    public SanPhamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sp_adm,parent, false);
        return new SanPhamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamViewHolder holder, @SuppressLint("RecyclerView") int position) {
        SanPham sanPham = list_sp.get(position);
        Picasso.get().load(sanPham.getAnhSP()).into(holder.imv_anhsp);
        holder.tv_idsp.setText("Mã sản phẩm: " +sanPham.getMaSP()+"");
        holder.tv_tensp.setText("Tên sản phẩm: " +sanPham.getTenSP());
        holder.tv_phanloai.setText("Phân loại: " +sanPham.getPhanloaiSP());
        holder.tv_trongluong.setText("Trọng lượng: " +sanPham.getTrongluongSP());
        holder.tv_giasp.setText("Giá: " +sanPham.getGiaSP());
        holder.tv_mota.setText("Mô tả: " +sanPham.getMota());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                try {
                    if (itemClickListener != null){
                        itemClickListener.UpdateItem(position);
                    }
                } catch (Exception e){
                    Log.e(TAG, "item sản phẩm" +e);
                }
                return false;
            }
        });

        holder.imv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDE(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_sp.size();
    }

    public class SanPhamViewHolder extends RecyclerView.ViewHolder{
        ImageView imv_anhsp, imv_delete;
        TextView tv_idsp, tv_tensp, tv_phanloai, tv_trongluong, tv_giasp, tv_mota;
        public SanPhamViewHolder(@NonNull View itemView) {
            super(itemView);
            imv_anhsp = itemView.findViewById(R.id.imv_anhsp);
            imv_delete = itemView.findViewById(R.id.imv_delete);
            tv_idsp = itemView.findViewById(R.id.tv_idsp);
            tv_tensp = itemView.findViewById(R.id.tv_tensp);
            tv_phanloai = itemView.findViewById(R.id.tv_phanloai);
            tv_trongluong = itemView.findViewById(R.id.tv_trongluong);
            tv_giasp = itemView.findViewById(R.id.tv_giasp);
            tv_mota = itemView.findViewById(R.id.tv_mota);
        }
    }
    public void showDE(int position){
        SanPham sanPham = list_sp.get(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.thongbao);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có chắc chắn muốn xóa " + sanPham.getTenSP() + " không ?");
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try{
                    sanPhamDao = new SanPham_DAO(context);
                    if (sanPhamDao.xoaSP(sanPham)){
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        list_sp.remove(position);
                        notifyItemRemoved(position);
                    } else {
                        Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
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
}
