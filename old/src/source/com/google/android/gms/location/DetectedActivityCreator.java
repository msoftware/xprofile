// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.location:
//            DetectedActivity

public class DetectedActivityCreator
    implements android.os.Parcelable.Creator
{

    public DetectedActivityCreator()
    {
    }

    static void a(DetectedActivity detectedactivity, Parcel parcel, int i)
    {
        int j = b.p(parcel);
        b.c(parcel, 1, detectedactivity.KS);
        b.c(parcel, 1000, detectedactivity.getVersionCode());
        b.c(parcel, 2, detectedactivity.KT);
        b.D(parcel, j);
    }

    public DetectedActivity createFromParcel(Parcel parcel)
    {
        int i = 0;
        int j = com.google.android.gms.common.internal.safeparcel.a.o(parcel);
        int k = 0;
        int l = 0;
        do
            if(parcel.dataPosition() < j)
            {
                int i1 = com.google.android.gms.common.internal.safeparcel.a.n(parcel);
                switch(com.google.android.gms.common.internal.safeparcel.a.S(i1))
                {
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, i1);
                    break;

                case 1: // '\001'
                    k = com.google.android.gms.common.internal.safeparcel.a.g(parcel, i1);
                    break;

                case 1000: 
                    l = com.google.android.gms.common.internal.safeparcel.a.g(parcel, i1);
                    break;

                case 2: // '\002'
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, i1);
                    break;
                }
            } else
            if(parcel.dataPosition() != j)
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(j).toString(), parcel);
            else
                return new DetectedActivity(l, k, i);
        while(true);
    }

    public volatile Object createFromParcel(Parcel parcel)
    {
        return createFromParcel(parcel);
    }

    public DetectedActivity[] newArray(int i)
    {
        return new DetectedActivity[i];
    }

    public volatile Object[] newArray(int i)
    {
        return newArray(i);
    }

    public static final int CONTENT_DESCRIPTION;
}
