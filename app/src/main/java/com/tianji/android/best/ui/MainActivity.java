package com.tianji.android.best.ui;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.tianji.android.best.R;
import com.tianji.android.best.utils.ViewMapping;
import com.tianji.android.best.utils.ViewMappingUtil;

@ViewMapping(id = R.layout.activity_main)
public class MainActivity extends FragmentActivity {

    @ViewMapping(id = android.R.id.tabhost)
    private FragmentTabHost tabHost;

    private Class fragmentArray[] = {};
    private int tabImageArray[] = {};
    private String tabTextArray[] = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewMappingUtil.mapView(this,this);
        initTab();
    }

    private void initTab() {
        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabs);

        for(int i = 0;i<fragmentArray.length;i++){
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(tabTextArray[i]).setIndicator(getTabItemView(i));
            tabHost.addTab(tabSpec,fragmentArray[i],null);
        }
        tabHost.getTabWidget().setDividerDrawable(android.R.color.transparent);
    }

    private View getTabItemView(int index) {
        View view = View.inflate(this,index == tabTextArray.length / 2?R.layout.tab_center_view:R.layout.tab_item_view,null);
        ImageView imageView = (ImageView) view.findViewById(R.id.tab_image);
        imageView.setImageResource(tabImageArray[index]);
        TextView textView = (TextView) view.findViewById(R.id.tab_text);
        textView.setText(tabTextArray[index]);
        return view;
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
}
