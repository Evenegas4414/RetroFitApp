package com.ervr.retrofitapp

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

class DetailFragment : Fragment() {
    private lateinit var item: Item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        item = arguments?.getParcelable("item")!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        val imageView = view.findViewById<ImageView>(R.id.image_view)
        Glide.with(view.context).load(item.img_src).into(imageView)


        val textView = view.findViewById<TextView>(R.id.price_text)
        textView.text = item.price.toString()
        val textView2 = view.findViewById<TextView>(R.id.type_text)
        textView2.text = item.type

        return view
    }
}
