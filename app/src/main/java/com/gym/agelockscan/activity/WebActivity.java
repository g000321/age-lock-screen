package com.gym.agelockscan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.gym.R;


public class WebActivity extends Activity{
	private TextView titleTextView;
	private WebView webView;
	private ImageView backButton;
	private ImageView reLoadButton;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.base_activity_web_browse);
			initView();
			initWebView();
		}
		private void initWebView() {
			
			
			Intent intent=getIntent();
			titleTextView.setText(intent.getStringExtra("title"));
			
			// TODO Auto-generated method stub
			//webView.loadUrl("file:///android_asset/example.html");
			webView.loadUrl(intent.getStringExtra("url"));
			  //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
		       webView.setWebViewClient(new WebViewClient(){
		           @Override
		        public boolean shouldOverrideUrlLoading(WebView view, String url) {
		            // TODO Auto-generated method stub
		               //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
		             view.loadUrl(url);
		            return true;
		        }
		       });
		       WebSettings settings = webView.getSettings();
		       settings.setJavaScriptEnabled(true);
		       webView.setWebChromeClient(new WebChromeClient() {
		    	   
		            @Override
		            public void onProgressChanged(WebView view, int newProgress) {
		                // TODO Auto-generated method stub
		                if (newProgress == 100) {
		                    // 网页加载完成

		                } else {
		                    // 加载中

		                }

		            }
		        });
		  //     webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		}
		
		private void initView() {
			// TODO Auto-generated method stub
			backButton=(ImageView) findViewById(R.id.back);
			webView=(WebView) findViewById(R.id.webView);
			titleTextView=(TextView) findViewById(R.id.title);
			reLoadButton=(ImageView) findViewById(R.id.reload);
			reLoadButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					webView.reload();
				}
			});
			backButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					// TODO Auto-generated method stub
					  if(webView.canGoBack())
			          {
			              webView.goBack();//返回上一页面
			              
			          }
			          else
			          {
			        	  finish();
			          }
					
				
				}
			});
			
		}
		  @Override
		    public boolean onKeyUp(int keyCode, KeyEvent event) {
		        // TODO Auto-generated method stub
		        if(keyCode==KeyEvent.KEYCODE_BACK)
		        {
		            if(webView.canGoBack())
		            {
		                webView.goBack();//返回上一页面
		                return true;
		            }
		            else
		            {
		            	finish();
		                //System.exit(0);//退出程序
		            }
		        }
		        return super.onKeyDown(keyCode, event);
		    }
}
