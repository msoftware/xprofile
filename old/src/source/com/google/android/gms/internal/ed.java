// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import android.os.Looper;
import android.util.Log;

public final class ed
{

    public static void a(boolean flag, Object obj)
    {
        if(!flag)
            throw new IllegalStateException(String.valueOf(obj));
        else
            return;
    }

    public static void ac(String s)
    {
        if(Looper.getMainLooper().getThread() != Thread.currentThread())
        {
            Log.e("Asserts", (new StringBuilder()).append("checkMainThread: current thread ").append(Thread.currentThread()).append(" IS NOT the main thread ").append(Looper.getMainLooper().getThread()).append("!").toString());
            throw new IllegalStateException(s);
        } else
        {
            return;
        }
    }

    public static void ad(String s)
    {
        if(Looper.getMainLooper().getThread() == Thread.currentThread())
        {
            Log.e("Asserts", (new StringBuilder()).append("checkNotMainThread: current thread ").append(Thread.currentThread()).append(" IS the main thread ").append(Looper.getMainLooper().getThread()).append("!").toString());
            throw new IllegalStateException(s);
        } else
        {
            return;
        }
    }

    public static void d(Object obj)
    {
        if(obj == null)
            throw new IllegalArgumentException("null reference");
        else
            return;
    }

    public static void v(boolean flag)
    {
        if(!flag)
            throw new IllegalStateException();
        else
            return;
    }
}
