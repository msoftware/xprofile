// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;

// Referenced classes of package com.google.android.gms.drive.internal:
//            al

public class TrashResourceRequest
    implements SafeParcelable
{

    TrashResourceRequest(int i, DriveId driveid)
    {
        wj = i;
        Do = driveid;
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        al.a(this, parcel, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new al();
    final DriveId Do;
    final int wj;

}
