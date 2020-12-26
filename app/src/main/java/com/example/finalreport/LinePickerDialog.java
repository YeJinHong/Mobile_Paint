package com.example.finalreport;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import static java.lang.Integer.parseInt;

public class LinePickerDialog extends Dialog
                        implements View.OnClickListener{

    private MainActivity mActivity;
    // 두께 조절을 위한 SeekBar
    private SeekBar sb;
    private static int line_width =4;
    private TextView tv;

    public LinePickerDialog(Context context){
        super(context);
        mActivity = (MainActivity) context;
    }
    public LinePickerDialog(Context context, int themeResId){
        super(context, themeResId);
        mActivity = (MainActivity) context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.width_dialog);
        setTitle("Pick Line Width");

        findViewById(R.id.width2).setOnClickListener(this);
        findViewById(R.id.width6).setOnClickListener(this);
        findViewById(R.id.width10).setOnClickListener(this);
        findViewById(R.id.width14).setOnClickListener(this);
        findViewById(R.id.selectBtn).setOnClickListener(this);

        tv = (TextView)findViewById(R.id.textView);
        tv.setText("current width:"+ line_width +"dp");
        sb  = (SeekBar)findViewById(R.id.seekBar1);
        sb.setProgress(line_width);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                line_width = progress;
                tv.setText("current width:"+ line_width +"dp");
            }
        });
    }

    @Override
    public void onClick(View view) {
       if(view.getId() == R.id.selectBtn){
           mActivity.mSetLineWidth(line_width);
           this.dismiss();
       }
       else{
           line_width = parseInt(view.getTag().toString());
           tv.setText("current width:"+ line_width +"dp");
       }
    }


}
