package com.example.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "grupo")
public class Grupo {
    @PrimaryKey
    @NonNull
    private int id;
    
    @ColumnInfo(name = "label")
    private String label;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grupo grupo = (Grupo) o;
        return id == grupo.id &&
                label.equals(grupo.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label);
    }
}
