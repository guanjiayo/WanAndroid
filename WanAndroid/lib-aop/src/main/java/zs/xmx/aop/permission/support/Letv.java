package zs.xmx.aop.permission.support;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import zs.xmx.aop.BuildConfig;
import zs.xmx.aop.permission.callback.ISetting;


public class Letv implements ISetting {

    private Context context;

    public Letv(Context context) {
        this.context = context;
    }

    @Override
    public Intent getSetting() {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
        ComponentName comp = new ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.PermissionAndApps");
        intent.setComponent(comp);
        return intent;
    }
}