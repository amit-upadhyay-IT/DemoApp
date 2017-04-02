package krishikalyan.aupadhyay.myapp.pest_detection;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import krishikalyan.aupadhyay.myapp.R;

public class PestDetectionActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pest_detection);

        webView = (WebView) findViewById(R.id.pest_detection_web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://192.168.43.186:8000/");
    }
}
