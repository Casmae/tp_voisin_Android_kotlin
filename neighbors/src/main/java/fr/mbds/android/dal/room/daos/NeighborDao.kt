package fr.mbds.android.dal.room.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import fr.mbds.android.dal.room.entities.NeighborEntity
import fr.mbds.android.models.Neighbor

@Dao
interface NeighborDao {
    @Query("SELECT * from neighbors")
    fun getNeighbors(): LiveData<List<NeighborEntity>>

    @Insert
    fun insertAll(neighbors: MutableList<NeighborEntity>)
}
