// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package crittercism.android;

import org.apache.http.util.CharArrayBuffer;

// Referenced classes of package crittercism.android:
//            af, al, ar

public final class aq extends af
{

    public aq(af af1)
    {
        super(af1);
        d = false;
    }

    public final boolean a(CharArrayBuffer chararraybuffer)
    {
        int i = chararraybuffer.substringTrimmed(0, chararraybuffer.length()).length();
        boolean flag = false;
        if(i == 0)
            flag = true;
        d = flag;
        return true;
    }

    public final af b()
    {
        if(d)
        {
            super.a.b(a());
            return super.a.b();
        } else
        {
            super.b.clear();
            return this;
        }
    }

    public final af c()
    {
        super.b.clear();
        return new ar(this);
    }

    protected final int d()
    {
        return 8;
    }

    protected final int e()
    {
        return 128;
    }

    private boolean d;
}
