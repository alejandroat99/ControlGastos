package com.example.controlgastos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.example.db.actions.GrupoActions;
import com.example.db.actions.RegistroActions;
import com.example.db.actions.RegistroGrupoCrossRefActions;
import com.example.model.Grupo;
import com.example.model.Registro;
import com.example.model.RegistroGrupoCrossRef;
import com.example.util.AppColors;
import com.example.util.Utils;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GrupoDetailsActivity extends AppCompatActivity {

    private Grupo grupo;
    private List<Registro> registros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_details);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        int grupoId = bundle.getInt("grupoId");

        GrupoActions ga = new GrupoActions(this);
        grupo = ga.getGrupo(grupoId).getValue();

        // Cargar los registros del grupo
        RegistroGrupoCrossRefActions rga = new RegistroGrupoCrossRefActions(this);
        RegistroActions ra = new RegistroActions(this);
        List<RegistroGrupoCrossRef> relaciones = rga.getRelacionByGrupo(grupoId);
        registros = new ArrayList<>();
        for(RegistroGrupoCrossRef ref : relaciones){
            registros.add(ra.getRegistro(ref.getRegistroId()).getValue());
        }

        TextView text_label = (TextView) findViewById(R.id.text_label);
        text_label.setText(grupo.getLabel());

        load_barchart_resume();
        load_piechart_resume();
    }

    /**
     * Se cargara un pie chart con los porcentajes totales de los registros
     * divididos por tipos
     */
    private void load_piechart_resume() {
        PieChart chart = (PieChart) findViewById(R.id.piechart_resume);
        Map<Boolean, List<Registro>> filter_type = Utils.divideByType(this.registros);
        float total = this.registros.size();
        int gastos = filter_type.get(true).size();
        int beneficios = filter_type.get(false).size();

        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(gastos/total, R.string.cost));
        entries.add(new PieEntry(beneficios/total, R.string.benefit));

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(AppColors.gasto, AppColors.beneficio);
        dataSet.setSliceSpace(2);
        PieData data = new PieData(dataSet);
        chart.setData(data);
        chart.setUsePercentValues(true);
        chart.invalidate();
    }

    /**
     * Se mostrara un grafico de barras con los totales gastados de cada tipo de registro
     * para el grupo concreto
     */
    private void load_barchart_resume() {
        BarChart chart = (BarChart) findViewById(R.id.barchar_grupo_resume);
        Map<Boolean, List<Registro>> filter_type = Utils.divideByType(this.registros);
        float gastos = Utils.sum(filter_type.get(true));
        float beneficios = Utils.sum(filter_type.get(false));

        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, gastos));
        entries.add(new BarEntry(1, beneficios));

        BarDataSet dataSet = new BarDataSet(entries, "Resumen");
        dataSet.setColors(Color.rgb(255,0,0));
        dataSet.setColors(Color.rgb(0,255,0));
        BarData data = new BarData(dataSet);
        chart.setData(data);
        chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(new String[]{"Gastos", "Beneficios"}));
        chart.getXAxis().setAxisMaximum((float) 1.1);
        chart.getXAxis().setAxisMinimum((float) -0.1);
        chart.getXAxis().setLabelCount(2, true);
        chart.invalidate();

    }
}