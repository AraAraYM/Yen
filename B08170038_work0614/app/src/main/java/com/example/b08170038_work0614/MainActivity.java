package com.example.b08170038_work0614;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText inputText;
    private ListView listInput;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {  /**初始化每個元件*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputText = (EditText)findViewById(R.id.inputText);
        listInput = (ListView)findViewById(R.id.listInputText);
        items = new ArrayList<String>();         /**ArrayList動態新增ListView的item*/
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,items);
        listInput.setAdapter(adapter);
        listInput.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View view, int position, long id) {
                final int pos = position;         /**要刪除一個項目，需要長案跳出對話框*/
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("刪除列")
                        .setMessage("你確定要刪除？")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                               /**詢問是否真的要刪除項目*/
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                items.remove(pos);
                                listInput.setAdapter(adapter);
                            }
                        })
                        .setNegativeButton("否", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();

                return false;
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, Menu.FIRST, Menu.NONE, "新增");
        menu.add(Menu.NONE, Menu.FIRST + 1, Menu.NONE, "離開程式");
        return super.onCreateOptionsMenu(menu);  /**在選單中加入跳出新增跟離開App的選項*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {  /**按下選項後的處理*/
        switch (item.getItemId()) {
            case Menu.FIRST:
                if(!inputText.getText().toString().equals("")){
                    items.add(inputText.getText().toString());
                    listInput.setAdapter(adapter);
                    inputText.setText("");  /**新增一個項目，讓列表更新，讓輸入空白可以重新輸入*/
                }
                break;
            case Menu.FIRST + 1:
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("離開此程式")
                        .setMessage("你確定要離開？")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                           /**跳出對話框詢問是否真的要離開App*/
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("否", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}