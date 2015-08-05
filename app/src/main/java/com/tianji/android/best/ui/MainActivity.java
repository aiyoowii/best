package com.tianji.android.best.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.tianji.android.best.R;
import com.tianji.android.best.ui.feed.FeedFragment;
import com.tianji.android.best.ui.tool.ToolFragment;
import com.tianji.android.best.utils.ViewMapping;
import com.tianji.android.best.utils.ViewMappingUtil;
import com.tianji.android.best.view.composerlayout.CircleAnimationHelper;
import com.tianji.android.best.view.composerlayout.InOutAnimationHelper;

@ViewMapping(id = R.layout.activity_main)
public class MainActivity extends FragmentActivity implements View.OnClickListener {

    @ViewMapping(id = android.R.id.tabhost)
    private FragmentTabHost tabHost;
    @ViewMapping(id = R.id.composer)
    private ViewGroup composer_wrapper;
    @ViewMapping(id = R.id.composer_show_hide_button)
    private View composer_button;
    @ViewMapping(id = R.id.tab1)
    private View tab1;
    @ViewMapping(id = R.id.tab2)
    private View tab2;
    @ViewMapping(id = R.id.tab3)
    private View tab3;
    @ViewMapping(id = R.id.tab4)
    private View tab4;
    private InOutAnimationHelper animationHelper;

    private Class fragmentArray[] = {FeedFragment.class, ToolFragment.class, ToolFragment.class, FeedFragment.class};
    private int tabImageArray[] = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private int tabTextArray[] = {R.string.fragment_feed, R.string.fragment_tool, R.string.fragment_message, R.string.fragment_profile};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewMappingUtil.mapView(this,this);
        initTab();
        initComposer();
    }

    private void initComposer() {
        animationHelper=new CircleAnimationHelper(composer_wrapper,200);
    }

    private void initTab() {
        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabs);

        for(int i = 0;i<fragmentArray.length;i++){
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(getString(tabTextArray[i])).setIndicator(getTabItemView(i));
            tabHost.addTab(tabSpec,fragmentArray[i],null);
        }
        tabHost.getTabWidget().setDividerDrawable(android.R.color.transparent);
    }

    private View getTabItemView(int index) {
        View view = View.inflate(this, R.layout.tab_item_view, null);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab1:
                tabHost.setCurrentTab(0);
                animationHelper.hide();
                break;
            case R.id.tab2:
                tabHost.setCurrentTab(1);
                animationHelper.hide();
                break;
            case R.id.tab3:
                tabHost.setCurrentTab(2);
                animationHelper.hide();
                break;
            case R.id.tab4:
                tabHost.setCurrentTab(3);
                animationHelper.hide();
                break;
            case R.id.composer_show_hide_button:
                animationHelper.toggle();
                break;
        }
    }
}
