package bm.hd.mlr.host;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.iflytek.voiceads.AdError;
import com.iflytek.voiceads.IFLYAdListener;
import com.iflytek.voiceads.IFLYAdSize;
import com.iflytek.voiceads.IFLYInterstitialAd;

public class InterstitialAdActivity extends Activity {

    private IFLYInterstitialAd interstitialAd;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interstitial_layout);

        createInterstitialAd();

        mTextView = ((TextView) findViewById(R.id.txtView_tip));
        mTextView.setText("requesting");
    }

    public void createInterstitialAd() {
        //此广告位为Demo专用，广告的展示不产生费用
        final String adUnitId = "88749E88BB40CED8F22E7A31491E7AAD";
        //创建插屏广告，传入广告位ID
        interstitialAd = IFLYInterstitialAd.createInterstitialAd(this, adUnitId);
        //设置广告尺寸
        interstitialAd.setAdSize(IFLYAdSize.INTERSTITIAL);
        //设置下载广告前，弹窗提示
//	    interstitialAd.setParameter(AdKeys.DOWNLOAD_ALERT, "true");

        //请求广告，添加监听器
        interstitialAd.loadAd(mAdListener);
    }

    IFLYAdListener mAdListener = new IFLYAdListener() {

        /**
         * 广告请求成功
         */
        @Override
        public void onAdReceive() {
            //展示广告
            interstitialAd.showAd();

            mTextView.setText("success");
            Log.d("Ad_Android_Demo", "onAdReceive");
        }

        /**
         * 广告请求失败
         */
        @Override
        public void onAdFailed(AdError error) {
            mTextView.setText("failed:" + error.getErrorCode() + "," +
                    error.getErrorDescription());
            Log.d("Ad_Android_Demo", "onAdFailed:" + error.getErrorCode() + "," +
                    error.getErrorDescription());
        }

        /**
         * 广告被点击
         */
        @Override
        public void onAdClick() {
            mTextView.setText("ad click");
            Log.d("Ad_Android_Demo", "onAdClick");
        }

        /**
         * 广告被关闭
         */
        @Override
        public void onAdClose() {
            mTextView.setText("ad close");
            Log.d("Ad_Android_Demo", "onAdClose");
        }

        @Override
        public void onAdExposure() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onCancel() {
            // TODO Auto-generated method stub
            Log.d("Ad_Android_Demo", "onCancel");
        }

        @Override
        public void onConfirm() {
            // TODO Auto-generated method stub
            Log.d("Ad_Android_Demo", "onConfirm");
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        interstitialAd.destroy();
    }
}
