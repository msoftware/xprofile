// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.events;

import com.roadtrippers.api.models.Trip;

public class TripUpdatedEvent
{

    public TripUpdatedEvent(Trip trip1)
    {
        trip = trip1;
    }

    public TripUpdatedEvent(Throwable throwable1)
    {
        throwable = throwable1;
    }

    public Throwable throwable;
    public Trip trip;
}
