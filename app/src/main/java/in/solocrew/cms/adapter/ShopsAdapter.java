package in.solocrew.cms.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.solocrew.cms.AddShopsActivity;
import in.solocrew.cms.R;
import in.solocrew.cms.appdb.Shops;

public class ShopsAdapter extends RecyclerView.Adapter<ShopsAdapter.VH> {

    private Context context;
    private List<Shops>list;

    public ShopsAdapter(Context context, List<Shops> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.lay_shops,parent,false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int p) {
        Shops s = list.get(p);
        h.shop_name.setText(s.getName());
        h.contact_number.setText(s.getContact_number());

        if(s.getLocation().isEmpty())
            h.map.setVisibility(View.GONE);

        h.map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String geoUri = "http://maps.google.com/maps?q=loc:" +s.getLocation()+ " (" + s.getName() + ")";

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
                context.startActivity(intent);
            }
        });

        h.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, AddShopsActivity.class).putExtra("id",list.get(p).getId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<Shops> shops) {
        this.list = shops;
        notifyDataSetChanged();
    }

    public class VH extends RecyclerView.ViewHolder {
        private TextView shop_name, contact_number, map;
        private ConstraintLayout layout;

        public VH(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout);
            shop_name = itemView.findViewById(R.id.textView4);
            contact_number = itemView.findViewById(R.id.textView5);
            map = itemView.findViewById(R.id.textView6);
        }
    }
}
