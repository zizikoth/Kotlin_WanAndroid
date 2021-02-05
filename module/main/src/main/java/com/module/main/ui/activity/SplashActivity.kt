package com.module.main.ui.activity

import androidx.lifecycle.lifecycleScope
import com.frame.core.core.CoreSplashActivity
import com.frame.core.utils.extra.startActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
class SplashActivity : CoreSplashActivity() {

    override fun initialize() {
        lifecycleScope.launch(Dispatchers.Main) {
            delay(1500)
            startActivity<MainActivity>()
            finish()
        }
    }
}