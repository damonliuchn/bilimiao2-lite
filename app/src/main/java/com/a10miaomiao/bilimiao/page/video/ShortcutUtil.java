package com.a10miaomiao.bilimiao.page.video;

import static android.content.Context.SHORTCUT_SERVICE;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;

import com.a10miaomiao.bilimiao.MainActivity;
import com.a10miaomiao.bilimiao.comm.R;

public class ShortcutUtil {
    public static void addShortcut(Context context, String label, String id) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri uri = Uri.parse("bilimiao://video/" + id);
        intent.setData(uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ShortcutManager scm = (ShortcutManager) context.getSystemService(SHORTCUT_SERVICE);
            ShortcutInfo si = new ShortcutInfo.Builder(context, label)
                    .setIcon(Icon.createWithResource(context, R.drawable.button_dialogx_miui_blue))
                    .setShortLabel(label)
                    .setIntent(intent)
                    .build();
            assert scm != null;
            scm.requestPinShortcut(si, null);
        } else {
            Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
            shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, label);
            shortcut.putExtra("duplicate", false);
            shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);
            Intent.ShortcutIconResource iconRes = Intent.ShortcutIconResource.fromContext(context, R.drawable.button_dialogx_miui_blue);
            shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);
            context.sendBroadcast(shortcut);
        }
    }
}