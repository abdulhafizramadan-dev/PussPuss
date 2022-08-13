package com.ahr.puspus

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ahr.puspus.adapter.CatAdapter
import com.ahr.puspus.data.Cat
import com.ahr.puspus.data.CatData
import com.ahr.puspus.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), CatAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        setupRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_profile -> navigateToProfile()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCardClicked(cat: Cat) {
        val toDetailCatActivityIntent = Intent(this, DetailCatActivity::class.java)
            .apply { putExtra(DetailCatActivity.EXTRA_CAT, cat) }
        startActivity(toDetailCatActivityIntent)
    }

    override fun onBtnShareClicked(cat: Cat) {
        val shareIntent = Intent()
            .apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TITLE, cat.name)
                putExtra(Intent.EXTRA_TEXT, cat.description)
                type = "text/plain"
            }
        try {
            startActivity(shareIntent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBtnFavoriteClicked(cat: Cat) {
        Toast.makeText(this, "This feature on constructor", Toast.LENGTH_SHORT).show()
    }

    private fun setupRecyclerView() {
        val catAdapter = CatAdapter(this)
            .apply { submitList(CatData.listCat) }
        binding.rvCat.setHasFixedSize(true)
        binding.rvCat.adapter = catAdapter
    }

    private fun navigateToProfile() {
        val toProfileActivityIntent = Intent(this, ProfileActivity::class.java)
        startActivity(toProfileActivityIntent)
    }
}