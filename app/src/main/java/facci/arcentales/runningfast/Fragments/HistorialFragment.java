package facci.arcentales.runningfast.Fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import facci.arcentales.runningfast.MyAdapter;
import facci.arcentales.runningfast.R;
import facci.arcentales.runningfast.SQL.sqliteopenhelper;
import facci.arcentales.runningfast.modelo.TiempoDistacia;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistorialFragment extends Fragment {
    int con = 0;
    private int counter=0;
    private TextView vacia;
    private Button botonAgregar;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private  RecyclerView.LayoutManager layoutManager;
    List<TiempoDistacia> tiempoDistancia;
    public HistorialFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_historial, container, false);
        showToolbar(getResources().getString(R.string.toolbar_historial),false, view);
        setHasOptionsMenu(true);

        sqliteopenhelper helperbd = new sqliteopenhelper(getContext());
        vacia = (TextView)view.findViewById(R.id.txt_sinActividad);
        if (!helperbd.mostrarTD().isEmpty()){
            vacia.setVisibility(view.INVISIBLE);
        }
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(getContext());
        MyAdapter adapter = new MyAdapter(helperbd.mostrarTD(), R.layout.cardview_historial, new MyAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(TiempoDistacia td, int position) {
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menun, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
