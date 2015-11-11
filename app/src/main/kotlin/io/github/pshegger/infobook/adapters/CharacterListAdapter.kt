package io.github.pshegger.infobook.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import io.github.pshegger.infobook.R
import io.github.pshegger.infobook.model.CharacterData
import io.github.pshegger.infobook.utils.IconLoadHelper
import io.github.pshegger.infobook.utils.OnRecyclerViewItemClickListener

/**
 * Created by pshegger on 2015. 11. 10..
 */
class CharacterListAdapter(val characters: List<CharacterData>, val listener: OnRecyclerViewItemClickListener?) : RecyclerView.Adapter<CharacterViewHolder>() {
    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(this[position], position)
    }

    override fun getItemCount(): Int = characters.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_character_row, parent, false)

        return CharacterViewHolder(v, listener)
    }

    operator fun get(position: Int) = characters[position]
}

class CharacterViewHolder(val root: View, val listener: OnRecyclerViewItemClickListener?) : RecyclerView.ViewHolder(root) {
    val ivLogo = root.findViewById(R.id.profession_logo) as ImageView
    val tvName = root.findViewById(R.id.character_name) as TextView
    val tvProfession = root.findViewById(R.id.character_profession) as TextView
    val tvLevel = root.findViewById(R.id.character_level) as TextView
    var mPosition = -1

    init {
        listener?.let {
            root.setOnClickListener { listener.onItemClick(mPosition) }
        }
    }

    fun bind(ch: CharacterData, position: Int) {
        mPosition = position

        tvName.text = ch.name
        tvProfession.text = ch.profession().name
        tvLevel.text = "" + ch.level

        IconLoadHelper.loadIcon(
                IconLoadHelper.IconType.File,
                ch.profession().iconId(true),
                ivLogo
        )
    }
}