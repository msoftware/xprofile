// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.nineoldandroids.animation;


// Referenced classes of package com.nineoldandroids.animation:
//            TypeEvaluator

public class IntEvaluator
    implements TypeEvaluator
{

    public IntEvaluator()
    {
    }

    public Integer evaluate(float f, Integer integer, Integer integer1)
    {
        int i = integer.intValue();
        return Integer.valueOf((int)((float)i + f * (float)(integer1.intValue() - i)));
    }

    public volatile Object evaluate(float f, Object obj, Object obj1)
    {
        return evaluate(f, (Integer)obj, (Integer)obj1);
    }
}
