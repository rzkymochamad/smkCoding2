package com.example.challenge2.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge2.Adapter.TipsAdapter
import com.example.challenge2.DataClass.Covid19
import com.example.challenge2.R
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_tips.*

/**
 * A simple [Fragment] subclass.
 */
class TipsFragment : Fragment() {

    lateinit var listTipsCovid:ArrayList<Covid19>
    private fun simulasiDataTeman(){
        listTipsCovid = ArrayList()
        listTipsCovid.add(
            Covid19(
                "1. Cuci tangan sesering mungkin",
                R.drawable.ic_wash,
                "Cuci tangan secara teratur dan sesering mungkin dengan sabun dan air atau bahan mengandung alkohol akan membunuh virus yang mungkin ada di tangan anda."
            )
        )
        listTipsCovid.add(
            Covid19(
                "2. Terapkan social distancing",
                R.drawable.ic_distance,
                "Jaga jarak minimal 1 meter dengan mereka yang batuk atau bersih. Alasannya, ketika seseorang batuk atau bersin atau bersih, mereka menyemprotkan tetesan cairan kecil dari hidung atau mulut mereka yang mungkin mengandung virus. Jika terlalu dekat, anda bisa menghirup tetesan air yang mungkin saja mengandung virus COVID-19."
            )
        )
        listTipsCovid.add(
            Covid19(
                "3. Hindari menyentuh mata, hidung dan mulut",
                R.drawable.ic_touch,
                "Tangan menyentuh banyak permukaan dan virus mungkin menempel di sana. Setelah terkontaminasi, tangan dapat memindahkan virus ke mata, hidung, atau mulut anda. Dari sana, virus bisa masuk ke tubuh dan bisa membuat sakit."
            )
        )
        listTipsCovid.add(
            Covid19(
                "4. Lakukan aturan bersin yang benar",
                R.drawable.ic_virus,
                "Tetap di rumah jika Anda merasa tidak sehat. Jika Anda mengalami demam, batuk dan kesulitan bernapas, cari bantuan medis dan ikuti arahan otoritas kesehatan setempat. Otoritas nasional dan lokal akan memiliki informasi terbaru tentang situasi di daerah Anda."
            )
        )
        listTipsCovid.add(
            Covid19(
                "5. Jika mengalami demam, batuk, dan kesulitan bernapas, segeralah berobat",
                R.drawable.ic_hospital,
                "Tetap di rumah jika Anda merasa tidak sehat. Jika Anda mengalami demam, batuk dan kesulitan bernapas, cari bantuan medis dan ikuti arahan otoritas kesehatan setempat. Otoritas nasional dan lokal akan memiliki informasi terbaru tentang situasi di daerah Anda."
            )
        )
    }

    private fun tampilTeman(){
        listTips.layoutManager = LinearLayoutManager(activity)
        listTips.adapter =
            TipsAdapter(activity!!, listTipsCovid)
    }

    private fun initView(){
        simulasiDataTeman()
        tampilTeman()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tips, container, false)
    }

    override fun onViewCreated(view: View,@Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }

}
