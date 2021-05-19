package com.app.boruto.manga.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.boruto.manga.databinding.FragmentMangaBinding
import com.app.boruto.manga.model.Manga
import com.app.boruto.manga.model.toSite
import com.app.boruto.manga.repository.FirebaseRepository

class MangaFragment : Fragment() {

    companion object {
        const val COLUMN_MAX = 2
    }

    private var _binding: FragmentMangaBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MangaViewModel by activityViewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MangaViewModel(FirebaseRepository()) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMangaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerMangaList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observerMangaList() {
        viewModel.mangaLiveData.observe(viewLifecycleOwner, Observer { mangas ->
            mangas ?: return@Observer
            showMangaList(mangas)
        })
    }

    private fun showMangaList(list: List<Manga>) {
        binding.recyclerViewManga.run {
            layoutManager = GridLayoutManager(
                requireContext(),
                COLUMN_MAX,
                RecyclerView.VERTICAL,
                false
            )
            setHasFixedSize(true)
            adapter = MangaAdapter(list) {
                it.link.toSite(requireContext())
            }
        }
    }
}