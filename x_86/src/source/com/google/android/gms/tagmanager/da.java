// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.tagmanager;

import java.util.*;

// Referenced classes of package com.google.android.gms.tagmanager:
//            k

class da
    implements k
{

    da(int i, l.a a)
    {
        Xt = i;
        Xu = a;
    }

    public void e(Object obj, Object obj1)
    {
        this;
        JVM INSTR monitorenter ;
        if(obj != null && obj1 != null)
            break MISSING_BLOCK_LABEL_25;
        throw new NullPointerException("key == null || value == null");
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        Xv = Xv + Xu.sizeOf(obj, obj1);
        if(Xv > Xt)
        {
            Iterator iterator = Xs.entrySet().iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                Xv = Xv - Xu.sizeOf(entry.getKey(), entry.getValue());
                iterator.remove();
            } while(Xv > Xt);
        }
        Xs.put(obj, obj1);
        this;
        JVM INSTR monitorexit ;
    }

    public Object get(Object obj)
    {
        this;
        JVM INSTR monitorenter ;
        Object obj1 = Xs.get(obj);
        this;
        JVM INSTR monitorexit ;
        return obj1;
        Exception exception;
        exception;
        throw exception;
    }

    private final Map Xs = new HashMap();
    private final int Xt;
    private final l.a Xu;
    private int Xv;
}
