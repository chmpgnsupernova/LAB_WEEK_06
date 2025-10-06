package com.example.lab_week_06

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_week_06.model.CatBreed
import com.example.lab_week_06.model.CatModel
import com.example.lab_week_06.model.Gender
import androidx.recyclerview.widget.ItemTouchHelper


class MainActivity : AppCompatActivity() {
    private val recyclerView: RecyclerView by lazy {
        findViewById(R.id.recycler_view)
    }
    private val catAdapter by lazy {
//Glide is used here to load the images
//Here we are passing the onClickListener function to the Adapter
        CatAdapter(layoutInflater, GlideImageLoader(this), object:
            CatAdapter.OnClickListener {
            //When this is triggered, the pop up dialog will be shown
            override fun onItemClick(cat: CatModel) = showSelectionDialog(cat)
        })
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//Setup the adapter for the recycler view
        recyclerView.adapter = catAdapter
//Setup the layout manager for the recycler view
//A layout manager is used to set the structure of the item views
//For this tutorial, we're using the vertical linear structure
        recyclerView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)
//Add data to the model list in the adapter
        catAdapter.setData(
            listOf(
                CatModel(
                    Gender.Male, CatBreed.BalineseJavanese, "Leo", "Loves to nap in the sun.",
                    "https://cdn2.thecatapi.com/images/47s.jpg"
                ),
                CatModel(
                    Gender.Female, CatBreed.ExoticShorthair, "Luna", "Master of the midnight zoomies.",
                    "https://cdn2.thecatapi.com/images/52b.jpg"
                ),
                CatModel(
                    Gender.Male, CatBreed.AmericanCurl, "Milo", "Will do anything for a treat.",
                    "https://cdn2.thecatapi.com/images/8kl.jpg"
                ),
                CatModel(
                    Gender.Female, CatBreed.ExoticShorthair, "Chloe", "Expert at finding comfortable spots.",
                    "https://cdn2.thecatapi.com/images/a2n.jpg"
                ),
                CatModel(
                    Gender.Male, CatBreed.BalineseJavanese, "Simba", "Thinks he's the king of the house.",
                    "https://cdn2.thecatapi.com/images/b63.jpg"
                ),
                CatModel(
                    Gender.Female, CatBreed.AmericanCurl, "Bella", "Graceful and elegant, until she trips.",
                    "https://cdn2.thecatapi.com/images/dbt.jpg"
                ),
                CatModel(
                    Gender.Male, CatBreed.ExoticShorthair, "Oliver", "A sophisticated gentleman.",
                    "https://cdn2.thecatapi.com/images/MTc5OTM0MA.jpg"
                ),
                CatModel(
                    Gender.Male, CatBreed.AmericanCurl, "Max", "Loud purrs and a love for boxes.",
                    "https://cdn2.thecatapi.com/images/96b.jpg"
                ),
                CatModel(
                    Gender.Female, CatBreed.BalineseJavanese, "Nala", "Agile hunter of toy mice.",
                    "https://cdn2.thecatapi.com/images/afr.jpg"
                ),
                CatModel(
                    Gender.Unknown, CatBreed.ExoticShorthair, "Oscar", "Grumpy face, heart of gold.",
                    "https://cdn2.thecatapi.com/images/c6.jpg"
                )
            )
        )
        val itemTouchHelper = ItemTouchHelper(catAdapter.swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
    private fun showSelectionDialog(cat: CatModel) {
        AlertDialog.Builder(this)
//Set the title for the dialog
            .setTitle("Cat Selected")
//Set the message for the dialog
            .setMessage("You have selected cat ${cat.name}")
//Set if the OK button should be enabled
            .setPositiveButton("OK") { _, _ -> }.show()
    }
}
