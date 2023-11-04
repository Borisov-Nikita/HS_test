package nik.borisov.hstest.presentation.menu

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import nik.borisov.hstest.R
import nik.borisov.hstest.databinding.FragmentProductsBinding
import nik.borisov.hstest.presentation.menu.adapters.ProductsAdapter
import nik.borisov.hstest.presentation.viewModelCreator
import javax.inject.Inject

@AndroidEntryPoint
class ProductsFragment : Fragment(R.layout.fragment_products) {

    private var _binding: FragmentProductsBinding? = null
    private val binding: FragmentProductsBinding
        get() = _binding ?: throw NullPointerException("FragmentProductsBinding is null")

    @Inject
    lateinit var factory: ProductsViewModel.Factory
    private val viewModel by viewModelCreator { factory.create(categoryId) }

    private val productsAdapter = ProductsAdapter()

    private val categoryId by lazy {
        parseCategoryIdArgs()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentProductsBinding.bind(view)

        setupRecyclerView()
        observeViewModel()
        setupClickListeners()
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.productsRecyclerView
        recyclerView.adapter = productsAdapter
    }

    private fun observeViewModel() {
        viewModel.screenState.observe(viewLifecycleOwner) { state ->
            hideAllView()
            when (state) {
                is ProductsFragmentState.Pending -> {
                    renderPending()
                }

                is ProductsFragmentState.Error -> {
                    renderError()
                }

                is ProductsFragmentState.Success -> {
                    renderSuccess()
                    productsAdapter.submitList(state.value)
                }
            }
        }
    }

    private fun setupClickListeners() {
        binding.tryAgainButton.setOnClickListener {
            viewModel.downloadProducts()
        }
    }

    private fun hideAllView() {
        binding.productsRecyclerView.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        binding.retryLinearLayout.visibility = View.GONE
    }

    private fun renderPending() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun renderError() {
        binding.retryLinearLayout.visibility = View.VISIBLE
    }

    private fun renderSuccess() {
        binding.productsRecyclerView.visibility = View.VISIBLE
    }

    private fun parseCategoryIdArgs(): Int {
        val args = requireArguments()
        if (!args.containsKey(ARG_CATEGORY_ID)) {
            throw IllegalArgumentException("Argument category id is absent.")
        }
        return args.getInt(ARG_CATEGORY_ID)
    }

    companion object {

        private const val ARG_CATEGORY_ID = "category_id"
        fun newInstance(categoryId: Int) = ProductsFragment().apply {
            arguments = bundleOf(
                ARG_CATEGORY_ID to categoryId
            )
        }
    }
}