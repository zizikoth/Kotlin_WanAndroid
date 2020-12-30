package com.frame.core.utils

/**
 * title:点击工具类 防止过快点击
 * tip:
 *
 * @author zhou
 * @date 2018/7/24 上午9:35
 */
object ClickHelper {

    /*** 最后一次点击界面的时间 ***/
    private var lastClickTime: Long = 0

    /*** 最后一次点击退出的时间 ***/
    private var lastExitTime: Long = 0

    /**
     * 是否过快点击 每一次点击都会重置时间
     *
     * @return true 可以执行  false 不可执行
     */
    @JvmStatic
    val isNotFastClick: Boolean
        get() {
            var flag = false
            val curClickTime = System.currentTimeMillis()
            if (curClickTime - lastClickTime >= 600) {
                flag = true
            }
            lastClickTime = curClickTime
            return flag
        }


    /**
     * 是否过快长按
     *
     * @return true 可以执行  false 不可执行
     */
    @JvmStatic
    val isNotFastLongClick: Boolean
        get() {
            var flag = false
            val curClickTime = System.currentTimeMillis()
            if (curClickTime - lastClickTime >= 1200) {
                flag = true
            }
            lastClickTime = curClickTime
            return flag
        }


    /**
     * 判断是否双击返回键进行退出
     *
     *  override fun onBackPressed() {
     *      if (ClickHelper.isDoubleClickExit(...)) {
     *          super.onBackPressed()
     *      }
     *  }
     *
     * @return true 退出引用  false 提示弹窗
     */
    @JvmStatic
    fun isDoubleClickExit(showToast: () -> Unit): Boolean {
        var flag = true
        val curClickTime = System.currentTimeMillis()
        if (curClickTime - lastExitTime > 1500) {
            flag = false
            lastExitTime = curClickTime
            showToast.invoke()
        }
        return flag
    }
}
