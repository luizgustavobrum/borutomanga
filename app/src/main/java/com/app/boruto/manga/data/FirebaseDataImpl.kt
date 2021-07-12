package com.app.boruto.manga.data

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class FirebaseDataImpl(
    private val myData: FirebaseDatabase
) : FirebaseData {

    private var listMangaResponse: MutableList<MangaResponse> = mutableListOf()

    @ExperimentalCoroutinesApi
    override fun getMangaList(): Flow<List<MangaResponse>> = callbackFlow {
        val callback = (object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listMangaResponse = mutableListOf()
                snapshot.children.forEach {
                    val manga = it.getValue<MangaResponse>()
                    manga?.let { manga ->
                        listMangaResponse.add(manga)
                    }
                }
                offer(listMangaResponse)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        myData.reference.addValueEventListener(callback)

        awaitClose { }
    }
}