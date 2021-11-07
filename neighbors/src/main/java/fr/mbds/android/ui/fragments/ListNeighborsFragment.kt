package fr.mbds.android.ui.fragments

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.mbds.android.NavigationListener
import fr.mbds.android.adapters.ListNeighborHandler
import fr.mbds.android.adapters.ListNeighborsAdapter
import fr.mbds.android.models.Neighbor
import fr.mbds.android.neighbors.R
import fr.mbds.android.neighbors.databinding.ListNeighborsFragmentBinding
import fr.mbds.android.viewmodels.RepositoryViewModel

class ListNeighborsFragment : Fragment(), ListNeighborHandler {

    private lateinit var recyclerView: RecyclerView
    private var _binding: ListNeighborsFragmentBinding? = null
    private lateinit var viewModel: RepositoryViewModel
    private val binding get() = _binding!!
    /**
     * Fonction permettant de définir une vue à attacher à un fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(RepositoryViewModel::class.java)
        _binding = ListNeighborsFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        recyclerView = view.findViewById(R.id.neighbors_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        (activity as? NavigationListener)?.let {
            it.updateTitle(R.string.listNeighbors_title)
        }

        _binding!!.addNeighbor.setOnClickListener {
            (activity as? NavigationListener)?.let {
                it.showFragment(AddNeighbourFragment())
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setData()
    }

    override fun onDeleteNeighbor(neighbor: Neighbor) {
        val application: Application = activity?.application ?: return
        viewModel.delete(neighbor)
    }

    private fun setData() {
        // Récupérer l'instance de l'application, si elle est null arrêter l'exécution de la méthode
        val application: Application = activity?.application ?: return

        viewModel.neighbors.observe(viewLifecycleOwner) {
            val adapter = ListNeighborsAdapter(it, this)
            binding.neighborsList.adapter = adapter
        }
    }
}
