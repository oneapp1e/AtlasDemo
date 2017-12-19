package bm.hd.mlr.firstbundle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.taobao.atlas.framework.Atlas;
import android.taobao.atlas.remote.HostTransactor;
import android.taobao.atlas.remote.IRemote;
import android.taobao.atlas.remote.IRemoteTransactor;
import android.taobao.atlas.remote.RemoteActivityManager;
import android.taobao.atlas.remote.RemoteFactory;
import android.taobao.atlas.remote.transactor.RemoteTransactor;
import android.taobao.atlas.util.AtlasCrashManager;
import android.util.Log;
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


//        Intent aIntent = new Intent("atlas.transaction.intent.action.funA");
//        RemoteFactory.requestRemote(RemoteTransactor.class, FirstActivity.this, aIntent, new RemoteFactory.OnRemoteStateListener<RemoteTransactor>() {
//            @Override
//            public void onRemotePrepared(RemoteTransactor remote) {
//                Log.e("RemoteTransactor", "FirstActivity onRemotePrepared 成功了 ");
//                IRemote hostTransactor = remote.getHostTransactor();
//                Log.e("RemoteTransactor", "FirstActivity hostTransactor 成功了 "+hostTransactor);
//            }
//
//            @Override
//            public void onFailed(String errorInfo) {
//                Log.e("RemoteTransactor", "FirstActivity onFailed 失败了 " + errorInfo);
//            }
//        });


//        Intent bIntent = new Intent("atlas.transaction.intent.action.funB");
//        RemoteFactory.requestRemote(RemoteTransactor.class, null, bIntent, new RemoteFactory.OnRemoteStateListener<RemoteTransactor>() {
//            @Override
//            public void onRemotePrepared(RemoteTransactor remote) {
//                Log.e("RemoteTransactor", "FirstActivity onRemotePrepared 成功了 ");
//                IRemote hostTransactor = remote.getHostTransactor();
//                Log.e("RemoteTransactor", "FirstActivity hostTransactor 成功了 " + hostTransactor);
//            }
//
//            @Override
//            public void onFailed(String errorInfo) {
//                Log.e("RemoteTransactor", "FirstActivity onFailed 失败了 " + errorInfo);
//            }
//        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }


}
