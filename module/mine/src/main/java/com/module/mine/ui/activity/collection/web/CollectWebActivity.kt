package com.module.mine.ui.activity.collection.web

import androidx.recyclerview.widget.LinearLayoutManager
import com.base.base.entity.remote.WebInfo
import com.base.base.manager.BusManager
import com.base.base.manager.DataManager
import com.base.base.ui.BaseVmActivity
import com.base.base.utils.*
import com.base.base.widget.recyclerview.RecyclerItemDecoration
import com.business.common.databinding.LayoutTitleRefreshListBinding
import com.business.common.ui.activity.web.WebActivity
import com.business.common.ui.dialog.DialogHelper
import com.frame.core.utils.extra.copyToClipboard
import com.frame.core.utils.extra.finish
import com.frame.core.utils.extra.observe
import com.kongzue.dialog.v3.FullScreenDialog
import com.module.mine.R
import com.module.mine.ui.adapter.CollectWebAdapter
import com.module.mine.viewmodel.CollectWebViewModel

/**
 * title:网页收藏
 * describe:
 *
 * @author memo
 * @date 2021-02-08 18:20
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class CollectWebActivity : BaseVmActivity<CollectWebViewModel, LayoutTitleRefreshListBinding>() {

    private val shouldFirstTip by lazy { DataManager.getWebFirstTip() }

    private val mAdapter = CollectWebAdapter()

    override fun bindLayoutRes(): Int = R.layout.layout_title_refresh_list

    override fun initData() {}

    override fun initView() {
        mBinding.run {
            mTitleView.setTitle("网址收藏")
            mTitleView.setRightDrawable(R.drawable.ic_collect_add)

            mRvList.run {
                setHasFixedSize(true)
                addItemDecoration(RecyclerItemDecoration.Builder(mContext).defaultBuild())
                layoutManager = LinearLayoutManager(mContext)
                adapter = mAdapter
            }
        }
    }

    override fun initListener() {
        mBinding.run {
            // 新增
            mTitleView.setOnRightClickListener {
                DialogHelper.showWebCollect(mContext, null, null, null, this@CollectWebActivity::collect)
            }
            // 刷新
            mRefreshLayout.setOnRefreshListener {
                mViewModel.collectWebList()
            }
        }
        // 长按复制到粘贴板
        mAdapter.onItemChildLongClick { viewId, data ->
            if (viewId == R.id.mFlContent) {
                copyToClipboard(data.link)
                toast("网址已复制到粘贴板")
            }
        }
        mAdapter.onItemChildClick { viewId, data ->
            when (viewId) {
                // 修改收藏
                R.id.mIvEdit -> DialogHelper.showWebCollect(mContext, data.id, data.name, data.link, this::collect)
                // 删除收藏
                R.id.mIvDelete -> mViewModel.collectWebDelete(data.id)
                // 跳转
                R.id.mFlContent -> WebActivity.start(mContext, data.name, data.link)
            }
        }

        observe(mViewModel.listLiveData, this::onCollectionList)
        observe(mViewModel.addLiveData, this::onCollectionAdd)
        observe(mViewModel.updateLiveData, this::onCollectionUpdate)
        observe(mViewModel.deleteLiveData, this::onCollectionDelete)

        // 登录返回
        BusManager.get().loginLiveData.observeInActivity(mContext) { start() }
    }

    override fun start() {
        mViewModel.collectWebList()
    }

    /**
     * 添加 修改 收藏网址
     */
    private fun collect(dialog: FullScreenDialog, id: Int?, name: String, link: String) {
        if (id == null) {
            mViewModel.collectWebAdd(dialog, name, link)
        } else {
            mViewModel.collectWebUpdate(dialog, id, name, link)
        }
    }

    /**
     * 回调网址列表
     * @param data ArrayList<WebInfo>
     */
    private fun onCollectionList(data: ArrayList<WebInfo>) {
        mBinding.mRefreshLayout.finish(false)
        mAdapter.setList(data)
        mAdapter.showEmpty(mContext, data.isEmpty())
        // 如果是第一次进入那么显示提示弹窗
        if (data.isNotEmpty() && shouldFirstTip) {
            DialogHelper.showWebCollectFirstTip(mContext)
        }
    }

    /**
     * 新增收藏
     * @param data WebInfo
     */
    private fun onCollectionAdd(data: WebInfo) {
        mAdapter.addData(data)
    }

    /**
     * 收藏修改
     * @param data WebInfo
     */
    private fun onCollectionUpdate(data: WebInfo) {
        mAdapter.data.run {
            forEachIndexed { index, webInfo ->
                if (webInfo.id == data.id) {
                    mAdapter.data[index] = data
                    mAdapter.notifyItemChanged(index)
                    return@run
                }
            }
        }
    }

    /**
     * 收藏删除
     * @param data WebInfo
     */
    private fun onCollectionDelete(id: Int) {
        mAdapter.data.findLast { it.id == id }?.let { mAdapter.remove(it) }
        if (mAdapter.data.isEmpty()) mBinding.mRefreshLayout.autoRefresh()
    }

}