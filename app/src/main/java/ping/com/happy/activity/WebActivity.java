package ping.com.happy.activity;

import android.net.http.SslError;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import ping.com.happy.R;
import ping.com.happy.utils.JLog;
import ping.com.happy.utils.ToastUtil;

public class WebActivity extends AppCompatActivity {

    private WebView webView;
    private WebSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        webView = (WebView) findViewById(R.id.news_details);
        settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view,
                                           SslErrorHandler handler, SslError error) {
                JLog.i("-----------onReceivedSslError------------------");
                handler.proceed();// 接受证书
            }



        });

        String url = getIntent().getStringExtra("news");
        webView.loadUrl(url);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK==keyCode){
            ToastUtil.showToast(this,"返回键被点击了");
            if (webView.canGoBack()){
                webView.goBack();
                return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }
}
