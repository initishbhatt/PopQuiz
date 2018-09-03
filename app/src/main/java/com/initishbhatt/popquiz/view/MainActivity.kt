package com.initishbhatt.popquiz.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.initishbhatt.popquiz.R
import com.initishbhatt.popquiz.databinding.ActivityMainBinding
import com.initishbhatt.popquiz.presentation.main.MainContract
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity(), MainContract.View {
    @Inject
    lateinit var presenter: MainContract.Presenter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        openFragment()
    }

    override fun onStart() {
        super.onStart()
        presenter.setView(this)
    }

    private fun openFragment() {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.fragment, ProfileFragment())
        transaction.disallowAddToBackStack()
        transaction.commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.removeView()
    }
}
