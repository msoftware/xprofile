// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.wallet;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

// Referenced classes of package com.google.android.gms.wallet:
//            o

public final class ProxyCard
    implements SafeParcelable
{

    ProxyCard(int i, String s, String s1, int j, int k)
    {
        wj = i;
        Zn = s;
        Zo = s1;
        Zp = j;
        Zq = k;
    }

    public int describeContents()
    {
        return 0;
    }

    public String getCvn()
    {
        return Zo;
    }

    public int getExpirationMonth()
    {
        return Zp;
    }

    public int getExpirationYear()
    {
        return Zq;
    }

    public String getPan()
    {
        return Zn;
    }

    public int getVersionCode()
    {
        return wj;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        o.a(this, parcel, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new o();
    String Zn;
    String Zo;
    int Zp;
    int Zq;
    private final int wj;

}
