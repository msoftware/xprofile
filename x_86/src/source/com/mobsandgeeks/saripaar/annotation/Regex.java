// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.mobsandgeeks.saripaar.annotation;

import java.lang.annotation.Annotation;

public interface Regex
    extends Annotation
{

    public abstract String message();

    public abstract int messageResId();

    public abstract int order();

    public abstract String pattern();

    public abstract int patternResId();

    public abstract boolean trim();
}
