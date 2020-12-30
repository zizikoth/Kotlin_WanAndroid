package com.memo.core

import android.content.Intent
import com.base.base.ui.BaseVmActivity
import com.memo.core.databinding.ActivityMainBinding

class MainActivity : BaseVmActivity<MainViewModel, ActivityMainBinding>() {

    override fun bindLayoutRes(): Int = R.layout.activity_main

    override fun initData() {
    }

    override fun initView() {
        supportFragmentManager.beginTransaction()
            .add(R.id.mContainer, MainFragment())
            .commit()
    }

    override fun initListener() {
    }

    override fun start() {
        mLoadService?.showSuccess()
    }

}