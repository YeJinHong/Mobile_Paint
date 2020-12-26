package com.example.finalreport;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class ColorPickerDialog extends Dialog implements View.OnClickListener {

    private MainActivity mActivity;

    public ColorPickerDialog(Context context){
        super(context);
        mActivity = (MainActivity) context;
    }

    public ColorPickerDialog(Context context, int themeResId){
        super(context, themeResId);
        mActivity = (MainActivity) context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_dialog);
        setTitle("Pick Line Color");

        findViewById(R.id.black).setOnClickListener(this);
        findViewById(R.id.blue).setOnClickListener(this);
        findViewById(R.id.cyan).setOnClickListener(this);
        findViewById(R.id.gray).setOnClickListener(this);
        findViewById(R.id.green).setOnClickListener(this);
        findViewById(R.id.magenta).setOnClickListener(this);
        findViewById(R.id.red).setOnClickListener(this);
        findViewById(R.id.yellow).setOnClickListener(this);

        findViewById(R.id.rgbBtn).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int color = 0;
        if(view.getId()==R.id.rgbBtn){
            new ColorPickerDialog_rgb(mActivity, android.R.style.Theme_Holo_Light_Dialog).show();
            this.dismiss();
        }
        switch(view.getId()){
            case R.id.black:
                color = Color.BLACK;
                break;
            case R.id.blue:
                color = Color.BLUE;
                break;
            case R.id.cyan:
                color = Color.CYAN;
                break;
            case R.id.gray:
                color = Color.GRAY;
                break;
            case R.id.green:
                color = Color.GREEN;
                break;
            case R.id.magenta:
                color = Color.MAGENTA;
                break;
            case R.id.red:
                color = Color.RED;
                break;
            case R.id.yellow:
                color = Color.YELLOW;
                break;
        }
        mActivity.mSetLineColor(color);
        dismiss();
    }
}
