package com.codingwithmitch.rxjavakotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

class MainActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Observable.just("Hello Reactive World!").subscribe { s ->
            Log.d(TAG, s)
        }.also {
            compositeDisposable.add(it)
        }

        Observable.just("Apple", "Orange", "Banana")
            .subscribe(
                /* onNext = */ { value: String? -> Log.d(TAG, "Received: $value")},
                /* onError = */ { error: Throwable? -> Log.d(TAG, "Error: $error")},
                /* onComplete = */ { Log.d(TAG, "Completed!")}
            ).also {
                compositeDisposable.add(it)
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    companion object {
        const val TAG = "MainActivityRxJavaLog"
    }
}