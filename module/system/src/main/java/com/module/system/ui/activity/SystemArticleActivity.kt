package com.module.system.ui.activity

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.base.entity.remote.ArticleList
import com.base.base.ui.BaseVmActivity
import com.base.base.utils.onItemClickListener
import com.base.base.utils.showEmpty
import com.blankj.utilcode.util.LogUtils
import com.business.common.ui.activity.web.WebActivity
import com.business.common.ui.adapter.ArticleAdapter
import com.frame.core.utils.extra.finish
import com.frame.core.utils.extra.observe
import com.frame.core.utils.extra.onRefreshAndLoadMore
import com.frame.core.utils.extra.startActivity
import com.module.system.R
import com.module.system.databinding.ActivitySystemArticleBinding
import com.module.system.viewmodel.SystemViewModel

/**
 * title:体系文章界面
 * describe:
 *
 * @author memo
 * @date 2021-02-02 15:05
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class SystemArticleActivity : BaseVmActivity<SystemViewModel, ActivitySystemArticleBinding>() {

    companion object {
        fun start(context: Context, title: String, cid: Int) {
            context.startActivity<SystemArticleActivity>("title" to title, "cid" to cid)
        }
    }

    private var title: String = ""
    private var cid: Int = 0
    private var page = 0
    private val mAdapter = ArticleAdapter()

    override fun bindLayoutRes(): Int = R.layout.activity_system_article

    override fun initData() {
        cid = intent.getIntExtra("cid", cid)
        title = intent.getStringExtra("title").orEmpty()
    }

    override fun initView() {
        mBinding.run {
            mTitleView.setTitle(title)
            mRvList.run {
                layoutManager = LinearLayoutManager(mContext)
                adapter = mAdapter
            }
        }
    }

    override fun initListener() {
        mBinding.mRefreshLayout.onRefreshAndLoadMore({
            page = 0
            mViewModel.getSystemArticle(page, cid)
        }, {
            mViewModel.getSystemArticle(page, cid)
        })
        mAdapter.onItemClickListener { WebActivity.start(mContext, it.title, it.link) }

        observe(mViewModel.articleLiveData, this::onSystemArticles)
    }

    override fun start() {
        mViewModel.getSystemArticle(page, cid)
    }

    private fun onSystemArticles(data: ArticleList) {
        mBinding.mRefreshLayout.finish(data.hasMore())
        LogUtils.iTag("data", data.datas)
        if (page == 0) {
            mAdapter.setList(data.datas)
            mAdapter.showEmpty(mContext, data.isEmpty())
        } else {
            mAdapter.addData(data.datas)
        }
        if (data.hasMore()) page++
    }


}