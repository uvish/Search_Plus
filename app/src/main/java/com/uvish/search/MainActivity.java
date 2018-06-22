package com.uvish.search;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.Loader;
import android.support.v4.graphics.drawable.TintAwareDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
   WebView wv;
    FloatingActionButton btn;
    FloatingActionButton btnError;
    RelativeLayout layout;
    String query;
    ProgressBar pb;
    TextView progressText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        query="https://www.google.co.in";
        pb=(ProgressBar)findViewById(R.id.pb);
        progressText=(TextView)findViewById(R.id.progress) ;
        wv=(WebView)findViewById(R.id.wv);
        layout=(RelativeLayout)findViewById(R.id.layout);

        wv.setWebViewClient(new myViewClient());
        wv.setWebChromeClient(new WebChromeClient(){
            public void onProgressChanged(WebView view,int progress)
            {
                if(progress<100 && pb.getVisibility()==ProgressBar.GONE) {
                    pb.setVisibility(ProgressBar.VISIBLE);
                    btn.setAlpha(0.4f);

                }
                if(progress==100 && pb.getVisibility()==ProgressBar.VISIBLE)
                {
                    pb.setVisibility(ProgressBar.GONE);
                    btn.setAlpha(1.0f);
                }
            }
        });
        WebSettings ws=wv.getSettings();
        btn=(FloatingActionButton)findViewById(R.id.button);
        btnError=(FloatingActionButton)findViewById(R.id.buttonError);

        btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                query="";


                AlertDialog.Builder ad=new AlertDialog.Builder(MainActivity.this);
                ad.setTitle("Query");
                ad.setIcon(R.drawable.ic_tune_black_24dp);
                ad.setNeutralButton("CANCEL", null);
                ad.setNegativeButton("More", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                     wv.loadUrl("https://www.exploit-db.com/google-hacking-database/");
                    }
                });

                //dialog layout
                DisplayMetrics dm=new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(dm);
                int width=dm.widthPixels;
                LinearLayout outer=new LinearLayout(MainActivity.this);
                outer.setOrientation(LinearLayout.HORIZONTAL);
                outer.setMinimumWidth(width);
                LinearLayout first=new LinearLayout(MainActivity.this);
                first.setOrientation(LinearLayout.VERTICAL);
                first.setMinimumWidth(width/2);
                LinearLayout second=new LinearLayout(MainActivity.this);
                second.setOrientation(LinearLayout.VERTICAL);
                second.setMinimumWidth(width/2);


               final EditText intext=new EditText(MainActivity.this);
                     intext.setHint("In Text");intext.setTextAlignment(EditText.TEXT_ALIGNMENT_CENTER);
                final EditText allintext=new EditText(MainActivity.this);
                    allintext.setHint("All In Text");allintext.setTextAlignment(EditText.TEXT_ALIGNMENT_CENTER);
                final EditText inurl=new EditText(MainActivity.this);
                    inurl.setHint("In URL");inurl.setTextAlignment(EditText.TEXT_ALIGNMENT_CENTER);
                final EditText allinurl=new EditText(MainActivity.this);
                    allinurl.setHint("All In URL");allinurl.setTextAlignment(EditText.TEXT_ALIGNMENT_CENTER);
                final EditText intitle=new EditText(MainActivity.this);
                    intitle.setHint("In Title");intitle.setTextAlignment(EditText.TEXT_ALIGNMENT_CENTER);
                final EditText allintitle=new EditText(MainActivity.this);
                    allintitle.setHint("All In Title");allintitle.setTextAlignment(EditText.TEXT_ALIGNMENT_CENTER);
                final EditText filetype=new EditText(MainActivity.this);
                    filetype.setHint("FileType");filetype.setTextAlignment(EditText.TEXT_ALIGNMENT_CENTER);
                final EditText related=new EditText(MainActivity.this);
                    related.setHint("Related");related.setTextAlignment(EditText.TEXT_ALIGNMENT_CENTER);
               final EditText ext=new EditText(MainActivity.this);
                    ext.setHint("Extension");ext.setTextAlignment(EditText.TEXT_ALIGNMENT_CENTER);
               final EditText site=new EditText(MainActivity.this);
                    site.setHint("Site");site.setTextAlignment(EditText.TEXT_ALIGNMENT_CENTER);
               final EditText cache=new EditText(MainActivity.this);
                    cache.setHint("Cache");cache.setTextAlignment(EditText.TEXT_ALIGNMENT_CENTER);

                first.addView(intext);
                first.addView(allintext);
                first.addView(inurl);
                first.addView(allinurl);
                first.addView(intitle);
                first.addView(allintitle);
                second.addView(filetype);
                second.addView(related);
                second.addView(ext);
                second.addView(site);
                second.addView(cache);
                outer.addView(first);
                outer.addView(second);
                ad.setView(outer);
                ad.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        InputMethodManager im=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        im.hideSoftInputFromWindow(wv.getWindowToken(),0);


                      if(!(intext.getText().toString()).equals(""))
                      {query=query.concat("intext:").concat(intext.getText().toString()).concat(" ");}
                        if(!(allintext.getText().toString()).equals(""))
                        { query=query.concat("allintext:").concat(allintext.getText().toString()).concat(" ");}
                        if(!(inurl.getText().toString()).equals(""))
                        { query=query.concat("inurl:").concat(inurl.getText().toString()).concat(" ");}
                        if(!(allinurl.getText().toString()).equals(""))
                            query=query.concat("allinurl:").concat(allinurl.getText().toString()).concat(" ");
                        if(!(intitle.getText().toString()).equals(""))
                            query=query.concat("intitle:").concat(intitle.getText().toString()).concat(" ");
                        if(!(allintitle.getText().toString()).equals(""))
                            query=query.concat("allintitle:").concat(allintitle.getText().toString()).concat(" ");
                        if(!(filetype.getText().toString()).equals(""))
                            query=query.concat("filetype:").concat(filetype.getText().toString()).concat(" ");
                        if(!(related.getText().toString()).equals(""))
                            query=query.concat("related:").concat(related.getText().toString()).concat(" ");
                        if(!(ext.getText().toString()).equals(""))
                            query=query.concat("ext:").concat(ext.getText().toString()).concat(" ");
                        if(!(site.getText().toString()).equals(""))
                            query=query.concat("site:").concat(site.getText().toString()).concat(" ");
                        if(!(cache.getText().toString()).equals(""))
                            query=query.concat("cache:").concat(cache.getText().toString()).concat(" ");
                        update(wv);


                    }
                });

                AlertDialog add = ad.create();

                add.show();
                return false;
            }
        });


        ws.setJavaScriptEnabled(true);
        ws.setSupportZoom(true);
        if(!DataConnection.checkInternetConnection(this)) {
            btn.setBackgroundResource(R.drawable.ic_error_black_24dp);
            btn.setBackgroundColor(Color.RED);
            Toast.makeText(this, "No Internet !", Toast.LENGTH_SHORT).show();
        }
        else
        {
            btn.setBackgroundResource(R.drawable.ic_search_black_24dp);
            btn.setBackgroundColor(Color.parseColor("#1a9fcf"));
            wv.loadUrl(query);}

    }


    public void update(View v)
    {
        String load="https://www.google.co.in/search?q=";
        if(!DataConnection.checkInternetConnection(this)) {
            btnError.setVisibility(FloatingActionButton.VISIBLE);
            btn.setVisibility(FloatingActionButton.INVISIBLE);
            Toast.makeText(this, "No Internet !", Toast.LENGTH_SHORT).show();
        }
        else {
            btnError.setVisibility(FloatingActionButton.INVISIBLE);
            btn.setVisibility(FloatingActionButton.VISIBLE);
            wv.loadUrl(load.concat(query));
        }
    }

    @Override
    public void onBackPressed() {
       if(wv.canGoBack())wv.goBack();
    }
}
