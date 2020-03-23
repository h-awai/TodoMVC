package com.example.todomvc

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import java.util.ArrayList

class CustomAdapter(private val context: Context, modelArrayList: ArrayList<Model>) : BaseAdapter() {
    private var modelArrayList: ArrayList<Model>

    init {
        this.modelArrayList = modelArrayList
    }
    override fun getViewTypeCount(): Int {
        return count
    }
    override fun getItemViewType(position: Int): Int {
        return position
    }
    override fun getCount(): Int {
        return modelArrayList.size
    }
    override fun getItem(position: Int): Any {
        return modelArrayList[position]
    }
    override fun getItemId(position: Int): Long {
        return 0
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val holder: ViewHolder

        if (convertView == null) {
            holder = ViewHolder()
            val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.list, null, true)

            holder.checkBox = convertView!!.findViewById(R.id.checkBox) as CheckBox
            holder.tvItem = convertView.findViewById(R.id.textView2) as TextView
            holder.x = convertView.findViewById(R.id.x) as Button
            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }

        holder.checkBox!!.text = "Checkbox $position"
        holder.tvItem!!.setText(modelArrayList[position].getItems())
        holder.checkBox!!.isChecked = modelArrayList[position].getSelecteds()
        holder.checkBox!!.setTag(R.integer.btnplusview, convertView)
        holder.checkBox!!.tag = position
        holder.checkBox!!.setOnClickListener {
            val tempview = holder.checkBox!!.getTag(R.integer.btnplusview) as View
            val pos = holder.checkBox!!.tag as Int
            if (modelArrayList[pos].getSelecteds()) {
                modelArrayList[pos].setSelecteds(false)
                public_modelArrayList = modelArrayList
            } else {
                modelArrayList[pos].setSelecteds(true)
                public_modelArrayList = modelArrayList
            }
        }
        return convertView
    }

    private inner class ViewHolder {
        var checkBox: CheckBox? = null
        var tvItem: TextView? = null
        var x: Button? = null
    }

    companion object {
        lateinit var public_modelArrayList: ArrayList<Model>
    }
}