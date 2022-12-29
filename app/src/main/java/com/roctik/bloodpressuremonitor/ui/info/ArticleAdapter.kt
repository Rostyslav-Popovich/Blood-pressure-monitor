package com.roctik.bloodpressuremonitor.ui.info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.roctik.bloodpressuremonitor.data.source.network.model.Article
import com.roctik.bloodpressuremonitor.databinding.ItemArticleBinding
import com.roctik.bloodpressuremonitor.domain.model.ItemAction
import com.roctik.bloodpressuremonitor.ui.listener.ItemClickListener
import com.roctik.bloodpressuremonitor.util.GlideApp

class ArticleAdapter(private var articleListener: ItemClickListener<Article>) :
    RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleViewHolder {
        return ArticleViewHolder(
            ItemArticleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
    }

    override fun getItemCount() = differ.currentList.size

    inner class ArticleViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(item: Article) {
            binding.itemArticleTitle.text = item.title

            GlideApp
                .with(binding.root.context)
                .load(item.urlToImage)
                .into(binding.itemArticleImage)

            binding.cardArticles.setOnClickListener {
                articleListener.onItemClick(item, ItemAction.SELECT)
            }
        }

    }

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(
            oldItem: Article,
            newItem: Article
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: Article,
            newItem: Article
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

}