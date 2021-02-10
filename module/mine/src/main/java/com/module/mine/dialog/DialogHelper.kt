package com.business.common.ui.dialog

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.base.base.entity.local.*
import com.base.base.manager.DataManager
import com.base.base.utils.toast
import com.frame.core.utils.extra.color
import com.frame.core.utils.extra.getCheckPosition
import com.frame.core.utils.extra.onClick
import com.frame.core.utils.extra.value
import com.kongzue.dialog.util.TextInfo
import com.kongzue.dialog.v3.BottomMenu
import com.kongzue.dialog.v3.FullScreenDialog
import com.kongzue.dialog.v3.MessageDialog
import com.module.mine.R
import com.module.mine.databinding.DialogCollectArticleBinding
import com.module.mine.databinding.DialogCollectWebBinding
import com.module.mine.databinding.DialogTodoFilterBinding

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-08 11:54 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
object DialogHelper {
    /**
     * 文章收藏
     */
    fun showArticleCollect(activity: AppCompatActivity, action: (dialog: FullScreenDialog, title: String, author: String, link: String) -> Unit) {
        FullScreenDialog.show(activity, R.layout.dialog_collect_article) { dialog, rootView ->
            val mBinding = DataBindingUtil.bind<DialogCollectArticleBinding>(rootView)
            mBinding?.run {
                mTvCollect.onClick {
                    val title = mEtTitle.value
                    val author = mEtAuthor.value
                    val link = mEtLink.value
                    when {
                        title.isEmpty() -> toast("请填写文章标题")
                        author.isEmpty() -> toast("请填写文章作者")
                        link.isEmpty() -> toast("请填写文章链接")
                        else -> action.invoke(dialog, title, author, link)
                    }
                }
            }
        }.setTitle("添加收藏").setTitleTextInfo(TextInfo().apply {
            isBold = true
            fontColor = color(R.color.textDark)
        }).setCancelButton("取消") { dialog, _ ->
            dialog.doDismiss()
            true
        }
    }

    /**
     * 网页收藏
     */
    fun showWebCollect(activity: AppCompatActivity, id: Int?, name: String?, link: String?,
                       action: (dialog: FullScreenDialog, id: Int?, name: String, link: String) -> Unit) {
        FullScreenDialog.show(activity, R.layout.dialog_collect_web) { dialog, rootView ->
            val mBinding = DataBindingUtil.bind<DialogCollectWebBinding>(rootView)
            mBinding?.run {
                mEtName.value = name.orEmpty()
                mEtLink.value = link.orEmpty()
                if (id != null) mTvCollect.text = "修改"
                mTvCollect.onClick {
                    val nameStr = mEtName.value
                    val linkStr = mEtLink.value
                    when {
                        nameStr.isEmpty() -> toast("请填写网页名称")
                        linkStr.isEmpty() -> toast("请填写网页链接")
                        else -> action.invoke(dialog, id, nameStr, linkStr)
                    }
                }
            }
        }.setTitle("添加收藏").setTitleTextInfo(TextInfo().apply {
            isBold = true
            fontColor = color(R.color.textDark)
        }).setCancelButton("取消") { dialog, _ ->
            dialog.doDismiss()
            true
        }
    }

    /**
     * 第一次进入网页收藏列表提示
     */
    fun showWebCollectFirstTip(activity: AppCompatActivity) {
        MessageDialog.show(activity, "小贴士", "长按条目复制网址到粘贴板，点击条目显示网页", "确定")
            .setCancelable(false)
            .setOkButton { baseDialog, _ ->
                baseDialog.doDismiss()
                DataManager.setWebFirstTip()
                true
            }
    }

