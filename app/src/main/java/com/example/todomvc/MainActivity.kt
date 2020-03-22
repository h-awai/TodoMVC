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
            var num = listView.count
            textView.text = if (num == 1) "1 item left" else "%d items left".format(num)
            adapter.notifyDataSetChanged()
            editText.text.clear()
        }
        listView.setOnItemClickListener { adapterView, view, i, l ->
            itemlist.get(i)
        }
        clear.setOnClickListener {
            val position: SparseBooleanArray = listView.checkedItemPositions
            val count = listView.count
            var num = count
            var item = count - 1
            while (item >= 0) {
                if (position.get(item))
                {
                    adapter.remove(itemlist.get(item))
                    num--
                }
                item--
            }
            position.clear()
            textView.text = if (num == 1) "1 item left" else "%d items left".format(num)
            adapter.notifyDataSetChanged()
        }
        all.setOnClickListener {
            adapter.notifyDataSetChanged()
        }
        active.setOnClickListener {
            val position: SparseBooleanArray = listView.checkedItemPositions
            val count = listView.count
            var num = count
            var item = count - 1
            while (item >= 0) {
                if (!position.get(item))
                {
                    adapter.remove(itemlist.get(item))
                    num--
                }
                item--
            }
            position.clear()
            adapter.notifyDataSetChanged()
        }
        completed.setOnClickListener {
            val position: SparseBooleanArray = listView.checkedItemPositions
            val count = listView.count
            var num = count
            var item = count - 1
            while (item >= 0) {
                if (position.get(item))
                {
                    adapter.remove(itemlist.get(item))
                    num--
                }
                item--
            }
            position.clear()
            adapter.notifyDataSetChanged()
        }
    }
}

class Model {
    var isSelected: Boolean = false
    var item: String? = null

    fun getItems(): String {
        return this.item.toString()
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