package com.example.android.booklisting;


import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class BookLoader extends AsyncTaskLoader<List<Books>> {
    private String Url;
    public BookLoader(Context context,String Url){
        super(context);
        this.Url=Url;

    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
    public List<Books> loadInBackground() {

        if (Url.length()==0|| Url == null) {
            return null;
        }

        ArrayList<Books> result=QueryUtils.fetchBookData(Url);
        return result;
    }




}
