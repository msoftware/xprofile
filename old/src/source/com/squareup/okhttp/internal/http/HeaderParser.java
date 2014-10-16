// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.http;


public final class HeaderParser
{

    private HeaderParser()
    {
    }

    public static int parseSeconds(String s)
    {
        long l;
        try
        {
            l = Long.parseLong(s);
        }
        catch(NumberFormatException numberformatexception)
        {
            return -1;
        }
        if(l > 0x7fffffffL)
            return 0x7fffffff;
        if(l < 0L)
            return 0;
        else
            return (int)l;
    }

    public static int skipUntil(String s, int i, String s1)
    {
        do
        {
            if(i >= s.length() || s1.indexOf(s.charAt(i)) != -1)
                return i;
            i++;
        } while(true);
    }

    public static int skipWhitespace(String s, int i)
    {
        do
        {
label0:
            {
                if(i < s.length())
                {
                    char c = s.charAt(i);
                    if(c == ' ' || c == '\t')
                        break label0;
                }
                return i;
            }
            i++;
        } while(true);
    }
}
