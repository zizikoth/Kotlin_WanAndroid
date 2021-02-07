package com.frame.core.simple

import android.view.animation.Animation

/**
 * title:简单的动画监听
 * tip:
 *
 * @author zhou
 * @date 2018-11-13 下午7:41
 */
open class SimpleAnimationListener : Animation.AnimationListener {
    /**
     * 动画重复
     */
    override fun onAnimationRepeat(animation: Animation?) {}

    /**
     * 动画结束
     */
    override fun onAnimationEnd(animation: Animation?) {}

    /**
     * 动画开始
     */
    override fun onAnimationStart(animation: Animation?) {}
}