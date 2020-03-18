package com.example.itunesgrabber.ui.view.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.example.itunesgrabber.R
import com.example.itunesgrabber.databinding.FragmentHomeAlbumDetailBinding
import com.example.itunesgrabber.ui.view.App
import com.example.itunesgrabber.ui.view.core.BaseFragment
import com.example.itunesgrabber.ui.viewmodel.AlbumDetailViewModel

/**
 * Showing album detail info
 *
 * @author EvgenySamarin
 * @since 20200315 v1
 */
class AlbumDetailFragment : BaseFragment() {
    private lateinit var binding: FragmentHomeAlbumDetailBinding
    private lateinit var viewModel: AlbumDetailViewModel
    private val args: AlbumDetailFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        viewModel = injectViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home_album_detail, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (args.collectionId > 0) viewModel.getCachedAlbum(args.collectionId)
    }
}