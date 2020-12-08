package com.example.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.model.Grupo;
import com.example.model.GrupoWithRegistros;

import java.util.List;

@Dao
public interface GrupoDao {
    @Query("SELECT * FROM grupo")
    List<Grupo> getGrupos();

    @Insert
    void addGrupo(Grupo g);

    @Delete
    void deleteGrupo(Grupo r);

    @Transaction
    @Query("SELECT * FROM grupo")
    public List<GrupoWithRegistros> getGrupoWithRegistros();
}