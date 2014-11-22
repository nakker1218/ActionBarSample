package com.example.nakayamashohei.actionbarsample;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements AbsListView.OnScrollListener {
    ListView listView;

    ColorDrawable actionbarBG = new ColorDrawable(0);
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionbarBG = new ColorDrawable(Color.parseColor("#009ae1"));


        listView = (ListView)findViewById(R.id.listView);
        listView.setOnScrollListener(this);


        List<String> list = new ArrayList<String>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);

        String text = "リスト";

        for (int i=0; i<51; i++){
            list.add(text + i);
        }

        listView.setAdapter(adapter);

        actionbarBG.setAlpha(0);

        ActionBar actionbar = getActionBar();
        actionbar.setBackgroundDrawable(actionbarBG);

        // 今回はGooglePlay風にするので、タイトルも透過します。
        // タイトルも透過できるように、タイトルは非表示にしてTextViewを代わりに表示
        actionbar.setDisplayShowTitleEnabled(false);
        actionbar.setDisplayShowCustomEnabled(true);

//        title.setText(getString(R.string.app_name));
//        title.setTextColor(Color.parseColor("#ffffff"));
//        title.setGravity(Gravity.CENTER_VERTICAL);
        // タイトルも透明にする
//        title.setAlpha(0);
//        actionbar.setCustomView(title);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        int scrollY = firstVisibleItem * 3;

        final int actionbarHight = getActionBar().getHeight();

        final float ratio = (float) Math.min(Math.max(scrollY, 0), actionbarHight) / actionbarHight;
        Log.d("onScroll", "ratio:" + ratio);
//        final int alpha = (int) ((1- ratio) * 255);
        final int alpha = (int) (ratio * 255);

       // title.setAlpha(alpha);
        actionbarBG.setAlpha(alpha);

    }
}
