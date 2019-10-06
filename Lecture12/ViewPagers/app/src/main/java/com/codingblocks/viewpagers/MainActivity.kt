package com.codingblocks.viewpagers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val adapter by lazy {
       ViewPagerAdapter(supportFragmentManager)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        adapter.add(BlankFragment(),"!")
        adapter.add(BlankFragment2(),"2")
        adapter.add(BlankFragment3(),"3")

        pager.adapter = adapter

        tabs.setupWithViewPager(pager)

    }

    override fun onBackPressed() {
        if(pager.currentItem != 0){
            pager.currentItem = 0
        }else{
            super.onBackPressed()
        }
    }
}
