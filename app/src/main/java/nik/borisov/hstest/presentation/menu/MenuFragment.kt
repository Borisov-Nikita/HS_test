package nik.borisov.hstest.presentation.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import nik.borisov.hstest.R
import nik.borisov.hstest.databinding.FragmentMenuBinding
import nik.borisov.hstest.presentation.menu.adapters.BannersAdapter
import nik.borisov.hstest.presentation.menu.adapters.ProductsFragmentViewPagerAdapter

@AndroidEntryPoint
class MenuFragment : Fragment(R.layout.fragment_menu) {

    private var _binding: FragmentMenuBinding? = null
    private val binding: FragmentMenuBinding
        get() = _binding ?: throw NullPointerException("FragmentMenuBinding is null")

    private val viewModel by viewModels<MenuViewModel>()

    private val bannerAdapter = BannersAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMenuBinding.bind(view)

        setupRecyclerView()
        observeViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.bannersToolbarSection.bannersRecyclerView
        recyclerView.adapter = bannerAdapter
    }

    private fun setupViewPager(
        viewPagerFragmentList: List<Fragment>,
        tabLayoutTitleList: List<String>
    ) {
        val viewPager = binding.productsFragmentViewPager
        viewPager.adapter = ProductsFragmentViewPagerAdapter(
            viewPagerFragmentList,
            requireActivity()
        )
        val tabLayout = binding.categoryTabLayout
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabLayoutTitleList[position]
        }.attach()
    }

    private fun observeViewModel() {
        viewModel.banners.observe(viewLifecycleOwner) {
            bannerAdapter.submitList(it)
        }
        viewModel.categories.observe(viewLifecycleOwner) { productCategoryItemList ->
            val tabLayoutTitleList = productCategoryItemList.map { it.name }
            setupViewPager(
                viewPagerFragmentList = mutableListOf<Fragment>().apply {
                    for (i in tabLayoutTitleList.indices) {
                        add(ProductsFragment.newInstance(productCategoryItemList[i].id))
                    }
                },
                tabLayoutTitleList = tabLayoutTitleList
            )
        }
    }
}