package Adapter;

import java.util.List;

import Model.LopHoc;
import com.example.assignment.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Spinneradapter extends ArrayAdapter<LopHoc>{

	public Spinneradapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public Spinneradapter(Context context, int resource, List<LopHoc> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.item_show_class, null);
        }

        LopHoc p = (LopHoc) getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.it_idclass);


            if (tt1 != null) {
                tt1.setText(p.getMaLop());
            }




        }

        return v;
    }
}
