package com.nikolaenko.andrew.warden


import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.fragment_send_money_bluetooth_third.*


class FragmentSendMoneyBluetoothThird : BaseFragment() {

    private var amount: String = "0.00"
    private var myBalanse: Double = 0.00

    override fun onStart() {
        super.onStart()

        changeFontInTextView(text3)
        changeFontInTextView(toolbarTitle)
        changeFontInTextView(textOk)

        amount = arguments!!.getString(ARG_AMOUNT)
        myBalanse = Prefs.getDouble("my_eur_balance_prefs", 0.0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_send_money_bluetooth_third, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textOk.setOnClickListener {
            mainActivity.hardReplaceFragment(FragmentMain())
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mainActivity.setSupportActionBar(toolbar)
        mainActivity.supportActionBar!!.title = null

        leftButton.setOnClickListener {
            goBack()
        }

        if (amount != null){

            showProgress(true)
            val mHandler = Handler()
            mHandler.postDelayed({

                showProgress(false)

                textBalance.text = (myBalanse - amount.toDouble()).toString() + " EUR"
                Prefs.putDouble("my_eur_balance_prefs", (myBalanse - amount.toDouble()))

            }, 3000L)
        }
    }

    companion object {

        private val ARG_AMOUNT = "arg_amount_"

        fun newInstance(amount: String): FragmentSendMoneyBluetoothThird {

            val args = Bundle()
            args.putString(ARG_AMOUNT, amount)

            val fragment = FragmentSendMoneyBluetoothThird()
            fragment.arguments = args
            return fragment
        }
    }
}
