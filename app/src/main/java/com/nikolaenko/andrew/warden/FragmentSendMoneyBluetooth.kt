package com.nikolaenko.andrew.warden


import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nikolaenko.andrew.warden.adapter.MoneyRequestAdapter
import com.nikolaenko.andrew.warden.model.MoneyRequestModel
import kotlinx.android.synthetic.main.fragment_send_money_bluetooth.*




class FragmentSendMoneyBluetooth : BaseFragment() {

    private lateinit var adapter: MoneyRequestAdapter


    override fun onStart() {
        super.onStart()

        changeFontInTextView(textCancel)
        changeFontInTextView(toolbarTitle)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_send_money_bluetooth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textCancel.setOnClickListener {
            goBack()
        }

        rvMoney.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvMoney.itemAnimator = DefaultItemAnimator()

        adapter = MoneyRequestAdapter {
            text ->
            replaceFragment(FragmentSendMoneyBluetoothSecond.newInstance(text), true)

        }
        rvMoney.adapter = adapter

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mainActivity.setSupportActionBar(toolbar)
        mainActivity.supportActionBar!!.title = null

        leftButton.setOnClickListener {
            goBack()
        }

        showProgress(true)
        val mHandler = Handler()
        mHandler.postDelayed({

            showProgress(false)

            val lst = ArrayList<MoneyRequestModel>()
            lst.clear()
            lst.add(0, MoneyRequestModel(1, getString(R.string.text_money_requests1)))
            lst.add(1, MoneyRequestModel(2, getString(R.string.text_money_requests2)))
            lst.add(2, MoneyRequestModel(3, getString(R.string.text_money_requests3)))
            lst.add(3, MoneyRequestModel(4, getString(R.string.text_money_requests4)))
            lst.add(4, MoneyRequestModel(5, getString(R.string.text_money_requests5)))

            adapter.setList(lst)

        }, 3000L)


    }
}
