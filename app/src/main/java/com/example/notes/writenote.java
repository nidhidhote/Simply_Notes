package com.example.notes;


import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;


public class writenote extends Fragment {
    private String fileTitle;
    private EditText enterText, title;
    private Spinner spinner;

    public void onBackPressed() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.launch,new addnote()).commit();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.writenote, container, false);

        showFonts(view);

        Button save = view.findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                enterText = view.findViewById(R.id.editText);
                title = view.findViewById(R.id.title);
                if(!title.getText().toString().isEmpty()) {
                    fileTitle = title.getText().toString();
                    fileOperations();
                    addnote fragment = new addnote();
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                   // fragment.Additem(fileTitle);
                    ft.replace(R.id.launch, fragment);
                    ft.commit();
                }
                else Toast.makeText(getContext(),"File Title is must",Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    private void fileOperations() {

        //getting abstract path
        File file = new File(getContext().getFilesDir(), "notes");

        //if folder doesn't exist then it will create
        if (!file.exists()) {
            file.mkdir();
        }

        //if you have written something
        if (!enterText.getText().toString().isEmpty()) {

            try {
//-------------------------------------------------------------------------------------------------------------
                // enterText.txt writing notes in the files
                String path = fileTitle + ".txt";
                String data = enterText.getText().toString();
                Toast.makeText(getContext(),data,Toast.LENGTH_LONG).show();
                useWriter(file, path, data);
//-------------------------------------------------------------------------------------------------------------
                //title save
                data = fileTitle;
                path = "titles.txt";
                useWriter(file, path, data);

//-------------------------------------------------------------------------------------------------------------
                path = "/" + fileTitle + ".txt";
                useReader(file, path);


                path = "/titles.txt";
                useReader(file, path);

//-------------------------------------------------------------------------------------------------------------

                Toast.makeText(getContext(), "Data Saved Successfully", Toast.LENGTH_LONG).show();
            }
            catch (Exception e) {
                Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
            }
        }
         /*String[]entries = file.list();
            for(String s: entries){
            File currentFile = new File(file.getPath(),s);
            currentFile.delete();
        }
        file.delete();*/
    }


    private void useWriter(File file,String path,String data) {
        Toast.makeText(getContext(), path, Toast.LENGTH_LONG).show();

        try
        {
            File note_1 = new File(file, path);
            FileWriter writer = new FileWriter(note_1, true);
            writer.write(data);
            writer.write(" ");
            writer.flush();
            writer.close();
        }
        catch (Exception e)
        {
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT);
        }
    }

    protected char[] useReader(File file, String path)
    {
        char[] read_ = new char[10000];
      //  Toast.makeText(getContext(), path, Toast.LENGTH_LONG).show();
        try{
            FileReader reader = new FileReader(file.toString() + path);


            int val = reader.read(read_);
            if (val == -1) {
              return null;
                //Toast.makeText(getContext(), "Empty file cannot be saved", Toast.LENGTH_LONG).show();
            }
            reader.close();
            //Toast.makeText(getContext(), String.valueOf(read_), Toast.LENGTH_LONG).show();
        }
        catch (Exception exp)
        {
            Log.e("Title Exception",exp.toString());
            //Toast.makeText(getContext(),exp.getMessage(),Toast.LENGTH_SHORT);
        }
       // Toast.makeText(getContext(), String.valueOf(read_).split(" ")[0], Toast.LENGTH_LONG).show();
        return read_;
    }

    private void showFonts(View view)
    {
        Spinner spinner =  view.findViewById(R.id.font);
        String []array = { "DEFAULT", "SERIF", "SANS_SERIF","MONOSPACE"};
      // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),R.layout.support_simple_spinner_dropdown_item,array); //(this,  //array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        CustomOnItemSelectedListener CS=new CustomOnItemSelectedListener(view);
        spinner.setOnItemSelectedListener(CS);
        //Toast.makeText(getContext(),"yes",Toast.LENGTH_SHORT).show();
        String name=CS.position;
        //Toast.makeText(getContext(),name,Toast.LENGTH_SHORT).show();
       // enterText.setFontFeatureSettings(array[CS.position]);
        if(name=="MONOSPACE")
           enterText.setTypeface(Typeface.MONOSPACE);
        if(name=="SANS_SERIF")
            enterText.setTypeface(Typeface.SANS_SERIF);
        if(name=="SERIF")
            enterText.setTypeface(Typeface.SERIF);
        if(name=="DEFAULT")
            enterText.setTypeface(Typeface.DEFAULT);

    }



}


 class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

    public String position;
    EditText temp  ;
    CustomOnItemSelectedListener( View view)
    {
        temp = view.findViewById(R.id.editText);
    }
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_SHORT).show();
        position=parent.getItemAtPosition(pos).toString();
        if(position=="MONOSPACE")
            temp.setTypeface(Typeface.MONOSPACE);
        if(position=="SANS_SERIF")
            temp.setTypeface(Typeface.SANS_SERIF);
        if(position=="SERIF")
            temp.setTypeface(Typeface.SERIF);
        if(position=="DEFAULT")
            temp.setTypeface(Typeface.DEFAULT);

    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


}