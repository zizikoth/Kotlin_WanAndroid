package com.business.common.ui

import android.content.Context
import com.base.base.ui.BaseActivity
import com.base.web.utils.WebHelper
import com.frame.core.utils.extra.startActivity
import com.module.business.common.R
import com.module.business.common.databinding.ActivityWebBinding

/**
 * title:网页详情界面
 * describe:
 *
 * @author memo
 * @date 2021-01-31 00:11
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class WebActivity : BaseActivity<ActivityWebBinding>() {

    companion object {
        fun start(context: Context, title: String, url: String) {
            context.startActivity<WebActivity>("title" to title, "url" to url)
        }
    }

    private var title: String = ""
    private var url: String = ""
    private val webHelper = WebHelper()

    override fun bindLayoutRes(): Int = R.layout.activity_web

    override fun initialize() {
        title = intent.getStringExtra("title").orEmpty()
        url = intent.getStringExtra("url").orEmpty()
        mBinding.mTitleView.setTitle(title)
        webHelper.init(mContext, mBinding.mFlContainer, R.layout.layout_web_error, url)
    }
}