    /**
     * Todo列表筛选
     */
    fun showTodoFilter(activity: AppCompatActivity, filter: TodoFilter, action: (filter: TodoFilter) -> Unit) {
        BottomMenu.show(activity, arrayOf(), null)
            .setCustomView(R.layout.dialog_todo_filter) { dialog, view ->
                val binding = DataBindingUtil.bind<DialogTodoFilterBinding>(view)
                binding?.run {
                    this.filter = filter
                    mTvPositive.onClick {
                        when (mGroupStatus.getCheckPosition()) {
                            0 -> filter.state = TodoStatus.ALL
                            1 -> filter.state = TodoStatus.UN_COMPLETE
                            2 -> filter.state = TodoStatus.COMPLETE
                        }
                        when (mGroupType.getCheckPosition()) {
                            0 -> filter.type = TodoType.ALL
                            1 -> filter.type = TodoType.WORK
                            2 -> filter.type = TodoType.LIFE
                            3 -> filter.type = TodoType.MINE
                            4 -> filter.type = TodoType.OTHER
                        }
                        when (mGroupPriority.getCheckPosition()) {
                            0 -> filter.priority = TodoPriority.ALL
                            1 -> filter.priority = TodoPriority.IMMEDIATE
                            2 -> filter.priority = TodoPriority.HIGH
                            3 -> filter.priority = TodoPriority.NORMAL
                            4 -> filter.priority = TodoPriority.LOW
                        }
                        when (mGroupOrder.getCheckPosition()) {
                            0 -> filter.orderBy = TodoOrderBy.CREATE_DATE_UP
                            1 -> filter.orderBy = TodoOrderBy.CREATE_DATE_DOWN
                            2 -> filter.orderBy = TodoOrderBy.COMPLETE_DATE_UP
                            3 -> filter.orderBy = TodoOrderBy.COMPLETE_DATE_DOWN
                        }
                        filter.page = 1
                        action.invoke(filter)
                        dialog.doDismiss()
                    }
                    mTvNegative.onClick { dialog.doDismiss() }
                }

            }.setShowCancelButton(false)
            .setTitle("筛选")
            .setMenuTitleTextInfo(TextInfo().apply {
                isBold = true
                fontColor = color(R.color.textDark)
            })
    }

    /**
     * 显示Todo类型
     */
    fun showTodoType(activity: AppCompatActivity, action: (type: TodoType, title: String) -> Unit) {
        val types = arrayOf(TodoType.WORK, TodoType.LIFE, TodoType.MINE, TodoType.OTHER)
        BottomMenu.show(activity, "清单类型", arrayOf("工作", "生活", "个人", "其他")) { text, index ->
            action.invoke(types[index], text)
        }
    }

    /**
     * 显示Todo级别
     */
    fun showTodoPriority(activity: AppCompatActivity, action: (priority: TodoPriority, title: String) -> Unit) {
        val priorities = arrayOf(TodoPriority.IMMEDIATE, TodoPriority.HIGH, TodoPriority.NORMAL, TodoPriority.LOW)
        BottomMenu.show(activity, "清单级别", arrayOf("立刻马上！！！", "比较重要！！", "一般般吧！", "无所谓的")) { text, index ->
            action.invoke(priorities[index], text)
        }
    }

    /**
     * 显示Todo状态
     */
    fun showTodoStatus(activity: AppCompatActivity, action: (status: TodoStatus, title: String) -> Unit) {
        val priorities = arrayOf(TodoStatus.COMPLETE, TodoStatus.UN_COMPLETE)
        BottomMenu.show(activity, "清单状态", arrayOf("已完成", "未完成")) { text, index ->
            action.invoke(priorities[index], text)
        }
    }


    /**
     * 第一次进入广场列表提示
     */
    fun showSquareFirstTip(activity: AppCompatActivity) {
        MessageDialog.show(activity, "小贴士", "长按条目查看更多作者文章，短击条目显示文章网页", "确定")
            .setCancelable(false)
            .setOkButton { baseDialog, _ ->
                baseDialog.doDismiss()
                DataManager.setSquareFirstTip()
                true
            }
    }


    /**
     * 网页收藏
     */
    fun showShareArticle(activity: AppCompatActivity, action: (dialog: FullScreenDialog, title: String, link: String) -> Unit) {
        FullScreenDialog.show(activity, R.layout.dialog_collect_web) { dialog, rootView ->
            val mBinding = DataBindingUtil.bind<DialogCollectWebBinding>(rootView)
            mBinding?.run {
                mTvCollect.text = "分享"
                mTvCollect.onClick {
                    val title = mEtName.value
                    val link = mEtLink.value
                    when {
                        title.isEmpty() -> toast("请填写分享网页名称")
                        link.isEmpty() -> toast("请填写分享网页链接")
                        else -> action.invoke(dialog, title, link)
                    }
                }
            }
        }.setTitle("添加分享").setTitleTextInfo(TextInfo().apply {
            isBold = true
            fontColor = color(R.color.textDark)
        }).setCancelButton("取消") { dialog, _ ->
            dialog.doDismiss()
            true
        }
    }
}