package edu.miracostacollege.cs134.sandiegomusicevents;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class EventDetailsActivity extends AppCompatActivity {


    private TextView eventTitleTextView;
    private TextView eventDateTextView;
    private ImageView eventImageView;
    private TextView eventTimeTextView;
    private TextView eventVenueTextView;
    private TextView eventCityTextView;
    private TextView eventStateTextView;
    private TextView eventDayTextView;
    private TextView eventImageNameTextView;

    public static final String TAG = EventDetailsActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        Intent intent = getIntent();
        String title = intent.getStringExtra("Artist");
        String date = intent.getStringExtra("Date");
        String day = intent.getStringExtra("Day");
        String time = intent.getStringExtra("Time");
        String venue = intent.getStringExtra("Venue");
        String city = intent.getStringExtra("City");
        String state = intent.getStringExtra("State");
        String imageName = intent.getStringExtra("ImageName");


        String fileName = title.replaceAll(" ", "") + ".png";

        eventTitleTextView = findViewById(R.id.eventArtistTextView);
        eventDateTextView = findViewById(R.id.eventDateDayTextView);
        eventImageView = findViewById(R.id.eventImageView);
        eventTimeTextView = findViewById(R.id.eventTimeTextView);
        eventVenueTextView = findViewById(R.id.eventVenueTextView);
        eventCityTextView = findViewById(R.id.eventCityTextView);
        eventStateTextView = findViewById(R.id.eventStateTextView);
        eventDayTextView = findViewById(R.id.eventDayTextView);
        eventImageNameTextView = findViewById(R.id.eventImageNameTextView);

        eventTitleTextView.setText(title);
        eventDateTextView.setText(date);
        eventTimeTextView.setText(time);
        eventVenueTextView.setText(venue);
        eventCityTextView.setText(city);
        eventStateTextView.setText(state);
        eventDayTextView.setText(day);
        eventImageNameTextView.setText(imageName);

        AssetManager am = getAssets();
        try {
            InputStream stream = am.open(fileName);
            Drawable eventImage = Drawable.createFromStream(stream, title);
            eventImageView.setImageDrawable(eventImage);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }

    }
}
