package nik.borisov.hstest.presentation.menu.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import nik.borisov.hstest.R
import nik.borisov.hstest.databinding.ProductItemBinding
import nik.borisov.hstest.domain.entities.ProductItem

class ProductsAdapter : ListAdapter<ProductItem, ProductsViewHolder>(ProductsDiffCallBack()) {
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
            Picasso.get()
                .load(product.imageUrl)
                .placeholder(R.drawable.banner_sample_1)
                .into(productImageView)
            productNameTextView.text = product.name
            productDescriptionTextView.text = product.description
            priceTextView.text = holder.itemView.context.getString(R.string.price, product.price)
        }
    }
}

class ProductsViewHolder(
    val binding: ProductItemBinding
) : RecyclerView.ViewHolder(binding.root)

class ProductsDiffCallBack : DiffUtil.ItemCallback<ProductItem>() {
    override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
        return oldItem == newItem
    }
}