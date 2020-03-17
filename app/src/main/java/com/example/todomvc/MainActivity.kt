package com.example.todomvc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var customAdapter: CustomAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var itemlist = arrayListOf<String>()
        var adapter =ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_multiple_choice
            , itemlist)
        add.setOnClickListener {
            itemlist.add(editText.text.toString())
            listView.adapter =  adapter
            adapter.notifyDataSetChanged()
            editText.text.clear()
        }
        listView.setOnItemClickListener { adapterView, view, i, l ->
            itemlist.get(i)
        }
        clear.setOnClickListener {
            val position: SparseBooleanArray = listView.checkedItemPositions
            val count = listView.count
            var item = count - 1
            while (item >= 0) {
                if (position.get(item))
                {
                    adapter.remove(itemlist.get(item))
                }
                item--
            }
            position.clear()
            itemlist.clear()
            adapter.notifyDataSetChanged()
        }
        all.setOnClickListener {
            itemlist.get(i)
        }
        active.setOnClickListener {
            itemlist.get(isSelected)
        }
        completed.setOnClickListener {
            itemlist.get(!isSelected)
        }
    }
}

class Model {
    var isSelected: Boolean = false
    var item: String? = null

    fun getItems(): String {
        return this!!.item.toString()
    }
    fun setItems(item: String) {
        this.item = item
    }
    fun getSelecteds(): Boolean {
        return isSelected
    }
    fun setSelecteds(selected: Boolean) {
        isSelected = selected
    }
}