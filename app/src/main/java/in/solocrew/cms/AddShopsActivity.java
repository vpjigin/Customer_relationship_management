package in.solocrew.cms;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import in.solocrew.cms.appdb.AppDB;
import in.solocrew.cms.appdb.Shops;
import in.solocrew.cms.util.GpsTracker;

public class AddShopsActivity extends AppCompatActivity implements GpsTracker.AppLocationService {

    private Shops shops;
    private EditText name, address, contact_number;
    private String location = "";
    private Button bt_save, bt_location;
    private ProgressBar pg_location;

    private AppDB db;
    private GpsTracker tracker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shops);

        setUI();

        int id = getIntent().getIntExtra("id",0);
        if(id != 0){
            shops = db.getShopById(id);
            setData(shops);
        }

        bt_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pg_location.setVisibility(View.VISIBLE);
                tracker = new GpsTracker(AddShopsActivity.this);
                tracker.getLocation(true);
            }
        });

        bt_save.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if(val()){
                if(shops == null){
                    shops = new Shops(name.getText().toString(), address.getText().toString(),
                            location,contact_number.getText().toString());

                    db.insertShops(shops);
                }
                else{
                    shops.setName(name.getText().toString());
                    shops.setAddress(address.getText().toString());
                    shops.setContact_number(contact_number.getText().toString());
                    shops.setLocation(location);

                    db.updateShops(shops);
                }

                Toast.makeText(this,R.string.saved,Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }

    private void setData(Shops shops) {
        name.setText(shops.getName());
        address.setText(shops.getAddress());
        contact_number.setText(shops.getContact_number());

        if(!shops.getLocation().isEmpty()){
            location = shops.getLocation();
            locationCaptured();
        }
    }

    private void setUI() {
        db = new AppDB(this);
        name = findViewById(R.id.editTextTextPersonName);
        address = findViewById(R.id.editTextTextPersonName2);
        contact_number = findViewById(R.id.editTextTextPersonName3);
        bt_save = findViewById(R.id.button2);
        bt_location = findViewById(R.id.button);
        pg_location = findViewById(R.id.progressBar);
    }

    private boolean val() {
        if(name.getText().toString().isEmpty()){
            Toast.makeText(this,R.string.name_cant_leave_empty,Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @Override
    public void locationTracked(Location _location) {
        if(_location == null){
            location_not_captured();
            return;
        }

        location = _location.getLatitude()+","+_location.getLongitude();
        Toast.makeText(this,location,Toast.LENGTH_LONG).show();

        locationCaptured();
        pg_location.setVisibility(View.GONE);
    }

    public void locationCaptured(){
        bt_location.setCompoundDrawablesWithIntrinsicBounds(R.drawable.tick, 0, 0, 0);
        bt_location.setText(R.string.location_captured);
        bt_location.setBackgroundColor(getResources().getColor(R.color.green));
    }

    public void location_not_captured(){
        bt_location.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_location, 0, 0, 0);
        bt_location.setText(R.string.save_this_location);
        bt_location.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
    }
}
