// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package crittercism.android;

import org.apache.http.util.CharArrayBuffer;

// Referenced classes of package crittercism.android:
//            af, al, as

public final class aj extends af
{

    public aj(af af1)
    {
        super(af1);
    }

    public final boolean a(int i)
    {
        if(i == -1)
        {
            super.a.b(a());
            super.a.a(as.d);
            return true;
        } else
        {
            c = 1 + c;
            return false;
        }
    }

    public final boolean a(CharArrayBuffer chararraybuffer)
    {
        return true;
    }

    public final int b(byte abyte0[], int i, int j)
    {
        if(j == -1)
        {
            super.a.b(a());
            super.a.a(as.d);
            return -1;
        } else
        {
            c = j + c;
            return j;
        }
    }

    public final af b()
    {
        return as.d;
    }

    public final af c()
    {
        return as.d;
    }

    protected final int d()
    {
        return 0;
    }

    protected final int e()
    {
        return 0x7fffffff;
    }

    public final void f()
    {
        super.a.b(a());
        super.a.a(as.d);
    }
}
