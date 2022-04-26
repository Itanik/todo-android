package me.itanik.todo.data.local.db.dao

import androidx.room.*
import me.itanik.todo.data.local.db.entity.NoteEntity

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes")
    suspend fun getAll(): List<NoteEntity>

    @Insert
    suspend fun insert(entity: NoteEntity)

    @Insert
    suspend fun insertAll(vararg entities: NoteEntity)

    @Update
    suspend fun update(entity: NoteEntity)

    @Delete
    suspend fun delete(entity: NoteEntity)
}