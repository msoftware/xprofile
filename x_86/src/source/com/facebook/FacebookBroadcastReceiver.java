// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.facebook;

import android.content.*;
import android.os.Bundle;
import com.facebook.internal.NativeProtocol;

public class FacebookBroadcastReceiver extends BroadcastReceiver
{

    public FacebookBroadcastReceiver()
    {
    }

    protected void onFailedAppCall(String s, String s1, Bundle bundle)
    {
    }

    public void onReceive(Context context, Intent intent)
    {
        String s;
        String s1;
        Bundle bundle;
label0:
        {
            s = intent.getStringExtra("com.facebook.platform.protocol.CALL_ID");
            s1 = intent.getStringExtra("com.facebook.platform.protocol.PROTOCOL_ACTION");
            if(s != null && s1 != null)
            {
                bundle = intent.getExtras();
                if(!NativeProtocol.isErrorResult(intent))
                    break label0;
                onFailedAppCall(s, s1, bundle);
            }
            return;
        }
        onSuccessfulAppCall(s, s1, bundle);
    }

    protected void onSuccessfulAppCall(String s, String s1, Bundle bundle)
    {
    }
}
