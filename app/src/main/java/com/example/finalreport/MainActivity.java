package com.example.finalreport;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int linewidth = 2;
    int color = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // 임시 메소드
        switch(item.getItemId()){
            case R.id.PickLineWidth:
                showLineWidth();
                return true;
            case R.id.PickColor:
                showColors();
                return true;
            default:
                Toast.makeText(this, "Other", Toast.LENGTH_SHORT).show();
                return true;
        }
    }

    //0-1 Width
    public void showLineWidth(){
        final Dialog widthDialog = new Dialog(this);
        widthDialog.setContentView(R.layout.width_dialog);
        widthDialog.setTitle("Pick Line Width");

        final View width2 = widthDialog.findViewById(R.id.view2);
        final View width6 = widthDialog.findViewById(R.id.view6);
        final View width10 = widthDialog.findViewById(R.id.view10);
        final View width14 = widthDialog.findViewById(R.id.view14);

        // linewidth라는 변수에 길이 저장. 이를 바탕으로 펜 두께 조절. 차후 수정.
        width2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                linewidth = width2.getHeight();
                widthDialog.dismiss();
            }
        });
        widthDialog.show();

    }

    //0-2 Color
    public void showColors(){
        final Dialog colorDialog = new Dialog(this);
        final PaintView paintView = findViewById(R.id.PaintView);

        colorDialog.setContentView(R.layout.color_dialog);
        colorDialog.setTitle("Pick Line Color");
        View blue = colorDialog.findViewById(R.id.blue);

        // 전체 색에 대해 처리가 안되어있음.
        blue.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                paintView.setColor(v.getTag().toString());
                colorDialog.dismiss();
            }
        });
        colorDialog.show();

    }

    //1-1. Line
    public void onClick_Mode(View v){

    }

    //2-2. Clear
    public void onClick_Clear(View v){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Do you want to clear?");
        alertDialogBuilder.setNegativeButton("No",
            new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface arg0, int arg1){
                    finish();
                }
            });

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface arg0, int arg1){
                        final PaintView paintView = findViewById(R.id.PaintView);
                        paintView.clear();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


}