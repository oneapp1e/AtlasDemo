package bm.hd.mlr.host;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by mulinrui on 2018/1/4.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //科大讯飞广告
        findViewById(R.id.ifytek).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IflytekActivity.class);
                startActivity(intent);
            }
        });

        //atlas组件
        findViewById(R.id.atlas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AtlasActivity.class);
                startActivity(intent);
            }
        });
    }
}
