// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.maps.model:
//            GroundOverlayOptions, LatLng, LatLngBounds

public class GroundOverlayOptionsCreator
    implements android.os.Parcelable.Creator
{

    public GroundOverlayOptionsCreator()
    {
    }

    static void a(GroundOverlayOptions groundoverlayoptions, Parcel parcel, int i)
    {
        int j = b.p(parcel);
        b.c(parcel, 1, groundoverlayoptions.getVersionCode());
        b.a(parcel, 2, groundoverlayoptions.he(), false);
        b.a(parcel, 3, groundoverlayoptions.getLocation(), i, false);
        b.a(parcel, 4, groundoverlayoptions.getWidth());
        b.a(parcel, 5, groundoverlayoptions.getHeight());
        b.a(parcel, 6, groundoverlayoptions.getBounds(), i, false);
        b.a(parcel, 7, groundoverlayoptions.getBearing());
        b.a(parcel, 8, groundoverlayoptions.getZIndex());
        b.a(parcel, 9, groundoverlayoptions.isVisible());
        b.a(parcel, 10, groundoverlayoptions.getTransparency());
        b.a(parcel, 11, groundoverlayoptions.getAnchorU());
        b.a(parcel, 12, groundoverlayoptions.getAnchorV());
        b.D(parcel, j);
    }

    public GroundOverlayOptions createFromParcel(Parcel parcel)
    {
        int i = com.google.android.gms.common.internal.safeparcel.a.o(parcel);
        int j = 0;
        android.os.IBinder ibinder = null;
        LatLng latlng = null;
        float f = 0.0F;
        float f1 = 0.0F;
        LatLngBounds latlngbounds = null;
        float f2 = 0.0F;
        float f3 = 0.0F;
        boolean flag = false;
        float f4 = 0.0F;
        float f5 = 0.0F;
        float f6 = 0.0F;
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
                    ibinder = com.google.android.gms.common.internal.safeparcel.a.n(parcel, k);
                    break;

                case 3: // '\003'
                    latlng = (LatLng)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, LatLng.CREATOR);
                    break;

                case 4: // '\004'
                    f = com.google.android.gms.common.internal.safeparcel.a.j(parcel, k);
                    break;

                case 5: // '\005'
                    f1 = com.google.android.gms.common.internal.safeparcel.a.j(parcel, k);
                    break;

                case 6: // '\006'
                    latlngbounds = (LatLngBounds)com.google.android.gms.common.internal.safeparcel.a.a(parcel, k, LatLngBounds.CREATOR);
                    break;

                case 7: // '\007'
                    f2 = com.google.android.gms.common.internal.safeparcel.a.j(parcel, k);
                    break;

                case 8: // '\b'
                    f3 = com.google.android.gms.common.internal.safeparcel.a.j(parcel, k);
                    break;

                case 9: // '\t'
                    flag = com.google.android.gms.common.internal.safeparcel.a.c(parcel, k);
                    break;

                case 10: // '\n'
                    f4 = com.google.android.gms.common.internal.safeparcel.a.j(parcel, k);
                    break;

                case 11: // '\013'
                    f5 = com.google.android.gms.common.internal.safeparcel.a.j(parcel, k);
                    break;

                case 12: // '\f'
                    f6 = com.google.android.gms.common.internal.safeparcel.a.j(parcel, k);
                    break;
                }
            } else
            if(parcel.dataPosition() != i)
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
            else
                return new GroundOverlayOptions(j, ibinder, latlng, f, f1, latlngbounds, f2, f3, flag, f4, f5, f6);
        while(true);
    }

    public volatile Object createFromParcel(Parcel parcel)
    {
        return createFromParcel(parcel);
    }

    public GroundOverlayOptions[] newArray(int i)
    {
        return new GroundOverlayOptions[i];
    }

    public volatile Object[] newArray(int i)
    {
        return newArray(i);
    }

    public static final int CONTENT_DESCRIPTION;
}
