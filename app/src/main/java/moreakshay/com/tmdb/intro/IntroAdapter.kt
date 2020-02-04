package moreakshay.com.tmdb.intro

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import moreakshay.com.tmdb.intro.fragments.InfoFragment
import moreakshay.com.tmdb.intro.fragments.ReviewFragment
import moreakshay.com.tmdb.intro.fragments.TrailerFragment

class IntroAdapter(supportFragmentManager: FragmentManager): FragmentPagerAdapter(supportFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(p0: Int): Fragment {
        when(p0){
            0 -> return InfoFragment.newInstance()
            1 -> return ReviewFragment.newInstace()
            2 -> return TrailerFragment.newInstance()
            else -> return InfoFragment.newInstance()
        }
    }

    override fun getCount(): Int {
        return 3
    }

}