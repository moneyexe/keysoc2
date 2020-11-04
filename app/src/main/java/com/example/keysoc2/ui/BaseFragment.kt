package com.example.keysoc2.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Handler.AlbumsApiLoader
import com.example.Model.AlbumsListModel
import com.example.Model.Result
import com.example.keysoc2.Adapter.AlbumsListAdapter
import com.example.keysoc2.Adapter.AlbumsListAdapterCallback
import com.example.keysoc2.Handler.SaveBookMarkHandle
import com.example.keysoc2.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

open class BaseFragment: Fragment(), AlbumsListAdapterCallback {
    lateinit var myAlbumsList: AlbumsListModel
    lateinit var adapter: AlbumsListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun setUpRecyclerView() {
        var myContext : Context = this.requireContext()
        var albumsListAdapterCallback : AlbumsListAdapterCallback = this
        adapter=AlbumsListAdapter(myContext, myAlbumsList, albumsListAdapterCallback)
        GlobalScope.launch(Dispatchers.Main) {
            val layoutManager = LinearLayoutManager(myContext)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            val dataList = getView()?.findViewById<RecyclerView>(R.id.mainRecycle_List)
            dataList?.layoutManager = layoutManager
            dataList?.adapter = adapter
        }
    }

    fun recyclerViewReload() {
        adapter.notifyDataSetChanged()
    }

    override fun albumsOnClick(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    override fun bookMark(bean: Result) {
        var saveBookMarkHandle: SaveBookMarkHandle = SaveBookMarkHandle(this.requireContext())
        if(!bean.isbookMark) {
            bean.isbookMark=true
            saveBookMarkHandle.addBookMarkToList(bean.collectionId)
        }else {
            bean.isbookMark=false
            saveBookMarkHandle.removeBookMarkToList(bean.collectionId)
        }
//        var readFile: String = saveBookMarkHandle.readFile()
//        print(readFile)
        bookMarkUpdate()
    }

    open fun bookMarkUpdate() {

    }
}