package fr.mbds.android.dal.memory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fr.mbds.android.dal.NeighborDatasource
import fr.mbds.android.models.Neighbor

class InMemoryNeighborDataSource : NeighborDatasource {

    private val _liveData: MutableLiveData<List<Neighbor>> = MutableLiveData<List<Neighbor>>()

    init {
        _liveData.value = DUMMY_NeighborS
    }

    override val neighbours: LiveData<List<Neighbor>>
        get() = _liveData

    override fun deleteNeighbour(neighbor: Neighbor) {
        DUMMY_NeighborS.remove(neighbor)
    }

    override fun createNeighbour(neighbor: Neighbor) {
        DUMMY_NeighborS.add(neighbor)
    }

    override fun updateFavoriteStatus(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }

    override fun updateDataNeighbour(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }
}
