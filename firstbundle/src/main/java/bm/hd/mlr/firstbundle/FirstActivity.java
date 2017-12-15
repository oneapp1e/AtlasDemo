package bm.hd.mlr.firstbundle;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by mulinrui on 2017/12/5.
 */
public class FirstActivity extends Activity {

    @BindView(R2.id.tv)
    TextView textView;

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);
        mUnbinder = ButterKnife.bind(this);
        textView.setText(" welcome FirstActivity " + BuildConfig.VERSION_NAME);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
