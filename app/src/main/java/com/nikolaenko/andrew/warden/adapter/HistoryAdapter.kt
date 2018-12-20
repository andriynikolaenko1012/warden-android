package com.nikolaenko.andrew.warden.adapter

import android.graphics.Paint
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.nikolaenko.andrew.warden.R
import com.nikolaenko.andrew.warden.model.TransferModel
import org.jetbrains.anko.find

class HistoryAdapter (private val callback: ((title: String, amount: String, comment: String, currency: String, date: String, time: String, position: Int) -> Unit)? = null) : RecyclerView.Adapter<HistoryAdapter.MyViewHolder>() {

    private var list: MutableList<TransferModel> = ArrayList()

    fun setList(list: MutableList<TransferModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var text: TextView = view.find(R.id.title)
        var item = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_for_transfer, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list[position]

        val type = Typeface.createFromAsset(holder.itemView.context.assets, "font/notosansdisplay.ttf")
        holder.text.typeface = type

        val title = item.title.toString()
        val amount = item.amount.toString()
        val comment = item.comment.toString()
        val currency = item.currency.toString()
        val date = item.date.toString()
        val time = item.time.toString()

        holder.text.text = "$amount($date)"

        holder.itemView.setOnClickListener {
            callback?.invoke(title, amount, comment, currency, date, time, position)
        }

    }

    override fun getItemCount(): Int = list.size
}
