package com.example.ryo.gshellfragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class fragmentHostEntry extends Fragment {
/*
    public fragmentHostEntry(){

    }
*//*
    onDatabasePassListener mCallback;

    // container class must implement this interface
    public interface onDatabasePassListener{
        public void onDatabasePass(DatabaseHandler db);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // this makes usre that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (onDatabasePassListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
            + " must implement onDatabasePassListener");
        }

    }
*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_host_entry, container, false);
    }
}
