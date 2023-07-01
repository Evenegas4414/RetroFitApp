package com.ervr.retrofitapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemsFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemAdapter
    private lateinit var db: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_items, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(context, 2)

        db = Room.databaseBuilder(
            requireActivity().applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        RetrofitInstance.instance.getItems().enqueue(object : Callback<List<Item>> {
            override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                if (response.isSuccessful) {
                    val items = response.body()!!

                    Thread {
                        db.itemDao().insertAll(*items.toTypedArray())

                        val itemsFromDb = db.itemDao().getAll()

                        activity?.runOnUiThread {
                            adapter = ItemAdapter(itemsFromDb)
                            recyclerView.adapter = adapter
                        }
                    }.start()
                }
            }

            override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                // Maneja el error aquí
            }
        })
    }
}

