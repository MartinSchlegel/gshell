package com.example.eduardo.gshell;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;


/**
 * Created by Hikmat on 24/11/2016.
 */

public class HostFormEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.host_form_activity);
        String filePath = getIntent().getExtras().getString("filepath");
        Server server = Server.load(filePath,MainActivity.global_password);

        String server_name = server.name;
        String server_user_name = server.user_name;
        String server_passwd = server.passwd;
        String server_hostname = server.hostname;

        EditText aliasEditText = (EditText) findViewById(R.id.aliasEditText);
        EditText usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        EditText hostnameEditText = (EditText) findViewById(R.id.hostnameEditText);
        EditText passwordEditText = (EditText) findViewById(R.id.passwordEditText);

        aliasEditText.setText(server_name, TextView.BufferType.EDITABLE);
        usernameEditText.setText(server_user_name, TextView.BufferType.EDITABLE);
        hostnameEditText.setText(server_hostname, TextView.BufferType.EDITABLE);
        passwordEditText.setText(server_passwd, TextView.BufferType.EDITABLE);
    }

    public void saveDetails(View view) {


        //Server server = Server.load(filePath);

        String filePath_old = getIntent().getExtras().getString("filepath");


        EditText aliasEditText = (EditText) findViewById(R.id.aliasEditText);
        EditText usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        EditText hostnameEditText = (EditText) findViewById(R.id.hostnameEditText);
        EditText passwordEditText = (EditText) findViewById(R.id.passwordEditText);


        String alias_new = aliasEditText.getText().toString();
        String username_new = usernameEditText.getText().toString();
        String hostname_new = hostnameEditText.getText().toString();
        String password_new = passwordEditText.getText().toString();

        Server server = new Server(alias_new, username_new, password_new, hostname_new);
        String filePath_new = getApplicationContext().getFilesDir().getAbsolutePath() + "/dataFiles";

        new File(filePath_old).delete();
        server.save(filePath_new,MainActivity.global_password);

        //Server s2 = Server.load(filePath+"/cx1");
        //Log.d("Passed:", s2.toString());
        //onUserLeaveHint();

        startActivity(new Intent(getApplicationContext(), MainActivity.class));


    }

    public void cancel_save(View view) {

        startActivity(new Intent(getApplicationContext(), MainActivity.class));

    }
}