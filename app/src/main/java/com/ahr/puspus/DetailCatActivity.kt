package com.ahr.puspus

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.ahr.puspus.data.Cat
import com.ahr.puspus.databinding.ActivityDetailCatBinding
import com.bumptech.glide.Glide

class DetailCatActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_CAT = "extra_cat"
    }

    private lateinit var binding: ActivityDetailCatBinding
    private var cat: Cat? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        cat = intent.getParcelableExtra(EXTRA_CAT)

        Glide.with(this)
            .load(cat?.photo)
            .into(binding.ivCat)
        binding.tvCatDescription.text = cat?.description

        setupToolbar(cat?.name)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> shareCat()
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupToolbar(title: String?) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = title
    }

    private fun shareCat() {
        val shareIntent = Intent()
            .apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TITLE, cat?.name)
                putExtra(Intent.EXTRA_TEXT, cat?.description)
                type = "text/plain"
            }
        try {
            startActivity(shareIntent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}