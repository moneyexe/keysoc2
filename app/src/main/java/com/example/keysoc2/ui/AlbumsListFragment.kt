package com.example.keysoc2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.Handler.AlbumsApiLoader
import com.example.Handler.ApiLoaderCallback
import com.example.Model.AlbumsListModel
import com.example.keysoc2.R
import okhttp3.Call
import java.io.IOException

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AlbumsListFragment : BaseFragment(), ApiLoaderCallback {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
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
        myAlbumsList=bean
        setUpRecyclerView()
    }
}