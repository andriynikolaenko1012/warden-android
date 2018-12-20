package com.nikolaenko.andrew.warden.adapter

import android.graphics.Paint
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.nikolaenko.andrew.warden.R
import com.nikolaenko.andrew.warden.model.MoneyRequestModel
import org.jetbrains.anko.find

class MoneyRequestAdapter (private val callback: ((text: String) -> Unit)? = null) : RecyclerView.Adapter<MoneyRequestAdapter.MyViewHolder>() {

    private var list: MutableList<MoneyRequestModel> = ArrayList()

    fun setList(list: MutableList<MoneyRequestModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var text: TextView = view.find(R.id.title)
        var item = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_for_money, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list[position]

        val type = Typeface.createFromAsset(holder.itemView.context.assets, "font/notosansdisplay_bold.ttf")
        holder.text.text = item.phrasa
        holder.text.typeface = type

        holder.text.paintFlags = holder.text.paintFlags or Paint.UNDERLINE_TEXT_FLAG

        holder.itemView.setOnClickListener {
            callback?.invoke(item.phrasa.toString())
        }

    }

    override fun getItemCount(): Int = list.size
}
