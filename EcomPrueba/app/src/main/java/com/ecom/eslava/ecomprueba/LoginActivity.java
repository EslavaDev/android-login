package com.ecom.eslava.ecomprueba;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

public class LoginActivity extends AppCompatActivity {

    //Variables
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    CardView _login;
    EditText _txtEmail, _txtPassword;
    TextView registertxt;
    Cursor cursor;

    //metodo OnCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Instancias
        openHelper = new DatabaseHelper(this);
        db=openHelper.getReadableDatabase();

        _login = (CardView) findViewById(R.id.cvLogin);
        _txtEmail = (EditText)findViewById(R.id.txtEmail);
        _txtPassword = (EditText)findViewById(R.id.txtPassword);
        registertxt = (TextView)findViewById(R.id.textView2);

        //onClick
        registertxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        _login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = _txtEmail.getText().toString();
                String password = _txtPassword.getText().toString();
                cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COL_5 + "=? AND " + DatabaseHelper.COL_4 + "=?", new String[]{email, password});
                if(cursor != null){
                    if(cursor.getCount() > 0){
                        cursor.moveToNext();
                        Toast.makeText(getApplicationContext(),"login successfully", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"error ", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"el usuario no existe",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}

