// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.tagmanager;

import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.io.*;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

// Referenced classes of package com.google.android.gms.tagmanager:
//            bl, bh

class av
    implements bl
{

    av()
    {
    }

    private InputStream a(HttpClient httpclient, HttpResponse httpresponse)
        throws IOException
    {
        int i = httpresponse.getStatusLine().getStatusCode();
        if(i == 200)
        {
            bh.v("Success response");
            return httpresponse.getEntity().getContent();
        }
        String s = (new StringBuilder()).append("Bad response: ").append(i).toString();
        if(i == 404)
            throw new FileNotFoundException(s);
        else
            throw new IOException(s);
    }

    private void a(HttpClient httpclient)
    {
        if(httpclient != null && httpclient.getConnectionManager() != null)
            httpclient.getConnectionManager().shutdown();
    }

    public InputStream bo(String s)
        throws IOException
    {
        Vi = jg();
        HttpClient httpclient = Vi;
        HttpGet httpget = new HttpGet(s);
        HttpResponse httpresponse;
        if(!(httpclient instanceof HttpClient))
            httpresponse = httpclient.execute(httpget);
        else
            httpresponse = HttpInstrumentation.execute((HttpClient)httpclient, httpget);
        return a(Vi, httpresponse);
    }

    public void close()
    {
        a(Vi);
    }

    HttpClient jg()
    {
        BasicHttpParams basichttpparams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basichttpparams, 20000);
        HttpConnectionParams.setSoTimeout(basichttpparams, 20000);
        return new DefaultHttpClient(basichttpparams);
    }

    private HttpClient Vi;
}
