package com.example.ryo.gshellfragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

/**
 * Created by ryo on 14/04/17.
 */

public class fragmentHostList extends Fragment {

    RecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    private static List<Host> hostData;
    private DatabaseHandler db;

    public fragmentHostList() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View v= inflater.inflate(R.layout.fragment_host_list,container,false);

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab_hostlist);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                    fragmentDialogHostEntry testfrag = new fragmentDialogHostEntry();
                    testfrag.show(manager,"fragment_host_entry");
            }
        });
        return v;
    }

    // onActivityCreated is called after onCreateView
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        recyclerView = (RecyclerView) getView().findViewById(R.id.hostList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);


        // inserting test hosts into SQL database
        db = new DatabaseHandler(getActivity());

        // reading host database into a list
        List<Host> hostData = db.getAllHosts();


        adapter = new RecyclerViewAdapter(hostData);
        recyclerView.setAdapter(adapter);

        // seting the list separator decoration after setting adapter
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        recyclerView.addItemDecoration(itemDecoration);

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                // do it
                Host item = ((RecyclerViewAdapter) recyclerView.getAdapter()).getHostItem(position);

                Toast.makeText(getActivity(),"Launch "+item._alias,Toast.LENGTH_SHORT).show();
            }
        });


    }
}
