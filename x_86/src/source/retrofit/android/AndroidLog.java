// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package retrofit.android;

import android.util.Log;

public class AndroidLog
    implements retrofit.RestAdapter.Log
{

    public AndroidLog(String s)
    {
        tag = s;
    }

    public String getTag()
    {
        return tag;
    }

    public final void log(String s)
    {
        int i = 0;
        for(int j = s.length(); i < j; i += 4000)
            logChunk(s.substring(i, Math.min(j, i + 4000)));

    }

    public void logChunk(String s)
    {
        Log.d(getTag(), s);
    }

    private static final int LOG_CHUNK_SIZE = 4000;
    private final String tag;
}
