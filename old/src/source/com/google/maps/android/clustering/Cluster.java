// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.maps.android.clustering;

import com.google.android.gms.maps.model.LatLng;
import java.util.Collection;

public interface Cluster
{

    public abstract Collection getItems();

    public abstract LatLng getPosition();

    public abstract int getSize();
}
