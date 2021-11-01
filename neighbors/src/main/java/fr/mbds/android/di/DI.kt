package fr.mbds.android.di

import android.app.Application
import fr.mbds.android.repositories.NeighborRepository

object DI {
    lateinit var repository: NeighborRepository
    fun inject(application: Application) {
        repository = NeighborRepository.getInstance(application)
    }
}
