package com.app.boruto.manga.repository

import android.util.Log
import com.app.boruto.manga.model.Manga
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FirebaseRepository : RepositoryInterface {

    companion object {
        private val TAG = FirebaseRepository::class.java.simpleName
    }

    private val myDataBase = Firebase.database.reference
    private val listManga: MutableList<Manga> = mutableListOf()

    override suspend fun onMangaCoroutine(): Result<List<Manga>> =
        suspendCoroutine { continuation ->
            val valueEventListener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach { dataSnapshot ->
                        val manga = dataSnapshot.getValue<Manga>()
                        manga?.let {
                            listManga.add(it)
                        }
                    }
                    continuation.resume(Result.success(listManga))
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e(TAG, "$error" )
                    continuation.resumeWith(Result.failure(Exception("$error")))
                }

            }
            myDataBase.addValueEventListener(valueEventListener)
    }
}