package com.example.db.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.db.dao.GrupoDao;
import com.example.db.dao.RegistroDao;
import com.example.model.Grupo;
import com.example.model.Registro;
import com.example.model.RegistroGrupoCrossRef;

@Database(entities = {Registro.class, Grupo.class, RegistroGrupoCrossRef.class}, version = 1)
public abstract class DataBase extends RoomDatabase {
    public abstract RegistroDao registroDao();
    public abstract GrupoDao grupoDao();
}
