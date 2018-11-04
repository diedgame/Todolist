package com.example.go.todolist;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public final List<String> list = new ArrayList<>();
    //保存
    void save()
    {SharedPreferences.Editor editor = getSharedPreferences("EnvironDataList", MODE_PRIVATE).edit();
        editor.putInt("EnvironNums", list.size());
        for (int i = 0; i < list.size(); i++)
        {
            editor.putString("item_"+i, list.get(i));
        }
        editor.commit();
   }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView listView = (ListView) findViewById(R.id.mylist);
        final EditText editText = (EditText) findViewById(R.id.mytext);
        Button add = (Button) findViewById(R.id.add);
        Button delete = (Button) findViewById(R.id.delete);
        Button change = (Button) findViewById(R.id.change);

        //读取
        SharedPreferences preferDataList = getSharedPreferences("EnvironDataList", MODE_PRIVATE);
        int environNums = preferDataList.getInt("EnvironNums", 0);
        for (int i = 0; i < environNums; i++)
        {
            String environItem = preferDataList.getString("item_"+i, null);
            list.add(environItem);
        }


        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editText.getText().toString().equals("")) {
                    list.add(editText.getText().toString());
                    adapter.notifyDataSetChanged();
                    editText.setText("");
                    save();

                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.size() > 0) {
                    editText.setText("");
                    list.remove(list.size() - 1);
                    adapter.notifyDataSetChanged();
                    //删除
                    SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                    editor.putString("neilong" + list.size(), "");
                    editor.commit();
                    save();
                }
            }
        });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editText.getText().toString().equals("")) {
                    list.remove(list.size() - 1);
                    list.add(editText.getText().toString());
                    adapter.notifyDataSetChanged();
                    editText.setText("");
                    save();
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                String name = adapter.getItem(i);
                intent.putExtra("a", name);
                intent.putExtra("num", i);
                startActivity(intent);
            }


        });

    }
}

