// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.internal:
//            jt

public class ju
    implements android.os.Parcelable.Creator
{

    public ju()
    {
    }

    static void a(jt jt1, Parcel parcel, int i)
    {
        int j = b.p(parcel);
        b.c(parcel, 1, jt1.getVersionCode());
        b.a(parcel, 2, jt1.ZN, false);
        b.a(parcel, 3, jt1.description, false);
        b.D(parcel, j);
    }

    public jt bm(Parcel parcel)
    {
        String s = null;
        int i = com.google.android.gms.common.internal.safeparcel.a.o(parcel);
        int j = 0;
        String s1 = null;
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
                    s1 = com.google.android.gms.common.internal.safeparcel.a.m(parcel, k);
                    break;

                case 3: // '\003'
                    s = com.google.android.gms.common.internal.safeparcel.a.m(parcel, k);
                    break;
                }
            } else
            if(parcel.dataPosition() != i)
                throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
            else
                return new jt(j, s1, s);
        while(true);
    }

    public Object createFromParcel(Parcel parcel)
    {
        return bm(parcel);
    }

    public jt[] cs(int i)
    {
        return new jt[i];
    }

    public Object[] newArray(int i)
    {
        return cs(i);
    }
}
