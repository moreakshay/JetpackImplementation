package moreakshay.com.mine.ui.features.intro.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_trailer.view.*
import moreakshay.com.mine.R
import moreakshay.com.mine.ui.features.login.LoginActivity

class TrailerFragment: Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_trailer, container, false)!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.btnNext.setOnClickListener { val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent) }
    }

    companion object {
        fun newInstance(): Fragment{
            return TrailerFragment()
        }
    }

}