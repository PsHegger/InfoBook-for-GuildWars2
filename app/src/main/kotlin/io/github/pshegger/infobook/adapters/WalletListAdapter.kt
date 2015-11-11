package io.github.pshegger.infobook.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import io.github.pshegger.infobook.R
import io.github.pshegger.infobook.model.CurrencyData
import io.github.pshegger.infobook.utils.OnRecyclerViewItemClickListener

/**
 * Created by pshegger on 2015. 11. 11..
 */
class WalletListAdapter(val currencies: List<CurrencyData>, val listener: OnRecyclerViewItemClickListener?) : RecyclerView.Adapter<CurrencyViewHolder>() {
    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    override fun getItemCount(): Int = currencies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder? {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_wallet_row, parent, false)

        return CurrencyViewHolder(v, listener)
    }

    fun getItem(position: Int) = currencies[position]

}

class CurrencyViewHolder(val root: View, val listener: OnRecyclerViewItemClickListener?) : RecyclerView.ViewHolder(root) {
    val ivIcon = root.findViewById(R.id.currencyLogo) as ImageView
    val tvName = root.findViewById(R.id.currencyName) as TextView
    val tvAmount = root.findViewById(R.id.currencyAmount) as TextView

    var mPosition = 0

    init {
        listener?.let {
            root.setOnClickListener {
                listener.onItemClick(mPosition)
            }
        }
    }

    fun bind(data: CurrencyData, position: Int) {
        mPosition = position

        tvName.text = data.name
        tvAmount.text = "${data.amount}"

        Picasso.with(root.context)
                .load(data.iconURL)
                .into(ivIcon)
    }
}