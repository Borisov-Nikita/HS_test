package nik.borisov.hstest.presentation.menu.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import nik.borisov.hstest.databinding.BannerItemBinding
import nik.borisov.hstest.presentation.entities.BannerUi

class BannersAdapter : ListAdapter<BannerUi, BannersViewHolder>(BannersDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannersViewHolder {
        val binding = BannerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BannersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BannersViewHolder, position: Int) {
        val banner = currentList[position]
        holder.binding.bannerImageView.setImageDrawable(
            AppCompatResources.getDrawable(
                holder.itemView.context,
                banner.drawable
            )
        )
    }
}

class BannersViewHolder(
    val binding: BannerItemBinding
) : RecyclerView.ViewHolder(binding.root)

class BannersDiffCallBack : DiffUtil.ItemCallback<BannerUi>() {

    override fun areItemsTheSame(oldItem: BannerUi, newItem: BannerUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BannerUi, newItem: BannerUi): Boolean {
        return oldItem == newItem
    }

}