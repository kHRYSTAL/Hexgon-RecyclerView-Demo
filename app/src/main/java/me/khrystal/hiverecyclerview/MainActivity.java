package me.khrystal.hiverecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import me.khrystal.widget.HiveRecyclerView;

public class MainActivity extends AppCompatActivity implements ImagesAdapter.ViewHolderClicks {

    public static final Integer[] resIds = new Integer[]{
            R.drawable.img_0
            , R.drawable.img_1
            , R.drawable.img_2
            , R.drawable.img_3
            , R.drawable.img_4
            , R.drawable.img_5
            , R.drawable.img_6
            , R.drawable.img_7
            , R.drawable.img_8
            , R.drawable.img_9
            , R.drawable.img_10
            , R.drawable.img_11
            , R.drawable.img_12
    };

    private HiveRecyclerView mRecyclerView;
    private ImagesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (HiveRecyclerView) findViewById(R.id.rV);
        List<Integer> list = Arrays.asList(resIds);
        mAdapter = new ImagesAdapter(list, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View view, int position, int resId) {
        Toast.makeText(this, "position:" + position + ",resId:" + resId, Toast.LENGTH_SHORT).show();
    }
}
