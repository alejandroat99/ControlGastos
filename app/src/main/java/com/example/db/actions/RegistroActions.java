package com.example.db.actions;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.db.database.DataBase;
import com.example.model.Registro;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class RegistroActions {
    private String DB_NAME = "ControlGastos";
    private DataBase db;

    public RegistroActions(Context context){
        db = Room.databaseBuilder(context, DataBase.class, DB_NAME).build();
    }

    public List<Registro> getAllRegistro() throws ExecutionException, InterruptedException {
        return new getAllAsyncTask(db).execute().get();
    }

    public static class getAllAsyncTask extends AsyncTask<Void,Void,List<Registro>>{
        private DataBase db;
        List<Registro> rs;

        getAllAsyncTask(DataBase dao){
            db = dao;
        }

        @Override
        protected List<Registro> doInBackground(Void... voids){
            return db.registroDao().getRegistros();
        }
    }

    public LiveData<Registro> getRegistro(int id){
        return db.registroDao().getRegistro(id);
    }

    public void insertTask(double value, boolean gasto, String titulo, String descripcion, String fecha){
        Registro registro = new Registro();
        registro.setTitulo(titulo);
        registro.setDescripcion(descripcion);
        registro.setValue(value);
        registro.setGasto(gasto);
        registro.setFecha(fecha);

        insert(registro);
    }

    public void insert(final Registro registro){
        new insertAsyncTask(db, registro).execute();
    }

    private class insertAsyncTask extends AsyncTask<Void,Void,Void>{
        private DataBase db;
        private Registro r;

        insertAsyncTask(DataBase dao, Registro registro){
            db = dao;
            r = registro;
        }

        @Override
        public Void doInBackground(Void... voids){
            db.registroDao().addRegistro(r);
            return null;
        }
    }

    public void delete(final int id){
        final LiveData<Registro> registro = getRegistro(id);
        if(registro != null){
            new deleteAsyncTask(db, registro.getValue());
        }
    }

    private class deleteAsyncTask extends AsyncTask<Void,Void,Void>{
        private DataBase db;
        private Registro r;

        deleteAsyncTask(DataBase dao, Registro registro){
            db = dao;
            r = registro;
        }

        @Override
        public Void doInBackground(Void... voids){
            db.registroDao().deleteRegistro(r);
            return null;
        }
    }

    public void update(final Registro registro){
        final LiveData<Registro> r = getRegistro(registro.getRegistroId());
        if(r != null){
            new updateAsyncTask(db, registro).execute();
        }
    }

    private class updateAsyncTask extends AsyncTask<Void,Void,Void>{
        private DataBase db;
        private Registro r;

        updateAsyncTask(DataBase dao, Registro registro){
            db = dao;
            r = registro;
        }

        @Override
        public Void doInBackground(Void... voids){
            db.registroDao().updateRegistro(r);
            return null;
        }
    }
}
