// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.api.v2;


public interface TraceMachineInterface
{

    public abstract long getCurrentThreadId();

    public abstract String getCurrentThreadName();

    public abstract boolean isUIThread();
}