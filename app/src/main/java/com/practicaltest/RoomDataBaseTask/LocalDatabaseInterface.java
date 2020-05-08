package com.practicaltest.RoomDataBaseTask;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface LocalDatabaseInterface {

    @Query("SELECT * FROM item")
    List<Item> getItems();

    @Insert
    void insertAll(Item items);

    @Delete
    public void deleteUsers(Item items);

    @Update
    public void updateUsers(Item items);

}
