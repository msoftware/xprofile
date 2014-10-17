// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.com.google.gson.internal;

import java.io.ObjectStreamException;
import java.math.BigDecimal;

public final class LazilyParsedNumber extends Number
{

    public LazilyParsedNumber(String s)
    {
        value = s;
    }

    private Object writeReplace()
        throws ObjectStreamException
    {
        return new BigDecimal(value);
    }

    public double doubleValue()
    {
        return Double.parseDouble(value);
    }

    public float floatValue()
    {
        return Float.parseFloat(value);
    }

    public int intValue()
    {
        int i;
        try
        {
            i = Integer.parseInt(value);
        }
        catch(NumberFormatException numberformatexception)
        {
            long l;
            try
            {
                l = Long.parseLong(value);
            }
            catch(NumberFormatException numberformatexception1)
            {
                return (new BigDecimal(value)).intValue();
            }
            return (int)l;
        }
        return i;
    }

    public long longValue()
    {
        long l;
        try
        {
            l = Long.parseLong(value);
        }
        catch(NumberFormatException numberformatexception)
        {
            return (new BigDecimal(value)).longValue();
        }
        return l;
    }

    public String toString()
    {
        return value;
    }

    private final String value;
}
