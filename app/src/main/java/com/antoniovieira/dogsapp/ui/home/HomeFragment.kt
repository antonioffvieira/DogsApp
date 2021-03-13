package com.antoniovieira.dogsapp.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.antoniovieira.dogsapp.DogsApplication
import com.antoniovieira.dogsapp.databinding.FragmentHomeBinding
import com.antoniovieira.dogsapp.ui.home.adapter.ImagesListAdapter
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val homeViewModel by viewModels<HomeViewModel> {
        viewModelFactory
    }

    // TODO This could and should be replaced with a delegate to avoid boilerplate code inside fragments
    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!

    private val compositeDisposables = CompositeDisposable()

    private lateinit var imagesListAdapter: ImagesListAdapter

    companion object {
        const val TAG = "HomeFragment"

        fun newInstance() = HomeFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as DogsApplication).applicationComponent.homeComponent()
            .create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        requestData()
    }

    private fun setupUI() {
        imagesListAdapter = ImagesListAdapter {
            // TODO Open detail page
        }

        with(binding.imagesList) {
            adapter = imagesListAdapter
        }
    }

    @ExperimentalCoroutinesApi
    private fun requestData() {
        compositeDisposables.add(homeViewModel.getBreeds().subscribe {
            imagesListAdapter.submitData(lifecycle, it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null

        compositeDisposables.clear()
    }

}