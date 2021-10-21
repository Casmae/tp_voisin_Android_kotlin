package fr.mbds.android.neighbors.adapters

import fr.mbds.android.neighbors.models.Neighbor

interface ListNeighborHandler {
    fun onDeleteNeighbor(neighbor: Neighbor)
}
