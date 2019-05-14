package practice.com.eltelinks;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import practice.com.eltelinks.helper.CustonWebViewClient;

public class Browser_Fragment extends Fragment {
    private WebView webView;
    private ProgressBar progressBar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_browser, container, false);

        progressBar = view.findViewById(R.id.wv_progress);
        webView = view.findViewById(R.id.website_view);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        CustonWebViewClient webViewClient = new CustonWebViewClient(progressBar);
        webView.setWebViewClient(webViewClient);


        String url = getArguments().getString("chosenURL");
        if (url != null){
            webView.loadUrl(url);
        }else {
            webView.loadUrl("https://www.elte.hu/en/");
        }


        return view;
    }

}
