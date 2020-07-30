package in.solocrew.cms;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

import java.util.ArrayList;
import java.util.List;

import in.solocrew.cms.appdb.AppDB;
import in.solocrew.cms.appdb.Details;
import in.solocrew.cms.appdb.Shops;
import in.solocrew.cms.listners.EdTextChange;
import in.solocrew.cms.util.Text;
import in.solocrew.cms.util.Util;

public class AddDetailsActivity extends AppCompatActivity implements EdTextChange.Refresh {

    private Spinner shops;
    private EditText ed_gross_weight,ed_no_of_boxes,ed_weight_of_empty_boxes,
            ed_net_weight,ed_no_of_boxes_returned,ed_no_of_boxes_yet_to_returned,ed_rate;
    private Button bt_save;
    private TextView tv_total;

    private Details details;
    private List<Shops>shop_list;

    private AppDB db;
    private EdTextChange ed_text_change;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_details);
        setUI();
        ed_text_change = new EdTextChange(this);

        ed_gross_weight.addTextChangedListener(ed_text_change);
        ed_no_of_boxes.addTextChangedListener(ed_text_change);
        ed_weight_of_empty_boxes.addTextChangedListener(ed_text_change);
        ed_no_of_boxes_returned.addTextChangedListener(ed_text_change);
        ed_rate.addTextChangedListener(ed_text_change);

        shop_list = db.getAllShops();
        List<String> array = db.getAllShopNames();

        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(AddDetailsActivity.this,
                R.layout.lay_spinner, array);
        shops.setAdapter(spinnerArrayAdapter);

        int id = getIntent().getIntExtra("id", 0);
        if(id != 0){
            this.details = db.getDetailById(id);
            setData(details);
        }

        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(val()){
                    int index = shops.getSelectedItemPosition()-1;
                    Shops shops = shop_list.get(index);
                    if(details == null){
                        details = new Details(shops.getId(),new Text(ed_no_of_boxes).toInt(),
                                new Text(ed_no_of_boxes_returned).toInt(),
                                new Text(ed_no_of_boxes_yet_to_returned).toInt(),
                                new Text(ed_gross_weight).toDouble(),
                                new Text(ed_weight_of_empty_boxes).toDouble(),

                                new Text(ed_rate).toDouble());

                        db.insertDetails(details);
                    }
                    else{
                        details.setData(shops.getId(),new Text(ed_gross_weight).toDouble(),
                                new Text(ed_no_of_boxes).toInt(),
                                new Text(ed_weight_of_empty_boxes).toDouble(),
                                new Text(ed_no_of_boxes_returned).toInt(),
                                new Text(ed_no_of_boxes_yet_to_returned).toInt(),
                                new Text(ed_rate).toDouble());

                        db.updateDetails(details);
                    }

                    finish();
                }
            }
        });

    }

    private void refreshViews(){
        double gross_weight = new Util().convertToDouble(ed_gross_weight.getText().toString());
        int no_of_boxes = new Util().convertToInt(ed_no_of_boxes.getText().toString());
        double weight_of_empty_boxes = new Util().convertToInt(ed_weight_of_empty_boxes.getText().toString());
        int no_of_empty_box_returned = new Util().convertToInt(ed_no_of_boxes_returned.getText().toString());
        double rate = new Util().convertToDouble(ed_rate.getText().toString());

        double net_weight = gross_weight - weight_of_empty_boxes;
        int no_of_empty_box_yet_to_return = no_of_boxes - no_of_empty_box_returned;
        double total = rate * net_weight;

        ed_net_weight.setText(String.valueOf(net_weight));
        ed_no_of_boxes_yet_to_returned.setText(String.valueOf(no_of_empty_box_yet_to_return));

        DecimalFormat myFormat = new DecimalFormat("##,##,##0");
        myFormat.setGroupingUsed(true);
        tv_total.setText(getString(R.string.total_dummy) + myFormat.format(total));
    }

    private void setData(Details details) {

        int size = shop_list.size();
        for (int i = 0; i < size; i++) {
            Shops _shops = shop_list.get(i);
            if(details.getShop_id() == _shops.getId()){
                shops.setSelection(i+1);
                break;
            }
        }

        ed_gross_weight.setText(details.getGross_weight()+"");
        ed_no_of_boxes.setText(details.getNo_of_boxes()+"");
        ed_weight_of_empty_boxes.setText(details.getWeight_of_empty_box()+"");
        ed_net_weight.setText(details.getNet_weight()+"");
        ed_no_of_boxes_returned.setText(details.getNo_of_empty_box_returned()+"");
        ed_no_of_boxes_yet_to_returned.setText(details.getNo_of_box_yet_to_return()+"");
        ed_rate.setText(details.getRate()+"");

        DecimalFormat myFormat = new DecimalFormat("##,##,##0");
        myFormat.setGroupingUsed(true);
        double total = details.getTotal();
        tv_total.setText(getString(R.string.total_dummy)+myFormat.format(total));
    }

    private boolean val() {

        if(shops.getSelectedItemPosition() == 0){
            Toast.makeText(getApplicationContext(),R.string.please_select_shop,Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    private void setUI() {
        db = new AppDB(this);
        shop_list = new ArrayList<>();

        bt_save = findViewById(R.id.button5);
        shops = findViewById(R.id.spinner);
        ed_gross_weight = findViewById(R.id.editTextTextPersonName4);
        ed_no_of_boxes = findViewById(R.id.editTextTextPersonName5);
        ed_weight_of_empty_boxes = findViewById(R.id.editTextTextPersonName6);
        ed_net_weight = findViewById(R.id.editTextTextPersonName7);
        ed_no_of_boxes_returned = findViewById(R.id.editTextTextPersonName8);
        ed_no_of_boxes_yet_to_returned = findViewById(R.id.editTextTextPersonName9);
        ed_rate = findViewById(R.id.editTextTextPersonName10);
        tv_total = findViewById(R.id.textView15);
    }

    @Override
    public void refresh() {
        refreshViews();
    }
}
