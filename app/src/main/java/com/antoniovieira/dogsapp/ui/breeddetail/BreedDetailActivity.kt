package com.antoniovieira.dogsapp.ui.breeddetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.antoniovieira.dogsapp.R
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBreedDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val breed: Breed = requireNotNull(intent?.extras?.getParcelable(EXTRA_BREED)) {
            "The Bundle must have a breed"
        }

        setupUI()
        setContent(breed)
    }

    private fun setupUI() {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun setContent(breed: Breed) {
        with(binding) {
            toolbar.title = breed.name
            breedNameValue.text = breed.name
            breedGroupValue.text = if (!breed.group.isNullOrEmpty()) breed.group else getString(R.string.breed_detail_undefined_value)
            breedOriginValue.text = breed.origin ?: getString(R.string.breed_detail_undefined_value)
            breedTemperamentValue.text = breed.temperament
        }
    }

}