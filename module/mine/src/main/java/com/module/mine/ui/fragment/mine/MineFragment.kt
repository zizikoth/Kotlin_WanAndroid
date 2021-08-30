package com.module.mine.ui.fragment.mine

import com.alibaba.android.arouter.facade.annotation.Route
import com.base.base.manager.BusManager
import com.base.base.manager.RouterManager
import com.base.base.manager.UserManager
import com.base.base.ui.BaseVmFragment
import com.frame.core.utils.extra.observe
import com.frame.core.utils.extra.onClick
import com.frame.core.utils.extra.paddingStatusBar
import com.frame.core.utils.extra.startActivity
import com.module.mine.R
import com.module.mine.databinding.FragmentMineBinding
import com.module.mine.ui.activity.coin.CoinActivity
import com.module.mine.ui.activity.coin.RankActivity
import com.module.mine.ui.activity.collection.article.CollectArticleActivity
import com.module.mine.ui.activity.collection.web.CollectWebActivity
import com.module.mine.ui.activity.login.LoginActivity
import com.module.mine.ui.activity.setting.SettingActivity
import com.module.mine.ui.activity.share.MyShareActivity
import com.module.mine.ui.activity.square.SquareActivity
import com.module.mine.ui.activity.todo.TodoActivity
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
            mTvAvatar.text = UserManager.getUser()?.nickname
        }
    }

    override fun initListener() {
        mBinding.run {
            // 设置
            mIvSetting.onClick { startActivity<SettingActivity>() }
            // 头像
            mTvAvatar.onClick { checkLogin { } }
            // 我的收藏
            mItemCollect.onClick { checkLogin { startActivity<CollectArticleActivity>() } }
            // 我的积分
            mItemCoin.onClick { checkLogin { startActivity<CoinActivity>() } }
            // 我的排名
            mItemRank.onClick { checkLogin { startActivity<RankActivity>() } }
            // 网址收藏
            mItemWeb.onClick { checkLogin { startActivity<CollectWebActivity>() } }
            // 未完清单
            mItemTodo.onClick { checkLogin { startActivity<TodoActivity>() } }
            // 我的分享
            mItemShare.onClick { checkLogin { startActivity<MyShareActivity>() } }
            // 分享广场
            mItemSquare.onClick { startActivity<SquareActivity>() }
        }
        // 登陆后响应
        BusManager.get().loginLiveData.observeInFragment(this) {
            mBinding.mTvAvatar.text = it.nickname
            mViewModel.getUserCollect()
            mViewModel.getUserCoin()
        }
        // 收藏后响应
        BusManager.get().collectionLiveData.observeInFragment(this) {
            mViewModel.getUserCollect()
        }
        // 回调用户信息 收藏
        observe(mViewModel.collectLiveData) { mBinding.mItemCollect.content = it.toString() }
        // 回调用户信息 积分 排名
        observe(mViewModel.coinLiveData) {
            // 个人积分
            mBinding.mItemCoin.content = it.coinCount.toString()
            // 个人排名
            mBinding.mItemRank.content = it.rank
        }
    }

    override fun start() {
        if (UserManager.hasCookie()) {
            mViewModel.getUserCoin()
            mViewModel.getUserCollect()
        }
    }


    /**
     * 检查是否登陆
     * @param action Function0<Unit>
     */
    private fun checkLogin(action: () -> Unit) = if (UserManager.hasCookie()) action.invoke() else startActivity<LoginActivity>()


}