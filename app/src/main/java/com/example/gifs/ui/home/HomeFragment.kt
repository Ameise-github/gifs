package com.example.gifs.ui.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.gifs.R
import com.example.gifs.data.network.ClientGiphy
import com.example.gifs.data.network.GifResponse
import com.example.gifs.databinding.FragmentHomeBinding
import com.example.gifs.ui.home.items.GifItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeFragment : Fragment(R.layout.fragment_home) {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel by lazy { ViewModelProvider(this)[HomeViewModel::class.java] }
    lateinit var bindingHome: FragmentHomeBinding

    private val client = ClientGiphy()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingHome = FragmentHomeBinding.inflate(inflater, container, false)
        // получить gif
        val groupAdapter = GroupAdapter<GroupieViewHolder>()

        var items = listOf<GifItem>(
            GifItem(
                GifResponse(
                    "1",
                    "https://media.giphy.com/media/hpAtD9u0KVR9NSiLBt/giphy.gif",
                    "gif-1"
                )
            ),
            GifItem(
                GifResponse(
                    "2",
                    "https://media.giphy.com/media/QXgrWK8kP866nCxdLe/giphy.gif",
                    "gif-2"
                )
            ),
            GifItem(
                GifResponse(
                    "3",
                    "https://media.giphy.com/media/UDU4oUJIHDJgQ/giphy.gif",
                    "gif-3"
                )
            )
        )

        groupAdapter.addAll(items)
        bindingHome.rvTrending.adapter = groupAdapter
        /*client.api.getTrending()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                groupAdapter.add(GifItem(it))
            }, Throwable::printStackTrace)
*/
        return bindingHome.root
    }

}