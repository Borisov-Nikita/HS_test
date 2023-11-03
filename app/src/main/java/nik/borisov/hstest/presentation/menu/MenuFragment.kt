package nik.borisov.hstest.presentation.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import nik.borisov.hstest.R
import nik.borisov.hstest.databinding.FragmentMenuBinding
import nik.borisov.hstest.presentation.menu.adapters.BannersAdapter
import nik.borisov.hstest.presentation.menu.adapters.ProductsFragmentViewPagerAdapter

class MenuFragment : Fragment(R.layout.fragment_menu) {

    private var _binding: FragmentMenuBinding? = null
    private val binding: FragmentMenuBinding
        get() = _binding ?: throw NullPointerException("FragmentMenuBinding is null")

    private val viewModel by viewModels<MenuViewModel>()

    private val bannerAdapter = BannersAdapter()

    private val tabLayoutTitleList by lazy {
        listOf(
            "Pizza",
            "Combo",
            "Snacks",
            "Desserts",
            "Drinks"
        )
    }

    private val viewPagerFragmentList by lazy {
        mutableListOf<Fragment>().apply {
            for (i in tabLayoutTitleList.indices) {
                add(ProductsFragment.newInstance())
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMenuBinding.bind(view)

        setupRecyclerView()
        setupViewPager()
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

    private fun setupViewPager() {
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
    }
}