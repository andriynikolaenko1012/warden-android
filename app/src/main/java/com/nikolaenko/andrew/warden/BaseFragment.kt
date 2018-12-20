package com.nikolaenko.andrew.warden

import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.EditText
import android.widget.TextView


abstract class BaseFragment : Fragment() {

    protected lateinit var mainActivity: MainActivity

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mainActivity = getActivity() as MainActivity

    }

    fun showProgress(show: Boolean) {
        mainActivity.progressView!!.visibility = if (show) View.VISIBLE else View.GONE
    }

    fun replaceFragment(fragment: Fragment, addToBackStack: Boolean = true, tag: String? = null) {
        var ft = mainActivity.supportFragmentManager.beginTransaction()
//        ft.setCustomAnimations(R.anim.slide_out_right, R.anim.slide_in_left)

        if(tag == null)
            ft.replace(R.id.placeholder, fragment)
        else
            ft.replace(R.id.placeholder, fragment, tag)

        if (addToBackStack) {
            ft.addToBackStack(null)
        }

        ft.commit()

    }

    fun goBack(){

        if (mainActivity.supportFragmentManager.backStackEntryCount > 0) {
            mainActivity.supportFragmentManager.popBackStackImmediate()
        }

    }

    fun changeFontInTextView(view: TextView){
        val type = Typeface.createFromAsset(context?.assets, "font/notosansdisplay.ttf")
        view.typeface = type
    }

    fun changeFontInTextViewBold(view: TextView){
        val type = Typeface.createFromAsset(context?.assets, "font/notosansdisplay_bold.ttf")
        view.typeface = type
    }

    fun changeFontInEditTextView(view: EditText){
        val type = Typeface.createFromAsset(context?.assets, "font/notosansdisplay.ttf")
        view.typeface = type
    }
}
