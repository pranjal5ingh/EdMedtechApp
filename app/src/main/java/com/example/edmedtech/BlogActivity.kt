package com.example.edmedtech

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.edmedtech.adapter.BlogAdapter
import com.example.edmedtech.databinding.ActivityBlogBinding

class BlogActivity : AppCompatActivity() {

    private val binding : ActivityBlogBinding by lazy {
        ActivityBlogBinding.inflate(layoutInflater)
    }
    private lateinit var  blogAdapter: BlogAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val titles = listOf(
            "COVID-19 Commentary from India: Lockdown Saga",
            "Two reasons why we need Ayurveda",
            "Why Should You Priorities Antenatal Care?",
            "COVID-19 Commentary from India: Lockdown Saga",
            "Two reasons why we need Ayurveda",
            "Why Should You Priorities Antenatal Care?"
        )
        val images = listOf(
            R.drawable.covid,
            R.drawable.ayurveda,
            R.drawable.care,
            R.drawable.covid,
            R.drawable.ayurveda,
            R.drawable.care
        )

        // Setup RecyclerView
        blogAdapter = BlogAdapter(titles, images)
        binding.blogRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.blogRecyclerView.adapter = blogAdapter
    }
}