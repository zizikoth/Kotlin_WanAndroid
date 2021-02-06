package com.module.main.ui.activity

import android.animation.Animator
import com.base.base.ui.BaseActivity
import com.frame.core.simple.SimpleAnimatorListener
import com.frame.core.utils.extra.startActivity
import com.module.main.R
import com.module.main.databinding.ActivitySplashBinding

/**
 * title:启动页
 * describe:
 *
 * @author memo
 * @date 2020-12-30 17:25
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun bindLayoutRes(): Int = R.layout.activity_splash

    override fun initialize() {
        mBinding.mLottieView.addAnimatorListener(object : SimpleAnimatorListener() {
            override fun onAnimationEnd(animation: Animator?) {
                startActivity<MainActivity>()
                finish()
            }
        })
    }

}