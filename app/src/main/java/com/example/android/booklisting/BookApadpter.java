package com.example.android.booklisting;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class BookApadpter extends ArrayAdapter<Books> {
    private List<Books>books;

    public BookApadpter(Context context, List<Books> books) {
        super(context,0);
        this.books=books;



    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        Books current_book=getItem(position);
        TextView title=(TextView)listItemView.findViewById(R.id.Title);
        title.setText(current_book.getTitle());

        TextView Author=(TextView)listItemView.findViewById(R.id.Author);
        Author.setText(current_book.getAuthor());

        TextView description=(TextView)listItemView.findViewById(R.id.Description);
        description.setText(current_book.getDescription());

        ImageView imageView=(ImageView)listItemView.findViewById(R.id.imageView);

        try{
            Picasso.with(getContext())
                    .load(current_book.getImageUrl())
                    .placeholder(R.mipmap.ic_launcher) //places a image while the book cover
                    .into(imageView);
        }catch (Exception e){
            e.printStackTrace();
            Picasso.with(getContext())
                    .load(R.mipmap.ic_launcher)
                    .into(imageView);
        }



        /*
        if(current_book.getImageUrl()!=null) {

            Picasso.with(getContext()).load(current_book.getImageUrl()).into(imageView);
        }*/

        Log.v("The image url is ",current_book.getImageUrl());

        Picasso.with(getContext()).setLoggingEnabled(true);







        return listItemView;

    }



}




