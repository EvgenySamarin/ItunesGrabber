package com.example.itunesgrabber.ui.view.home.fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.itunesgrabber.R
import com.example.itunesgrabber.databinding.ItemHomeAlbumsBinding
import com.example.itunesgrabber.model.remote.itunes.entity.AlbumItemEntity
import com.example.itunesgrabber.ui.view.home.fragments.AlbumsFragmentDirections
import com.example.itunesgrabber.ui.viewmodel.ItemAlbumsViewModel

/**
 * Adapter for album items
 *
 * @author EvgenySamarin
 * @since 20200315 v1
 */
class AlbumsAdapter : RecyclerView.Adapter<AlbumsAdapter.ViewHolder>() {
    private lateinit var albumList: List<AlbumItemEntity>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemHomeAlbumsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_home_albums, parent, false
        )
        return ViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = albumList[position]
        holder.itemView.setOnClickListener {
            val action = AlbumsFragmentDirections.actionAlbumsDestToAlbumDetailDest()
                .setCollectionId(item.collectionId)
            it.findNavController().navigate(action)
        }
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return if (::albumList.isInitialized) albumList.size else 0
    }

    /** @since 20200318 v1: update adapter list */
    fun updateAlbumsList(list: List<AlbumItemEntity>) {
        this.albumList = list
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemHomeAlbumsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val viewModel = ItemAlbumsViewModel()

        fun bind(album: AlbumItemEntity) {
            viewModel.bind(album)
            binding.viewModel = viewModel
        }
    }
}