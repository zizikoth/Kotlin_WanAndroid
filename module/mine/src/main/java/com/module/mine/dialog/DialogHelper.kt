package com.module.mine.dialog

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.base.base.utils.toast
import com.frame.core.utils.extra.*
import com.kongzue.dialog.v3.FullScreenDialog
import com.module.mine.R
import com.module.mine.databinding.DialogCollectionBinding

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
    fun showCollection(activity: AppCompatActivity, action: (dialog: FullScreenDialog, title: String, author: String, link: String) -> Unit) {
        FullScreenDialog.show(activity, R.layout.dialog_collection) { dialog, rootView ->
            val mBinding = DataBindingUtil.bind<DialogCollectionBinding>(rootView)
            mBinding?.run {
                val radius = 8.dp2px
                mEtTitle.round(color(R.color.color_F5F5F5), radius)
                mEtAuthor.round(color(R.color.color_F5F5F5), radius)
                mEtLink.round(color(R.color.color_F5F5F5), radius)
                mTvCollect.round(color(R.color.color_shape), radius)

                mTitleView.setOnLeftClickListener { dialog.doDismiss() }

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
        }
    }
}