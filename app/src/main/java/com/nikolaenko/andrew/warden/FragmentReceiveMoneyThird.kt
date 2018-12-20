package com.nikolaenko.andrew.warden


import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.fragment_receive_money_third.*


class FragmentReceiveMoneyThird : BaseFragment() {

    private var amount: String = "0.00"
    private var myBalanse: Double = 0.00

    var handler: Handler? = null
    var handler2: Handler? = null

    override fun onStart() {
        super.onStart()

        changeFontInTextView(textCancel)
        changeFontInTextView(toolbarTitle)

        changeFontInTextView(text)
        changeFontInTextViewBold(text1)
        changeFontInTextView(text2)

        amount = arguments!!.getString(ARG_AMOUNT)
        myBalanse = Prefs.getDouble("my_eur_balance_prefs", 0.0)

        handler = Handler()
        handler2 = Handler()
        handler?.postDelayed({

            showProgress(true)
            handler2?.postDelayed({

                showProgress(false)
                Prefs.putDouble("my_eur_balance_prefs", (myBalanse + amount.toDouble()))
                replaceFragment(FragmentReceiveMoneySuccess(), true)

            }, 3000L)

        }, 5000L)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_receive_money_third, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textCancel.setOnClickListener {
            replaceFragment(FragmentReceiveMoney(), true)
        }
    }

    override fun onStop() {
        super.onStop()
        handler?.removeCallbacksAndMessages(null)
        handler2?.removeCallbacksAndMessages(null)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler?.removeCallbacksAndMessages(null)
        handler2?.removeCallbacksAndMessages(null)
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

        private val ARG_AMOUNT = "arg_amount__"

        fun newInstance(amount: String): FragmentReceiveMoneyThird {

            val args = Bundle()
            args.putString(ARG_AMOUNT, amount)

            val fragment = FragmentReceiveMoneyThird()
            fragment.arguments = args
            return fragment
        }
    }
}
