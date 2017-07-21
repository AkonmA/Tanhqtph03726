package Adapter;

import java.util.ArrayList;
import java.util.List;

import Database.Database;
import Model.LopHoc;
import Model.SinhVien;

import com.example.assignment.MainActivity;
import com.example.assignment.R;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AdapterSinhVien extends ArrayAdapter<SinhVien>{
//    private List<SinhVien> items;
    private Context context;
    private Database database;
    private ArrayList<SinhVien> items;

//	public AdapterSinhVien(Context context, int textViewResourceId) {
//		super(context, textViewResourceId);
//		// TODO Auto-generated constructor stub
//	}

	public AdapterSinhVien(Context context, int resource, ArrayList<SinhVien> items) {
        super(context, resource, items);

        this.context = context;
        this.items = items;

        database = new Database(context);
        items.addAll(database.ShowSinhVien());
    }

	@Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.item_show_student, null);
        }

        final SinhVien sinhVien = items.get(position);

        if (sinhVien != null) {
            setData(v, sinhVien);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateStudent(sinhVien, position);
                }
            });

            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    deleteStudent(sinhVien, position);
                    return true;
                }
            });
        }

        return v;
    }

    /*
    * Khởi tạo view và hiển thị dữ liệu cho item
    * */
    private void setData(View v, SinhVien p) {
        TextView tt1 = (TextView) v.findViewById(R.id.it_idclass);
        TextView tt2 = (TextView) v.findViewById(R.id.it_idstudent);
        TextView tt3 = (TextView) v.findViewById(R.id.it_namestudent);

        if (tt1 != null) {
            tt1.setText(p.getMaLop());
        }

        if (tt2 != null) {
            tt2.setText(p.getMaSinhVien());
        }

        if (tt3 != null) {
            tt3.setText(p.getTenSinhVien());
        }
    }

    /*
    * Xóa sinh viên
    * Check xóa thành công và thông báo cho người dùng
    * */
    private void deleteStudent(SinhVien sinhVien, int position) {
        if (database.deleteStudent(String.valueOf(sinhVien.getId()))) {
            showShortToast("Xóa sinh viên thành công");
            items.remove(position);
            notifyDataSetChanged();
        } else {
            showShortToast("Xóa sinh viên thất bại");
        }
    }

    /*
    * Hiển thị dialog cho người dùng cập nhật
    * Check cập nhật thành công và thông báo cho người dùng
    * */
    private void updateStudent(final SinhVien sinhVien, final int position) {
        final Dialog dialogsv = new Dialog(context);
        dialogsv.setContentView(R.layout.layout_dialog_themsinhvien);

        Spinner spinner = (Spinner) dialogsv.findViewById(R.id.sp_addst_id);
        spinner.setVisibility(View.GONE);

        final EditText masv = (EditText) dialogsv.findViewById(R.id.ed_dialog_addst_id);
        final EditText tensv = (EditText) dialogsv.findViewById(R.id.ed_dialog_addst_name);

        Button btnclear = (Button) dialogsv.findViewById(R.id.btn_dialog_addst_clear);
        Button btnadd = (Button) dialogsv.findViewById(R.id.btn_dialog_addst_add);

        masv.setText(sinhVien.getMaSinhVien());
        tensv.setText(sinhVien.getTenSinhVien());

        btnclear.setText("Hủy");
        btnadd.setText("Cập nhật");

        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dialogsv.dismiss();
            }
        });

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sinhVien.setMaSinhVien(masv.getText().toString());
                sinhVien.setTenSinhVien(tensv.getText().toString());

                if (database.updateStudent(sinhVien)) {
                    showShortToast("Cập nhật sinh viên thành công");
                    items.remove(position);
                    items.add(position, sinhVien);
                    notifyDataSetChanged();
                } else {
                    showShortToast("Cập nhật sinh viên thất bại");
                }

                dialogsv.dismiss();
            }
        });
        dialogsv.show();
    }

    /*
    * Hiển thị thông báo ngắn
    * */
    private void showShortToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}