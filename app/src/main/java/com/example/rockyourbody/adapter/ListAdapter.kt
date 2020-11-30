package com.example.rockyourbody.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.rockyourbody.R
import com.example.rockyourbody.model.Atividade
import kotlinx.android.synthetic.main.list_item.view.*

class ListAdapter(private val items: List<Atividade>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val item = items[position]
        val inflater = LayoutInflater.from(parent?.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)

        view.txtWorkoutTitle.text = item.titulo
        view.txtWorkoutType.text = item.tipoAtividade
        view.txtWorkoutDate.text = item.dataAtividade

        return view
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return items[position].id.toLong()
    }
}