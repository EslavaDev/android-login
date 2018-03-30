package com.ecom.eslava.ecomprueba;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    CardView _btnreg;
    TextView logintxt;
    EditText _txtfname, _txtlname, _txtemail, _txtpass, _txtphone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        openHelper= new DatabaseHelper(this);
        _btnreg = (CardView) findViewById(R.id.btnreg);
        _txtfname=(EditText)findViewById(R.id.txtfname);
        _txtlname=(EditText)findViewById(R.id.txtlname);
        _txtemail=(EditText)findViewById(R.id.txtemail);
        _txtpass=(EditText)findViewById(R.id.txtpass);
        _txtphone=(EditText)findViewById(R.id.txtphone);
        logintxt = (TextView)findViewById(R.id.login);

        _btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=openHelper.getWritableDatabase();
                String fname= _txtfname.getText().toString();
                String lname= _txtlname.getText().toString();
                String email= _txtemail.getText().toString();
                String password= _txtpass.getText().toString();
                String phone= _txtphone.getText().toString();
                insertData(fname,lname,email,password,phone);
                Toast.makeText(getApplicationContext(),"registro correctamente", Toast.LENGTH_LONG).show();

            }
        });

        logintxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void insertData(String fname, String lname, String email, String password, String phone){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_2, fname);
        contentValues.put(DatabaseHelper.COL_3, lname);
        contentValues.put(DatabaseHelper.COL_4, password);
        contentValues.put(DatabaseHelper.COL_5, email);
        contentValues.put(DatabaseHelper.COL_6, phone);

        long id = db.insert(DatabaseHelper.TABLE_NAME,null,contentValues);


    }
}
