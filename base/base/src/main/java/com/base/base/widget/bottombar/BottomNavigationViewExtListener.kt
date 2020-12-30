package com.base.base.widget.bottombar

import android.view.MenuItem

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-01-02 10:13
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */

interface OnItemChangeListener {
    fun onItemChanged(menu: MenuItem, position: Int)
}

interface OnItemClickListener {
    fun onItemClicked(menu: MenuItem, position: Int)
}