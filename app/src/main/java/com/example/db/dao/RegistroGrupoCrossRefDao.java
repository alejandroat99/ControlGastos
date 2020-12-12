package com.example.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.model.RegistroGrupoCrossRef;

@Dao
public interface RegistroGrupoCrossRefDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(RegistroGrupoCrossRef rel);

    @Delete()
    void delete(RegistroGrupoCrossRef rel);

    @Query("SELECT * FROM RegistroGrupoCrossRef WHERE registroId=:registroId AND grupoId=:grupoId")
    LiveData<RegistroGrupoCrossRef> getRelacion(int registroId, int grupoId);

}
