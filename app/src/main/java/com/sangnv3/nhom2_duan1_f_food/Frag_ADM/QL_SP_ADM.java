package com.sangnv3.nhom2_duan1_f_food.Frag_ADM;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sangnv3.nhom2_duan1_f_food.Adapter.SanPham_ADT;
import com.sangnv3.nhom2_duan1_f_food.Dao.SanPham_DAO;
import com.sangnv3.nhom2_duan1_f_food.Interface.ItemClickListener;
import com.sangnv3.nhom2_duan1_f_food.Model.SanPham;
import com.sangnv3.nhom2_duan1_f_food.R;

import java.util.ArrayList;

public class QL_SP_ADM extends Fragment {
    RecyclerView rc_ql_sp_adm;
    SanPham_DAO sanPhamDao;
    SanPham_ADT sanPhamAdt;
    ArrayList<SanPham> list_sp = new ArrayList<>();
    EditText edt_masp, edt_anhsp, edt_tensp, edt_trongluong, edt_giasp, edt_phanloai, edt_mota;
    public static final String TAG = "ql_sp";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_ql_sp_adm, container, false);
        rc_ql_sp_adm = view.findViewById(R.id.rc_ql_sp_adm);

        sanPhamDao = new SanPham_DAO(getContext());
        LoadData();

        view.findViewById(R.id.fab_SanPham).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showThemorSua(getContext(),0, null);
            }

        });
        sanPhamAdt.setItemClickListener(new ItemClickListener() {
            @Override
            public void UpdateItem(int position) {
                SanPham sanPham = list_sp.get(position);
                showThemorSua(getContext(), 1, sanPham);
            }
        });
        return view;
    }

    private void showThemorSua(Context context, int type, SanPham sanPham){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view1 = LayoutInflater.from(context).inflate(R.layout.dialog_add_sp, null);
        builder.setView(view1);
        AlertDialog dialog = builder.create();

        edt_masp = view1.findViewById(R.id.edt_masp);
        edt_anhsp = view1.findViewById(R.id.edt_anhsp);
        edt_tensp = view1.findViewById(R.id.edt_tensp);
        edt_phanloai = view1.findViewById(R.id.edt_phanloai);
        edt_trongluong = view1.findViewById(R.id.edt_trongluong);
        edt_giasp = view1.findViewById(R.id.edt_giasp);
        edt_mota = view1.findViewById(R.id.edt_mota);

        edt_masp.setEnabled(false);

        edt_phanloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] phanloai = {"Đồ ăn", "Nước Uống"};
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                builder1.setIcon(R.drawable.thongbao);
                builder1.setTitle("Chọn phân loại sản phẩm: ");
                builder1.setItems(phanloai, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edt_phanloai.setText(phanloai[which]);
                    }
                });
                AlertDialog alertDialog = builder1.create();
                alertDialog.show();
            }
        });

        if (type != 0 ){
            edt_masp.setText(sanPham.getMaSP()+"");
            edt_anhsp.setText(sanPham.getAnhSP());
            edt_tensp.setText(sanPham.getTenSP());
            edt_phanloai.setText(sanPham.getPhanloaiSP());
            edt_trongluong.setText(sanPham.getTrongluongSP()+"");
            edt_giasp.setText(sanPham.getGiaSP()+"");
            edt_mota.setText(sanPham.getMota());
        }

        view1.findViewById(R.id.btnThem_themsp).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                String anh = edt_anhsp.getText().toString();
                String ten = edt_tensp.getText().toString();
                String ploai = edt_phanloai.getText().toString();
                String tluong = edt_trongluong.getText().toString();
                String gia = edt_giasp.getText().toString();
                String mota = edt_mota.getText().toString();

                if (anh.isEmpty() || ten.isEmpty() || ploai.isEmpty() || tluong.isEmpty() || gia.isEmpty() || mota.isEmpty() ){
                    Toast.makeText(getContext(), "Vui lòng không để trống thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if (type == 0){
                        SanPham sanPham1 = new SanPham();
                        sanPham1.setAnhSP(anh);
                        sanPham1.setTenSP(ten);
                        sanPham1.setPhanloaiSP(ploai);
                        sanPham1.setTrongluongSP(Integer.parseInt(tluong));
                        sanPham1.setGiaSP(Integer.parseInt(gia));
                        sanPham1.setMota(mota);
                        try {
                            if (sanPhamDao.themSP(sanPham1)){
                                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                                LoadData();
                                dialog.dismiss();
                            } else {
                                Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e){
                            Log.e(TAG, "Lỗi khi thao tác với cơ sở dữ liệu", e);
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else{
                        sanPham.setAnhSP(anh);
                        sanPham.setTenSP(ten);
                        sanPham.setPhanloaiSP(ploai);
                        sanPham.setTrongluongSP(Integer.parseInt(tluong));
                        sanPham.setGiaSP(Integer.parseInt(gia));
                        sanPham.setMota(mota);
                        try {
                            if (sanPhamDao.suaSP(sanPham)){
                                Toast.makeText(getContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                                LoadData();
                                dialog.dismiss();
                            } else {
                                Toast.makeText(getContext(), "Sửa thất bại", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e){
                            Log.e(TAG, "Lỗi khi thao tác với cơ sở dũ liệu", e);
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });




        view1.findViewById(R.id.btnHuy_themsp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
    private void LoadData(){
        list_sp = sanPhamDao.getSP();
        sanPhamAdt = new SanPham_ADT(getContext(), list_sp, sanPhamDao);
        rc_ql_sp_adm.setLayoutManager(new LinearLayoutManager(getContext()));
        rc_ql_sp_adm.setAdapter(sanPhamAdt);
    }
    @SuppressLint("NotifyDataSetChanged")
    public void updateList() {
        list_sp.clear();
        sanPhamAdt.notifyDataSetChanged();
    }
}
