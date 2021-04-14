package com.example.testprocessor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.lib_annotation.annotation.DefaultAnnotation;
import com.example.lib_annotation.annotation.ViewById;
import com.example.lib_annotation.annotation.ViewMethod;


public class MainActivity extends AppCompatActivity {

    @DefaultAnnotation("888788")
    private String wyc;

    @ViewById(id = R.id.anno_text)
    private TextView textView_wyc8878;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @ViewMethod(id = 888)
    public String getName(Object ff,Integer dd){
        return "wyc";
    }
}