package in.solocrew.cms.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

import in.solocrew.cms.AddDetailsActivity;
import in.solocrew.cms.R;
import in.solocrew.cms.appdb.Details;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.VH> {

    private Context context;
    private List<Details>list;
    private String last_date = "";

    public DetailsAdapter(Context context, List<Details> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.lay_details,parent,false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int p) {
        Details s = list.get(p);
        h.shop_name.setText(s.getShop_name());
        h.date.setText(s.getDateFormat());
        h.box_yet_to_return.setText(context.getString(R.string.no_of_empty_boxes_yet_to_return)
                +" : "+s.getNo_of_box_yet_to_return()+"");

        if(last_date.isEmpty() || !last_date.equalsIgnoreCase(s.getDateOnly())){
            h.date_tag.setVisibility(View.VISIBLE);
            h.date_tag.setText(s.getDateFormat());
            last_date = s.getDateOnly();
        }
        else if(last_date.equalsIgnoreCase(s.getDateOnly())){
            h.date_tag.setVisibility(View.GONE);
        }

        h.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, AddDetailsActivity.class).putExtra("id",list.get(p).getId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<Details> details) {
        this.list = details;
        notifyDataSetChanged();
    }

    public class VH extends RecyclerView.ViewHolder {
        private TextView shop_name,box_yet_to_return,date,date_tag;
        private ConstraintLayout layout;

        public VH(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout);
            shop_name = itemView.findViewById(R.id.textView4);
            box_yet_to_return = itemView.findViewById(R.id.textView16);
            date = itemView.findViewById(R.id.textView5);
            date_tag = itemView.findViewById(R.id.textView17);
        }
    }
}
