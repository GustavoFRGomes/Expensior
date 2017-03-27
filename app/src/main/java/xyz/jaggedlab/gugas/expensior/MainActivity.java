package xyz.jaggedlab.gugas.expensior;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import xyz.jaggedlab.gugas.expensior.sections.ExpenseSectionFragment;
import xyz.jaggedlab.gugas.expensior.sections.ProfileSectionFragment;
import xyz.jaggedlab.gugas.expensior.sections.ReportSectionFragment;
import xyz.jaggedlab.gugas.expensior.sections.new_expenses.NewExpenseActivity;
import xyz.jaggedlab.gugas.expensior.utils.DimensionUtils;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, FloatingActionButton.OnClickListener {

    private ViewPager sectionViewPager;
    private BottomNavigationView navigation;
    private FloatingActionButton newExpenseFab;

    static final int NUM_PAGES = 3;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            boolean returnedBool = false;
            switch (item.getItemId()) {
                case R.id.navigation_reports:
                    //mTextMessage.setText(R.string.title_report);
                    //changeMainFragment(reportFragment);
                    sectionViewPager.setCurrentItem(0);
                    returnedBool = true;
                    break;
                case R.id.navigation_expense:
                    //mTextMessage.setText(R.string.title_expense);
                    //changeMainFragment(expenseFragment);
                    sectionViewPager.setCurrentItem(1);
                    returnedBool = true;
                    break;
                case R.id.navigation_profile:
                    //mTextMessage.setText(R.string.title_profile);
                    //changeMainFragment(profileFragment);
                    sectionViewPager.setCurrentItem(2);
                    returnedBool = true;
                    break;
            }
            return returnedBool;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.sectionViewPager = ((ViewPager)this.findViewById(R.id.content_view_pager));
        this.sectionViewPager.setOffscreenPageLimit(NUM_PAGES);
        this.sectionViewPager.addOnPageChangeListener(this);
        this.sectionViewPager.setAdapter(new ScreenSlidePagerAdapter(this.getSupportFragmentManager()));
        this.sectionViewPager.setPageMargin(DimensionUtils.convertFromDpToPixel(this, 8));

        this.navigation = (BottomNavigationView) findViewById(R.id.navigation);
        this.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        this.newExpenseFab = ((FloatingActionButton)this.findViewById(R.id.fab));
        this.newExpenseFab.setOnClickListener(this);

        this.sectionViewPager.setCurrentItem(1); // Setting the initial selected section to the Expense section
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("MAIN_ACTIVITY", "onActivityResult called");
        for (Fragment fragment : this.getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int itemId = R.id.navigation_reports;

        switch (position) {
            case 0:
                itemId = R.id.navigation_reports;
                break;
            case 1:
                itemId = R.id.navigation_expense;
                break;
            case 2:
                itemId = R.id.navigation_profile;
                break;
        }

        this.navigation.setSelectedItemId(itemId);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == this.newExpenseFab.getId()) {
            presentNewExpenseActivity();
        }
    }

    private void presentNewExpenseActivity() {
        Intent newExpenseIntent = new Intent(this, NewExpenseActivity.class);
        this.startActivityForResult(newExpenseIntent, NewExpenseActivity.REQUEST_CODE);

        this.overridePendingTransition(R.anim.animation_enter_right_to_left, R.anim.animation_leave_left_to_right);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;

            switch (position) {
                case 0: // Report Section
                    fragment = ReportSectionFragment.newInstance();
                    break;
                case 1: // Expense Section
                    fragment = ExpenseSectionFragment.newInstance();
                    break;
                default:
                case 2: // Profile Section
                    fragment =  ProfileSectionFragment.newInstance();
                    break;
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
