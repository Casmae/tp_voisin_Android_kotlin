package fr.mbds.android.adapters

import fr.mbds.android.models.Neighbor

interface ListNeighborHandler {
    fun onDeleteNeighbor(neighbor: Neighbor)
}
