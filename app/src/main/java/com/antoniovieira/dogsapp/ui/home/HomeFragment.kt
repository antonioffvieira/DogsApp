package com.antoniovieira.dogsapp.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.antoniovieira.dogsapp.DogsApplication
import com.antoniovieira.dogsapp.R
import com.antoniovieira.dogsapp.databinding.FragmentHomeBinding
import com.antoniovieira.dogsapp.ui.breeddetail.BreedDetailActivity
import com.antoniovieira.dogsapp.ui.home.adapter.ImagesListAdapter
import com.antoniovieira.dogsapp.utils.ExceptionHelper
import com.antoniovieira.dogsapp.utils.OffsetItemDecoration
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class HomeFragment : Fragment(), Toolbar.OnMenuItemClickListener {

    companion object {
        const val TAG = "HomeFragment"

        private const val VIEW_SWITCHER_LOADING_POSITION = 0
        private const val VIEW_SWITCHER_CONTENT_POSITION = 1

        private const val NUMBER_OF_COLUMNS = 2

        fun newInstance() = HomeFragment()
    }

    enum class LayoutManagerType {
        LINEAR_LAYOUT_MANAGER,
        GRID_LAYOUT_MANAGER
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

    private var currentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER

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

    override fun onMenuItemClick(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_order_alphabetically -> {
                item.isChecked = !item.isChecked

                // TODO Sort alphabetically the list items
            }

            R.id.action_show_content_as_grid -> {
                item.isChecked = !item.isChecked

                setCurrentLayoutManager()
            }
        }

        return true
    }

    private fun setupUI() {
        initAdapter()

        with(binding.imagesList) {
            addItemDecoration(offsetItemDecoration)
            layoutManager = LinearLayoutManager(context)
            adapter = imagesListAdapter
        }

        binding.toolbar.inflateMenu(R.menu.menu_home_options)
        binding.toolbar.setOnMenuItemClickListener(this)
    }

    private fun initAdapter() {
        imagesListAdapter = ImagesListAdapter {
            BreedDetailActivity.startActivity(requireContext(), it)
        }

        imagesListAdapter.addLoadStateListener { loadState ->
            when (loadState.source.refresh) {
                is LoadState.NotLoading -> binding.viewSwitcher.displayedChild =
                    VIEW_SWITCHER_CONTENT_POSITION
                is LoadState.Loading -> binding.viewSwitcher.displayedChild =
                    VIEW_SWITCHER_LOADING_POSITION
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
        val refresh = states.source.refresh as? LoadState.Error
        val throwable = refresh?.error ?: return

        val errorTitleAndMessage: Pair<Int, Int> = ExceptionHelper.getExceptionMessage(throwable)

        MaterialAlertDialogBuilder(requireContext())
            .setTitle(errorTitleAndMessage.first)
            .setMessage(errorTitleAndMessage.second)
            .show()
    }

    private fun setCurrentLayoutManager() {
        var layoutManager: RecyclerView.LayoutManager? = binding.imagesList.layoutManager
        var scrollPosition = 0

        if (binding.imagesList.layoutManager != null) {
            scrollPosition =
                (binding.imagesList.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
        }

        when (currentLayoutManagerType) {
            LayoutManagerType.LINEAR_LAYOUT_MANAGER -> {
                layoutManager = GridLayoutManager(context, NUMBER_OF_COLUMNS)
                currentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER
            }

            LayoutManagerType.GRID_LAYOUT_MANAGER -> {
                layoutManager = LinearLayoutManager(context)
                currentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER
            }
        }

        binding.imagesList.post {
            binding.imagesList.layoutManager = layoutManager
            binding.imagesList.scrollToPosition(scrollPosition)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding.imagesList.removeItemDecoration(offsetItemDecoration)
        _binding = null

        compositeDisposables.clear()
    }

}