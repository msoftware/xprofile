// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.ads.doubleclick;

import android.content.Context;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.internal.al;

// Referenced classes of package com.google.android.gms.ads.doubleclick:
//            PublisherAdRequest, AppEventListener

public final class PublisherInterstitialAd
{

    public PublisherInterstitialAd(Context context)
    {
        kE = new al(context);
    }

    public AdListener getAdListener()
    {
        return kE.getAdListener();
    }

    public String getAdUnitId()
    {
        return kE.getAdUnitId();
    }

    public AppEventListener getAppEventListener()
    {
        return kE.getAppEventListener();
    }

    public boolean isLoaded()
    {
        return kE.isLoaded();
    }

    public void loadAd(PublisherAdRequest publisheradrequest)
    {
        kE.a(publisheradrequest.N());
    }

    public void setAdListener(AdListener adlistener)
    {
        kE.setAdListener(adlistener);
    }

    public void setAdUnitId(String s)
    {
        kE.setAdUnitId(s);
    }

    public void setAppEventListener(AppEventListener appeventlistener)
    {
        kE.setAppEventListener(appeventlistener);
    }

    public void show()
    {
        kE.show();
    }

    private final al kE;
}
