package com.app.boruto.manga.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.boruto.manga.databinding.BorutoMangaBinding
import com.app.boruto.manga.domain.model.Manga
import com.app.boruto.manga.presentation.components.loadImage

internal class MangaAdapter
    : RecyclerView.Adapter<MangaAdapter.MangaViewHolder>() {

    private var mangaList: List<Manga> = listOf()
    private lateinit var listener: (Manga) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaViewHolder {
        val binding = BorutoMangaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MangaViewHolder(parent.context, binding)
    }

    override fun getItemCount() = mangaList.count()

    override fun onBindViewHolder(holder: MangaViewHolder, position: Int) {
        holder.bindView(manga = mangaList[position])
    }

    fun setMangaList(mangaList: List<Manga>) {
        this.mangaList = mangaList
        notifyDataSetChanged()
    }

    fun setListener(listener: (Manga) -> Unit) {
        this.listener = listener
    }

    inner class MangaViewHolder(
        context: Context,
        binding: BorutoMangaBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val cardView = binding.cardViewManga
        private val titleView = binding.title
        private val imageView = binding.imageManga
        private val mContext = context

        fun bindView(manga: Manga) {
            titleView.text = "Capítulo ${manga.numero} - ${manga.title}"
            loadImage {
                context = mContext
                url = manga.image
                image = imageView
            }

            cardView.setOnClickListener {
                listener.invoke(manga)
            }
        }
    }
}