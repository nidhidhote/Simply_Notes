package com.example.notes;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import static android.content.ContentValues.TAG;



public class showData extends Fragment {

    TextView showTitle , showData;

    public void onBackPressed() {
        getActivity().getSupportFragmentManager().popBackStack();


    }

    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_show_data, container, false);
        String title = getArguments().getString("Title");
        String data = getArguments().getString("data");
      //  Toast.makeText(getContext(),title,Toast.LENGTH_LONG).show();
        showTitle = view.findViewById(R.id.title);
        showData = view.findViewById(R.id.noteData);

        showTitle.setText(title);
        showData.setText(data);
        return view;
    }

}
