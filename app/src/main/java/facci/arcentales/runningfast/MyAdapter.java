package facci.arcentales.runningfast;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<String> historial;
    private int layout;
    private static OnItemClickListener onItemClickListener;

    public MyAdapter (List<String> historial, int layout, OnItemClickListener listener){
        this.historial=historial;
        this.layout=layout;
        this.onItemClickListener=listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(layout,viewGroup,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(historial.get(i),onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return historial.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tiempo_historial;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tiempo_historial=(TextView) itemView.findViewById(R.id.tiempo_historial);
        }

        public void bind(final String tiempo, final OnItemClickListener listener){
            this.tiempo_historial.setText(tiempo);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(tiempo, getAdapterPosition());
                }
            });

        }
    }
    public interface OnItemClickListener{
        void OnItemClick(String tiempo, int position);

    }
}

