package com.example.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.model.Registro;
import com.example.model.RegistroWithGrupos;

import java.util.List;

@Dao
public interface RegistroDao {
    @Query("SELECT * FROM registro")
    List<Registro> getRegistros();

    // @Query("SELECT * FROM registro WHERE gasto = 1")
    // List<Registro> getGastos();

    // @Query("SELECT * FROM registro WHERE gasto = 0")
    // List<Registro> getIngresos();

    @Query("SELECT * FROM registro WHERE registroId=:id")
    LiveData<Registro> getRegistro(int id);

    @Transaction
    @Query("SELECT * FROM registro")
    public List<RegistroWithGrupos> getRegistroWithGrupos();

    @Insert
    void addRegistro(Registro r);

    @Delete
    void deleteRegistro(Registro r);

    @Update
    void updateRegistro(Registro r);

}