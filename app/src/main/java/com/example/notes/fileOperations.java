package com.example.notes;


import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashSet;

import javax.security.auth.login.LoginException;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class fileOperations {
    File file1;
    String  filename;
    fileOperations(File file,String fileName)
    {
        Log.e(TAG,file.getName().toString());
        file1=file;
        filename=fileName;
    }
     void deleteFile(String[] titlesList)
    {
          File currentFile = new File(file1.getPath()+"/"+filename+".txt");
          Log.e(TAG, filename );
          if(currentFile.delete())
              Log.e(TAG, "True " );
          else Log.e(TAG, "False " );


          try {

              File current = new File(file1.getPath()+"/titles.txt");
              Log.e(TAG, current.toString() );
              FileWriter fileWriter = new FileWriter(current);

              for (String s : titlesList) {
              fileWriter.write(s);
              fileWriter.write(" ");
              }
              fileWriter.flush();
              fileWriter.close();
          }
          catch(IOException e){ Log.e(TAG,"error");}

    }
    void editFile()
    {

    }
    void deleteAll()
    {
       /*   String[]entries = file.list();
            for(String s: entries){
            File currentFile = new File(file.getPath(),s);
            currentFile.delete();
        }
        fileName.delete();*/
    }
}
