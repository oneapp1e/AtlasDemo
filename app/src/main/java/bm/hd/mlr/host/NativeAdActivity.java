package bm.hd.mlr.host;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.iflytek.voiceads.AdError;
import com.iflytek.voiceads.AdKeys;
import com.iflytek.voiceads.IFLYNativeAd;
import com.iflytek.voiceads.IFLYNativeListener;
import com.iflytek.voiceads.NativeADDataRef;

import java.util.List;

public class NativeAdActivity extends Activity implements IFLYNativeListener {
    private IFLYNativeAd nativeAd;
    private List<NativeADDataRef> mlist;
    protected AQuery $;
    private NativeADDataRef adItem;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native);

        mTextView = (TextView) findViewById(R.id.textView1);

        $ = new AQuery(this);
        $.id(R.id.loadNative).clicked(this, "loadAD");
        $.id(R.id.showNative).clicked(this, "showAD").enabled(false);
    }

    public void loadAD() {
        if (nativeAd == null) {
            nativeAd = new IFLYNativeAd(this,
                    "995AB51CA8EC3F31BC0E1DD113D4E87A", this);
        }
        mTextView.setText("requesting");
        int count = 1; // 一次拉取的广告条数,当前仅支持一条
        nativeAd.loadAd(count);
    }

    public void showAD() {
        $.id(R.id.ad_source_mark).text(adItem.getAdSourceMark() + "|广告");
        $.id(R.id.img_logo).image((String) adItem.getIcon(), false, true);
        if (adItem.getImgUrls() != null && adItem.getImgUrls().size() > 0) {
            $.id(R.id.img_poster).image(adItem.getImgUrls().get(0), false, true);
        } else {
            $.id(R.id.img_poster).image(adItem.getImage(), false, true);
        }
        $.id(R.id.text_name).text((String) adItem.getTitle());
        $.id(R.id.text_desc).text((String) adItem.getSubTitle());
        $.id(R.id.btn_download).clicked(new OnClickListener() {
            @Override
            public void onClick(View view) {
                adItem.onClicked(view);
            }
        });
        //原生广告需上传点击位置
        findViewById(R.id.btn_download).setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        nativeAd.setParameter(AdKeys.CLICK_POS_DX, event.getX() + "");
                        nativeAd.setParameter(AdKeys.CLICK_POS_DY, event.getY() + "");
                        break;
                    case MotionEvent.ACTION_UP:
                        nativeAd.setParameter(AdKeys.CLICK_POS_UX, event.getX() + "");
                        nativeAd.setParameter(AdKeys.CLICK_POS_UY, event.getY() + "");
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

        if (adItem.onExposured(this.findViewById(R.id.nativeADContainer))) {
            Log.d("", "曝光成功");
        }
    }

    @Override
    public void onAdFailed(AdError error) {
        // TODO Auto-generated method stub
        Log.d("onAdFailed", error.getErrorDescription());
        mTextView.setText("" + error.getErrorCode());
    }

    @Override
    public void onADLoaded(List<NativeADDataRef> lst) {
//		Log.d("onADLoaded", lst + ""+Thread.currentThread().toString());
        mTextView.setText("success");
        if (lst.size() > 0) {

            adItem = lst.get(0);
            $.id(R.id.showNative).enabled(true);
            Toast.makeText(this, "原生广告加载成功", Toast.LENGTH_LONG).show();
        } else {
            Log.i("AD_DEMO", "NOADReturn");
        }
    }

    @Override
    public void onCancel() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onConfirm() {
        // TODO Auto-generated method stub
    }
}
