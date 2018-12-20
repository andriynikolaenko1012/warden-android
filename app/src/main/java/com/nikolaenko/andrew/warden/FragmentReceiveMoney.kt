package com.nikolaenko.andrew.warden


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_receive_money.*


class FragmentReceiveMoney : BaseFragment() {

    override fun onStart() {
        super.onStart()

        changeFontInTextView(webWallet)
        changeFontInTextView(cardWallet)
        changeFontInTextView(toolbarTitle)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_receive_money, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        webWallet.setOnClickListener {
            replaceFragment(FragmentReceiveMoneySecondStep(), true)
        }

        cardWallet.setOnClickListener {
            replaceFragment(FragmentCard(), true)
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mainActivity.setSupportActionBar(toolbar)
        mainActivity.supportActionBar!!.title = null

        leftButton.setOnClickListener {
            goBack()
        }
    }
}
