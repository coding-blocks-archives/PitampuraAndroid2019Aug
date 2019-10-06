package com.codingblocks.viewpagers

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm){

    private val fragments:ArrayList<Fragment> = ArrayList()
    private val tabName:ArrayList<String> = ArrayList()

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

    fun add(fragment: Fragment,name:String){
        fragments.add(fragment)
        tabName.add(name)
    }

    override fun getPageTitle(position: Int): CharSequence? = tabName[position]

}