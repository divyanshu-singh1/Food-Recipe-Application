package com.example.foodrecipeapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.foodrecipeapp.databinding.ActivityCategoryBinding
import com.example.foodrecipeapp.databinding.CategoryRvBinding

class CategoryActivity : AppCompatActivity() {
    private lateinit var rvAdapter: CategoryAdapter
    private lateinit var dataList: ArrayList<Recipe>
    private val binding by lazy{
        ActivityCategoryBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.title.text = intent.getStringExtra("TITTLE")
        setUpRecyclerView()

        binding.goBackHome.setOnClickListener{
            finish()
        }

    }
    private  fun setUpRecyclerView(){
        dataList = ArrayList()
        binding.rvCategory.layoutManager = LinearLayoutManager (this,
            LinearLayoutManager.VERTICAL,false)

        var db = Room.databaseBuilder(this@CategoryActivity, AppDatabase::class.java, "db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()
        var daoObject = db.getDao()
        var recipes = daoObject.getAll()
        for(i in recipes!!.indices){
            if(recipes[i]!!.category.contains(intent.getStringExtra("CATEGORY")!!)){
                dataList.add(recipes[i]!!)
            }
        }
        rvAdapter = CategoryAdapter(dataList,this)
        binding.rvCategory.adapter = rvAdapter
    }
}