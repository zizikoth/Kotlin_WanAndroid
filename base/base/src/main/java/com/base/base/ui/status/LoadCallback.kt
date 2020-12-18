package com.base.base.ui.status

import android.content.Context
import android.view.View
import com.base.base.R
import com.load.status.callback.Callback


class LoadCallback : Callback() {

    override fun onCreateView(): Int = R.layout.layout_status_loading

    override fun onReloadEvent(context: Context?, view: View?): Boolean {
        return true
    }
}