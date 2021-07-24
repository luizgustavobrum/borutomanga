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
import com.app.boruto.manga.data.firebase.FirebaseDataImpl
import com.app.boruto.manga.databinding.FragmentMangaBinding
import com.app.boruto.manga.domain.model.Manga
import com.app.boruto.manga.data.repository.FirebaseRepositoryImpl
import com.app.boruto.manga.domain.usecase.MangaUseCaseImpl
import com.app.boruto.manga.presentation.adapter.MangaAdapter
import com.app.boruto.manga.presentation.components.toSite
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
                return MangaViewModel(
                    MangaUseCaseImpl(
                        FirebaseRepositoryImpl(
                            FirebaseDataImpl(
                                Firebase.database
                            )
                        )
                    )
                ) as T
            }
        }
    }

    private val mangaAdapter = MangaAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMangaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRecyclerView()
        setListenerInMangaAdapter()
        observerMangaList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initializeRecyclerView() {
        binding.recyclerViewManga.apply {
            layoutManager = GridLayoutManager(
                requireContext(),
                COLUMN_MAX,
                RecyclerView.VERTICAL,
                false
            )
            setHasFixedSize(true)
            adapter = mangaAdapter
        }
    }

    private fun setListenerInMangaAdapter() {
        mangaAdapter.setListener {
            it.link.toSite(this.requireContext())
        }
    }

    private fun updateDataMangaList(list: List<Manga>) {
        mangaAdapter.setMangaList(list)
    }

    private fun observerMangaList() {
        viewModel.uiStateManga.asLiveData().observe(viewLifecycleOwner, Observer {
            when (it) {
                is UiStateManga.Loading -> {
                    uiProgressVisibile()
                }
                is UiStateManga.Success -> {
                    updateDataMangaList(it.value)
                    uiProgressGone()
                }
                is UiStateManga.Error -> {

                }
                is UiStateManga.Initial -> Unit
            }
        })
    }

    private fun uiProgressVisibile() {
        binding.recyclerViewManga.visibility = GONE
        binding.progressBar.visibility = VISIBLE
    }

    private fun uiProgressGone() {
        binding.recyclerViewManga.visibility = VISIBLE
        binding.progressBar.visibility = GONE
    }
}