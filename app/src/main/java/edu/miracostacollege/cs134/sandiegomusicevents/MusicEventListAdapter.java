package edu.miracostacollege.cs134.sandiegomusicevents;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import edu.miracostacollege.cs134.sandiegomusicevents.model.MusicEvent;

public class MusicEventListAdapter extends ArrayAdapter<MusicEvent>
{

    // Declare member variables to store parameters (context, resource id, list of <MusicEvent>)
    private Context mContext;
    private int mresourceId;
    private List<MusicEvent> mAllEvents;


    //constructor is being called by MainActivity
    public MusicEventListAdapter(@NonNull Context context, int resource, @NonNull List<MusicEvent> objects) {
        super(context, resource, objects);
        mContext = context;
        mresourceId = resource;
        mAllEvents = objects;
    }

    //in order to bridge View (music_event_list_item) with the Model (MusicEvent), we override:
    //Ctrl + o --> override

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //inflate custom layout with data from List<MusicEvent>
        MusicEvent focusedEvent = mAllEvents.get(position);

        //Manually inflate custom layout
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Tell inflater to inflate music_event_list_item
        View customView = inflater.inflate(mresourceId, null);

        //Fill parts of custom view
        ImageView listItemImageView = customView.findViewById(R.id.listItemImageView);
        TextView listArtistTextView = customView.findViewById(R.id.listItemArtistTextView);
        TextView listItemDateTextView = customView.findViewById(R.id.listItemDateTextView);

        listArtistTextView.setText(focusedEvent.getArtist());
        listItemDateTextView.setText(focusedEvent.getDate());

        //load picture
        AssetManager am = mContext.getAssets();
        try {
            InputStream stream = am.open(focusedEvent.getImageName());
            Drawable image = Drawable.createFromStream(stream, focusedEvent.getArtist());

            //put image into image view

            listItemImageView.setImageDrawable(image);
        } catch (IOException e) {
            Log.e("SD Music Events", e.getMessage());
        }

        //return custom view, inflated with all information
        return customView;
    }
}
