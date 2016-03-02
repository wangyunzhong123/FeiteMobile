package com.example.tianxi.getphonenumber;

/**
 * Created by tianxi on 16-2-24.
 */
import android.content.Context;
import android.content.Intent;

public class LaunchEmailUtil {

    public static void launchEmailToIntent(Context context) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL,
                new String[] { "feed@back.com" });
        intent.putExtra(Intent.EXTRA_SUBJECT, "[50AH] Feedback");
        intent.putExtra(Intent.EXTRA_TEXT, "Feedback:\n");
        context
                .startActivity(Intent.createChooser(intent, "Send feedback"));
    }
}