package fr.mbds.android.neighbors.data

import fr.mbds.android.neighbors.data.service.DummyNeighborApiService
import fr.mbds.android.neighbors.data.service.NeighborApiService
import fr.mbds.android.neighbors.models.Neighbor

class NeighborRepository {
    private val apiService: NeighborApiService

    init {
        apiService = DummyNeighborApiService()
    }

    fun getNeighbours(): List<Neighbor> = apiService.neighbours

    fun deleteNeighbour(neighbor: Neighbor) {
        apiService.deleteNeighbour(neighbor)
    }

    fun createNeighbour(neighbor: Neighbor) {
        apiService.createNeighbour(neighbor)
    }

    companion object {
        private var instance: NeighborRepository? = null
        fun getInstance(): NeighborRepository {
            if (instance == null) {
                instance = NeighborRepository()
            }
            return instance!!
        }
    }
}
