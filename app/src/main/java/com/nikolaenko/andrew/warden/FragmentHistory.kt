package com.nikolaenko.andrew.warden


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.fragment_history.*


class FragmentHistory : BaseFragment() {

    private var isForWeb: Boolean? = false

    override fun onStart() {
        super.onStart()

        isForWeb = arguments?.getBoolean(ARG_IS_FOR_WEB)

        if (isForWeb!!){
            toolbarTitle.text = getString(R.string.text_web)
            textWeb.visibility = View.GONE
        } else {
            toolbarTitle.text = getString(R.string.text_history)
            textWeb.visibility = View.VISIBLE
        }

        changeFontInTextView(toolbarTitle)

        changeFontInTextView(text)
        changeFontInTextView(text1)
        changeFontInTextView(text2)
        changeFontInTextView(text3)
        changeFontInTextView(textWeb)
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
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textWeb.setOnClickListener {
            replaceFragment(FragmentHistory.newInstance(true), true)
        }

        textCard.setOnClickListener {
            replaceFragment(FragmentCard(),true)
        }

        text3.setOnClickListener {
            replaceFragment(FragmentUSDHistory.newInstance("EUR History"), true)
        }

        text1.setOnClickListener {
            replaceFragment(FragmentUSDHistory.newInstance("USD History"), true)
        }

        text2.setOnClickListener {
            replaceFragment(FragmentUSDHistory.newInstance("RUB History"), true)
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

    companion object {

        private val ARG_IS_FOR_WEB = "arg_for_web"

        fun newInstance(isForWeb: Boolean): FragmentHistory {

            val args = Bundle()
            args.putBoolean(ARG_IS_FOR_WEB, isForWeb)

            val fragment = FragmentHistory()
            fragment.arguments = args
            return fragment
        }
    }
}
