package com.app.boruto.manga.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.boruto.manga.databinding.BorutoMangaBinding
import com.app.boruto.manga.model.Manga
import com.app.boruto.manga.model.load

internal class MangaAdapter(
    val mangaList: List<Manga>,
    val callback: (Manga) -> Unit
) : RecyclerView.Adapter<MangaAdapter.MangaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaViewHolder {
        val binding = BorutoMangaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MangaViewHolder(parent.context, binding, callback)
    }

    override fun getItemCount() = mangaList.count()

    override fun onBindViewHolder(holder: MangaViewHolder, position: Int) {
        holder.bindView(manga = mangaList[position])
    }

    class MangaViewHolder(
        context: Context,
        binding: BorutoMangaBinding,
        callback: (Manga) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private val cardView = binding.cardViewManga
        private val titleView = binding.title
        private val imageView = binding.imageManga
        private val mCallback = callback
        private val mContext = context

        fun bindView(manga: Manga) {
            titleView.text = "Capítulo ${manga.numero} - ${manga.title}"
            load {
                context = mContext
                url = manga.image
                image = imageView
            }
            cardView.setOnClickListener {
                mCallback.invoke(manga)
            }
        }
    }
}