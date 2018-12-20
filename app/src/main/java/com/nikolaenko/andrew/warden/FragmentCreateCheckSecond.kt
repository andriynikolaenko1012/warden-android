package com.nikolaenko.andrew.warden


import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.fragment_create_chech_second.*


class FragmentCreateCheckSecond : BaseFragment() {

    private var amount: String = "0.00"

    override fun onStart() {
        super.onStart()

        changeFontInTextView(textOk)
        changeFontInTextView(toolbarTitle)
        changeFontInTextView(text)
        changeFontInTextView(text2)
        changeFontInTextView(text4)

        changeFontInTextViewBold(text3)
        changeFontInTextViewBold(text5)

        amount = arguments!!.getString(ARG_AMOUNT)

//        val bl = Prefs.getDouble("balance_to_transfer", 0.0)
        Log.e("sfa", "amount")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_chech_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textOk.setOnClickListener {

            if (amount != "0.00"){
                Prefs.putString("balance_to_transfer_usd", amount)
                mainActivity.hardReplaceFragment(FragmentMain())
            }
        }

        text3.paintFlags = text3.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        text5.paintFlags = text5.paintFlags or Paint.UNDERLINE_TEXT_FLAG

//        text3.setOnClickListener {
//            replaceFragment(FragmentSendMoneyBluetoothSecond(), true)
//        }
//
//        text5.setOnClickListener {
//            replaceFragment(FragmentSendMoneyBluetoothSecond(), true)
//        }

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

        fun newInstance(amount: String): FragmentCreateCheckSecond {

            val args = Bundle()
            args.putString(ARG_AMOUNT, amount)

            val fragment = FragmentCreateCheckSecond()
            fragment.arguments = args
            return fragment
        }
    }
}
