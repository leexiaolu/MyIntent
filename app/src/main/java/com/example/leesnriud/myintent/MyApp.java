package com.example.leesnriud.myintent;

import android.app.Application;

/**
 * Created by lee.snriud on 2018/3/15.
 */

public class MyApp extends Application{

    private String myState;
    private static MyApp instance;

    public static MyApp getInstance(){
        return instance;
    }


    public String getState(){
        return myState;
    }
    public void setState(String s){
        myState = s;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

}
