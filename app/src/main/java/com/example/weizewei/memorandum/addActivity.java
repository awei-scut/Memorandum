package com.example.weizewei.memorandum;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.List;

public class addActivity extends AppCompatActivity {
    public EditText ct;
    public TimePicker time_choose;
    public schedule sche=new schedule();
    public Button confirm;
    public Button cancel;
    public Boolean aBoolean=false;
    public int hour;
    public int min;
    //判断来自修改还是增加
    public Boolean bBoolean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //实例化各控件
        ct=(EditText)findViewById(R.id.content);
        confirm=(Button)findViewById(R.id.confirm);
        cancel=(Button)findViewById(R.id.cancel);
        time_choose=(TimePicker)findViewById(R.id.time_choose);
        //作出作出判断，如果传入intent有数据，则是更改日程
        bBoolean=getIntent().getStringExtra("hour")!=null&&getIntent().getStringExtra("min")!=null;
        if(bBoolean)
        {   hour=Integer.parseInt(getIntent().getStringExtra("hour"));
            min=Integer.parseInt(getIntent().getStringExtra("min"));
            time_choose.setHour(hour);
            time_choose.setMinute(min);
            ct.setText(getIntent().getStringExtra("content"));
        }
        //设置时间选择控件的转换监听
        time_choose.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                sche.time=i+":"+i1+"";
                aBoolean=true;
            }
        });
        //
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!aBoolean)
                {
                    sche.time=hour+":"+min;
                }
                else
                {
                    aBoolean=false;
                }
                if(bBoolean)
                {
                    sche.content=ct.getText().toString();
                    Intent intent=new Intent();
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("add_data",sche);
                    intent.putExtra("add",bundle);
                    setResult(2,intent);
                    finish();
                } //返回的resultcode不同
                else {
                    sche.content = ct.getText().toString();
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("add_data", sche);
                    intent.putExtra("add", bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
        //
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED,intent);
                finish();
            }
        });
  }

}
