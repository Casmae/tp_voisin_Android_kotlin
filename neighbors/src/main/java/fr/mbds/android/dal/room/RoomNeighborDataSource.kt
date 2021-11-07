package fr.mbds.android.dal.room

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import fr.mbds.android.dal.NeighborDatasource
import fr.mbds.android.dal.room.daos.NeighborDao
import fr.mbds.android.dal.utils.toEntity
import fr.mbds.android.dal.utils.toNeighbor
import fr.mbds.android.models.Neighbor


class RoomNeighborDataSource(application: Application) : NeighborDatasource {
    private val database: NeighborDataBase = NeighborDataBase.getDataBase(application)
    private val dao: NeighborDao = database.neighborDao()

    private val _neighors = MediatorLiveData<List<Neighbor>>()

    init {

        _neighors.addSource(dao.getNeighbors()) { entities ->
            _neighors.value = entities.map { entity ->
                entity.toNeighbor()
            }
        }
    }

    override val neighbours: LiveData<List<Neighbor>>
        get() = _neighors

    override fun deleteNeighbour(neighbor: Neighbor) {
        Thread {
            dao.remove(neighbor.toEntity())
        }.start()
    }

    override fun createNeighbour(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }

    override fun updateFavoriteStatus(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }

    override fun updateDataNeighbour(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }

    fun updateNeighbour(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }
}
