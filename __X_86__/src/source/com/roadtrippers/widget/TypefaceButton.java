// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import com.ctrlplusz.anytextview.Util;

public class TypefaceButton extends Button
{

    public TypefaceButton(Context context)
    {
        super(context);
    }

    public TypefaceButton(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        if(!isInEditMode())
            Util.setTypeface(attributeset, this);
    }

    public TypefaceButton(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        if(!isInEditMode())
            Util.setTypeface(attributeset, this);
    }
}
