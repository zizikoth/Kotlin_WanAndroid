package com.module.mine.ui.activity.todo

import androidx.recyclerview.widget.LinearLayoutManager
import com.base.base.entity.local.TodoFilter
import com.base.base.entity.remote.PageList
import com.base.base.entity.remote.TodoInfo
import com.base.base.manager.BusManager
import com.base.base.ui.BaseVmActivity
import com.base.base.utils.onItemChildClick
import com.base.base.utils.showEmpty
import com.business.common.ui.dialog.DialogHelper
import com.frame.core.utils.extra.*
import com.module.mine.R
import com.module.mine.databinding.ActivityTodoBinding
import com.module.mine.ui.adapter.TodoAdapter
import com.module.mine.viewmodel.TodoViewModel

/**
 * title:未完清单
 * describe:
 *
 * @author memo
 * @date 2021-02-09 11:49 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class TodoActivity : BaseVmActivity<TodoViewModel, ActivityTodoBinding>() {

    /*** 列表的筛选条件 ***/
    private var filter = TodoFilter()

    private val mAdapter = TodoAdapter()

    override fun bindLayoutRes(): Int = R.layout.activity_todo

    override fun initData() {}

    override fun initView() {
        mBinding.run {
            mTitleView.setTitle("未完清单")
            mTitleView.setRightDrawable(R.drawable.ic_filter)

            mRvList.run {
                layoutManager = LinearLayoutManager(mContext)
                adapter = mAdapter
            }
        }
    }

    override fun initListener() {
        // 筛选
        mBinding.mTitleView.setOnRightClickListener {
            DialogHelper.showTodoFilter(mContext, filter) {
                filter = it
                showLoad()
                mViewModel.getTodoList(filter)
            }
        }
        // 刷新 加载
        mBinding.mRefreshLayout.onRefreshAndLoadMore({
            filter.page = 1
            mViewModel.getTodoList(filter)
        }, {
            mViewModel.getTodoList(filter)
        })
        // 新增Todo
        mBinding.mBtnAdd.onClick { TodoEditActivity.start(mContext, null, false) }

        mAdapter.onItemChildClick { viewId, data ->
            when (viewId) {
                // 修改Todo状态
                R.id.mIvStatus -> {
                    mViewModel.setTodoStatus(data)
                }
                // 编辑Todo
                R.id.mIvEdit -> {
                    TodoEditActivity.start(mContext, data, false)
                    mAdapter.close(mAdapter.data.indexOf(data))
                }
                // 删除Todo
                R.id.mIvDelete -> mViewModel.deleteTodo(data)
                // Todo详情
                R.id.mLlContent -> {
                    TodoEditActivity.start(mContext, data, true)
                    mAdapter.close(mAdapter.data.indexOf(data))
                }
            }
        }


        observe(mViewModel.listLiveData, this::onTodoList)
        observe(mViewModel.statusLiveData, this::onTodoStatus)
        observe(mViewModel.deleteLiveData, this::onTodoDelete)

        // 清单新增修改后刷新列表
        BusManager.get().todoLiveData.observeInActivity(mContext) { mBinding.mRefreshLayout.autoRefresh() }
        // 登录返回
        BusManager.get().loginLiveData.observeInActivity(mContext) { start() }
    }

    override fun start() {
        mViewModel.getTodoList(filter)
    }

    /**
     * 清单列表回调
     */
    private fun onTodoList(data: PageList<TodoInfo>) {
        mBinding.mRefreshLayout.finish(data.hasMore())
        if (filter.page == 1) {
            mAdapter.setList(data.datas)
            mAdapter.showEmpty(mContext, data.isEmpty())
        } else {
            mAdapter.addData(data.datas)
        }
        if (data.hasMore()) filter.page++
    }

    /**
     * 修改Todo状态
     */
    private fun onTodoStatus(info: TodoInfo) {
        mAdapter.data.run {
            forEachIndexed { index, todoInfo ->
                if (todoInfo.id == info.id) {
                    mAdapter.data[index] = info
                    mAdapter.notifyItemChanged(index)
                    return@run
                }
            }
        }
    }

    /**
     * 删除Todo
     */
    private fun onTodoDelete(id: Int) {
        mAdapter.data.findLast { id == it.id }?.let { mAdapter.remove(it) }
        if (mAdapter.data.isEmpty()) mBinding.mRefreshLayout.autoRefresh()
    }

}