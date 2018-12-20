package com.nikolaenko.andrew.warden


import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.fragment_send_money_bluetooth_second.*
import org.jetbrains.anko.support.v4.toast


class FragmentSendMoneyBluetoothSecond : BaseFragment() {

    private var text: String? = null
    var myBalance: String = "0.00"
    var balanceToTransfer: String = "0.00"

    override fun onStart() {
        super.onStart()

        changeFontInTextView(text1)
        changeFontInTextView(text2)

        changeFontInTextView(text3)
        changeFontInTextView(toolbarTitle)
        changeFontInTextView(textOk)
        changeFontInTextView(textCancel)
        changeFontInTextView(textHz)

        changeFontInEditTextView(text2)

        myBalance = Prefs.getDouble("my_eur_balance_prefs", 0.0).toString()

        text = arguments?.getString("ARG_TEXT")
        textBalance.text = "$myBalance EUR"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_send_money_bluetooth_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textCancel.setOnClickListener {
            goBack()
        }

        textOk.setOnClickListener {

            if (!text2.text.isNullOrBlank()){
                if (balanceToTransfer.toDouble() > myBalance.toDouble()){
                    toast("Enter less amount")
                    return@setOnClickListener
                } else {
                    replaceFragment(FragmentSendMoneyBluetoothThird.newInstance(balanceToTransfer), true)
                }
            } else {
                toast("Enter amount")
                return@setOnClickListener
            }

        }

        text2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

                if (!p0.isNullOrBlank()){
                    balanceToTransfer = p0.toString()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!p0.isNullOrBlank()){
                    textHz.visibility = View.VISIBLE
                } else {
                    textHz.visibility = View.GONE
                }

            }
        })
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

        private val ARG_TEXT = "arg_text"

        fun newInstance(text: String): FragmentSendMoneyBluetoothSecond {

            val args = Bundle()
            args.putString(ARG_TEXT, text)

            val fragment = FragmentSendMoneyBluetoothSecond()
            fragment.arguments = args
            return fragment
        }
    }
}
