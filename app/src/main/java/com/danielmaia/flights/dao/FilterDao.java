package com.danielmaia.flights.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.danielmaia.flights.model.Filter;

import java.util.List;

@Dao
public interface FilterDao {

    @Query("SELECT * FROM Filter")
    List<Filter> getFilters();

    @Query("SELECT * FROM Filter WHERE filterType = :filterType")
    List<Filter> getFiltersByType(int filterType);

    @Query("SELECT * FROM Filter WHERE id = :id")
    Filter getFiltersById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Filter filter);

    @Query("DELETE FROM Filter WHERE id = :id")
    void deleteFilterById(int id);

    @Query("DELETE FROM Filter")
    void deleteFilters();
}
