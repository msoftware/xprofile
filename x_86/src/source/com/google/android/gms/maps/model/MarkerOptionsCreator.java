// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.maps.model:
//            MarkerOptions, LatLng

public class MarkerOptionsCreator
    implements android.os.Parcelable.Creator
{

    public MarkerOptionsCreator()
    {
    }

    static void a(MarkerOptions markeroptions, Parcel parcel, int i)
    {
        int j = b.p(parcel);
        b.c(parcel, 1, markeroptions.getVersionCode());
        b.a(parcel, 2, markeroptions.getPosition(), i, false);
        b.a(parcel, 3, markeroptions.getTitle(), false);
        b.a(parcel, 4, markeroptions.getSnippet(), false);
        b.a(parcel, 5, markeroptions.hf(), false);
        b.a(parcel, 6, markeroptions.getAnchorU());
        b.a(parcel, 7, markeroptions.getAnchorV());
        b.a(parcel, 8, markeroptions.isDraggable());
        b.a(parcel, 9, markeroptions.isVisible());
        b.a(parcel, 10, markeroptions.isFlat());
        b.a(parcel, 11, markeroptions.getRotation());
        b.a(parcel, 12, markeroptions.getInfoWindowAnchorU());
        b.a(parcel, 13, markeroptions.getInfoWindowAnchorV());
        b.a(parcel, 14, markeroptions.getAlpha());
        b.D(parcel, j);
    }

    public MarkerOptions createFromParcel(Parcel parcel)
    {
        int i = com.google.android.gms.common.internal.safeparcel.a.o(parcel);
        int j = 0;
        LatLng latlng = null;
        String s = null;
        String s1 = null;
        android.os.IBinder ibinder = null;
        float f = 0.0F;
        float f1 = 0.0F;
        boolean flag = false;
        boolean flag1 = false;
        boolean flag2 = false;
        float f2 = 0.0F;
        float f3 = 0.5F;
        float f4 = 0.0F;
        float f5 = 1.0F;
        do
            if(parcel.dataPosition() < i)
            {
                int k = com.google.android.gms.common.internal.safeparcel.a.n(parcel);
                switch(com.google.android.gms.common.internal.safeparcel.a.S(k))
                {
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, k);
                    break;

                case 1: // '\001'
                    j = com.google.android.gms.common.internal.safeparcel.a.g(parcel, k);
                    break;

                case 2: // '\002'
                    latlng = (LatLng)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, LatLng.CREATOR);
                    break;

                case 3: // '\003'
                    s = com.google.android.gms.common.internal.safeparcel.a.m(parcel, k);
                    break;

                case 4: // '\004'
                    s1 = com.google.android.gms.common.internal.safeparcel.a.m(parcel, k);
                    break;

                case 5: // '\005'
                    ibinder = com.google.android.gms.common.internal.safeparcel.a.n(parcel, k);
                    break;

                case 6: // '\006'
                    f = com.google.android.gms.common.internal.safeparcel.a.j(parcel, k);
                    break;

                case 7: // '\007'
                    f1 = com.google.android.gms.common.internal.safeparcel.a.j(parcel, k);
                    break;

                case 8: // '\b'
                    flag = com.google.android.gms.common.internal.safeparcel.a.c(parcel, k);
                    break;

                case 9: // '\t'
                    flag1 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, k);
                    break;

                case 10: // '\n'
                    flag2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, k);
                    break;

                case 11: // '\013'
                    f2 = com.google.android.gms.common.internal.safeparcel.a.j(parcel, k);
                    break;

                case 12: // '\f'
                    f3 = com.google.android.gms.common.internal.safeparcel.a.j(parcel, k);
                    break;

                case 13: // '\r'
                    f4 = com.google.android.gms.common.internal.safeparcel.a.j(parcel, k);
                    break;

                case 14: // '\016'
                    f5 = com.google.android.gms.common.internal.safeparcel.a.j(parcel, k);
                    break;
                }
            } else
            if(parcel.dataPosition() != i)
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
            else
                return new MarkerOptions(j, latlng, s, s1, ibinder, f, f1, flag, flag1, flag2, f2, f3, f4, f5);
        while(true);
    }

    public volatile Object createFromParcel(Parcel parcel)
    {
        return createFromParcel(parcel);
    }

    public MarkerOptions[] newArray(int i)
    {
        return new MarkerOptions[i];
    }

    public volatile Object[] newArray(int i)
    {
        return newArray(i);
    }

    public static final int CONTENT_DESCRIPTION;
}
