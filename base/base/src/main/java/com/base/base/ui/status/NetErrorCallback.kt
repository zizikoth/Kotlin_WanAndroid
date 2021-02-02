package com.base.base.ui.status

import com.base.base.R
import com.load.status.callback.Callback

class NetErrorCallback : Callback() {
    override fun onCreateView(): Int = R.layout.layout_status_network_error

    //override fun childRetryClickId(): Int = R.id.status_net_error_view
}