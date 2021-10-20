package com.esafirm.imagepicker.features.imageloader

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.esafirm.imagepicker.R
import com.esafirm.imagepicker.model.Image

class DefaultImageLoader : ImageLoader {
    override fun loadImage(
        image: Image,
        imageView: ImageView,
        imageType: ImageType,
        onSuccess: (Boolean) -> Unit
    ) {
        Glide.with(imageView.context)
            .load(image.uri)
            .apply(
                RequestOptions
                    .placeholderOf(if (imageType == ImageType.FOLDER) R.drawable.ef_folder_placeholder else R.drawable.ef_image_placeholder)
                    .error(if (imageType == ImageType.FOLDER) R.drawable.ef_folder_placeholder else R.drawable.ef_image_error)
            )
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    onSuccess(false)
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean  {
                    onSuccess(true)
                    return false
                }
            })
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)
    }
}