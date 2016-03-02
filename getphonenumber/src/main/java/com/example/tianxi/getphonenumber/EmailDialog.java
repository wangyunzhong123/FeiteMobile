package com.example.tianxi.getphonenumber;

/**
 * Created by tianxi on 16-2-24.
 */
import android.content.Context;
import android.content.DialogInterface;
import android.preference.DialogPreference;
import android.util.AttributeSet;

public class EmailDialog extends DialogPreference {
    Context mContext;

    public EmailDialog(Context context) {
        this(context, null);
    }

    public EmailDialog(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmailDialog(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        super.onClick(dialog, which);

        if (DialogInterface.BUTTON_POSITIVE == which) {
            LaunchEmailUtil.launchEmailToIntent(mContext);
        }
    }
}