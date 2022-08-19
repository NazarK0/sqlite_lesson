package com.example.sqlite_lesson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.sqlite_lesson.databinding.ActivityMainBinding
import com.example.sqlite_lesson.db.MyDbManager

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val myDbManager = MyDbManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        myDbManager.open()
        printData()
    }

    override fun onDestroy() {
        super.onDestroy()

        myDbManager.close()
    }

    fun onClickSave(view: View) {
        val title = binding.etTitle.text.toString()
        val content = binding.etContent.text.toString()

        myDbManager.apply {
            open()
            insert(title, content)
        }

        printData()
    }

    private fun printData() {
        val dataList = myDbManager.getTitlesList().joinToString("\n")
        binding.tvOutput.text = dataList
    }
}