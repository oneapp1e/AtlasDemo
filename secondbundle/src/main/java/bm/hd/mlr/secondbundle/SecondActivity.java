package bm.hd.mlr.secondbundle;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by mulinrui on 2017/12/5.
 */
public class SecondActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);

        TextView textView = (TextView) findViewById(R.id.tv);

        textView.setText(" welcome SecondActivity " + BuildConfig.VERSION_NAME);
    }

}
