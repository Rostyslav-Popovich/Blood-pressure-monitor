package com.roctik.bloodpressuremonitor.ui.info

import androidx.lifecycle.MutableLiveData
import com.roctik.bloodpressuremonitor.data.source.network.model.Article
import com.roctik.bloodpressuremonitor.domain.usecase.InfoUseCase
import com.roctik.bloodpressuremonitor.ui.base.BaseViewModel

class InfoViewModel(private val infoUseCase: InfoUseCase) : BaseViewModel() {

    val articleListLiveData = MutableLiveData<List<Article>>()

    init {
        getArticles()
    }

    private fun getArticles() = launch {
        articleListLiveData.postValue(infoUseCase.getArticles())
    }

}