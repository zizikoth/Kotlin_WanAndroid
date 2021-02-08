package com.business.common.ui.activity.web

import android.animation.Animator
import android.content.Context
import com.base.base.manager.BusViewModel
import com.base.base.manager.RouterManager
import com.base.base.manager.UserManager
import com.base.base.ui.BaseVmActivity
import com.base.web.utils.web.WebHelper
import com.blankj.utilcode.util.LogUtils
import com.business.common.R
import com.business.common.databinding.ActivityWebBinding
import com.business.common.viewmodel.CollectViewModel
import com.frame.core.simple.SimpleAnimatorListener
import com.frame.core.utils.extra.gone
import com.frame.core.utils.extra.observe
import com.frame.core.utils.extra.startActivity
import com.frame.core.utils.extra.visible

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
class WebActivity : BaseVmActivity<CollectViewModel, ActivityWebBinding>() {

    companion object {
        fun start(context: Context, id: Int, title: String, url: String) {
            context.startActivity<WebActivity>("id" to id, "title" to title, "url" to url)
        }

        fun start(context: Context, id: Int, originId: Int, title: String, url: String) {
            context.startActivity<WebActivity>("id" to id, "originId" to originId, "title" to title, "url" to url)
        }
    }

    private var title: String = ""
    private var url: String = ""
    private var id: Int = 0
    private var originId: Int = Int.MIN_VALUE
    private var isCollect = false
    private val webHelper = WebHelper()

    override fun bindLayoutRes(): Int = R.layout.activity_web

    override fun initData() {
        id = intent.getIntExtra("id", id)
        title = intent.getStringExtra("title").orEmpty()
        url = intent.getStringExtra("url").orEmpty()
        originId = intent.getIntExtra("originId", originId)
        isCollect = originId != Int.MIN_VALUE
        if (!isCollect) isCollect = UserManager.hasCollected(id)

        LogUtils.iTag("Web", "id = $id title = $title url = $url originId = $originId isCollect=$isCollect ")
        LogUtils.iTag("Web", UserManager.getUser()?.collectIds)
    }

    override fun initView() {
        showContent()
        mBinding.mTitleView.run {
            setTitle(title)
            setRightDrawable(if (isCollect) R.drawable.ic_collected else R.drawable.ic_collect)
        }
        webHelper.init(mContext, mBinding.mFlContainer, R.layout.layout_status_web_error, url)
    }

    override fun initListener() {
        mBinding.mTitleView.setOnRightClickListener {
            if (UserManager.hasCookie()) {
                if (isCollect) {
                    if (originId == Int.MIN_VALUE) {
                        // 取消收藏站内文章
                        mViewModel.unCollectInArticle(id)
                    } else {
                        // 取消收藏文章（可能是站外的文章）
                        mViewModel.unCollectInList(id, originId)
                    }
                } else {
                    // 收藏 首先使用originId 需要判断originId的可用性
                    mViewModel.collect(if (originId != Int.MIN_VALUE && originId != -1) originId else id)
                    mBinding.mLottieView.run {
                        visible()
                        playAnimation()
                    }
                }
            } else {
                RouterManager.startLogin()
            }
        }

        mBinding.mLottieView.addAnimatorListener(object : SimpleAnimatorListener() {
            override fun onAnimationEnd(animation: Animator?) {
                mBinding.mLottieView.gone()
            }
        })

        observe(mViewModel.collectLiveData) {
            isCollect = it
            mBinding.mTitleView.setRightDrawable(if (isCollect) R.drawable.ic_collected else R.drawable.ic_collect)
            if (isCollect) UserManager.addCollected(id) else UserManager.removeCollected(id)
            BusViewModel.get().collectionLiveData.postValue("")
        }
    }

    override fun start() {

    }

}