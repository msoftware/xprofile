// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.HashSet;
import java.util.Set;

public class iz
    implements android.os.Parcelable.Creator
{

    public iz()
    {
    }

    static void a(ir.f f, Parcel parcel, int i)
    {
        int j = b.p(parcel);
        Set set = f.hB();
        if(set.contains(Integer.valueOf(1)))
            b.c(parcel, 1, f.getVersionCode());
        if(set.contains(Integer.valueOf(2)))
            b.a(parcel, 2, f.getDepartment(), true);
        if(set.contains(Integer.valueOf(3)))
            b.a(parcel, 3, f.getDescription(), true);
        if(set.contains(Integer.valueOf(4)))
            b.a(parcel, 4, f.getEndDate(), true);
        if(set.contains(Integer.valueOf(5)))
            b.a(parcel, 5, f.getLocation(), true);
        if(set.contains(Integer.valueOf(6)))
            b.a(parcel, 6, f.getName(), true);
        if(set.contains(Integer.valueOf(7)))
            b.a(parcel, 7, f.isPrimary());
        if(set.contains(Integer.valueOf(8)))
            b.a(parcel, 8, f.getStartDate(), true);
        if(set.contains(Integer.valueOf(9)))
            b.a(parcel, 9, f.getTitle(), true);
        if(set.contains(Integer.valueOf(10)))
            b.c(parcel, 10, f.getType());
        b.D(parcel, j);
    }

    public ir.f aP(Parcel parcel)
    {
        int i = 0;
        String s = null;
        int j = com.google.android.gms.common.internal.safeparcel.a.o(parcel);
        HashSet hashset = new HashSet();
        String s1 = null;
        boolean flag = false;
        String s2 = null;
        String s3 = null;
        String s4 = null;
        String s5 = null;
        String s6 = null;
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
                    k = com.google.android.gms.common.internal.safeparcel.a.g(parcel, l);
                    hashset.add(Integer.valueOf(1));
                    break;

                case 2: // '\002'
                    s6 = com.google.android.gms.common.internal.safeparcel.a.m(parcel, l);
                    hashset.add(Integer.valueOf(2));
                    break;

                case 3: // '\003'
                    s5 = com.google.android.gms.common.internal.safeparcel.a.m(parcel, l);
                    hashset.add(Integer.valueOf(3));
                    break;

                case 4: // '\004'
                    s4 = com.google.android.gms.common.internal.safeparcel.a.m(parcel, l);
                    hashset.add(Integer.valueOf(4));
                    break;

                case 5: // '\005'
                    s3 = com.google.android.gms.common.internal.safeparcel.a.m(parcel, l);
                    hashset.add(Integer.valueOf(5));
                    break;

                case 6: // '\006'
                    s2 = com.google.android.gms.common.internal.safeparcel.a.m(parcel, l);
                    hashset.add(Integer.valueOf(6));
                    break;

                case 7: // '\007'
                    flag = com.google.android.gms.common.internal.safeparcel.a.c(parcel, l);
                    hashset.add(Integer.valueOf(7));
                    break;

                case 8: // '\b'
                    s1 = com.google.android.gms.common.internal.safeparcel.a.m(parcel, l);
                    hashset.add(Integer.valueOf(8));
                    break;

                case 9: // '\t'
                    s = com.google.android.gms.common.internal.safeparcel.a.m(parcel, l);
                    hashset.add(Integer.valueOf(9));
                    break;

                case 10: // '\n'
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, l);
                    hashset.add(Integer.valueOf(10));
                    break;
                }
            } else
            {
                if(parcel.dataPosition() != j)
                    throw new com.google.android.gms.common.internal.safeparcel.a.a((new StringBuilder()).append("Overread allowed size end=").append(j).toString(), parcel);
                return new ir.f(hashset, k, s6, s5, s4, s3, s2, flag, s1, s, i);
            }
        while(true);
    }

    public ir.f[] bM(int i)
    {
        return new ir.f[i];
    }

    public Object createFromParcel(Parcel parcel)
    {
        return aP(parcel);
    }

    public Object[] newArray(int i)
    {
        return bM(i);
    }
}
