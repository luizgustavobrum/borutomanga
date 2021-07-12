package com.app.boruto.manga.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.boruto.manga.data.FirebaseDataImpl
import com.app.boruto.manga.databinding.FragmentMangaBinding
import com.app.boruto.manga.model.Manga
import com.app.boruto.manga.model.toSite
import com.app.boruto.manga.repository.FirebaseRepositoryImpl
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MangaFragment : Fragment() {

    companion object {
        const val COLUMN_MAX = 2
    }

    private var _binding: FragmentMangaBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MangaViewModel by activityViewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MangaViewModel(FirebaseRepositoryImpl(FirebaseDataImpl(Firebase.database))) as T
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
        viewModel.uiStateManga.asLiveData().observe(viewLifecycleOwner, Observer {
            when (it) {
                is UiStateManga.Loading -> {

                }
                is UiStateManga.Success -> {
                    showMangaList(it.value)
                    gone()
                }
                is UiStateManga.Error -> {

                }
                is UiStateManga.Initial -> {

                }
            }
        })
    }

    private fun visibile() {
        binding.recyclerViewManga.visibility = GONE
        binding.progressBar.visibility = VISIBLE
    }

    private fun gone() {
        binding.recyclerViewManga.visibility = VISIBLE
        binding.progressBar.visibility = GONE
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