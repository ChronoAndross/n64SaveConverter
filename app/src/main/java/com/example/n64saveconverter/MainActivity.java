/*
* MainActivity.java
* Simple UI code for n64SaveConverter App.
* Contains two buttons: one for folder choosing, and one for converting files once the folder is selected.
* The app will also cache the location of the last folder that was selected so it remembers when the app is loaded again.
* Some of this is definitely boilerplate btw.
* Written by Andrew Gorbaty.
* */
package com.example.n64saveconverter;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import lib.folderpicker.FolderPicker;


public class MainActivity extends AppCompatActivity {

    private static final int FOLDERPICKER_CODE = 2;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 3;
    private static final String SAVED_APP_PATH = "FILEPATH";

    private String fFileDir = "";
    private N64SaveConverter fConverter = new N64SaveConverter();

    private void SetSavedFolderPath(String inFolderPath){
        try{
            FileOutputStream fos = openFileOutput(SAVED_APP_PATH, Context.MODE_PRIVATE);
            fos.write(inFolderPath.getBytes());
            fos.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private String GetSavedFolderPath(){
        String outFolderPath = "";
        try{
            FileInputStream fis = new FileInputStream(getFileStreamPath(SAVED_APP_PATH));
            if (fis != null) {
                byte[] fileBytes = new byte[fis.available()];
                fis.read(fileBytes);
                outFolderPath = new String(fileBytes);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return outFolderPath;
    }

    private void askReadWritePermission(){
        // Also boilerplate...
        if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
        }

        if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
        }
    }

    private void showPopUp(String inTitle, String inText){
        // From StackOverflow lol
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle(inTitle);
        alertDialog.setMessage(inText);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ask for permissions before doing anything
        askReadWritePermission();
        fFileDir = GetSavedFolderPath();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fabFolder = findViewById(R.id.folderButton);
        fabFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FolderPicker.class);
                startActivityForResult(intent, FOLDERPICKER_CODE);
            }
        });

        FloatingActionButton fabDone = findViewById((R.id.doneButton));
        fabDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!fFileDir.isEmpty()){ // convert files here
                    if (fConverter.ConvertFiles(fFileDir))
                        showPopUp("", "File Conversion Successful!");
                    else{
                        showPopUp("Error", "Conversion was unsuccessful. " +
                                "Please choose another directory that contains save files.");
                        fFileDir = ""; // set this to reset the directory
                    }
                }
                else {
                    showPopUp("Error","Cannot continue unless a directory has been selected.");
                    fFileDir = ""; // set this to reset the directory
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        fFileDir = "";
        if(resultCode == RESULT_OK && requestCode == FOLDERPICKER_CODE){
            fFileDir = data.getExtras().getString("data");
            SetSavedFolderPath(fFileDir);
            Log.i( "folderLocation", fFileDir );
        }
        else
            showPopUp("Error","Please choose a directory.");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings || id == R.id.folderButton || id == R.id.doneButton) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
