package com.antoniovieira.dogsapp.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.antoniovieira.dogsapp.DogsApplication
import com.antoniovieira.dogsapp.R
import com.antoniovieira.dogsapp.databinding.FragmentHomeBinding
import com.antoniovieira.dogsapp.ui.home.adapter.ImagesListAdapter
import com.antoniovieira.dogsapp.utils.OffsetItemDecoration
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class HomeFragment : Fragment() {

    companion object {
        const val TAG = "HomeFragment"

        private const val VIEW_SWITCHER_LOADING_POSITION = 0
        private const val VIEW_SWITCHER_CONTENT_POSITION = 1

        fun newInstance() = HomeFragment()
    }

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

    private val offsetItemDecoration by lazy {
        OffsetItemDecoration(requireContext(), R.dimen.images_list_offset)
    }

    private lateinit var imagesListAdapter: ImagesListAdapter

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
        initAdapter()

        with(binding.imagesList) {
            addItemDecoration(offsetItemDecoration)
            adapter = imagesListAdapter
        }
    }

    private fun initAdapter() {
        imagesListAdapter = ImagesListAdapter {
            // TODO Open detail page
        }

        imagesListAdapter.addLoadStateListener { loadState ->
            when (loadState.source.refresh) {
                is LoadState.NotLoading -> binding.viewSwitcher.displayedChild = VIEW_SWITCHER_CONTENT_POSITION
                is LoadState.Loading -> binding.viewSwitcher.displayedChild = VIEW_SWITCHER_LOADING_POSITION
                is LoadState.Error -> handleError(loadState)
            }
        }
    }

    @ExperimentalCoroutinesApi
    private fun requestData() {
        compositeDisposables.add(homeViewModel.getBreeds().subscribe {
            imagesListAdapter.submitData(lifecycle, it)
        })
    }

    private fun handleError(states: CombinedLoadStates) {
        // TODO Handle type of error and show a popup
        val refresh = states.source.refresh as? LoadState.Error
        Log.d(TAG, "Pagination error: ${refresh?.error}")
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding.imagesList.removeItemDecoration(offsetItemDecoration)
        _binding = null

        compositeDisposables.clear()
    }

}