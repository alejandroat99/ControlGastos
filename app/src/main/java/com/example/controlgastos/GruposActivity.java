package com.example.controlgastos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.Grupos.GrupoAdapter;
import com.example.db.actions.GrupoActions;
import com.example.model.Grupo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class GruposActivity extends AppCompatActivity {
    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupos);

        list = (ListView) findViewById(R.id.grupos_list);
        GrupoActions ga = new GrupoActions(this);

        try {
            ArrayList<Grupo> grupos = new ArrayList<>(ga.getAllGrupos());
            GrupoAdapter adapter = new GrupoAdapter(grupos, this);
            list.setAdapter(adapter);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public void save_grupo(View v) {
        TextView text_grupo = (TextView) findViewById(R.id.text_grupo);
        String new_grupo = (String) text_grupo.getText();
        GrupoActions ga = new GrupoActions(this);
        ga.insertTask(new_grupo);

        try {
            ArrayList<Grupo> grupos = new ArrayList<>(ga.getAllGrupos());
            GrupoAdapter adapter = new GrupoAdapter(grupos, this);
            list.setAdapter(adapter);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}