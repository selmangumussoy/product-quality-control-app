package com.selman.hechaton.models;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDao {
    @Query("SELECT * FROM product")
    List<Product> getAll();

//    @Query("SELECT * FROM product WHERE uid IN (:userIds)")
//    List<Product> loadAllByIds(int[] userIds);
//
//    @Query("SELECT * FROM product WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    Product findByName(String first, String last);

    @Insert
    void insertAll(Product... products);

    @Delete
    void delete(Product product);
}
