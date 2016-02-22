package com.beastpotato.movieinformation.adapters;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.beastpotato.movieinformation.managers.ImageManager;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Oleksiy on 1/31/2016.
 */
public class Bindings {
    @android.databinding.BindingAdapter(value = {"items", "itemBinding", "layoutManager"})
    public static <T> void showItemsList(RecyclerView recyclerView, List<T> items, ItemBinder<T> itemBinding, RecyclerView.LayoutManager layoutManager) {

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new BindingAdapter(items, itemBinding));
    }

    @android.databinding.BindingAdapter(value = {"imageUrl", "placeholder"}, requireAll = false)
    public static void loadImage(ImageView imageView, String imageUrl, Drawable drawable) {
        Picasso.with(imageView.getContext()).load(imageUrl).placeholder(drawable).into(imageView);
    }

    @android.databinding.BindingAdapter(value = {"posterImageUrl", "placeholder"}, requireAll = false)
    public static void loadPosterImage(ImageView imageView, String imageUrl, Drawable drawable) {
        Picasso.with(imageView.getContext()).load(ImageManager.getInstance().getPosterImageUrl(imageUrl)).placeholder(drawable).into(imageView);
    }

}
