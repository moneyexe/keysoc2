package com.example.keysoc2.Handler

import android.content.Context
import android.os.Environment
import java.io.BufferedReader
import java.io.File

class SaveBookMarkHandle {
    val fileName = "keysoc.txt"
    lateinit var file: File

    constructor(context: Context) {
        file = File(context.getExternalFilesDir(null)!!.absolutePath+"/"+fileName);
        if(!file.exists())
        {
            file.createNewFile()
            // write code for saving data to the file
        }
    }

    fun checkBookMark(bookMarkId: Int): Boolean {
        var fullList=getFullList()
        for ((index, value) in fullList.withIndex()) {
            if (value.equals(bookMarkId.toString())) {
                return true
            }
        }
        return false
    }

    fun getFullList(): MutableList<String> {
        var fullList=readFile()
        var result: MutableList<String> = fullList.split(",").toMutableList()
        return result
    }

    fun fullToString(list: List<String>): String {
        var result = list.joinToString(",")
        return result
    }

    fun addBookMarkToList(bookMarkId: Int) {
        var fullList=getFullList()
        fullList.add(bookMarkId.toString())
        var content = fullToString(fullList)
        updateTextFile(content)
    }

    fun removeBookMarkToList(bookMarkId: Int) {
        var fullList=getFullList()
        for ((index, value) in fullList.withIndex()) {
            if (value.equals(bookMarkId.toString())) {
                fullList.removeAt(index)
                break
            }
        }
        var content = fullToString(fullList)
        updateTextFile(content)

    }

    fun updateTextFile(content: String) {
        file.writeText(content)
    }

    fun readFile(): String {
        val bufferedReader: BufferedReader = file.bufferedReader()
        val inputString = bufferedReader.use { it.readText() }
        return inputString
    }
}