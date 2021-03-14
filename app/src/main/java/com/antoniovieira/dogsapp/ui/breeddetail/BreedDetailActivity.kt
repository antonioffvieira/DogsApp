package com.antoniovieira.dogsapp.ui.breeddetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.antoniovieira.dogsapp.data.model.Breed
import com.antoniovieira.dogsapp.databinding.ActivityBreedDetailBinding

class BreedDetailActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_BREED = "extra_breed"

        fun startActivity(context: Context, breed: Breed) {
            val intent = Intent(context, BreedDetailActivity::class.java).apply {
                putExtra(EXTRA_BREED, breed)
            }

            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityBreedDetailBinding

    private var breed: Breed? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBreedDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        breed = intent?.extras?.getParcelable(EXTRA_BREED) as? Breed

        setupUI()

        // TODO Bind data : Breed name, category, origin, temperament
    }

    private fun setupUI() {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

}