package io.github.pshegger.infobook.fragments.account

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import io.github.pshegger.infobook.R
import io.github.pshegger.infobook.adapters.CharacterListAdapter
import io.github.pshegger.infobook.datasource.CachedGWDataSource
import io.github.pshegger.infobook.datasource.LoadResult
import io.github.pshegger.infobook.fragments.BaseInfoBookFragment
import io.github.pshegger.infobook.utils.OnRecyclerViewItemClickListener

/**
 * Created by pshegger on 2015. 11. 10..
 */
class CharactersFragment : BaseInfoBookFragment() {
    lateinit var characterList: RecyclerView
    lateinit var refreshLayout: SwipeRefreshLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_character_list, container, false)

        (root.findViewById(R.id.characters_refresh) as SwipeRefreshLayout).apply {
            refreshLayout = this
            setOnRefreshListener { refresh() }
        }

        (root.findViewById(R.id.characters_list) as RecyclerView).apply {
            characterList = this
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
        }

        return root
    }

    override fun onResume() {
        super.onResume()

        refresh()
    }

    private fun refresh() {
        refreshLayout.isRefreshing = true

        CachedGWDataSource.getAllCharacters(false).subscribe { result ->
            if (result.loadResult != LoadResult.NetworkError) {
                result.result?.let { characters ->
                    val sorted = characters.sorted()
                    characterList.adapter = CharacterListAdapter(sorted, object : OnRecyclerViewItemClickListener {
                        override fun onItemClick(position: Int) {
                            Toast.makeText(activity, sorted[position].name, Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            }

            refreshLayout.isRefreshing = false
        }
    }

    override fun name(): String = "Characters"
}