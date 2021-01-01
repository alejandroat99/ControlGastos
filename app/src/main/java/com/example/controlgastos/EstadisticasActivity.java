package com.example.controlgastos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.db.actions.RegistroActions;
import com.example.model.Registro;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class EstadisticasActivity extends AppCompatActivity {
    private List<Registro> gastos;
    private List<Registro> beneficios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        RegistroActions ra = new RegistroActions(this);
        gastos = new LinkedList<>();
        beneficios = new LinkedList<>();
        try {
            List<Registro> registros = ra.getAllRegistro();
            for(Registro r : registros){
                if(r.isGasto()){
                    gastos.add(r);
                }else{
                    beneficios.add(r);
                }
            }
            LineChart chart = (LineChart) findViewById(R.id.chart);
            List<Entry> entries = new ArrayList<>();
            Random rnd = new Random();
            for(int i = 0; i <= 10; i++){
                entries.add(new Entry(i, rnd.nextFloat()));
            }

            LineDataSet dataset = new LineDataSet(entries, "Label");
            LineData lineData = new LineData(dataset);
            chart.setData(lineData);
            chart.invalidate(); //refresh
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}