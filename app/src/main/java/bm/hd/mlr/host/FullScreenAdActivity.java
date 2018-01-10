package bm.hd.mlr.host;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.iflytek.voiceads.AdError;
import com.iflytek.voiceads.AdKeys;
import com.iflytek.voiceads.IFLYAdListener;
import com.iflytek.voiceads.IFLYAdSize;
import com.iflytek.voiceads.IFLYFullScreenAd;

public class FullScreenAdActivity extends Activity {

	private IFLYFullScreenAd fullScreenAd;
	private TextView mTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/**为了使全屏广告满屏显示，可以通过移除标题栏和状态栏实现*/
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fullscreen_layout);

		createFullScreenAd();
		
		mTextView = ((TextView)findViewById(R.id.txtView_tip));
    	mTextView.setText("requesting");
	}
	
	public void createFullScreenAd() {
		//此广告位为Demo专用，广告的展示不产生费用
	    final String adUnitId = "2F74BC6410FEB9EAE9F33D5B98FA0454";
	    
	    //创建全屏广告，传入广告位ID
	    fullScreenAd = IFLYFullScreenAd.createFullScreenAd(this, adUnitId);
	    //设置广告尺寸
	    fullScreenAd.setAdSize(IFLYAdSize.FULLSCREEN);
	    //设置参数：全屏广告展示时间。单位为ms，默认5000ms
	    fullScreenAd.setParameter(AdKeys.SHOW_TIME_FULLSCREEN, "6000");
		//设置下载广告前，弹窗提示
//	    fullScreenAd.setParameter(AdKeys.DOWNLOAD_ALERT, "true");
	    
	    //请求广告，添加监听器
	    fullScreenAd.loadAd(mAdListener);
	}
	
	IFLYAdListener mAdListener = new IFLYAdListener(){
		
		/**
		 * 广告请求成功
		 */
		@Override
		public void onAdReceive() {
        	//隐藏状态栏
        	setFullScreen(true);
			//展示广告
        	fullScreenAd.showAd();
        	
			mTextView.setText("success");
		    Log.d("Ad_Android_Demo", "onAdReceive");
		}

		/**
		 * 广告请求失败
		 */
		@Override
		public void onAdFailed(AdError error) {
			mTextView.setText("failed:"+error.getErrorCode()+","+
						error.getErrorDescription());
		    Log.d("Ad_Android_Demo", "onAdFailed");
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
			//恢复状态栏
			setFullScreen(false);
			
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
			
		}

		@Override
		public void onConfirm() {
			// TODO Auto-generated method stub
			
		}
    };

	/**
	 * 设置是否全屏显示广告，即隐藏状态栏
	 * @param enable
	 */
	@SuppressLint("NewApi")
	private void setFullScreen(boolean enable) {
        if (enable) {
        	getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
        	getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

	@Override
	protected void onDestroy() {
		super.onDestroy();
		fullScreenAd.destroy();
	}
}
