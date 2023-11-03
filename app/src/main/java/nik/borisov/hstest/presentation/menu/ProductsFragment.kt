package nik.borisov.hstest.presentation.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import nik.borisov.hstest.R
import nik.borisov.hstest.databinding.FragmentProductsBinding
import nik.borisov.hstest.presentation.menu.adapters.ProductsAdapter

class ProductsFragment : Fragment(R.layout.fragment_products) {

    private var _binding: FragmentProductsBinding? = null
    private val binding: FragmentProductsBinding
        get() = _binding ?: throw NullPointerException("FragmentProductsBinding is null")

    private val viewModel by viewModels<ProductsViewModel>()

    private val productsAdapter = ProductsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentProductsBinding.bind(view)

        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.productsRecyclerView
        recyclerView.adapter = productsAdapter
    }

    private fun observeViewModel() {
        viewModel.banners.observe(viewLifecycleOwner) {
            productsAdapter.submitList(it)
        }
    }

    companion object {
        fun newInstance() = ProductsFragment()
    }
}