// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.core.sym;


// Referenced classes of package com.fasterxml.jackson.core.sym:
//            Name

public final class NameN extends Name
{

    NameN(String s, int i, int ai[], int j)
    {
        super(s, i);
        if(j < 3)
        {
            throw new IllegalArgumentException("Qlen must >= 3");
        } else
        {
            mQuads = ai;
            mQuadLen = j;
            return;
        }
    }

    public boolean equals(int i)
    {
        return false;
    }

    public boolean equals(int i, int j)
    {
        return false;
    }

    public boolean equals(int ai[], int i)
    {
        if(i == mQuadLen) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        int j = 0;
label0:
        do
        {
label1:
            {
                if(j >= i)
                    break label1;
                if(ai[j] != mQuads[j])
                    break label0;
                j++;
            }
        } while(true);
        if(true) goto _L1; else goto _L3
_L3:
        return true;
    }

    final int mQuadLen;
    final int mQuads[];
}
