package org.cocos2dx.cpp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class UserInfoActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  
  
        Intent intent = getIntent();
        String string = "string";
          
        TextView textView = new TextView(this);
        textView.setTextSize(40);    
        textView.setText(string);    
            
        setContentView(textView);    
    }
}