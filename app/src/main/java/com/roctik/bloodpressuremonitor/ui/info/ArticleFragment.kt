package com.roctik.bloodpressuremonitor.ui.info

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.roctik.bloodpressuremonitor.databinding.FragmentArticleBinding
import com.roctik.bloodpressuremonitor.util.Constants.KEY_ARTICLE_URL
import com.roctik.bloodpressuremonitor.util.Constants.REQUEST_ARTICLE_URL

class ArticleFragment : Fragment() {

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!

    private var articleUrl: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleBackPress()

        setFragmentResultListener(REQUEST_ARTICLE_URL) { requestKey, bundle ->
            if (requestKey == REQUEST_ARTICLE_URL) {
                articleUrl = bundle.getString(KEY_ARTICLE_URL)
            }

            articleUrl?.let {
                initWebView(it)
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView(url: String) {
        binding.webView.webViewClient = WebViewClient()
        binding.webView.loadUrl(url)
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.setSupportZoom(true)
    }

    private fun handleBackPress() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (binding.webView.canGoBack())
                        binding.webView.goBack()
                    else
                        findNavController().popBackStack()
                }
            }

        activity?.onBackPressedDispatcher?.addCallback(callback)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}