package com.nikolaenko.andrew.warden


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.fragment_redeem_check_second.*


class FragmentRedeemCheckSecond : BaseFragment() {

    private var amount: String = "0.00"
    private var myBalanse: Double = 0.00

    override fun onStart() {
        super.onStart()

        changeFontInTextView(text2)
        changeFontInTextView(toolbarTitle)
        changeFontInTextView(textBalance)
        changeFontInTextView(text3)
        changeFontInTextView(textOk)

        amount = arguments!!.getString(ARG_AMOUNT)
        Prefs.remove("balance_to_transfer_usd")
        myBalanse = Prefs.getDouble("my_usd_balance_prefs", 0.0)

        if (amount != null){
            textBalance.text = (amount.toDouble() + myBalanse).toString() + " USD"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_redeem_check_second, container, false)
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
    }

    companion object {

        private val ARG_AMOUNT = "arg_amount"

        fun newInstance(amount: String): FragmentRedeemCheckSecond {

            val args = Bundle()
            args.putString(ARG_AMOUNT, amount)

            val fragment = FragmentRedeemCheckSecond()
            fragment.arguments = args
            return fragment
        }
    }
}
