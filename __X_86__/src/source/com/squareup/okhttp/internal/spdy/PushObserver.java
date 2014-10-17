// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.spdy;

import com.squareup.okhttp.internal.okio.BufferedSource;
import java.io.IOException;
import java.util.List;

// Referenced classes of package com.squareup.okhttp.internal.spdy:
//            ErrorCode

public interface PushObserver
{

    public abstract boolean onData(int i, BufferedSource bufferedsource, int j, boolean flag)
        throws IOException;

    public abstract boolean onHeaders(int i, List list, boolean flag);

    public abstract boolean onRequest(int i, List list);

    public abstract void onReset(int i, ErrorCode errorcode);

    public static final PushObserver CANCEL = new PushObserver() {

        public boolean onData(int i, BufferedSource bufferedsource, int j, boolean flag)
            throws IOException
        {
            bufferedsource.skip(j);
            return true;
        }

        public boolean onHeaders(int i, List list, boolean flag)
        {
            return true;
        }

        public boolean onRequest(int i, List list)
        {
            return true;
        }

        public void onReset(int i, ErrorCode errorcode)
        {
        }

    }
;

}
