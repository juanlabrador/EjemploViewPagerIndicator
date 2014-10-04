package com.example.juanlabrador.ejemploviewpagerindicator;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.efoad.views.ViewPagerIndicator;


public class MainActivity extends FragmentActivity {

    /**
     * The pager widget, which handles animation and allows swiping horizontally
     * to access previous and next pages.
     */
    ViewPager pager = null;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    PageFragmentAdapter pagerAdapter;
    ViewPagerIndicator pIndicator;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        this.setContentView(R.layout.activity_main);

        // Instantiate a ViewPager
        this.pager = (ViewPager) this.findViewById(R.id.pager);

        // Create an adapter with the fragments we show on the ViewPager
        PageFragmentAdapter adapter = new PageFragmentAdapter(
                getSupportFragmentManager());
        adapter.addFragment(PageFragment.newInstance(Color.BLUE, 0));
        adapter.addFragment(PageFragment.newInstance(Color.MAGENTA, 1));
        adapter.addFragment(PageFragment.newInstance(Color.GREEN, 2));
        adapter.addFragment(PageFragment.newInstance(Color.YELLOW, 3));
        adapter.addFragment(PageFragment.newInstance(Color.RED, 4));
        this.pager.setAdapter(adapter);

        pIndicator = (ViewPagerIndicator)findViewById(R.id.indicator);
        //CirclePageIndicator indicator = (CirclePageIndicator)findViewById(R.id.indicator);
        //pIndicator = indicator;
        pIndicator.setViewPager(pager);
        //pIndicator.setSnap(true);

    }

    @Override
    public void onBackPressed() {

        // Return to previous page when we press back button
        if (this.pager.getCurrentItem() == 0)
            super.onBackPressed();
        else
            this.pager.setCurrentItem(this.pager.getCurrentItem() - 1);

    }
}
