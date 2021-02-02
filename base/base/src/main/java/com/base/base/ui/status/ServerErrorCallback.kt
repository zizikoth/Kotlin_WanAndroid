package com.base.base.ui.status

import com.base.base.R
import com.load.status.callback.Callback


class ServerErrorCallback : Callback() {
    override fun onCreateView(): Int = R.layout.layout_status_server_error

    //override fun childRetryClickId(): Int = R.id.status_server_error_view
}