package bm.hd.mlr.firstbundle;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.taobao.atlas.bundleInfo.AtlasBundleInfoManager;
import android.taobao.atlas.framework.Atlas;
import android.taobao.atlas.remote.IRemote;
import android.taobao.atlas.remote.IRemoteTransactor;
import android.taobao.atlas.remote.RemoteFactory;
import android.taobao.atlas.remote.transactor.RemoteTransactor;
import android.taobao.atlas.runtime.ActivityTaskMgr;
import android.taobao.atlas.versionInfo.BaselineInfoManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleException;
import org.osgi.framework.BundleListener;

import java.io.File;

import bm.hd.mlr.firstbundle.update.Updater;

/**
 * Created by mulinrui on 2017/12/5.
 */
public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);

        Atlas.getInstance().addBundleListener(new BundleListener() {
            @Override
            public void bundleChanged(BundleEvent event) {
                Log.e("bundleChanged", "bundleChanged success" + event.getType() + " " + event.getBundle().getLocation());
            }
        });

        TextView textView = (TextView) findViewById(R.id.textview);
        textView.setText(" welcome activity " + BuildConfig.VERSION_NAME);

        findViewById(R.id.roolback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BaselineInfoManager.instance().rollback();

            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        Updater.update(getBaseContext());
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                }.execute();
            }
        });

        findViewById(R.id.dexpatch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        Updater.dexPatchUpdate(getBaseContext());
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                }.execute();
            }
        });

        findViewById(R.id.install_firstBundle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String bundleName = "bm.hd.mlr.firstbundle";

                if (!AtlasBundleInfoManager.instance().isInternalBundle(bundleName)) {

                    //远程bundle
                    final Activity activity = ActivityTaskMgr.getInstance().peekTopActivity();
                    File remoteBundleFile = new File(activity.getExternalCacheDir(), "lib" + bundleName.replace(".", "_") + ".so");

                    String path = "";
                    if (remoteBundleFile.exists()) {
                        path = remoteBundleFile.getAbsolutePath();
                    } else {
                        Toast.makeText(activity, " 远程bundle不存在，请确定 : " + remoteBundleFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
                        return;
                    }


                    final PackageInfo info = activity.getPackageManager().getPackageArchiveInfo(path, 0);
                    try {
                        Atlas.getInstance().installBundle(info.packageName, new File(path));

                    } catch (BundleException e) {
                        Toast.makeText(activity, " 远程bundle 安装失败，" + e.getMessage(), Toast.LENGTH_LONG).show();

                        e.printStackTrace();
                    }

                    //安装成功
                    Toast.makeText(activity, " 远程bundle 安装成功", Toast.LENGTH_LONG).show();
                }


            }
        });

        findViewById(R.id.open_firstBundle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName(MainActivity.this, "bm.hd.mlr.firstbundle.FirstActivity");
                startActivity(intent);
            }
        });


        findViewById(R.id.uninstall_firstBundle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                Intent intent = new Intent();
                intent.setClassName(MainActivity.this, "bm.hd.mlr.firstbundle.FirstActivity");
                startActivity(intent);

                try {
                    Atlas.getInstance().setClassNotFoundInterceptorCallback(null);

                    Atlas.getInstance().uninstallBundle("bm.hd.mlr.firstbundle");

                } catch (BundleException e) {
                    Toast.makeText(MainActivity.this, " 远程bundle 卸载失败，" + e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        });


        findViewById(R.id.install_firstBundle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String bundleName = "bm.hd.mlr.firstbundle";

                if (!AtlasBundleInfoManager.instance().isInternalBundle(bundleName)) {

                    //远程bundle
                    final Activity activity = ActivityTaskMgr.getInstance().peekTopActivity();
                    File remoteBundleFile = new File(activity.getExternalCacheDir(), "lib" + bundleName.replace(".", "_") + ".so");

                    String path = "";
                    if (remoteBundleFile.exists()) {
                        path = remoteBundleFile.getAbsolutePath();
                    } else {
                        Toast.makeText(activity, " 远程 bundle 不存在，请确定 : " + remoteBundleFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
                        return;
                    }


                    final PackageInfo info = activity.getPackageManager().getPackageArchiveInfo(path, 0);
                    try {
                        Atlas.getInstance().installBundle(info.packageName, new File(path));

                    } catch (BundleException e) {
                        Toast.makeText(activity, " 远程 firstbundle 安装失败，" + e.getMessage(), Toast.LENGTH_LONG).show();

                        e.printStackTrace();
                    }

                    //安装成功
                    Toast.makeText(activity, " 远程 firstbundle 安装成功", Toast.LENGTH_LONG).show();
                }


            }
        });

        findViewById(R.id.open_firstBundle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName(MainActivity.this, "bm.hd.mlr.firstbundle.FirstActivity");
                startActivity(intent);
            }
        });


        findViewById(R.id.uninstall_firstBundle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Atlas.getInstance().uninstallBundle("bm.hd.mlr.firstbundle");

                } catch (BundleException e) {
                    Toast.makeText(MainActivity.this, " 远程 firstbundle 卸载失败，" + e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        });


        findViewById(R.id.install_secondBundle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String bundleName = "bm.hd.mlr.secondbundle";

                if (!AtlasBundleInfoManager.instance().isInternalBundle(bundleName)) {

                    //远程bundle
                    final Activity activity = ActivityTaskMgr.getInstance().peekTopActivity();
                    File remoteBundleFile = new File(activity.getExternalCacheDir(), "lib" + bundleName.replace(".", "_") + ".so");

                    String path = "";
                    if (remoteBundleFile.exists()) {
                        path = remoteBundleFile.getAbsolutePath();
                    } else {
                        Toast.makeText(activity, " 远程 bundle 不存在，请确定 : " + remoteBundleFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
                        return;
                    }


                    final PackageInfo info = activity.getPackageManager().getPackageArchiveInfo(path, 0);
                    try {
                        Atlas.getInstance().installBundle(info.packageName, new File(path));

                    } catch (BundleException e) {
                        Toast.makeText(activity, " 远程 secondbundle 安装失败，" + e.getMessage(), Toast.LENGTH_LONG).show();

                        e.printStackTrace();
                    }

                    //安装成功
                    Toast.makeText(activity, " 远程 secondbundle 安装成功", Toast.LENGTH_LONG).show();
                }


            }
        });

        findViewById(R.id.open_secondBundle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName(MainActivity.this, "bm.hd.mlr.secondbundle.SecondActivity");
                startActivity(intent);
            }
        });


        findViewById(R.id.uninstall_secondBundle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    Atlas.getInstance().uninstallBundle("bm.hd.mlr.secondbundle");

                } catch (BundleException e) {
                    Toast.makeText(MainActivity.this, " 远程 secondbundle 卸载失败，" + e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        });


    }

}
