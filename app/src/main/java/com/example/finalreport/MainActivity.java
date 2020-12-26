package com.example.finalreport;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private PaintView mView;
    private boolean mIsEmboss;
    private boolean mIsBlur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mView = (PaintView)findViewById(R.id.PaintView);
        ((Button)findViewById(mView.mGetType())).setTextColor(Color.RED);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.menu_emboss).setChecked(mIsEmboss);
        menu.findItem(R.id.menu_blur).setChecked(mIsBlur);
        return super.onPrepareOptionsMenu(menu);
    }

    // menu 버튼 기능 구현
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.PickLineWidth:
                new LinePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog).show();
                return true;
            case R.id.PickColor:
                new ColorPickerDialog(this, android.R.style.Theme_Holo_Light_Dialog).show();
                return true;
            case R.id.menu_emboss:
                mIsEmboss = !mIsEmboss;
                if (mIsEmboss) mIsBlur = false;
                mView.mSetFilter(mIsEmboss, mIsBlur);
                break;
            case R.id.menu_blur:
                mIsBlur = !mIsBlur;
                if (mIsBlur) mIsEmboss = false;
                mView.mSetFilter(mIsEmboss, mIsBlur);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        Button btnEmboss = (Button)findViewById(R.id.btnEmboss);
        Button btnBlur = (Button)findViewById(R.id.btnBlur);
        btnEmboss.setTextColor(mIsEmboss ? Color.RED : Color.BLACK);
        btnBlur.setTextColor(mIsBlur ? Color.RED : Color.BLACK);
        return true;
    }

    //0-1 Width
    public void mSetLineWidth(int width){
        mView.mSetLineWidth(width);
    }

    public void mSetLineColor(int color){
        mView.mSetLineColor(color);
    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.btnLine:
            case R.id.btnRect:
            case R.id.btnCirc:
            case R.id.btnCurve:
            case R.id.btnErase:
                ((Button) findViewById(R.id.btnLine)).setTextColor(Color.BLACK);
                ((Button) findViewById(R.id.btnRect)).setTextColor(Color.BLACK);
                ((Button) findViewById(R.id.btnCirc)).setTextColor(Color.BLACK);
                ((Button) findViewById(R.id.btnCurve)).setTextColor(Color.BLACK);
                ((Button) findViewById(R.id.btnErase)).setTextColor(Color.BLACK);
                ((Button) v).setTextColor(Color.RED);
                mView.mSetType(v.getId());
                return;
            case R.id.btnClear:
                new AlertDialog.Builder(this).setTitle("Do you want to clear?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mView.mSetClear();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();
                return;
            case R.id.btnEmboss:
                mIsEmboss = !mIsEmboss;
                if (mIsEmboss) mIsBlur = false;
                mView.mSetFilter(mIsEmboss, mIsBlur);
                break;
            case R.id.btnBlur:
                mIsBlur = !mIsBlur;
                if (mIsBlur) mIsEmboss = false;
                mView.mSetFilter(mIsEmboss, mIsBlur);
                break;
        }
        Button btnEmboss = (Button) findViewById(R.id.btnEmboss);
        Button btnBlur = (Button) findViewById(R.id.btnBlur);
        btnEmboss.setTextColor(mIsEmboss ? Color.RED : Color.BLACK);
        btnBlur.setTextColor(mIsBlur ? Color.RED : Color.BLACK);
    }

    public int mGetLineColor(){
        return mView.mGetLineColor();
    }

}
