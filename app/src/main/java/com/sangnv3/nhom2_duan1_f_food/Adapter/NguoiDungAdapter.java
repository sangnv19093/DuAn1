    package com.sangnv3.nhom2_duan1_f_food.Adapter;



    import static android.content.ContentValues.TAG;

    import android.annotation.SuppressLint;
    import android.app.Activity;
    import android.content.Context;
    import android.content.DialogInterface;
    import android.graphics.Bitmap;
    import android.graphics.BitmapShader;
    import android.graphics.Canvas;
    import android.graphics.Paint;
    import android.net.Uri;
    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.AdapterView;
    import android.widget.ImageView;
    import android.widget.TextView;
    import android.widget.Toast;

    import androidx.annotation.NonNull;
    import androidx.appcompat.app.AlertDialog;
    import androidx.recyclerview.widget.RecyclerView;

    import com.sangnv3.nhom2_duan1_f_food.Dao.NguoiDungDAO;
    import com.sangnv3.nhom2_duan1_f_food.Model.NguoiDung;
    import com.sangnv3.nhom2_duan1_f_food.R;
    import com.squareup.picasso.Picasso;
    import com.squareup.picasso.Transformation;

    import java.util.ArrayList;


    public class NguoiDungAdapter extends RecyclerView.Adapter<NguoiDungAdapter.MyViewHover> {

        private Context context;
        private Activity activity;
        private ArrayList<NguoiDung> list;
        private NguoiDungDAO nguoidungdao;

        private static final int REQUEST_CODE_GALLERY = 999;
        private Uri newImageUri;
        private int currentUserPosition;
        private AdapterView.OnItemClickListener mListener;

        public NguoiDungAdapter(Context context, ArrayList<NguoiDung> list, AdapterView.OnItemClickListener listener) {
            this.context = context;
            this.list = list;
            this.mListener = listener;
        }


        public void setActivity(Activity activity) {
            this.activity = activity;
        }
        public NguoiDungAdapter(Context context, ArrayList<NguoiDung> list) {
            this.context = context;
            this.list = list;
        }

        public void updateImageForSelectedUser(Uri selectedImageUri) {
            if (currentUserPosition != -1 && currentUserPosition < list.size() && selectedImageUri != null) {
                NguoiDung currentUser = list.get(currentUserPosition);
                currentUser.setImgSrc(selectedImageUri.toString());
                notifyItemChanged(currentUserPosition);
            }
        }
        private OnImagePickListener onImagePickListener;

        public interface OnImagePickListener {
            void onImagePick(Uri imageUri);
        }


        public void setOnImagePickListener(OnImagePickListener onImagePickListener) {
            this.onImagePickListener = onImagePickListener;
        }

        @NonNull
        @Override
        public NguoiDungAdapter.MyViewHover onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nguoi_dung, parent,false);
            return new MyViewHover(view);
        }



        @Override
        public void onBindViewHolder(@NonNull NguoiDungAdapter.MyViewHover holder, @SuppressLint("RecyclerView") int position) {
            if (list.get(position).getLoaiTaiKhoan() == 1){
                holder.txtLoaiNguoiDung.setText("Vai Trò : Admin");
            }else {holder.txtLoaiNguoiDung.setText("Vai Trò : Người Dùng");}
            String imgSrc = list.get(position).getImgSrc();
            boolean isUri = imgSrc.startsWith("content://");
            Picasso.get().load(Uri.parse(imgSrc)).transform(new CircleTransform()).into(holder.imgNguoiDung);

//            if (isUri) {
//                // Nếu là đường dẫn URI, sử dụng Picasso để tải ảnh từ đường dẫn URI
//                Picasso.get().load(Uri.parse(imgSrc)).transform(new CircleTransform()).into(holder.imgNguoiDung);
//            } else {
//                // Nếu không phải là đường dẫn URI, sử dụng cách khác để hiển thị ảnh (ví dụ: từ nguồn drawable)
//                int resourceId = context.getResources().getIdentifier(imgSrc, "drawable", context.getPackageName());
//                Picasso.get().load(resourceId).transform(new CircleTransform()).into(holder.imgNguoiDung);
//            }
            holder.txtTenNguoiDung.setText("Họ Và Tên : "+ list.get(position).getHoTen());
            holder.txtSDTNguoiDung.setText("Số Điện Thoại : "+ list.get(position).getSoDienThoai());
            holder.txtEmailNguoiDung.setText("Email : " + list.get(position).getEmail());
//            if (list.get(position).getIsXoaMem() == 1){
//                holder.txtIsXoaMem.setText("Trạng Thái :  Đã Xóa");
//            }else {holder.txtIsXoaMem.setText("Trạng Thái :  Hoạt Động");}
            holder.txtIsXoaMem.setText("Tài khoản: "+list.get(position).getTaiKhoan());
            holder.txtMatKhau.setText("Mật khẩu:" +list.get(position).getMatKhau());

            holder.imv_xoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NguoiDung nguoiDung = list.get(position);
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setIcon(R.drawable.thongbao);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn có chắc chắn muốn xóa " + nguoiDung.getNguoiDung_id() + " không ?");
                    builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try{
                                nguoidungdao = new NguoiDungDAO(context);
                                if (nguoidungdao.xoaNguoiDung(nguoiDung)){
                                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                    list.remove(position);
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
            });
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    mListener.onItemClick(null,view,position,view.getId());
//                }
//            });

        }



        @Override
        public int getItemCount() {
            if (list != null){
                return list.size();
            }
            return 0;
        }

        public  class MyViewHover extends RecyclerView.ViewHolder {


            TextView txtLoaiNguoiDung, txtTenNguoiDung, txtSDTNguoiDung, txtEmailNguoiDung, txtIsXoaMem, txtMatKhau;
            ImageView imgNguoiDung, imv_xoa;


            public MyViewHover(@NonNull View itemView) {
                super(itemView);
                txtLoaiNguoiDung = itemView.findViewById(R.id.idLoaiNguoiDung);
                txtTenNguoiDung = itemView.findViewById(R.id.idTenNguoiDung);
                txtSDTNguoiDung = itemView.findViewById(R.id.idSDTNguoiDung);
                txtEmailNguoiDung = itemView.findViewById(R.id.idEmailNguoiDung);
                txtIsXoaMem = itemView.findViewById(R.id.idIsXoaMem);
                txtMatKhau = itemView.findViewById(R.id.idmatKhau);
                imgNguoiDung = itemView.findViewById(R.id.imgNguoiDung);
                imv_xoa = itemView.findViewById(R.id.imv_del_nguoidung);


//                itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
//                        View view = inflater.inflate(R.layout.dialog_sua_tt_qlndung,null);
//                        builder.setView(view);
//                        EditText edtTen = view.findViewById(R.id.edtSuaTenNguoiDung);
//                        EditText edtSDT = view.findViewById(R.id.edtSuaSDTNguoiDung);
//                        EditText edtEmail = view.findViewById(R.id.edtSuaEmailNguoiDung);
//                        ImageView imgNguoiDung = view.findViewById(R.id.imgSuaNguoiDung);
//                        Spinner spinner_quyen = view.findViewById(R.id.Sp_Role_QlNDung);
//                        String[] roles = {"Admin", "Người Dùng"};
//                        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, roles);
//
//
//
//                        NguoiDung nguoiDung = list.get(getLayoutPosition());
//
//                        edtTen.setText(nguoiDung.getHoTen());
//                        edtSDT.setText(nguoiDung.getSoDienThoai());
//                        edtEmail.setText(nguoiDung.getEmail());
//
//                        String imgSrc = list.get(getLayoutPosition()).getImgSrc();
//
//                        // Kiểm tra xem ảnh có phải là đường dẫn URI hay không
//                        boolean isUri = imgSrc.startsWith("content://");
//
//                        if (isUri) {
//                            // Nếu là đường dẫn URI, sử dụng Picasso để tải ảnh từ đường dẫn URI
//                            Picasso.get().load(Uri.parse(imgSrc)).transform(new CircleTransform()).into(imgNguoiDung);
//                        } else {
//                            // Nếu không phải là đường dẫn URI, sử dụng cách khác để hiển thị ảnh (ví dụ: từ nguồn drawable)
//                            int resourceId = context.getResources().getIdentifier(imgSrc, "drawable", context.getPackageName());
//                            Picasso.get().load(resourceId).transform(new CircleTransform()).into(imgNguoiDung);
//                        }
//                        spinner_quyen.setAdapter(adapter);
//
//
//
//                        imgNguoiDung.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                currentUserPosition = getLayoutPosition();
//
//                                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                                if (onImagePickListener != null && intent.resolveActivity(context.getPackageManager()) != null) {
//                                    activity.startActivityForResult(intent, REQUEST_CODE_GALLERY);
//                                }
//                            }
//                        });
//
//                        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//
//
//                            }
//                        }).setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                String tenNDung = edtTen.getText().toString();
//                                String SDTNDung = edtSDT.getText().toString();
//                                String emailNDung = edtEmail.getText().toString();
//
//                                nguoidungdao = new NguoiDungDAO(context);
//
//
//                                nguoiDung.setHoTen(tenNDung);
//                                nguoiDung.setSoDienThoai(SDTNDung);
//                                nguoiDung.setEmail(emailNDung);
//                                if (nguoidungdao.thayDoiThonTinQL(nguoiDung) > 0){
//                                    Toast.makeText(context, "Sủa Thành Công", Toast.LENGTH_SHORT).show();
//                                    list.clear();
//                                    list = nguoidungdao.getDsNguoiDung();
//                                    notifyDataSetChanged();
//                                }else {
//                                    Toast.makeText(context, "Sửa Thất Bại", Toast.LENGTH_SHORT).show();
//                                }
//
//
//                            }
//
//                        });
//                        Dialog dialog = builder.create();
//                        dialog.show();
//                    }
//
//                });
//
//
//            }


            }
        }
        public static class CircleTransform implements Transformation {
            @Override
            public Bitmap transform(Bitmap source) {
                int size = Math.min(source.getWidth(), source.getHeight());
                int x = (source.getWidth() - size) / 2;
                int y = (source.getHeight() - size) / 2;

                Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
                if (squaredBitmap != source) {
                    source.recycle();
                }

                Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

                Canvas canvas = new Canvas(bitmap);
                Paint paint = new Paint();
                BitmapShader shader = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
                paint.setShader(shader);
                paint.setAntiAlias(true);

                float r = size / 2f;
                canvas.drawCircle(r, r, r, paint);

                squaredBitmap.recycle();
                return bitmap;
            }

            @Override
            public String key() {
                return "circle";
            }
        }
    }
