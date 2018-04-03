package extensionit.activity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import bdapps.activity.R;
import extensionit.adapter.ItemAdapter;

public class MainActivity extends AppCompatActivity{

    private ItemAdapter itemAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager recyclerLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLongClickable(true);

        recyclerLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(recyclerLayoutManager);
        recyclerView.setHasFixedSize(true);
        final int gridMargin = getResources().getDimensionPixelOffset(R.dimen.item_margin);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                       RecyclerView.State state) {
                outRect.set(gridMargin, gridMargin, gridMargin, gridMargin);
            }
        });


        itemAdapter = new ItemAdapter();
        recyclerView.setAdapter(itemAdapter);
    }

    private boolean isChecked = true;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_list).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_list:

                if (isChecked) {

                    item.setIcon(R.mipmap.icon_grid);
                    isChecked = false;
                    recyclerLayoutManager = new LinearLayoutManager(this);
                } else {

                    item.setIcon(R.mipmap.icon_list);
                    isChecked = true;
                    recyclerLayoutManager = new GridLayoutManager(this, 3);
                }

                item.getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                itemAdapter.setIsGrid(isChecked);
                recyclerView.setLayoutManager(recyclerLayoutManager);
                itemAdapter.notifyDataSetChanged();

                return true;
            default:
                return false;
        }
    }


}
