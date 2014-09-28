package net.nym.extendcomponent.test;

import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import net.nym.extendcomponent.R;
import net.nym.extendcomponent.widget.RoundProgressBar;

public class TestRoundProgressBarActivity extends ActionBarActivity implements View.OnClickListener {

    EditText edtTxt_text;
    RoundProgressBar mRoundProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_round_progress_bar);
        edtTxt_text = (EditText) findViewById(R.id.editText);
        mRoundProgressBar = (RoundProgressBar) findViewById(R.id.roundProgressBar);
        mRoundProgressBar.setProgress(50);
    }

    @Override
    public void onClick(View v) {
        String progress = edtTxt_text.getText().toString().trim();
        if (!progress.equals(""))
            mRoundProgressBar.setProgress(Integer.parseInt(progress));
    }
}
