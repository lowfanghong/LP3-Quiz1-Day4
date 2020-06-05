package com.myapplicationdev.android.lp3quiz1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnBasic, btnPicture, btnInbox;
    int notificationID = 88;
    int requestCode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBasic = (Button) findViewById(R.id.ButtonBasic);
        btnPicture = (Button) findViewById(R.id.ButtonBigPic);
        btnInbox = (Button) findViewById(R.id.ButtonInbox);

        final NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new
                    NotificationChannel("default", "Default Channel",
                    NotificationManager.IMPORTANCE_DEFAULT);

            channel1.setDescription("This is for default notification");
            notificationManager.createNotificationChannel(channel1);

        }

        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        final PendingIntent pIntent = PendingIntent.getActivity (MainActivity.this, requestCode, intent,
                PendingIntent.FLAG_CANCEL_CURRENT);



        btnBasic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "default");
                builder.setContentTitle("LP3 Quiz1");
                builder.setContentText("This is a basic/simple notification");
                builder.setSmallIcon(android.R.drawable.btn_star_big_off);
                builder.setContentIntent(pIntent);
                builder.setAutoCancel(true);

                Notification n = builder.build();

                notificationManager.notify(notificationID, n);
                finish();
            }
        });

        btnPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.BigPictureStyle bigpicStyle =
                        new NotificationCompat.BigPictureStyle();
                bigpicStyle.bigPicture(BitmapFactory.decodeResource(getResources(),
                        R.drawable.koala)).build();

                NotificationCompat.Builder builder = new
                        NotificationCompat.Builder(MainActivity.this, "default");
                builder.setContentTitle("LP3 Quiz1");
                builder.setContentText("Expand to see picture");
                builder.setStyle(bigpicStyle);
                builder.setSmallIcon(android.R.drawable.btn_star_big_off);
                builder.setContentIntent(pIntent);
                builder.setAutoCancel(true);

                Notification n = builder.build();

                notificationManager.notify(notificationID, n);
                finish();
            }
        });

        btnInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<String> al = new ArrayList<>();
                al.add("This is the first line");
                al.add("This is the second line");
                al.add("This is the third line");

                NotificationCompat.InboxStyle inboxS = new NotificationCompat.InboxStyle();
                for (String tmp : al){ //iterate the entire ArrayList object and extract each element into variable tmp
                    inboxS.addLine(tmp);  //do something to the tmp, which is to retrive the value and place into Notification Inbox Style
                };
                inboxS.setBigContentTitle("Inbox style");
                inboxS.setSummaryText("List of entries");

                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "default");
                builder.setContentTitle("LP3 Quiz1");
                builder.setContentText("Expand to see content");
                builder.setSmallIcon(android.R.drawable.btn_star_big_off);
                builder.setContentIntent(pIntent);
                builder.setAutoCancel(true);

                Notification n = builder.build();

                notificationManager.notify(notificationID, n);
                finish();
            }
        });

    }

}
