package fr.mbds.android.dal.room.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import fr.mbds.android.dal.room.entities.NeighborEntity

@Dao
interface NeighborDao {
    @Query("SELECT * from neighbors")
    fun getNeighbors(): LiveData<List<NeighborEntity>>

    @Insert
    fun insert(neighbor: NeighborEntity)

    @Delete
    fun remove(neighbor: NeighborEntity)
}
