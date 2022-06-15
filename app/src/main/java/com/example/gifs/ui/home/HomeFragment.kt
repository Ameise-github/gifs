package com.example.gifs.ui.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.gifs.R
import com.example.gifs.data.network.ClientGiphy
import com.example.gifs.databinding.FragmentHomeBinding
import com.example.gifs.ui.items.ClickListener
import com.example.gifs.ui.items.GifItem
import com.example.gifs.ui.items.RvItemGif
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeFragment : Fragment(R.layout.fragment_home), ClickListener {

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

        bindingHome.rvTrending.adapter = groupAdapter


        client.api.getTrending()
            .subscribeOn(Schedulers.io())
            .map { resp ->
                resp.data.map { GifItem(RvItemGif(it.id, title = it.title), this) }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                groupAdapter.addAll(it)
            }, Throwable::printStackTrace)

        return bindingHome.root
    }

    override fun onItemClick(gif: RvItemGif) {
        findNavController().navigate(HomeFragmentDirections.actionHomeToDetails(gif))
    }
}