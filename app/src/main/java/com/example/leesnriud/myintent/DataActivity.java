package com.example.leesnriud.myintent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DataActivity extends AppCompatActivity {

    @BindView(R.id.iv)
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        ButterKnife.bind(this);

        //接收数组
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String[] str = bundle.getStringArray("stringarray");
        Log.e("111", Arrays.toString(str));

        //接收list 基本类型
        Intent intent1 = getIntent();
        ArrayList<String> arr = intent1.getStringArrayListExtra("stringarraylist");
        if (arr != null) {
            for (String a : arr) {
                Log.e("111", a);
            }
        }

        //接收list 对象
        Intent intent2 = getIntent();
        Bundle bundle2 = intent2.getExtras();
        ArrayList<Person> personArrayList = (ArrayList<Person>) bundle2.getSerializable("personarraylist");
            if(personArrayList!=null) {
                for (Person p : personArrayList) {
                    Log.e("111", p.toString());
                }
            }

        //接收 map
        Intent intent3 = getIntent();
        Bundle bundle3 = intent3.getExtras();
            ArrayList bundlelist = bundle3.getParcelableArrayList("list");
            if (bundlelist != null) {
            for (int i = 0; i < bundlelist.size(); i++) {
                ArrayList<Map<String, Person>> mapArrayList = (ArrayList<Map<String, Person>>) bundlelist.get(i);
                for (int j = 0; j < mapArrayList.size(); j++) {
                    Map<String, Person> map = mapArrayList.get(j);
                    for (String key : map.keySet()) {
                        Log.e("111", map.get(key) + "");
                    }
                }
            }
        }

        //接收对象
        Intent intent4 = getIntent();
        Person person = (Person) intent4.getSerializableExtra("person");
        if(person!=null) {
            Log.e("111", person.toString());
        }


        //接收bitmap
        Intent intent5 = getIntent();
        Bundle b=intent.getExtras();
        byte[] bytes=b.getByteArray("bitmap");
        Bitmap bmp= BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        iv.setImageBitmap(bmp);
    }
}
