package xyz.jaggedlab.gugas.expensior.sections.new_expenses;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import io.realm.Realm;
import xyz.jaggedlab.gugas.expensior.R;

public class NewExpenseActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 2018;
    public static final String RESULT_CODE_NEEDS_UPDATE = "RESULT_CODE_NEEDS_UPDATE";

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_expense);

        this.toolbar = (Toolbar) findViewById(R.id.toolbar);

        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);

        //this.getSupportActionBar().setCustomView(R.layout.toolbar_layout);
        //((TextView)this.getSupportActionBar().getCustomView().findViewById(R.id.toolbar_title)).setText(R.string.title_activity_new_expense);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewExpenseActivity.this.onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        this.goBackToMainActivity();
    }

    private void goBackToMainActivity() {
        this.finish();
        this.overridePendingTransition(R.anim.animation_enter_left_to_right, R.anim.animation_leave_right_to_left);
    }
}
