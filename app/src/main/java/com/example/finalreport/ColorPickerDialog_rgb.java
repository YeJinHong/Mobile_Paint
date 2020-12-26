package com.example.finalreport;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class ColorPickerDialog_rgb extends Dialog implements View.OnClickListener {

    private MainActivity mActivity;
    private TextView colornumber;
    private Button selectBtn;
    private View color_preview;
    private SeekBar seekBarR;
    private SeekBar seekBarG;
    private SeekBar seekBarB;
    private int color_r;
    private int color_g;
    private int color_b;
    private int selected_color;


    public ColorPickerDialog_rgb(Context context){
        super(context);
        mActivity = (MainActivity) context;
    }
    public ColorPickerDialog_rgb(Context context, int themeResId){
        super(context, themeResId);
        mActivity = (MainActivity) context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_dialog_rgb);
        setTitle("Pick RGB Color");
        init();
    }

    protected void init(){
        colornumber = (TextView)findViewById(R.id.colornumber);
        color_preview = findViewById(R.id.colorPreview);
        seekBarR = (SeekBar) findViewById(R.id.seekBarR);
        seekBarG = (SeekBar) findViewById(R.id.seekBarG);
        seekBarB = (SeekBar) findViewById(R.id.seekBarB);

        seekBarR.setOnSeekBarChangeListener(new rgb());
        seekBarG.setOnSeekBarChangeListener(new rgb());
        seekBarB.setOnSeekBarChangeListener(new rgb());
        selectBtn = findViewById(R.id.selectBtn);

        //메인 액티비티에서 현재 색깔을 가져온다.
        selected_color = mActivity.mGetLineColor();
        //preview라는 View에서 현재 색깔을 볼 수 있다.
        color_preview.setBackgroundColor(selected_color);

        //현재 색깔은 각각의 r,g,b값으로 나뉘어 SeekBar에 반영된다.
        color_r = Color.red(selected_color);
        color_g = Color.green(selected_color);
        color_b = Color.blue(selected_color);

        //각 r,g,b값을 담당하는 SeekBar에 현재 컬러값 반영
        seekBarR.setProgress(color_r);
        seekBarG.setProgress(color_g);
        seekBarB.setProgress(color_b);

        //SeekBar를 통해 선택된 color값 반영
        selectBtn.setOnClickListener(this);
    }

    // 버튼에 지정되어있는 onclick 메소드
    // 버튼 클릭시 현 dialog에서 선택한 색깔인 selected_color가 반영된다.
    @Override
    public void onClick(View view){
        mActivity.mSetLineColor(selected_color);
        this.dismiss();
    }

    public class rgb implements SeekBar.OnSeekBarChangeListener{
        // 아래는 SeekBar값 반영을 위한 메소드
        // 상단 두개는 쓰이지 않는다.
        // selected_color는 최상단 View를 통해 미리볼 수 있다.
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {}
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {}
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            if(seekBar.getId()==R.id.seekBarR)
                color_r = progress;
            else if(seekBar.getId()==R.id.seekBarG)
                color_g = progress;
            else if(seekBar.getId()==R.id.seekBarB)
                color_b = progress;
            selected_color = Color.rgb(color_r, color_g, color_b);
            color_preview.setBackgroundColor(selected_color);
            colornumber.setText("current color : #"+Integer.toHexString(selected_color));
        }
    }
}
