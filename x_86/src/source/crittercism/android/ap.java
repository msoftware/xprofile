// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package crittercism.android;

import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.message.BasicLineParser;
import org.apache.http.message.ParserCursor;
import org.apache.http.util.CharArrayBuffer;

// Referenced classes of package crittercism.android:
//            af, al, ao, as

public final class ap extends af
{

    public ap(al al1)
    {
        super(al1);
        d = -1;
    }

    public final boolean a(CharArrayBuffer chararraybuffer)
    {
        ParserCursor parsercursor = new ParserCursor(0, chararraybuffer.length());
        try
        {
            StatusLine statusline = BasicLineParser.DEFAULT.parseStatusLine(chararraybuffer, parsercursor);
            d = statusline.getStatusCode();
            super.a.a(statusline.getStatusCode());
        }
        catch(ParseException parseexception)
        {
            return false;
        }
        return true;
    }

    public final af b()
    {
        return new ao(this, d);
    }

    public final af c()
    {
        return as.d;
    }

    protected final int d()
    {
        return 20;
    }

    protected final int e()
    {
        return 64;
    }

    private int d;
}
