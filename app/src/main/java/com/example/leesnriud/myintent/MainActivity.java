package com.example.leesnriud.myintent;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * android intent
 */
public class MainActivity extends AppCompatActivity {

    private Intent intent;
    private Bundle bundle;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_one, R.id.bt_two, R.id.bt_three, R.id.bt_intent_array, R.id.bt_intent_list, R.id.bt_intent_list2, R.id.bt_intent_map, R.id.bt_intent_object, R.id.bt_intent_all, R.id.bt_intent_bitmap})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_one:
                //显式intent
                Intent it = new Intent();
                it.setAction(Intent.ACTION_MAIN);
                it.addCategory(Intent.CATEGORY_HOME);
                startActivity(it);
                break;
            case R.id.bt_two:
                //隐式intent 预定义动作
                Intent it2 = new Intent();
                it2.setAction(Intent.ACTION_VIEW);
                startActivity(it2);
                break;
            case R.id.bt_three:
                //隐式intent 自定义动作
                Intent it3 = new Intent();
                it3.setAction("my_action");
                it3.addCategory("my_category");
                startActivity(it3);
                break;
            case R.id.bt_intent_array:
                //传递数组
                intent = new Intent(MainActivity.this,DataActivity.class);
                bundle = new Bundle();
                bundle.putStringArray("stringarray",new String[]{"111","222","333"});
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.bt_intent_list:
                //传递普通list    list<string>
                intent = new Intent(MainActivity.this,DataActivity.class);
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add("string111");
                arrayList.add("string222");
                arrayList.add("string333");
                intent.putStringArrayListExtra("stringarraylist",arrayList);
                startActivity(intent);
                break;
            case R.id.bt_intent_list2:
                //传递list<object>
                intent = new Intent(MainActivity.this,DataActivity.class);
                ArrayList<Person> personArrayList = new ArrayList<Person>();
                personArrayList.add(new Person("张三",10));
                personArrayList.add(new Person("李四",23));
                personArrayList.add(new Person("王五",12));
                bundle = new Bundle();
                bundle.putSerializable("personarraylist",personArrayList);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.bt_intent_map:
                //传递map
                //传递复杂些的参数
                Map<String, Person> map1 = new HashMap<String, Person>();
                map1.put("key1", new Person("string11",11));
                map1.put("key2", new Person("string22",22));
                ArrayList<Map<String, Person>> list = new ArrayList<Map<String, Person>>();
                list.add(map1);

                intent = new Intent();
                intent.setClass(MainActivity.this,DataActivity.class);
                bundle = new Bundle();

                //须定义一个list用于在budnle中传递需要传递的ArrayList<Object>,这个是必须要的
                ArrayList bundlelist = new ArrayList();
                bundlelist.add(list);
                bundle.putParcelableArrayList("list",bundlelist);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.bt_intent_object:
                //传递对象
                intent = new Intent(MainActivity.this,DataActivity.class);
                Person person = new Person("lee",42);
                intent.putExtra("person",person);
                startActivity(intent);
                break;
            case R.id.bt_intent_bitmap:
                //传递bitmap
                Resources res=getResources();
                Bitmap bmp=BitmapFactory.decodeResource(res, R.drawable.ic_bug);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] bytes=baos.toByteArray();

                Bundle b = new Bundle();
                b.putByteArray("bitmap", bytes);

                Intent intent = new Intent(this, DataActivity.class);
                intent.putExtras(b);
                startActivity(intent);
                break;
            case R.id.bt_intent_all:
                //全局定义
                Toast.makeText(MainActivity.this,"可查看项目中MyApp文件",Toast.LENGTH_LONG).show();
                break;

        }
    }

}
