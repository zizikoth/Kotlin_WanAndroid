package com.frame.core.simple

import android.animation.Animator


/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-02-11 17:59
 */
open class SimpleAnimatorListener : Animator.AnimatorListener {

	override fun onAnimationStart(animation: Animator?) {}

	override fun onAnimationStart(animation: Animator?, isReverse: Boolean) {}

	override fun onAnimationRepeat(animation: Animator?) {}

	override fun onAnimationEnd(animation: Animator?) {}

	override fun onAnimationEnd(animation: Animator?, isReverse: Boolean) {}

	override fun onAnimationCancel(animation: Animator?) {}
}