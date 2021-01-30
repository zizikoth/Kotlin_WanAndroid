package com.module.core.tool.lifecycle


import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.concurrent.TimeUnit


/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-12-03 3:03 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class FlowCountDownTimer internal constructor(
	private val start: Int,
	private val end: Int,
	private val timeUnit: TimeUnit,
	private val interval: Long
) : IFlowCountDownTimer {
	companion object {
		private const val TAG = "FlowCountDownTimer"
		fun getTimer(time: Int) = Builder().from(time).to(0).interval(1000L).withUnit(TimeUnit.SECONDS).create()
	}

	private var mStartCallback: StartCallback? = null
	private var mCountDownCallback: CountDownCallback? = null
	private var mCompleteCallback: CompleteCallback? = null
	private var job: Job? = null
	private var mRemainTimeInUnit = 0
	private var mJobState: JobState = JobState.INITIAL
	override fun watchStart(startCallback: StartCallback): FlowCountDownTimer {
		mStartCallback = startCallback
		return this
	}

	override fun watchCountDown(countDownCallback: CountDownCallback): FlowCountDownTimer {
		mCountDownCallback = countDownCallback
		return this
	}

	override fun watchComplete(completeCallback: CompleteCallback): FlowCountDownTimer {
		mCompleteCallback = completeCallback
		return this
	}

	override fun getJobState(): JobState = mJobState

	override fun cancel() {
		job?.let { if (!it.isCancelled) it.cancel() }
	}

	override fun pause() {
		if (mutableListOf(
				JobState.INITIAL,
				JobState.RESUME,
				JobState.DISPATCH
			).contains(mJobState)) {
			mJobState = JobState.PAUSE
			cancel()
		}
	}

	override fun resume() {
		if (mJobState == JobState.PAUSE) {
			startInternal(currentTimeInUnit = mRemainTimeInUnit)
		}
	}

	override fun start(): FlowCountDownTimer {
		return startInternal(currentTimeInUnit = start)
	}

	@ExperimentalCoroutinesApi
	private fun startInternal(
		dispatcher: CoroutineDispatcher = Dispatchers.Main,
		currentTimeInUnit: Int = start
	): FlowCountDownTimer {
		val factor = when (timeUnit) {
			TimeUnit.SECONDS -> 1
			TimeUnit.MINUTES -> 60
			TimeUnit.HOURS -> 60 * 60
			else -> 1
		}
		if (mJobState == JobState.COMPLETE) {
			Log.e(TAG, "already completed")
			return this
		}
		job = CoroutineScope(dispatcher).launch(start = CoroutineStart.LAZY) {
			(currentTimeInUnit * factor downTo end * factor)
				.asFlow()
				.map { it - 1 }
				.onEach { delay(interval) }
				.flowOn(Dispatchers.IO)
				.onStart {
					mJobState = JobState.RESUME
					if (currentTimeInUnit == start) {
						mRemainTimeInUnit = start
						mStartCallback?.invoke()
					}
				}
				.onCompletion {
					cancel()
				}.collect {
					if (it == end - 1) {
						mJobState = JobState.COMPLETE
						mCompleteCallback?.invoke()
						return@collect
					}
					mJobState = JobState.DISPATCH
					mCountDownCallback?.invoke(it)
					mRemainTimeInUnit = it
				}
		}
		job?.start()
		return this
	}

	class Builder {
		private var mTimeUnit = TimeUnit.SECONDS
		private var mInterval = 1000L
		private var mStart = 0
		private var mEnd = 0
		fun from(start: Int): Builder {
			mStart = start
			return this
		}

		fun to(end: Int): Builder {
			mEnd = end
			return this
		}

		fun withUnit(timeUnit: TimeUnit): Builder {
			mTimeUnit = timeUnit
			return this
		}

		fun interval(interval: Long): Builder {
			mInterval = interval
			return this
		}

		fun create(): FlowCountDownTimer {
			if (mStart < mEnd - 1) {
				throw IllegalArgumentException("start count must be bigger than end count")
			}
			if (mInterval < 0L) {
				throw IllegalArgumentException("invalid interval ")
			}
			return FlowCountDownTimer(mStart, mEnd, mTimeUnit, mInterval)
		}
	}


}

interface IFlowCountDownTimer {

	fun watchStart(startCallback: StartCallback): FlowCountDownTimer
	fun watchCountDown(countDownCallback: CountDownCallback): FlowCountDownTimer
	fun watchComplete(completeCallback: CompleteCallback): FlowCountDownTimer
	fun getJobState(): JobState

	fun cancel()
	fun pause()
	fun resume()
	fun start(): FlowCountDownTimer
}

typealias StartCallback = () -> Unit
typealias CountDownCallback = (currentTimeInUnit: Int) -> Unit
typealias CompleteCallback = () -> Unit

enum class JobState {
	INITIAL,
	DISPATCH,
	COMPLETE,
	PAUSE,
	RESUME
}