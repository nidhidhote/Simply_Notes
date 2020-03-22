package com.example.notes;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class addnote extends Fragment {

    private ListView list;
    private static ArrayList<String> Filename= new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter ;
    writenote obj ;
    String []titlesList;
    FloatingActionButton fab;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        final View view = inflater.inflate(R.layout.addnotelayout,container, false);

        fab = view.findViewById(R.id.fab);


        final File file = new File(getContext().getFilesDir(), "notes");

        obj = new writenote();
        Toast.makeText(getContext(),"abcd" ,Toast.LENGTH_LONG).show();
        char []listOfTitles = obj.useReader(file,"/titles.txt");
        if(listOfTitles == null)
        {
            Toast.makeText(getContext(),"Error From Reading File", Toast.LENGTH_LONG).show();
        }
        else {

            titlesList = String.valueOf(listOfTitles/*,0,i+1*/).split(" ");
            Toast.makeText(getContext(),titlesList[titlesList.length-1]+" "+titlesList[titlesList.length-2],Toast.LENGTH_LONG);
            List<String> list1 = new ArrayList<>(Arrays.asList(titlesList));
                list1.remove(titlesList.length - 1);
            titlesList=list1.toArray(new String[0]);

            list = view.findViewById(R.id.filelist);
            registerForContextMenu(list);

            //FileList.add(s);
            // Filename.add("1");
            setAdapter();
            list.setLongClickable(true);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Toast.makeText(getContext(),String.valueOf(position),Toast.LENGTH_SHORT).show();
                    String fileName = titlesList[position];
                    char[] noteData = obj.useReader(file, "/" + fileName + ".txt");
                    showData frgmnt = new showData();
                    Bundle bundle = new Bundle();
                    bundle.putString("Title", fileName);
                    bundle.putString("data", String.valueOf(noteData));
                    frgmnt.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("second").replace(R.id.launch, frgmnt).commit();

                }
            });

            list.setLongClickable(true);
            list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                @Override
                public boolean onItemLongClick(AdapterView<?> arg0, View v,
                                               final int pos, long id) {

                    final String fileName = titlesList[pos];
                    List<String> list = new ArrayList<>(Arrays.asList(titlesList));
                    list.remove(titlesList[pos]);
                    titlesList = list.toArray(new String[0]);

                    PopupMenu popup = new PopupMenu(getActivity(), v);
                    popup.getMenuInflater().inflate(R.menu.longclickeditdelete, popup.getMenu());
                    Toast.makeText(getContext(), "popup", Toast.LENGTH_LONG).show();
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            //add functionality on click menuitem
                            performAction(item, file, fileName, titlesList);
                            return true;
                        }
                    });
                    Toast.makeText(getContext(), "clicked", Toast.LENGTH_LONG).show();
                    popup.show();
                    return true;
                }
            });
        }
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.launch,obj).addToBackStack("addNote").commit();

                }
            });

        return view;

    }

    public void Additem(String s)
    {

        Filename.add(s);
        // arrayAdapter.notifyDataSetChanged();
     //   Toast.makeText(getContext(),"Inserted",Toast.LENGTH_LONG).show();
        //arrayAdapter.notifyDataSetChanged();

    }
    /*
    perform task selected in popmenu
    Edit : Allow to Edit the content of file
    Delete: Allow to Delete the whole file
    */
    void performAction(MenuItem item,File file,String fileName, String []titlesList)
    {
        Toast.makeText(getContext(),item.getTitle(),Toast.LENGTH_LONG).show();
        fileOperations obj = new fileOperations(file,fileName);
        if(item.getTitle()=="Edit")
        {
        obj.editFile();
        }
        if(item.getTitle().toString().equals("Delete"))
        {
            //Toast.makeText(getContext(),"inside delete",Toast.LENGTH_LONG).show();
            obj.deleteFile(titlesList);
            setAdapter();
        }
    }
    void setAdapter()
    {
     //   Toast.makeText(getContext(),"coming",Toast.LENGTH_LONG).show();
        arrayAdapter  = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, titlesList);

        // arrayAdapter  = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, Filename);
        list.setAdapter(arrayAdapter);

    }
}
