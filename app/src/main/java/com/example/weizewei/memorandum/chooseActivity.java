package com.example.weizewei.memorandum;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.sql.BatchUpdateException;

public class chooseActivity extends Activity implements View.OnClickListener{
    public Button btnLook;
    public Button btndelete;
    public Button btnmodify;
    public Button btnshare;
    public Button btnAlarm;
    /*
    *此活动时通过startActivityforresult启动的
    * 故只会根据用户的选择返回不同的判断值
    * 然后具体在逻辑处理仍旧时在MainActivity中处理
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        btnLook=(Button)findViewById(R.id.look);
        btndelete=(Button)findViewById(R.id.delete);
        btnmodify=(Button)findViewById(R.id.modify);
        btnshare=(Button)findViewById(R.id.share);
        btnAlarm=(Button)findViewById(R.id.alarm_set);
        btnAlarm.setOnClickListener(this);
        btndelete.setOnClickListener(this);
        btnmodify.setOnClickListener(this);
        btnLook.setOnClickListener(this);
        btnshare.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent();
        switch (view.getId())
        {
            case R.id.look:
                //返回判断值1
                intent.putExtra("judge",1);
                setResult(RESULT_OK,intent);
                finish();
                break;
            case R.id.delete:
                //返回判断值2
                intent.putExtra("judge",2);
                setResult(RESULT_OK,intent);
                finish();
                break;
            case R.id.modify:
                //返回判断值3
                intent.putExtra("judge",3);
                setResult(RESULT_OK,intent);
                finish();
                break;
            case R.id.share:
                Intent intent1=new Intent(Intent.ACTION_SEND);
                intent1.setType("text/plain");
                //分享文本内容
                intent1.putExtra(Intent.EXTRA_TEXT,getIntent().getStringExtra("content"));
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(intent1,getTitle()));
                break;
            case R.id.alarm_set:
                intent.putExtra("judge",4);
                setResult(RESULT_OK,intent);
                finish();
                break;


        }
    }
}
