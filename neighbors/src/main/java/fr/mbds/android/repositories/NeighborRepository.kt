package fr.mbds.android.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import fr.mbds.android.dal.NeighborDatasource
import fr.mbds.android.dal.memory.InMemoryNeighborDataSource
import fr.mbds.android.dal.room.RoomNeighborDataSource
import fr.mbds.android.models.Neighbor

class NeighborRepository private constructor(application: Application) {
    private var dataSource: NeighborDatasource
    private val roomDataSource: RoomNeighborDataSource = RoomNeighborDataSource(application)
    private val inMemoryDataSource: InMemoryNeighborDataSource = InMemoryNeighborDataSource()

    init {
        dataSource = roomDataSource
    }

    // Méthode qui retourne la liste des voisins
    fun getNeighbours(): LiveData<List<Neighbor>> = dataSource.neighbours

    fun delete(neighbor: Neighbor) = dataSource.deleteNeighbour(neighbor)
    fun createNeighbour(neighbor: Neighbor) = dataSource.createNeighbour(neighbor)

    fun defineDataSource(source: Boolean) {
        dataSource = if (source) roomDataSource else inMemoryDataSource
    }
    companion object {
        private var instance: NeighborRepository? = null

        // On crée un méthode qui retourne l'instance courante du repository si elle existe ou en crée une nouvelle sinon
        fun getInstance(application: Application): NeighborRepository {
            if (instance == null) {
                instance = NeighborRepository(application)
            }
            return instance!!
        }
    }
}
