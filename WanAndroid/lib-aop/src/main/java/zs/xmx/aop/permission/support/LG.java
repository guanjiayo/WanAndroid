package zs.xmx.aop.permission.support;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import zs.xmx.aop.BuildConfig;
import zs.xmx.aop.permission.callback.ISetting;


public class LG implements ISetting {

    private Context context;

    public LG(Context context) {
        this.context = context;
    }

    @Override
    public Intent getSetting() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
        ComponentName comp = new ComponentName("com.android.settings", "com.android.settings.Settings$AccessLockSummaryActivity");
        intent.setComponent(comp);
        return intent;
    }
}