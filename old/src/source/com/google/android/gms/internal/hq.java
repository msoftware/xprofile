// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

// Referenced classes of package com.google.android.gms.internal:
//            hp

public class hq
    implements android.os.Parcelable.Creator
{

    public hq()
    {
    }

    static void a(hp hp1, Parcel parcel, int i)
    {
        int j = b.p(parcel);
        b.c(parcel, 1, hp1.wj);
        b.a(parcel, 2, hp1.gt(), false);
        b.a(parcel, 3, hp1.getTag(), false);
        b.D(parcel, j);
    }

    public hp ax(Parcel parcel)
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
                return new hp(j, s1, s);
        while(true);
    }

    public hp[] br(int i)
    {
        return new hp[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return ax(parcel);
    }

    public Object[] newArray(int i)
    {
        return br(i);
    }
}
