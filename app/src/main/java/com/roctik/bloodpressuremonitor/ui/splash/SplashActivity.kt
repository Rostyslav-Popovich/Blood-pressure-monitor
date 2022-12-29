package com.roctik.bloodpressuremonitor.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.roctik.bloodpressuremonitor.R
import com.roctik.bloodpressuremonitor.databinding.ActivitySplashBinding
import com.roctik.bloodpressuremonitor.ui.main.MainActivity


class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAnimation()
    }

    private fun initAnimation() {
        val alphaAnim = AnimationUtils.loadAnimation(this, R.anim.anim_alpha)
        binding.logo.startAnimation(alphaAnim)
        binding.txtSplashTitle.startAnimation(alphaAnim)

        alphaAnim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                navigateToMainActivity()
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }

        })
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}