package com.example.model;

import androidx.room.Entity;

@Entity(primaryKeys = {"registroId", "grupoId"})
public class RegistroGrupoCrossRef{
    public int registroId;
    public int grupoId;
}

