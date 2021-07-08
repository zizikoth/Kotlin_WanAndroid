package com.base.base.ui.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.rxLifeScope
import com.base.base.api.ApiCode
import com.base.base.api.ExceptionHandler
import com.base.base.entity.local.UiStatus
import com.base.base.manager.RouterManager
import com.base.base.utils.toast
import kotlinx.coroutines.CoroutineScope

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-12-18 10:49 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
abstract class BaseViewModel : ViewModel() {

    /*** 是否是第一次加载 ***/
    private var isFirstLoad = true

    /*** 请求加载框 ***/
    val loadingEvent: MutableLiveData<Boolean> = MutableLiveData()

    /*** 页面展示状态 ***/
    val statusEvent: MutableLiveData<UiStatus> = MutableLiveData()

    fun <T> request(
        request: (suspend (scope: CoroutineScope) -> T),
        onSuccess: ((data: T) -> Unit),
        onError: ((code: Int) -> Unit)? = null,
        showLoading: Boolean = false) {
        rxLifeScope.launch(
            block = {
                onSuccess(request.invoke(this))
                isFirstLoad = false
                statusEvent.postValue(UiStatus(isFirstLoad, ApiCode.Success))
            },
            onError = {
                val error = ExceptionHandler.handleException(it)
                toast(error.message)
                statusEvent.postValue(UiStatus(isFirstLoad, error.code))
                onError?.invoke(error.code)
                if (error.code == ApiCode.UnLogin) {
                    RouterManager.startLogin()
                }
            },
            onStart = { if (showLoading) loadingEvent.postValue(true) },
            onFinally = { loadingEvent.postValue(false) }
        )
    }

    fun requestNoCheck(request: suspend (scope: CoroutineScope) -> Unit) {
        var list = arrayListOf<Int>()
        list.orEmpty()
        rxLifeScope.launch { request.invoke(this) }
    }

    fun <T> requestNoStatus(
        request: suspend (scope: CoroutineScope) -> T,
        onSuccess: ((data: T) -> Unit),
        showLoading: Boolean = false) {
        rxLifeScope.launch(
            block = { onSuccess(request.invoke(this)) },
            onStart = { if (showLoading) loadingEvent.postValue(true) },
            onFinally = { loadingEvent.postValue(false) }
        );

    }

}