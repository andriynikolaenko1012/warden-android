package com.nikolaenko.andrew.warden


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_deleting.*


class FragmentDeletings : BaseFragment() {

    private var position: Int? = null

    override fun onStart() {
        super.onStart()

        changeFontInTextView(textOk)
        changeFontInTextView(toolbarTitle)

        changeFontInTextView(text)
        changeFontInTextView(text1)

        position = arguments?.getInt(ARG_FOR_POSITION)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_deleting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textOk.setOnClickListener {
            replaceFragment(FragmentUSDHistory(), true)
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

        private val ARG_FOR_POSITION = "arg_for_position"

        fun newInstance(position: Int): FragmentDeletings {

            val args = Bundle()
            args.putInt(ARG_FOR_POSITION, position)

            val fragment = FragmentDeletings()
            fragment.arguments = args
            return fragment
        }
    }
}
