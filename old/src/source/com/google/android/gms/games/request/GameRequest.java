// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.games.request;

import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.Player;
import java.util.ArrayList;

public interface GameRequest
    extends Parcelable, Freezable
{

    public abstract ArrayList fU();

    public abstract long getCreationTimestamp();

    public abstract byte[] getData();

    public abstract long getExpirationTimestamp();

    public abstract Game getGame();

    public abstract Player getRecipient();

    public abstract int getRecipientStatus();

    public abstract int getRecipientStatus(String s);

    public abstract String getRequestId();

    public abstract Player getSender();

    public abstract int getType();

    public abstract boolean isConsumed();

    public static final int RECIPIENT_STATUS_ACCEPTED = 1;
    public static final int RECIPIENT_STATUS_PENDING = 0;
    public static final int TYPE_ALL = 65535;
    public static final int TYPE_GIFT = 1;
    public static final int TYPE_WISH = 2;
}
