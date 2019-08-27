package com.example.android.booklisting;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements LoaderCallbacks<List<Books>> {
    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int BOOK_LOADER_ID = 1;


    public static final String LOG_TAG = MainActivity.class.getName();
    LoaderManager loaderManager = getLoaderManager();
    private String query= "";
    private  BookApadpter adapter;
    private Button button;
    private TextView textView;
    private ProgressBar progressBar;
    private TextView blankState;
    private EditText editText;
    private ListView bookView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar=(ProgressBar)findViewById(R.id.progress_bar);
        blankState=(TextView)findViewById(R.id.blank_state);
        button=(Button)findViewById(R.id.search);


        editText=(EditText) findViewById(R.id.editText);
        bookView=(ListView)findViewById(R.id.list);





        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        // Find a reference to the {@link ListView} in the layout









        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //progressBar.setVisibility(View.VISIBLE);

                String s=editText.getText().toString();
                query="https://www.googleapis.com/books/v1/volumes?q="+s+"&maxResults=30";
                //adapter.add(new Books("A","b","c","f"));


                   search();

            }
        });


        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface

      bookView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              if(isConnected()) {
                  Books currentBook = adapter.getItem(position);

                  Uri bookUri = Uri.parse(currentBook.getUrl());

                  Intent web = new Intent(Intent.ACTION_VIEW, bookUri);
                  startActivity(web);

              }


          }
      });
    }

    @Override
    public Loader<List<Books>> onCreateLoader(int i, Bundle bundle) {
        blankState.setText("");


        // TODO: Create a new loader for the given URL

        // Create a new loader for the given URL
        return new BookLoader(this,query);
    }
    public void search(){
        progressBar.setVisibility(View.VISIBLE);
        getLoaderManager().restartLoader(BOOK_LOADER_ID,null,MainActivity.this);
        blankState.setVisibility(View.GONE);


        adapter=new BookApadpter(this, new ArrayList<Books>());
        bookView.setAdapter(adapter);


    }
    public boolean isConnected(){
        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
         boolean is_connected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return is_connected;



    }

    @Override
    public void onLoadFinished(Loader<List<Books>> loader, List<Books> books) {
        bookView.setEmptyView(blankState);


        // TODO: Update the UI with the result

        // TODO: Loader reset, so we can clear out our existing data.

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.


        if (books!= null && !books.isEmpty()) {

            adapter.clear();

            adapter.addAll(books);
            progressBar.setVisibility(View.GONE);


            Log.v("The books list",adapter.getItem(0).getImageUrl());


        }else if(isConnected()==false){

            blankState.setText("No Internet Connection");
            progressBar.setVisibility(View.GONE);
                    }
        else {
            blankState.setText("No Books found");
            progressBar.setVisibility(View.GONE);
        }






    }

    @Override
    public void onLoaderReset(Loader<List<Books>> loader) {
        // TODO: Loader reset, so we can clear out our existing data.
        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.

        adapter.clear();

    }
}
