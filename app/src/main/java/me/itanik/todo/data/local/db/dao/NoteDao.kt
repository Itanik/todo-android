package me.itanik.todo.data.local.db.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import me.itanik.todo.data.local.db.entity.NoteEntity
import java.util.*

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes")
    fun getAll(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun getNoteById(id: UUID): NoteEntity

    @Insert
    suspend fun insert(entity: NoteEntity)

    @Insert
    suspend fun insertAll(vararg entities: NoteEntity)

    @Update
    suspend fun update(entity: NoteEntity)

    @Delete
    suspend fun delete(entity: NoteEntity)
}