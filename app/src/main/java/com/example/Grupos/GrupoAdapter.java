package com.example.Grupos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.controlgastos.R;
import com.example.db.actions.GrupoActions;
import com.example.model.Grupo;

import java.util.ArrayList;

// Esta clase es un adaptador personalizado para poder usar los grupos en las
// ListView.
// La ListView mostrara, en cada fila, el nombre del grupo y un boton
// para eliminar el grupo de la base de datos.
public class GrupoAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<Grupo> list = new ArrayList<>();
    private Context context;

    public GrupoAdapter(ArrayList<Grupo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Grupo getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getGrupoId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_group_item, null);
        }
        TextView listItemText = (TextView) view.findViewById(R.id.list_group_item_label);
        listItemText.setText(list.get(position).getLabel());

        ImageButton deleteBtn = (ImageButton) view.findViewById(R.id.list_group_boton_delete);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Grupo grupo = list.get(position);
                GrupoActions ga = new GrupoActions(context);
                ga.delete(grupo.getGrupoId());
                list.remove(position);
                notifyDataSetChanged();
            }
        });
        return view;
    }
}
