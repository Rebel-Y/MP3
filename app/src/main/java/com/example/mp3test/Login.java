package com.example.mp3test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText name;
    private EditText password;
    private Button login;
   // private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name=(EditText)findViewById(R.id.et_name);
        password=(EditText)findViewById(R.id.et_password);
        login=(Button)findViewById(R.id.btn_login);
       // textView=(TextView)findViewById(R.id.textView);
        SQLiteDatabase db;
        OpenHelper openHelper=new OpenHelper(this,"user.db",null,1);//创建数据库
        db=openHelper.getWritableDatabase();//数据库可写
        db.execSQL("drop table if exists mytable");
        db.execSQL("create table mytable(username varchar(10),pwd varchar(10))");
        db.execSQL("insert into mytable values('admin','123456')");
       // Cursor cursor=db.rawQuery("select * from mytable where username='admin' and pwd='123456'",null);
       // int result=cursor.getCount();
        //if(result==1)
        //{
        //    textView.setText("登录成功");
       // }
       // else
       //     textView.setText("登录失败");

        //按钮事件
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=name.getText().toString();
                String userpassword=password.getText().toString();
                if(username.equals("admin")&&(userpassword.equals(("123456"))))
                {
                    Toast.makeText(Login.this,"登录成功",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Login.this,Main3Activity.class);
                    startActivity( intent);
                    finish();
                }
                else
                    Toast.makeText(Login.this,"登录失败",Toast.LENGTH_SHORT).show();
            }
        });







    }






    public class OpenHelper extends SQLiteOpenHelper{

        public OpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
