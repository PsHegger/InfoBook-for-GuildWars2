package io.github.pshegger.infobook.fragments.account

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.pshegger.infobook.R
import io.github.pshegger.infobook.adapters.WalletListAdapter
import io.github.pshegger.infobook.fragments.BaseInfoBookFragment
import io.github.pshegger.infobook.model.CurrencyData
import io.github.pshegger.infobook.model.WalletResponse
import retrofit.Callback
import retrofit.Response
import retrofit.Retrofit

/**
 * Created by pshegger on 2015. 11. 10..
 */
class WalletFragment : BaseInfoBookFragment() {
    val availableCurrencies: MutableList<CurrencyData> = arrayListOf()

    lateinit var currencyRefreshLayout: SwipeRefreshLayout
    lateinit var currencyList: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_wallet, container, false)

        (root.findViewById(R.id.currencyRefreshLayout) as SwipeRefreshLayout).apply {
            currencyRefreshLayout = this
            setOnRefreshListener { updateWallet() }
        }

        (root.findViewById(R.id.currencyList) as RecyclerView).apply {
            currencyList = this
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
        }

        return root
    }

    override fun onResume() {
        super.onResume()

        if (availableCurrencies.size == 0) updateCurrencies()
        else updateWallet()
    }

    private fun updateCurrencies() {
        currencyRefreshLayout.isRefreshing = true

        val service = apiService() ?: return

        service.getAvailableCurrencies().enqueue(object : Callback<MutableList<CurrencyData>> {
            override fun onFailure(t: Throwable?) {
            }

            override fun onResponse(response: Response<MutableList<CurrencyData>>?, retrofit: Retrofit?) {
                response?.body()?.let {
                    availableCurrencies.clear()
                    availableCurrencies.addAll(it)

                    updateWallet()
                }
            }

        })
    }

    private fun updateWallet() {
        currencyRefreshLayout.isRefreshing = true

        val service = apiService() ?: return

        service.getWalletContent().enqueue(object : Callback<MutableList<WalletResponse>> {
            override fun onFailure(t: Throwable?) {
            }

            override fun onResponse(response: Response<MutableList<WalletResponse>>?, retrofit: Retrofit?) {
                response?.body()?.let { response ->
                    val walletContent = availableCurrencies.map { currency ->
                        val content = response.firstOrNull { it.id == currency.id }
                        currency.copy(amount = content?.amount ?: 0)
                    }

                    availableCurrencies.clear()
                    availableCurrencies.addAll(walletContent.sorted())

                    currencyList.adapter = WalletListAdapter(availableCurrencies, null)

                    currencyRefreshLayout.isRefreshing = false
                }
            }

        })
    }

    override fun name(): String = "Wallet"
}