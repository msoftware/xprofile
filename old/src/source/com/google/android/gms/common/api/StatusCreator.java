// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.common.api:
//            Status

public class StatusCreator
    implements android.os.Parcelable.Creator
{

    public StatusCreator()
    {
    }

    static void a(Status status, Parcel parcel, int i)
    {
        int j = b.p(parcel);
        b.c(parcel, 1, status.getStatusCode());
        b.c(parcel, 1000, status.getVersionCode());
        b.a(parcel, 2, status.dF(), false);
        b.a(parcel, 3, status.dE(), i, false);
        b.D(parcel, j);
    }

    public Status createFromParcel(Parcel parcel)
    {
        PendingIntent pendingintent = null;
        int i = 0;
        int j = com.google.android.gms.common.internal.safeparcel.a.o(parcel);
        String s = null;
        int k = 0;
        do
            if(parcel.dataPosition() < j)
            {
                int l = com.google.android.gms.common.internal.safeparcel.a.n(parcel);
                switch(com.google.android.gms.common.internal.safeparcel.a.S(l))
                {
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, l);
                    break;

                case 1: // '\001'
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, l);
                    break;

                case 1000: 
                    k = com.google.android.gms.common.internal.safeparcel.a.g(parcel, l);
                    break;

                case 2: // '\002'
                    s = com.google.android.gms.common.internal.safeparcel.a.m(parcel, l);
                    break;

                case 3: // '\003'
                    pendingintent = (PendingIntent)com.google.android.gms.common.internal.safeparcel.a.a(parcel, l, PendingIntent.CREATOR);
                    break;
                }
            } else
            if(parcel.dataPosition() != j)
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(j).toString(), parcel);
            else
                return new Status(k, i, s, pendingintent);
        while(true);
    }

    public volatile Object createFromParcel(Parcel parcel)
    {
        return createFromParcel(parcel);
    }

    public Status[] newArray(int i)
    {
        return new Status[i];
    }

    public volatile Object[] newArray(int i)
    {
        return newArray(i);
    }

    public static final int CONTENT_DESCRIPTION;
}
