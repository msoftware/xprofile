// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.HashSet;
import java.util.Set;

public class iy
    implements android.os.Parcelable.Creator
{

    public iy()
    {
    }

    static void a(ir.d d, Parcel parcel, int i)
    {
        int j = b.p(parcel);
        Set set = d.hB();
        if(set.contains(Integer.valueOf(1)))
            b.c(parcel, 1, d.getVersionCode());
        if(set.contains(Integer.valueOf(2)))
            b.a(parcel, 2, d.getFamilyName(), true);
        if(set.contains(Integer.valueOf(3)))
            b.a(parcel, 3, d.getFormatted(), true);
        if(set.contains(Integer.valueOf(4)))
            b.a(parcel, 4, d.getGivenName(), true);
        if(set.contains(Integer.valueOf(5)))
            b.a(parcel, 5, d.getHonorificPrefix(), true);
        if(set.contains(Integer.valueOf(6)))
            b.a(parcel, 6, d.getHonorificSuffix(), true);
        if(set.contains(Integer.valueOf(7)))
            b.a(parcel, 7, d.getMiddleName(), true);
        b.D(parcel, j);
    }

    public ir.d aO(Parcel parcel)
    {
        String s = null;
        int i = com.google.android.gms.common.internal.safeparcel.a.o(parcel);
        HashSet hashset = new HashSet();
        int j = 0;
        String s1 = null;
        String s2 = null;
        String s3 = null;
        String s4 = null;
        String s5 = null;
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
                    hashset.add(Integer.valueOf(1));
                    break;

                case 2: // '\002'
                    s5 = com.google.android.gms.common.internal.safeparcel.a.m(parcel, k);
                    hashset.add(Integer.valueOf(2));
                    break;

                case 3: // '\003'
                    s4 = com.google.android.gms.common.internal.safeparcel.a.m(parcel, k);
                    hashset.add(Integer.valueOf(3));
                    break;

                case 4: // '\004'
                    s3 = com.google.android.gms.common.internal.safeparcel.a.m(parcel, k);
                    hashset.add(Integer.valueOf(4));
                    break;

                case 5: // '\005'
                    s2 = com.google.android.gms.common.internal.safeparcel.a.m(parcel, k);
                    hashset.add(Integer.valueOf(5));
                    break;

                case 6: // '\006'
                    s1 = com.google.android.gms.common.internal.safeparcel.a.m(parcel, k);
                    hashset.add(Integer.valueOf(6));
                    break;

                case 7: // '\007'
                    s = com.google.android.gms.common.internal.safeparcel.a.m(parcel, k);
                    hashset.add(Integer.valueOf(7));
                    break;
                }
            } else
            {
                if(parcel.dataPosition() != i)
                    throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(i).toString(), parcel);
                return new ir.d(hashset, j, s5, s4, s3, s2, s1, s);
            }
        while(true);
    }

    public ir.d[] bL(int i)
    {
        return new ir.d[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return aO(parcel);
    }

    public Object[] newArray(int i)
    {
        return bL(i);
    }
}
