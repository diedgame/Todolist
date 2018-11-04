package com.example.go.todolist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        final EditText editText=(EditText)findViewById(R.id.content) ;
        TextView textView=(TextView)findViewById(R.id.title);
        //接收数据
        Intent intent=getIntent();
        final int num=intent.getIntExtra("num",0);
        String name=intent.getStringExtra("a");
        textView.setText(name);
        //读取
        SharedPreferences preferences=getSharedPreferences("data",MODE_PRIVATE);
        String content1=preferences.getString("neilong"+num,"");
        editText.setText(content1);


        Button button=(Button)findViewById(R.id.change);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=getSharedPreferences("data",MODE_PRIVATE).edit();
                String content=editText.getText().toString();
                editor.putString("neilong"+num,content);
                editor.commit();
                }


        });
    }
}
