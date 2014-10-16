// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.facebook.android;

import android.content.Context;
import android.os.Bundle;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

// Referenced classes of package com.facebook.android:
//            Facebook, FacebookError

public class AsyncFacebookRunner
{
    public static interface RequestListener
    {

        public abstract void onComplete(String s, Object obj);

        public abstract void onFacebookError(FacebookError facebookerror, Object obj);

        public abstract void onFileNotFoundException(FileNotFoundException filenotfoundexception, Object obj);

        public abstract void onIOException(IOException ioexception, Object obj);

        public abstract void onMalformedURLException(MalformedURLException malformedurlexception, Object obj);
    }


    public AsyncFacebookRunner(Facebook facebook)
    {
        fb = facebook;
    }

    public void logout(Context context, RequestListener requestlistener)
    {
        logout(context, requestlistener, null);
    }

    public void logout(final Context context, final RequestListener listener, final Object state)
    {
        (new Thread() {

            public void run()
            {
                String s;
                s = fb.logoutImpl(context);
                if(s.length() == 0 || s.equals("false"))
                {
                    listener.onFacebookError(new FacebookError("auth.expireSession failed"), state);
                    return;
                }
                try
                {
                    listener.onComplete(s, state);
                    return;
                }
                catch(FileNotFoundException filenotfoundexception)
                {
                    listener.onFileNotFoundException(filenotfoundexception, state);
                    return;
                }
                catch(MalformedURLException malformedurlexception)
                {
                    listener.onMalformedURLException(malformedurlexception, state);
                    return;
                }
                catch(IOException ioexception)
                {
                    listener.onIOException(ioexception, state);
                }
                return;
            }

            final AsyncFacebookRunner this$0;
            final Context val$context;
            final RequestListener val$listener;
            final Object val$state;

            
            {
                this$0 = AsyncFacebookRunner.this;
                context = context1;
                listener = requestlistener;
                state = obj;
                super();
            }
        }
).start();
    }

    public void request(Bundle bundle, RequestListener requestlistener)
    {
        request(null, bundle, "GET", requestlistener, null);
    }

    public void request(Bundle bundle, RequestListener requestlistener, Object obj)
    {
        request(null, bundle, "GET", requestlistener, obj);
    }

    public void request(String s, Bundle bundle, RequestListener requestlistener)
    {
        request(s, bundle, "GET", requestlistener, null);
    }

    public void request(String s, Bundle bundle, RequestListener requestlistener, Object obj)
    {
        request(s, bundle, "GET", requestlistener, obj);
    }

    public void request(final String graphPath, final Bundle parameters, final String httpMethod, final RequestListener listener, final Object state)
    {
        (new Thread() {

            public void run()
            {
                try
                {
                    String s = fb.requestImpl(graphPath, parameters, httpMethod);
                    listener.onComplete(s, state);
                    return;
                }
                catch(FileNotFoundException filenotfoundexception)
                {
                    listener.onFileNotFoundException(filenotfoundexception, state);
                    return;
                }
                catch(MalformedURLException malformedurlexception)
                {
                    listener.onMalformedURLException(malformedurlexception, state);
                    return;
                }
                catch(IOException ioexception)
                {
                    listener.onIOException(ioexception, state);
                }
            }

            final AsyncFacebookRunner this$0;
            final String val$graphPath;
            final String val$httpMethod;
            final RequestListener val$listener;
            final Bundle val$parameters;
            final Object val$state;

            
            {
                this$0 = AsyncFacebookRunner.this;
                graphPath = s;
                parameters = bundle;
                httpMethod = s1;
                listener = requestlistener;
                state = obj;
                super();
            }
        }
).start();
    }

    public void request(String s, RequestListener requestlistener)
    {
        request(s, new Bundle(), "GET", requestlistener, null);
    }

    public void request(String s, RequestListener requestlistener, Object obj)
    {
        request(s, new Bundle(), "GET", requestlistener, obj);
    }

    Facebook fb;
}
