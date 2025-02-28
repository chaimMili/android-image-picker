package com.esafirm.imagepicker.adapter

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.esafirm.imagepicker.features.ImagePickerConfig
import com.esafirm.imagepicker.features.imageloader.ImageLoader

abstract class BaseListAdapter<T : RecyclerView.ViewHolder>(
    val context: Context,
    val imageLoader: ImageLoader,
    val config: ImagePickerConfig? = null
) : RecyclerView.Adapter<T>() {
    val inflater: LayoutInflater = LayoutInflater.from(context)
}