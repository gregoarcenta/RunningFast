package facci.arcentales.runningfast.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import facci.arcentales.runningfast.MyAdapter;
import facci.arcentales.runningfast.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistorialFragment extends Fragment {
    int con = 0;
    private int counter=0;
    SharedPreferences preferences;
    private TextView vacia;
    private Button botonAgregar;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private  RecyclerView.LayoutManager layoutManager;
    List<String> tiempoDistancia;
    public HistorialFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_historial, container, false);
        showToolbar(getResources().getString(R.string.toolbar_historial),false, view);

        preferences = getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        vacia = (TextView)view.findViewById(R.id.txt_sinActividad);
        tiempoDistancia = this.getAllTiempoDistancia();
        if (!tiempoDistancia.isEmpty()){
            vacia.setVisibility(view.INVISIBLE);
        }
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(getContext());
        //addTiempoDistancia();
        adapter = new MyAdapter(tiempoDistancia, R.layout.cardview_historial, new MyAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(String tiempo, int position) {

            }
        });

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }
    public void showToolbar(String tittle, boolean button,View view){
        Toolbar toolbar= (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(tittle);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(button);
    }
    /* public List<String>tiempoDistancia(){
         List<String> time = new ArrayList<>();
         //time.add(0,preferences.getString("tiempo", ""));
         return time;
     }*/
    public List<String> getAllTiempoDistancia(){
        return new ArrayList<String>() {{
            //add("45:29");
            add(preferences.getString("tiempo", ""));
        }};
    }
    public void addTiempoDistancia(){
        tiempoDistancia.add(0,"45:56");
        adapter.notifyItemInserted(0);
        layoutManager.scrollToPosition(0);
    }
}
