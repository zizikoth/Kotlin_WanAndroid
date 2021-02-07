package com.frame.core.livedata;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-07 2:44 PM
 * @email zhou_android@163.com
 * <p>
 * Talk is cheap, Show me the code.
 */
public class UnPeekLiveData<T> extends ProtectedUnPeekLiveData<T> {

    @Override
    public void setValue(T value) {
        super.setValue(value);
    }

    @Override
    public void postValue(T value) {
        super.postValue(value);
    }

    /**
     * TODO：Tip：请不要在 UnPeekLiveData 中使用 observe 方法。
     * 取而代之的是在 Activity 和 fragment 中分别使用 observeInActivity 和 observeInFragment 来观察。
     * <p>
     * 2020.10.15 背景缘由：
     * UnPeekLiveData 通过 ViewModelStore 来在各种场景下（如旋屏后）确定订阅者的唯一性和消息的消费状况，
     * 因而在 Activity 和 fragment 对 LifecycleOwner 的使用存在差异的现状下，
     * 我们采取注入局部变量的方式，来获取 store 和 owner。
     *
     * @param owner
     * @param observer
     */
    @Override
    @Deprecated
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
        throw new IllegalArgumentException("请不要在 UnPeekLiveData 中使用 observe 方法。" +
                "取而代之的是在 Activity 和 Fragment 中分别使用 observeInActivity 和 observeInFragment 来观察。\n\n" +
                "Taking into account the normal permission of preventing backflow logic, " +
                " do not use observeForever to communicate between pages." +
                "Instead, you can use ObserveInActivity and ObserveInFragment methods " +
                "to observe in Activity and Fragment respectively.");
    }

    /**
     * TODO：Tip：请不要在 UnPeekLiveData 中使用 observeForever 方法。
     * <p>
     * 2020.8.1 背景缘由：
     * UnPeekLiveData 主要用于表现层的 页面转场 和 页面间通信 场景下的非粘性消息分发，
     * 出于生命周期安全等因素的考虑，不建议使用 observeForever 方法，
     * <p>
     * 对于数据层的工作，如有需要，可结合实际场景使用 RxJava 或 kotlin flow。
     *
     * @param observer
     */
    @Override
    @Deprecated
    public void observeForever(@NonNull Observer<? super T> observer) {
        throw new IllegalArgumentException("出于生命周期安全的考虑，请不要在 UnPeekLiveData 中使用 observeForever 方法。\n\n" +
                "Considering avoid lifecycle security issues," +
                " do not use observeForever for communication between pages.");
    }


    public static class Builder<T> {

        /**
         * 是否允许传入 null value
         */
        private boolean isAllowNullValue;

        public Builder<T> setAllowNullValue(boolean allowNullValue) {
            this.isAllowNullValue = allowNullValue;
            return this;
        }

        public UnPeekLiveData<T> create() {
            UnPeekLiveData<T> liveData = new UnPeekLiveData<>();
            liveData.isAllowNullValue = this.isAllowNullValue;
            return liveData;
        }
    }
}
