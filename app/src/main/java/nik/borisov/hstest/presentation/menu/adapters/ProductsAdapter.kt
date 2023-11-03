package nik.borisov.hstest.presentation.menu.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import nik.borisov.hstest.R
import nik.borisov.hstest.databinding.ProductItemBinding
import nik.borisov.hstest.presentation.entities.ProductUi

class ProductsAdapter : ListAdapter<ProductUi, ProductsViewHolder>(ProductsDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val binding = ProductItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val product = currentList[position]
        with(holder.binding) {
            productImageView.setImageDrawable(
                AppCompatResources.getDrawable(
                    holder.itemView.context,
                    R.drawable.banner_sample_1
                )
            )
            productNameTextView.text = "Product Name"
            productDescriptionTextView.text =
                "Product Description   Product Description   Product Description   Product Description    Product Description   Product Description"
            priceTextView.text = "от 345 р"
        }
    }
}

class ProductsViewHolder(
    val binding: ProductItemBinding
) : RecyclerView.ViewHolder(binding.root)

class ProductsDiffCallBack : DiffUtil.ItemCallback<ProductUi>() {
    override fun areItemsTheSame(oldItem: ProductUi, newItem: ProductUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ProductUi, newItem: ProductUi): Boolean {
        return oldItem == newItem
    }
}