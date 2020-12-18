package com.base.base.ui.status

import android.app.Activity
import android.content.Context
import android.view.View
import com.base.base.R
import com.frame.core.utils.extra.onClick
import com.frame.core.utils.extra.visible
import com.load.status.callback.Callback

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-12-18 11:35 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class NetErrorCallback : Callback() {
    override fun onCreateView(): Int = R.layout.layout_status_network_error

    override fun onViewCreate(context: Context?, view: View?) {
        if (context != null && view != null) {
            val backView = view.findViewById<View>(R.id.back)
            if (context is Activity) {
                backView.visible()
                backView.onClick {
                    context.finish()
                }
            }
        }
    }
}