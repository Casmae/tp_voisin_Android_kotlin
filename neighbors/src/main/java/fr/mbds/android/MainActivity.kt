package fr.mbds.android.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import fr.mbds.android.NavigationListener
import fr.mbds.android.di.DI
import fr.mbds.android.neighbors.R
import fr.mbds.android.repositories.NeighborRepository
import fr.mbds.android.ui.fragments.ListNeighborsFragment

class MainActivity : AppCompatActivity(), NavigationListener {
    private lateinit var toolbar: Toolbar
    private lateinit var repository: NeighborRepository
    private lateinit var memoryMenu: MenuItem
    private lateinit var roomMenu: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DI.inject(application)
        repository = DI.repository
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        showFragment(ListNeighborsFragment())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        memoryMenu = menu.findItem(R.id.memorySource)
        roomMenu = menu.findItem(R.id.roomSource)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.roomSource) {
            Toast.makeText(this, R.string.roomSourceActive, Toast.LENGTH_SHORT).show()
            repository.defineDataSource(true)

            return true
        } else {
            Toast.makeText(this, R.string.memorySourceActive, Toast.LENGTH_SHORT).show()
            repository.defineDataSource(false)

            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }.commit()
    }

    override fun updateTitle(title: Int) {
        toolbar.setTitle(title)
    }
}
