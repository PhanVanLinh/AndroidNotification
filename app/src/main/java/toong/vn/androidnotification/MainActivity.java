package toong.vn.androidnotification;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    int notificationId = 101;
    String CHANNEL_ID = "100";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_show_notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotification();
            }
        });

        findViewById(R.id.button_hide_notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideNotification();
            }
        });
    }

    private void showNotification() {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this, CHANNEL_ID).setSmallIcon(
                        R.drawable.ic_launcher_foreground)
                        .setContentTitle("My notification")
                        .setContentText("Much longer text that cannot fit one line...")
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(
                                "Much longer text that cannot fit one line..."))
                        .setContentIntent(pendingIntent)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(notificationId, mBuilder.build());
    }

    private void hideNotification(){
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        notificationManager.cancel(notificationId);
    }
}
