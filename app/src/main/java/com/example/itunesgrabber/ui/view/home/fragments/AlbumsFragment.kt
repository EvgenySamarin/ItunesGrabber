package com.example.itunesgrabber.ui.view.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.itunesgrabber.R
import com.example.itunesgrabber.databinding.FragmentHomeAlbumsBinding
import com.example.itunesgrabber.ui.view.App
import com.example.itunesgrabber.ui.view.core.BaseFragment
import com.example.itunesgrabber.ui.viewmodel.AlbumsViewModel
import kotlinx.android.synthetic.main.fragment_home_albums.*


/**
 * Main fragment to show list of music albums
 *
 * @author EvgenySamarin
 * @since 20200315 v1
 */
class AlbumsFragment : BaseFragment() {
    private lateinit var binding: FragmentHomeAlbumsBinding
    private lateinit var albumsViewModel: AlbumsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        albumsViewModel = injectViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_albums, container, false)
        binding.viewModel = albumsViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_albums.addItemDecoration(
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        )
        albumsViewModel.onSearchClick()

        albumsViewModel.errors.observe(viewLifecycleOwner, Observer {
            if (it != null) showError(it)
        })
    }

    private fun showError(errorMessage: String) {
        base {
            showSnackbar(errorMessage) {
                albumsViewModel.onSearchClick()
            }
        }
    }
}