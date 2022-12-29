package com.roctik.bloodpressuremonitor.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.roctik.bloodpressuremonitor.data.source.network.model.Article
import com.roctik.bloodpressuremonitor.databinding.FragmentInfoBinding
import com.roctik.bloodpressuremonitor.domain.model.ItemAction
import com.roctik.bloodpressuremonitor.ui.listener.ItemClickListener
import com.roctik.bloodpressuremonitor.util.Constants.KEY_ARTICLE_URL
import com.roctik.bloodpressuremonitor.util.Constants.REQUEST_ARTICLE_URL
import org.koin.androidx.viewmodel.ext.android.viewModel

class InfoFragment : Fragment(), ItemClickListener<Article>  {

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!

    private val infoViewModel: InfoViewModel by viewModel()
    private lateinit var adapter: ArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObserver()
        initArticleRecycler()
    }

    private fun setupObserver() {
        infoViewModel.articleListLiveData.observe(viewLifecycleOwner){
            adapter.differ.submitList(it)
        }
    }

    private fun initArticleRecycler() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = ArticleAdapter(this)
        binding.rvArticles.layoutManager = layoutManager
        binding.rvArticles.setHasFixedSize(true)
        binding.rvArticles.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(item: Article, action: ItemAction) {
        when(action){
            ItemAction.SELECT ->{
                setFragmentResult(
                    REQUEST_ARTICLE_URL,
                    bundleOf(KEY_ARTICLE_URL to item.url)
                )

                val articleAction = InfoFragmentDirections.actionInfoFragmentToArticleFragment()
                findNavController().navigate(articleAction)
            }

            else -> {}
        }
    }
}