package com.example.notes;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

import static android.provider.Telephony.Mms.Part.FILENAME;

public class FirstPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
       // getFilePath();
       getSupportFragmentManager().beginTransaction().add(R.id.launch,new addnote()).commit();
    }
 /*   File getFilePath()
    {
        File file = new File(FirstPage.this.getFilesDir(), "notes");
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
    }*/

}
