package com.example.primeos.basictest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

public class HomeUtil {

    public static void handleHomeButton(Context context, int... iArr) {
        HomeUtil.enableLauncher(context, context.getPackageManager());
        if (HomeUtil.getLauncherType(context) == LauncherType.IS_DEFAULT) {
            HomeUtil.openLauncherSelectionDialog(context, iArr);
        } else {
            HomeUtil.openLauncher(context, iArr);
        }
    }

    private static void enableLauncher(Context context, PackageManager packageManager) {
        packageManager.setComponentEnabledSetting(new ComponentName(context, LauncherActivity.class), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
        packageManager.setComponentEnabledSetting(new ComponentName(context, EmptyActivity.class), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
    }

    public static void disableLauncher(Context context, PackageManager packageManager) {
        packageManager.setComponentEnabledSetting(new ComponentName(context, LauncherActivity.class), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, 0);
        packageManager.setComponentEnabledSetting(new ComponentName(context, EmptyActivity.class), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, 0);
    }

    private static LauncherType getLauncherType(Context context) {
        ResolveInfo g = HomeUtil.getResolveInfo(context);
        if (g.activityInfo != null && g.activityInfo.packageName != null) {
            if (g.activityInfo.packageName.equals("android")) {
                return LauncherType.NO_DEFAULT;
            }
            if (!g.activityInfo.packageName.equals(context.getPackageName())) {
                return LauncherType.OTHER_DEFAULT;
            }
        }
        return LauncherType.IS_DEFAULT;
    }

    private static void openLauncherSelectionDialog(Context context, int... iArr) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
        for (int addFlags : iArr) {
            intent.addFlags(addFlags);
        }
        try {
            context.startActivity(intent);
        } catch (Throwable e) {
            HomeUtil.handleHomeButton(context, iArr);
        }
    }

    private static void openLauncher(Context context, int... iArr) {
        Intent intent = new Intent(context, LauncherActivity.class);
        intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
        for (int addFlags : iArr) {
            intent.addFlags(addFlags);
        }
        context.startActivity(intent);
    }

    private static ResolveInfo getResolveInfo(Context context) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        return context.getPackageManager().resolveActivity(intent, 0);
    }

}

enum LauncherType {
    NO_DEFAULT,
    IS_DEFAULT,
    OTHER_DEFAULT
}
