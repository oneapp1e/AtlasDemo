package bm.hd.mlr.host;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

public class IflytekActivity extends Activity implements OnClickListener {

//    public final int PERMISSION_RESULT_CODE = 100;
//    public final int PERMISSION_STORAGE_CODE = 101;

//    //android 6.0以上，获取广告需动态申请的权限
//    public static String permissionArray[] = {
//            "android.permission.READ_PHONE_STATE",
//            "android.permission.ACCESS_FINE_LOCATION",
//            "android.permission.WRITE_EXTERNAL_STORAGE",
//            //若不使用语音交互广告，无需声明此权限
//            "android.permission.RECORD_AUDIO"
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iflytek);

        //须在应用初始化时创建SpeechUtility，appid为开发者在云平台注册时申请的appid
        SpeechUtility.createUtility(IflytekActivity.this, SpeechConstant.APPID + "=5715986a");

        //申请权限
//        requestPermission();

        findViewById(R.id.btn_banner).setOnClickListener(this);
        findViewById(R.id.btn_interstitial).setOnClickListener(this);
        findViewById(R.id.btn_fullscreen).setOnClickListener(this);
        findViewById(R.id.btn_native).setOnClickListener(this);
    }

//    public void requestPermission() {
//        if (Build.VERSION.SDK_INT >= 23) {
//            List<String> permissionList = new ArrayList<>();
//            for (String permission : permissionArray) {
//                if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
//                    permissionList.add(permission);
//                }
//            }
//            if (permissionList.size() > 0) {
//                requestPermissions(permissionList.toArray(new String[permissionList.size()]), PERMISSION_RESULT_CODE);
//            }
//        }
//    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_banner:
//                if (Build.VERSION.SDK_INT >= 23) {
//                    if (checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != PackageManager.PERMISSION_GRANTED) {
//                        requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, PERMISSION_STORAGE_CODE);
//                        return;
//                    }
//                }
                intent = new Intent(IflytekActivity.this, BannerAdActivity.class);
                break;
            case R.id.btn_interstitial:
//                if (Build.VERSION.SDK_INT >= 23) {
//                    if (checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != PackageManager.PERMISSION_GRANTED) {
//                        requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, PERMISSION_STORAGE_CODE);
//                        return;
//                    }
//                }
                intent = new Intent(IflytekActivity.this, InterstitialAdActivity.class);
                break;
            case R.id.btn_fullscreen:
//                if (Build.VERSION.SDK_INT >= 23) {
//                    if (checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != PackageManager.PERMISSION_GRANTED) {
//                        requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, PERMISSION_STORAGE_CODE);
//                        return;
//                    }
//                }
                intent = new Intent(IflytekActivity.this, FullScreenAdActivity.class);
                break;
            case R.id.btn_native:
                intent = new Intent(IflytekActivity.this, NativeAdActivity.class);
//			intent = new Intent(IflytekActivity.this, NativeAdListviewDemo.class);
                break;

        }
        if (null != intent) {
            startActivity(intent);
        }
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        //注意当WRITE_EXTERNAL_STORAGE权限被拒绝后，无法创建Banner、Interstitial、FullScreen这几类广告
//        switch (requestCode) {
//            case PERMISSION_RESULT_CODE:
//                for (int i = 0; i < permissions.length; i++) {
//                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
//                        //Toast.makeText(this, permissions[i] + " 权限被拒绝", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                break;
//            case PERMISSION_STORAGE_CODE:
//                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
//                    new AlertDialog.Builder(this)
//                            .setMessage("需要授权存储权限!")
//                            .setPositiveButton("去授权", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                    Uri uri = Uri.fromParts("package", getApplicationContext().getPackageName(), null);
//                                    intent.setData(uri);
//                                    startActivity(intent);
//                                }
//                            })
//                            .setNegativeButton("取消", null).show();
//                }
//                break;
//            default:
//                break;
//        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Ad_Android_Demo", "onDestroy");
    }
}
