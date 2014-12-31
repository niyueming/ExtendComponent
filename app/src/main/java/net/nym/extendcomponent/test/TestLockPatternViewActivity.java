package net.nym.extendcomponent.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;
import android.widget.TextView;

import net.nym.extendcomponent.R;
import net.nym.extendcomponent.common.BaseApplication;
import net.nym.extendcomponent.widget.lockpattern.LockPatternUtils;
import net.nym.extendcomponent.widget.lockpattern.LockPatternView;

import java.util.List;

/**
 * @author nym
 * @date 2014/12/31 0031.
 * @since 1.0
 */
public class TestLockPatternViewActivity extends ActionBarActivity implements LockPatternView.OnPatternListener {

    LockPatternView mLockPatternView;
    LockPatternUtils mLockPatternUtils;
    TextView hint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_test_lock_pattern);
        hint = (TextView) findViewById(R.id.hint);
        hint.setText("");

        mLockPatternUtils = new LockPatternUtils(BaseApplication.getAppContext());
        mLockPatternView = (LockPatternView) findViewById(R.id.LockPatternView);
        mLockPatternView.setOnPatternListener(this);
    }


    @Override
    public void onBackPressed() {
        mLockPatternUtils.clearLock();
        super.onBackPressed();
    }

    @Override
    public void onPatternStart() {

    }

    @Override
    public void onPatternCleared() {

    }

    @Override
    public void onPatternCellAdded(List<LockPatternView.Cell> pattern) {

    }

    @Override
    public void onPatternDetected(List<LockPatternView.Cell> pattern) {
        switch (mLockPatternUtils.checkPattern(pattern))
        {
            case 1:
                mLockPatternUtils.saveLockPattern(pattern);
                onBackPressed();
                break;
            case 0:
                mLockPatternUtils.clearLock();
                mLockPatternView.clearPattern();
                hint.setText("两次手势不一致，请重绘");
                break;
            case -1:
                mLockPatternUtils.saveLockPattern(pattern);
                mLockPatternView.clearPattern();
                hint.setText("再次绘制图案进行确认");
                break;
        }
    }
}
