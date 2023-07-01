package com.ervr.retrofitapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ItemDao {
    @Query("SELECT * FROM Item")
    fun getAll(): List<Item>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg items: Item)
}
