package com.frame.core.core

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.BarUtils

/**
 * title:启动页
 * describe:
 *
 * @author memo
 * @date 2020-12-30 5:27 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
abstract class CoreSplashActivity : AppCompatActivity() {

    open fun isLightMode(): Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BarUtils.setStatusBarColor(this, Color.TRANSPARENT, false)
        BarUtils.setStatusBarLightMode(this, isLightMode())
        initialize()
    }

    abstract fun initialize()

}