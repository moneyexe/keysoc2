package com.example.keysoc2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.Handler.AlbumsApiLoader
import com.example.Handler.ApiLoaderCallback
import com.example.Model.AlbumsListModel
import com.example.Model.Result
import com.example.keysoc2.Handler.SaveBookMarkHandle
import com.example.keysoc2.R
import okhttp3.Call
import java.io.IOException

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class BookMarkListFragment : BaseFragment(), ApiLoaderCallback {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var apiLoader = AlbumsApiLoader()
        apiLoader.loadAlbumsList(this)
    }

    override fun onFailure(call: Call, e: IOException) {
        TODO("Not yet implemented")
    }

    override fun onResponse(bean: AlbumsListModel) {
        recyclerViewListUpdate(bean)
        setUpRecyclerView()
    }

    fun recyclerViewListUpdate(bean: AlbumsListModel) {
        var saveBookMarkHandle: SaveBookMarkHandle = SaveBookMarkHandle(this.requireContext())
        var bookMarkList = saveBookMarkHandle.getFullList()
        var albumsList: MutableList<Result> = ArrayList()
        for ((index, resultValue) in bean.results.withIndex()) {
            for ((index, value) in bookMarkList.withIndex()) {
                if (value.equals(resultValue.collectionId.toString())) {
                    albumsList.add(resultValue)
                }
            }
        }
        myAlbumsList=bean
        myAlbumsList.results=albumsList
        myAlbumsList.resultCount=albumsList.count()
    }

    override fun bookMarkUpdate() {
        recyclerViewListUpdate(myAlbumsList)
        recyclerViewReload()
    }
}