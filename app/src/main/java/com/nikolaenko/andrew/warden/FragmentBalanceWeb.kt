package com.nikolaenko.andrew.warden


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.fragment_web_balance.*


class FragmentBalanceWeb : BaseFragment() {

    override fun onStart() {
        super.onStart()


        changeFontInTextView(toolbarTitle)

        changeFontInTextView(text)
        changeFontInTextView(text1)
        changeFontInTextView(text2)
        changeFontInTextView(text3)
        changeFontInTextView(textCard)

        text1.text = Prefs.getDouble("my_usd_balance_prefs", 0.0).toString() + " USD"
        text2.text = Prefs.getDouble("my_rub_balance_prefs", 0.0).toString() + " RUB"
        text3.text = Prefs.getDouble("my_eur_balance_prefs", 0.0).toString() + " EUR"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_web_balance, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textCard.setOnClickListener {
            replaceFragment(FragmentCard(),true)
        }

        text2.setOnClickListener {
            replaceFragment(FragmentUSDHistory.newInstance("RUB History"), true)
        }

        text3.setOnClickListener {
            replaceFragment(FragmentUSDHistory.newInstance("EUR History"), true)
        }

        text1.setOnClickListener {
            replaceFragment(FragmentUSDHistory.newInstance("USD History"), true)
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
