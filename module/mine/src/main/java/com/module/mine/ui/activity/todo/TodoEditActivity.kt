package com.module.mine.ui.activity.todo

import android.content.Context
import android.widget.EditText
import com.base.base.entity.local.TodoContent
import com.base.base.entity.remote.TodoInfo
import com.base.base.manager.BusManager
import com.base.base.ui.BaseVmActivity
import com.business.common.ui.dialog.DialogHelper
import com.frame.core.utils.extra.*
import com.module.mine.R
import com.module.mine.databinding.ActivityTodoEditBinding
import com.module.mine.viewmodel.TodoViewModel

/**
 * title:清单列表详情 填写 修改
 * describe:
 *
 * @author memo
 * @date 2021-02-09 23:15
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class TodoEditActivity : BaseVmActivity<TodoViewModel, ActivityTodoEditBinding>() {

    companion object {
        fun start(context: Context, info: TodoInfo?, isDetail: Boolean) {
            context.startActivity<TodoEditActivity>("info" to info, "isDetail" to isDetail)
        }

        const val TYPE_ADD = 0
        const val TYPE_UPDATE = 1
        const val TYPE_DETAIL = 2
    }

    /*** 详情内容 ***/
    private var info: TodoInfo? = null

    /*** 表示清单类型 0 添加 1修改 2详情***/
    private var editType: Int = 0

    /*** 添加 修改内容 ***/
    private var content: TodoContent = TodoContent()

    private val editViews = arrayListOf<EditText>()

    override fun bindLayoutRes(): Int = R.layout.activity_todo_edit

    override fun initData() {
        info = intent.getParcelableExtra("info")
        val isDetail = intent.getBooleanExtra("isDetail", false)
        editType = if (info == null) TYPE_ADD else if (isDetail) TYPE_DETAIL else TYPE_UPDATE
    }

    override fun initView() {
        showContent()
        mBinding.run {
            // 输入框集合
            editViews.run {
                add(mEtContent)
                add(mEtTitle)
            }
            
            // 设置界面数据
            if (editType == TYPE_DETAIL) mTitleView.setRightText("修改")
            resetContent()

        }
    }

    override fun initListener() {
        mBinding.run {
            // 如果是详情可以切换更新状态
            if (editType == TYPE_DETAIL) {
                mTitleView.setOnRightClickListener {
                    if (editType == TYPE_DETAIL) {
                        editType = TYPE_UPDATE
                        // 修改 -> 取消
                        mTitleView.setRightText("取消")
                        resetContent()
                    } else {
                        // 取消 -> 修改
                        editType = TYPE_DETAIL
                        mTitleView.setRightText("修改")
                        resetContent()
                    }
                }
            }

            // 类型
            mTvType.onClick {
                if (editType != TYPE_DETAIL) {
                    DialogHelper.showTodoType(mContext) { type, title ->
                        mTvType.value = title
                        content.type = type.value
                    }
                }
            }
            // 级别
            mTvPriority.onClick {
                if (editType != TYPE_DETAIL) {
                    DialogHelper.showTodoPriority(mContext) { priority, title ->
                        mTvPriority.value = title
                        content.priority = priority.value
                    }
                }
            }
            // 状态
            mTvStatus.onClick {
                if (editType == TYPE_UPDATE) {
                    DialogHelper.showTodoStatus(mContext) { status, title ->
                        mTvStatus.value = title
                        content.status = status.value
                    }
                }
            }

            // 保存
            mTvSave.onClick {
                mBinding.run {
                    // 第一步填充内容
                    with(content) {
                        title = mEtTitle.value
                        content = mEtContent.value
                    }
                    // 校验数据
                    if (content.checkSuccess()) {
                        if (editType == TYPE_ADD) {
                            mViewModel.addTodo(content)
                        } else if (editType == TYPE_UPDATE) {
                            mViewModel.updateTodo(content)
                        }
                    }
                }
            }
        }

        observe(mViewModel.resultLiveData, this::onTodoResult)
    }

    override fun start() {
    }

    /**
     * 重置页面数据
     */
    private fun resetContent() {
        info?.let {
            content.run {
                id = it.id
                title = it.title
                content = it.content
                status = it.status
                type = it.type
                priority = it.priority
            }
        }
        mBinding.run {
            mEtTitle.value = content.title
            mEtContent.value = content.content
            mTvType.value = content.getTypeContent()
            mTvPriority.value = content.getPriorityContent()
            mTvStatus.value = content.getStatusContent()
            mTvSave.setVisible(editType == TYPE_UPDATE || editType == TYPE_ADD)
            mTvStatus.setVisible(editType != TYPE_ADD)
            mTipStatus.setVisible(editType != TYPE_ADD)
        }
        editViews.forEach { it.editable(editType == TYPE_UPDATE || editType == TYPE_ADD) }
    }

    /**
     * 新增 修改 清单信息
     */
    private fun onTodoResult(data: TodoInfo) {
        BusManager.get().todoLiveData.postValue(0)
        finish()
    }

}