package com.example.keysoc2.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.Model.AlbumsListModel
import com.example.Model.Result
import com.example.keysoc2.Handler.SaveBookMarkHandle
import com.example.keysoc2.R
import kotlinx.android.synthetic.main.item_list_layout.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Call
import java.io.IOException

interface AlbumsListAdapterCallback {
    fun albumsOnClick(url : String)
    fun bookMark(bean : Result)
}

class AlbumsListAdapter(private val context: Context, private val mBean: AlbumsListModel, private val albumsListAdapterCallback: AlbumsListAdapterCallback) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_list_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var nowBean=mBean.results[position]
        holder.itemView.collectionName_textView.setText(nowBean.collectionCensoredName)
        holder.itemView.artistName_textView.setText(nowBean.artistName)
        holder.itemView.collectionPrice_textView.setText("$"+nowBean.collectionPrice)

        holder.itemView.albumImageView.load(nowBean.artworkUrl60)

        var saveBookMarkHandle: SaveBookMarkHandle = SaveBookMarkHandle(context)
        nowBean.isbookMark=saveBookMarkHandle.checkBookMark(nowBean.collectionId)

        updateBookMark(holder, nowBean.isbookMark)

        holder.itemView.artistName_textView.setOnClickListener {
            albumsListAdapterCallback.albumsOnClick(nowBean.artistViewUrl)
        }

        holder.itemView.setOnClickListener {
            albumsListAdapterCallback.albumsOnClick(nowBean.collectionViewUrl)
        }

        holder.itemView.bookMark_button.setOnClickListener {
            albumsListAdapterCallback.bookMark(nowBean)
            updateBookMark(holder, nowBean.isbookMark)
        }
    }

    override fun getItemCount(): Int {
        return mBean.resultCount
    }

    fun updateBookMark(holder: RecyclerView.ViewHolder, isbookMark: Boolean) {
        if(isbookMark) {
            holder.itemView.bookMark_button.setText(R.string.btn_removebookMark)
        }else {
            holder.itemView.bookMark_button.setText(R.string.btn_bookMark)
        }
    }
}

class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    val albumImageView: ImageView = v.findViewById(R.id.albumImageView)
    val collectionName_textView: TextView = v.findViewById(R.id.collectionName_textView)
    val artistName_textView: TextView = v.findViewById(R.id.artistName_textView)
    val collectionPrice_textView: TextView = v.findViewById(R.id.collectionPrice_textView)
    val bookMark_button: Button = v.findViewById(R.id.bookMark_button)
}