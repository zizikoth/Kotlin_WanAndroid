package com.module.mine.ui.fragment.mine

import com.alibaba.android.arouter.facade.annotation.Route
import com.base.base.entity.remote.ArticleList
import com.base.base.entity.remote.CoinInfo
import com.base.base.entity.zip.Zip2
import com.base.base.manager.RouterManager
import com.base.base.manager.UserManager
import com.base.base.ui.BaseVmFragment
import com.blankj.utilcode.util.CleanUtils
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.FileUtils
import com.frame.core.utils.extra.observe
import com.frame.core.utils.extra.onClick
import com.frame.core.utils.extra.paddingStatusBar
import com.frame.core.utils.extra.startActivity
import com.module.mine.R
import com.module.mine.databinding.FragmentMineBinding
import com.module.mine.ui.activity.login.LoginActivity
import com.module.mine.viewmodel.MineViewModel

/**
 * title:我的
 * describe:
 *
 * @author memo
 * @date 2021-02-05 15:14
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
@Route(path = RouterManager.Mine.MINE_FRAGMENT)
class MineFragment : BaseVmFragment<MineViewModel, FragmentMineBinding>() {
    /*** 绑定界面 ***/
    override fun bindLayoutRes(): Int = R.layout.fragment_mine

    override fun initData() {
    }

    override fun initView() {
        showContent()
        mBinding.run {
            root.paddingStatusBar()
            // 缓存文件
            (FileUtils.getLength(mContext.externalCacheDir) + FileUtils.getLength(mContext.cacheDir)).run {
                if (this > 0) mItemCache.setItemExtraText(ConvertUtils.byte2FitMemorySize(this))
            }
        }
    }

    override fun initListener() {
        mBinding.run {
            // 我的收藏
            mItemCollect.onClick { checkLogin { } }
            // 我的积分
            mItemCoin.onClick { checkLogin { } }
            // 我的排名
            mItemRank.onClick { checkLogin {} }
            // TODO列表
            mItemTodo.onClick { checkLogin { } }
            // 我的分享
            mItemShare.onClick { }
            // 清理缓存
            mItemCache.onClick {
                CleanUtils.cleanInternalCache()
                CleanUtils.cleanExternalCache()
                mItemCache.setItemExtraText("")
            }
            // 关于我们
            mItemAbout.onClick { checkLogin { } }
        }
        // 登陆后响应
        UserManager.responseLogin(this) {
            // 积分+ 排名
            mViewModel.getUserInfo()
        }
        observe(mViewModel.infoLiveData, this::onUserInfo)
    }

    override fun start() {
        if (UserManager.isLogin()) mViewModel.getUserInfo()
    }

    /**
     * 获取到个人积分信息
     * @param data Zip2<CoinInfo,ArticleList>
     */
    private fun onUserInfo(data: Zip2<CoinInfo, ArticleList>) {
        // 个人积分
        mBinding.mItemCoin.content = data.first.coinCount.toString()
        // 个人排名
        mBinding.mItemRank.content = data.first.rank.toString()
        // 个人收藏
        mBinding.mItemCollect.content = data.second.total.toString()
    }

    /**
     * 检查是否登陆
     * @param action Function0<Unit>
     */
    private fun checkLogin(action: () -> Unit) = if (UserManager.isLogin()) action.invoke() else startActivity<LoginActivity>()


}