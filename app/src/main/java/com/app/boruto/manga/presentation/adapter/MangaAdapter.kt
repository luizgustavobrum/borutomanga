package com.app.boruto.manga.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.boruto.manga.databinding.BorutoMangaBinding
import com.app.boruto.manga.domain.model.Manga
import com.app.boruto.manga.presentation.components.loadImage

internal class MangaAdapter(
    private val mangaList: List<Manga>,
    private val callback: (Manga) -> Unit
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
            loadImage {
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