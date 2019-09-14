package com.danielmaia.flights.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;

@Entity(primaryKeys = {"id","filterType"})
public class Filter {
    @NonNull
    private int id;

    @NonNull
    private int filterType;

    public Filter() {
    }

    @Ignore
    public Filter(int id, int filterType) {
        this.id = id;
        this.filterType = filterType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFilterType() {
        return filterType;
    }

    public void setFilterType(int filterType) {
        this.filterType = filterType;
    }
}
