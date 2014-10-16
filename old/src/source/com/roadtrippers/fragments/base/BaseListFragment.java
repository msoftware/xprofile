// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.fragments.base;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import butterknife.ButterKnife;
import com.roadtrippers.RoadTrippersApp;
import com.squareup.otto.Bus;
import dagger.Lazy;

public class BaseListFragment extends ListFragment
{

    public BaseListFragment()
    {
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        RoadTrippersApp.from(getActivity()).inject(this);
    }

    public void onDestroyView()
    {
        ButterKnife.reset(this);
        super.onDestroyView();
    }

    public void onPause()
    {
        super.onPause();
        ((Bus)bus.get()).unregister(this);
    }

    public void onResume()
    {
        super.onResume();
        ((Bus)bus.get()).register(this);
    }

    public void onViewCreated(View view, Bundle bundle)
    {
        super.onViewCreated(view, bundle);
        ButterKnife.inject(this, view);
    }

    Lazy bus;
}
