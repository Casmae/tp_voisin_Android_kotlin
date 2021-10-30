package fr.mbds.android.dal.room.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import fr.mbds.android.dal.room.entities.NeighborEntity

@Dao
interface NeighborDao {
    @Query("SELECT * from neighbors")
    fun getNeighbors(): LiveData<List<NeighborEntity>>
}