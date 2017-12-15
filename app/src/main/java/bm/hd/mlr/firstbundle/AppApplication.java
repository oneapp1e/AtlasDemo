package bm.hd.mlr.firstbundle;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.taobao.atlas.bundleInfo.AtlasBundleInfoManager;
import android.taobao.atlas.framework.Atlas;
import android.taobao.atlas.framework.BundleImpl;
import android.taobao.atlas.framework.BundleInstaller;
import android.taobao.atlas.runtime.ActivityTaskMgr;
import android.taobao.atlas.runtime.ClassNotFoundInterceptorCallback;
import android.text.TextUtils;
import android.widget.Toast;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;

import java.io.File;

/**
 * Created by mulinrui on 2017/12/6.
 */
public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //类没有发现的处理
        Atlas.getInstance().setClassNotFoundInterceptorCallback(new ClassNotFoundInterceptorCallback() {
            @Override
            public Intent returnIntent(Intent intent) {
                final String className = intent.getComponent().getClassName();
                final String bundleName = AtlasBundleInfoManager.instance().getBundleForComponet(className);

                if (!TextUtils.isEmpty(bundleName) && !AtlasBundleInfoManager.instance().isInternalBundle(bundleName)) {

                    //远程bundle
                    final Activity activity = ActivityTaskMgr.getInstance().peekTopActivity();

                    Toast.makeText(activity, " 远程bundle没有安装，请确定 " + bundleName, Toast.LENGTH_LONG).show();
                }

                return intent;
            }
        });
    }
}
