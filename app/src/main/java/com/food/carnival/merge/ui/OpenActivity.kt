package com.food.carnival.merge.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.food.carnival.merge.BaseActivity
import com.food.carnival.merge.R
import com.food.carnival.merge.databinding.UiLayoutOpenBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class OpenActivity : BaseActivity<UiLayoutOpenBinding>() {
    private var mCountDownJob: Job? = null
    override fun getViewBinding(): UiLayoutOpenBinding {
        return UiLayoutOpenBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ui_layout_open)

        mCountDownJob?.cancel()
        mCountDownJob = countDownCoroutine(5, 1, lifecycleScope, onTick = {
            for (i in 1..100) {
                binding.pb.progress = i
            }
        }, onStart = {
            binding.pb.visibility=View.VISIBLE
        }, onEnd = {
            mCountDownJob?.cancel()
            val intent = Intent(this, Food::class.java)
            startActivity(intent)
        })

    }


    private fun countDownCoroutine(
        duration: Int,
        interval: Int = 1,
        scope: CoroutineScope,
        onTick: (Int) -> Unit,
        onStart: ((Int) -> Unit)? = null,
        onEnd: (() -> Unit)? = null,
    ): Job {
        if (duration <= 0 || interval <= 0) {
            throw IllegalArgumentException("duration or interval can not less than zero")
        }
        return flow {
            for (i in duration downTo 0 step interval) {
                delay((interval * 1000).toLong())
                emit(i)
            }
        }.onEach { onTick.invoke(it) }.onStart { onStart?.invoke(duration) }
            .onCompletion {
                if (it == null) {
                    onEnd?.invoke()
                }
            }
            .flowOn(Dispatchers.Main).launchIn(scope)
    }
}