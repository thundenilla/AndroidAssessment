package com.amalinamakhtar.assessment

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var restaurantsList: List<RestaurantModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        restaurantsList = arrayListOf()

        with(listView) {
            layoutManager = android.support.v7.widget.LinearLayoutManager(context)
            adapter = RestaurantAdapter(kotlin.collections.arrayListOf())
        }

        loadList()
    }

    private fun loadList() {
        val inputStream = resources.openRawResource(R.raw.restaurantcsv)
        val csvFile = CSVHelper(inputStream)
        val restaurants = csvFile.read()

        val resultMap: Map<String, String> = restaurants.map {
            it.split(",")[0] to it.split("\",\"")[1]
        }.toMap()

        val list = arrayListOf<RestaurantModel>()

        resultMap.forEach { t, u ->
            val model = Utils.parseSchedules(t, u)
            list.add(model)
        }
        (listView.adapter as RestaurantAdapter).updateItems(list)
    }
}
