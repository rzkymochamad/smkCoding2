package com.example.challenge2.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import com.example.challenge2.R
import kotlinx.android.synthetic.main.fragment_total.*

/**
 * A simple [Fragment] subclass.
 */
class TotalFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_total, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(context!!.applicationContext).load("https://covid19.mathdro.id/api/og").into(imgWorld)
        Glide.with(context!!.applicationContext).load("https://covid19.mathdro.id/api/countries/IDN/og").into(imgInd)

    }

}
