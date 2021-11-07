package fr.mbds.android.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import fr.mbds.android.di.DI
import fr.mbds.android.models.Neighbor
import fr.mbds.android.repositories.NeighborRepository

class RepositoryViewModel : ViewModel() {
    private val repository: NeighborRepository = DI.repository

    // On fait passe plat sur le résultat retourné par le repository
    val neighbors: LiveData<List<Neighbor>>
        get() = repository.getNeighbours()

    fun delete(neighbor: Neighbor) {
        repository.delete(neighbor)
    }

    fun add(neighbor: Neighbor) {
        repository.createNeighbour(neighbor)
    }
}
