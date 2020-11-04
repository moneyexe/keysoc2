package com.example.Handler

import com.example.Model.AlbumsListModel
import com.example.keysoc.albumsListUrl
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException

interface ApiLoaderCallback {
    fun onFailure(call: Call, e: IOException)
    fun onResponse(bean: AlbumsListModel)
}

class AlbumsApiLoader : ApiLoader(), Callback {
    lateinit var myCallback:ApiLoaderCallback

    fun loadAlbumsList(callback:ApiLoaderCallback) {
        loadApi(albumsListUrl, this)
        myCallback=callback
    }

    override fun onFailure(call: Call, e: IOException) {
        TODO("Not yet implemented")
        myCallback.onFailure(call, e)
    }

    override fun onResponse(call: Call, response: Response) {
//        println(response.body?.string())
        val bean = Gson().fromJson(response.body?.string(), AlbumsListModel::class.javaObjectType)
        val results = bean.results
        //验证是否有数据
//        println(results.toString())
        myCallback.onResponse(bean)
    }
}