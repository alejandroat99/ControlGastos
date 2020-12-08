package com.example.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity(tableName = "registro")
public class Registro {
    @PrimaryKey
    @NonNull
    private int id;

    @ColumnInfo(name = "value")
    private int value;
    
    @ColumnInfo(name = "gasto")
    private boolean gasto; // True -> es un gasto, False -> es un ingreso
    
    @ColumnInfo(name = "titulo")
    private String titulo;
    
    @ColumnInfo(name = "descripcion")
    private String descripcion;
    
    @ColumnInfo(name = "fecha")
    private Date fecha;
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Registro registro = (Registro) o;
        return id == registro.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
