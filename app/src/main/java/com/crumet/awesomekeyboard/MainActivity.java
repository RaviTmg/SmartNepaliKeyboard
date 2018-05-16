package com.crumet.awesomekeyboard;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(MainActivity.this, isThisKeyboardSetAsDefaultIME(MainActivity.this)+"", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS));
            }
        });
        findViewById(R.id.select_keyboard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imeManager = (InputMethodManager) getApplicationContext().getSystemService(INPUT_METHOD_SERVICE);
                imeManager.showInputMethodPicker();
            }
        });
    }
    public static boolean isThisKeyboardSetAsDefaultIME(Context context) {
        final String defaultIME = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ENABLED_INPUT_METHODS);
        if (TextUtils.isEmpty(defaultIME))
            return false;
        ComponentName defaultInputMethod = ComponentName.unflattenFromString(defaultIME);
//        return defaultInputMethod.getPackageName().equals(myPackageName);
        return defaultIME.contains("com.crumet.awesomekeyboard");

    }
    public boolean isInputMethodEnabled() {
        String id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.DEFAULT_INPUT_METHOD);

        ComponentName defaultInputMethod = ComponentName.unflattenFromString(id);

        ComponentName myInputMethod = new ComponentName(this, SimpleIme.class);

        return myInputMethod.equals(defaultInputMethod);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
