// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public final class OperatorOnErrorResumeNextViaFunction
    implements rx.Observable.Operator
{

    public OperatorOnErrorResumeNextViaFunction(Func1 func1)
    {
        resumeFunction = func1;
    }

    public volatile Object call(Object obj)
    {
        return call((Subscriber)obj);
    }

    public Subscriber call(final Subscriber final_subscriber)
    {
        return new Subscriber(final_subscriber) {

            public void onCompleted()
            {
                child.onCompleted();
            }

            public void onError(Throwable throwable)
            {
                try
                {
                    ((Observable)resumeFunction.call(throwable)).unsafeSubscribe(child);
                    return;
                }
                catch(Throwable throwable1)
                {
                    child.onError(throwable1);
                }
            }

            public void onNext(Object obj)
            {
                child.onNext(obj);
            }

            final OperatorOnErrorResumeNextViaFunction this$0;
            final Subscriber val$child;

            
            {
                this$0 = OperatorOnErrorResumeNextViaFunction.this;
                child = subscriber1;
                super(final_subscriber);
            }
        }
;
    }

    private final Func1 resumeFunction;

}
