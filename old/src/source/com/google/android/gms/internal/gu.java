// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;


public final class gu
{

    public static String aW(int i)
    {
        switch(i)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown time span ").append(i).toString());

        case 0: // '\0'
            return "DAILY";

        case 1: // '\001'
            return "WEEKLY";

        case 2: // '\002'
            return "ALL_TIME";
        }
    }
}
