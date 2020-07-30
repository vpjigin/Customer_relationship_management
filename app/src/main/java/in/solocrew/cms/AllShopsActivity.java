package in.solocrew.cms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import in.solocrew.cms.adapter.ShopsAdapter;
import in.solocrew.cms.appdb.AppDB;
import in.solocrew.cms.appdb.Shops;

public class AllShopsActivity extends AppCompatActivity {

    private RecyclerView rv;
    private ShopsAdapter adapter;
    private List<Shops> list;
    private AppDB db;

    @Override
    protected void onResume() {
        super.onResume();
        adapter.setList(db.getAllShops());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_shops);

        setUI();

        adapter.setList(db.getAllShops());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add){
            startActivity(new Intent(getApplicationContext(), AddShopsActivity.class));
        }
        return true;
    }

    private void setUI() {
        db = new AppDB(this);
        rv = findViewById(R.id.rv);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new ShopsAdapter(this,list);
        rv.setAdapter(adapter);
    }
}