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

import in.solocrew.cms.adapter.DetailsAdapter;
import in.solocrew.cms.appdb.AppDB;
import in.solocrew.cms.appdb.Details;

public class AllDetailsActivity extends AppCompatActivity {

    private RecyclerView rv;
    private DetailsAdapter adapter;

    private AppDB db;

    @Override
    protected void onResume() {
        super.onResume();
        adapter.setList(db.getAllDetails());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_details);

        setUI();

        adapter.setList(db.getAllDetails());

    }

    private void setUI() {
        db = new AppDB(this);
        rv = findViewById(R.id.rv);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DetailsAdapter(this,new ArrayList<>());
        rv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add){
            startActivity(new Intent(getApplicationContext(), AddDetailsActivity.class));
        }
        return true;
    }
}