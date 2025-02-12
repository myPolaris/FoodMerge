package com.food.carnival.merge.ui

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import com.cocos.lib.CocosActivity
import com.cocos.service.SDKWrapper

class Food : CocosActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SDKWrapper.shared().init(this)
    }

    override fun onResume() {
        super.onResume()
        SDKWrapper.shared().onResume()
    }

    override fun onPause() {
        super.onPause()
        SDKWrapper.shared().onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            if (!isTaskRoot) {
                return
            }
            moveTaskToBack(false)
            SDKWrapper.shared().onDestroy()
        } catch (throwable: Throwable) {
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        SDKWrapper.shared().onActivityResult(requestCode, resultCode, data)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        SDKWrapper.shared().onNewIntent(intent)
    }

    override fun onRestart() {
        super.onRestart()
        SDKWrapper.shared().onRestart()
    }

    override fun onStop() {
        super.onStop()
        SDKWrapper.shared().onStop()
    }

    @Override
    override fun onBackPressed() {
        SDKWrapper.shared().onBackPressed()
        moveTaskToBack(false)
        super.onBackPressed();
    }

    private var backTime: Long = 0

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        if (event.keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - backTime > 1500) {
                backTime = System.currentTimeMillis()
            } else {
                SDKWrapper.shared().onBackPressed()
                moveTaskToBack(false)
            }
        }
        return super.dispatchKeyEvent(event)
    }

    override fun onStart() {
        SDKWrapper.shared().onStart()
        super.onStart()
    }

    override fun onLowMemory() {
        SDKWrapper.shared().onLowMemory()
        super.onLowMemory()
    }

}
