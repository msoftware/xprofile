// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.flurry.sdk;

import android.content.Context;
import android.location.*;
import android.os.*;
import android.text.TextUtils;

// Referenced classes of package com.flurry.sdk:
//            eh, ei, ex, eg, 
//            en, em

public class en
    implements ei.a
{
    class a extends Binder
        implements en
    {
        private static class a
            implements en
        {

            public void a(em em1, int i1)
                throws RemoteException
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                if(em1 == null)
                    break MISSING_BLOCK_LABEL_68;
                IBinder ibinder = em1.asBinder();
_L1:
                parcel.writeStrongBinder(ibinder);
                parcel.writeInt(i1);
                ky.transact(4, parcel, parcel1, 0);
                parcel1.readException();
                parcel1.recycle();
                parcel.recycle();
                return;
                ibinder = null;
                  goto _L1
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
            }

            public void a(em em1, int i1, String s)
                throws RemoteException
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                if(em1 == null)
                    break MISSING_BLOCK_LABEL_80;
                IBinder ibinder = em1.asBinder();
_L1:
                parcel.writeStrongBinder(ibinder);
                parcel.writeInt(i1);
                parcel.writeString(s);
                ky.transact(3, parcel, parcel1, 0);
                parcel1.readException();
                parcel1.recycle();
                parcel.recycle();
                return;
                ibinder = null;
                  goto _L1
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
            }

            public void a(em em1, int i1, String s, Bundle bundle)
                throws RemoteException
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                if(em1 == null) goto _L2; else goto _L1
_L1:
                IBinder ibinder = em1.asBinder();
_L5:
                parcel.writeStrongBinder(ibinder);
                parcel.writeInt(i1);
                parcel.writeString(s);
                if(bundle == null) goto _L4; else goto _L3
_L3:
                parcel.writeInt(1);
                bundle.writeToParcel(parcel, 0);
_L6:
                ky.transact(2, parcel, parcel1, 0);
                parcel1.readException();
                parcel1.recycle();
                parcel.recycle();
                return;
_L2:
                ibinder = null;
                  goto _L5
_L4:
                parcel.writeInt(0);
                  goto _L6
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
                  goto _L5
            }

            public void a(em em1, int i1, String s, IBinder ibinder, Bundle bundle)
                throws RemoteException
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                if(em1 == null) goto _L2; else goto _L1
_L1:
                IBinder ibinder1 = em1.asBinder();
_L5:
                parcel.writeStrongBinder(ibinder1);
                parcel.writeInt(i1);
                parcel.writeString(s);
                parcel.writeStrongBinder(ibinder);
                if(bundle == null) goto _L4; else goto _L3
_L3:
                parcel.writeInt(1);
                bundle.writeToParcel(parcel, 0);
_L6:
                ky.transact(19, parcel, parcel1, 0);
                parcel1.readException();
                parcel1.recycle();
                parcel.recycle();
                return;
_L2:
                ibinder1 = null;
                  goto _L5
_L4:
                parcel.writeInt(0);
                  goto _L6
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
                  goto _L5
            }

            public void a(em em1, int i1, String s, String s1, String as[])
                throws RemoteException
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                if(em1 == null)
                    break MISSING_BLOCK_LABEL_95;
                IBinder ibinder = em1.asBinder();
_L1:
                parcel.writeStrongBinder(ibinder);
                parcel.writeInt(i1);
                parcel.writeString(s);
                parcel.writeString(s1);
                parcel.writeStringArray(as);
                ky.transact(10, parcel, parcel1, 0);
                parcel1.readException();
                parcel1.recycle();
                parcel.recycle();
                return;
                ibinder = null;
                  goto _L1
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
            }

            public void a(em em1, int i1, String s, String s1, String as[], String s2, Bundle bundle)
                throws RemoteException
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                if(em1 == null) goto _L2; else goto _L1
_L1:
                IBinder ibinder = em1.asBinder();
_L5:
                parcel.writeStrongBinder(ibinder);
                parcel.writeInt(i1);
                parcel.writeString(s);
                parcel.writeString(s1);
                parcel.writeStringArray(as);
                parcel.writeString(s2);
                if(bundle == null) goto _L4; else goto _L3
_L3:
                parcel.writeInt(1);
                bundle.writeToParcel(parcel, 0);
_L6:
                ky.transact(1, parcel, parcel1, 0);
                parcel1.readException();
                parcel1.recycle();
                parcel.recycle();
                return;
_L2:
                ibinder = null;
                  goto _L5
_L4:
                parcel.writeInt(0);
                  goto _L6
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
                  goto _L5
            }

            public void a(em em1, int i1, String s, String s1, String as[], String s2, IBinder ibinder, 
                    String s3, Bundle bundle)
                throws RemoteException
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                if(em1 == null) goto _L2; else goto _L1
_L1:
                IBinder ibinder1 = em1.asBinder();
_L5:
                parcel.writeStrongBinder(ibinder1);
                parcel.writeInt(i1);
                parcel.writeString(s);
                parcel.writeString(s1);
                parcel.writeStringArray(as);
                parcel.writeString(s2);
                parcel.writeStrongBinder(ibinder);
                parcel.writeString(s3);
                if(bundle == null) goto _L4; else goto _L3
_L3:
                parcel.writeInt(1);
                bundle.writeToParcel(parcel, 0);
_L6:
                ky.transact(9, parcel, parcel1, 0);
                parcel1.readException();
                parcel1.recycle();
                parcel.recycle();
                return;
_L2:
                ibinder1 = null;
                  goto _L5
_L4:
                parcel.writeInt(0);
                  goto _L6
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
                  goto _L5
            }

            public void a(em em1, int i1, String s, String as[], String s1, Bundle bundle)
                throws RemoteException
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                if(em1 == null) goto _L2; else goto _L1
_L1:
                IBinder ibinder = em1.asBinder();
_L5:
                parcel.writeStrongBinder(ibinder);
                parcel.writeInt(i1);
                parcel.writeString(s);
                parcel.writeStringArray(as);
                parcel.writeString(s1);
                if(bundle == null) goto _L4; else goto _L3
_L3:
                parcel.writeInt(1);
                bundle.writeToParcel(parcel, 0);
_L6:
                ky.transact(20, parcel, parcel1, 0);
                parcel1.readException();
                parcel1.recycle();
                parcel.recycle();
                return;
_L2:
                ibinder = null;
                  goto _L5
_L4:
                parcel.writeInt(0);
                  goto _L6
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
                  goto _L5
            }

            public IBinder asBinder()
            {
                return ky;
            }

            public void b(em em1, int i1, String s)
                throws RemoteException
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                if(em1 == null)
                    break MISSING_BLOCK_LABEL_81;
                IBinder ibinder = em1.asBinder();
_L1:
                parcel.writeStrongBinder(ibinder);
                parcel.writeInt(i1);
                parcel.writeString(s);
                ky.transact(21, parcel, parcel1, 0);
                parcel1.readException();
                parcel1.recycle();
                parcel.recycle();
                return;
                ibinder = null;
                  goto _L1
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
            }

            public void b(em em1, int i1, String s, Bundle bundle)
                throws RemoteException
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                if(em1 == null) goto _L2; else goto _L1
_L1:
                IBinder ibinder = em1.asBinder();
_L5:
                parcel.writeStrongBinder(ibinder);
                parcel.writeInt(i1);
                parcel.writeString(s);
                if(bundle == null) goto _L4; else goto _L3
_L3:
                parcel.writeInt(1);
                bundle.writeToParcel(parcel, 0);
_L6:
                ky.transact(5, parcel, parcel1, 0);
                parcel1.readException();
                parcel1.recycle();
                parcel.recycle();
                return;
_L2:
                ibinder = null;
                  goto _L5
_L4:
                parcel.writeInt(0);
                  goto _L6
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
                  goto _L5
            }

            public void c(em em1, int i1, String s)
                throws RemoteException
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                if(em1 == null)
                    break MISSING_BLOCK_LABEL_81;
                IBinder ibinder = em1.asBinder();
_L1:
                parcel.writeStrongBinder(ibinder);
                parcel.writeInt(i1);
                parcel.writeString(s);
                ky.transact(22, parcel, parcel1, 0);
                parcel1.readException();
                parcel1.recycle();
                parcel.recycle();
                return;
                ibinder = null;
                  goto _L1
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
            }

            public void c(em em1, int i1, String s, Bundle bundle)
                throws RemoteException
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                if(em1 == null) goto _L2; else goto _L1
_L1:
                IBinder ibinder = em1.asBinder();
_L5:
                parcel.writeStrongBinder(ibinder);
                parcel.writeInt(i1);
                parcel.writeString(s);
                if(bundle == null) goto _L4; else goto _L3
_L3:
                parcel.writeInt(1);
                bundle.writeToParcel(parcel, 0);
_L6:
                ky.transact(6, parcel, parcel1, 0);
                parcel1.readException();
                parcel1.recycle();
                parcel.recycle();
                return;
_L2:
                ibinder = null;
                  goto _L5
_L4:
                parcel.writeInt(0);
                  goto _L6
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
                  goto _L5
            }

            public void d(em em1, int i1, String s)
                throws RemoteException
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                if(em1 == null)
                    break MISSING_BLOCK_LABEL_81;
                IBinder ibinder = em1.asBinder();
_L1:
                parcel.writeStrongBinder(ibinder);
                parcel.writeInt(i1);
                parcel.writeString(s);
                ky.transact(24, parcel, parcel1, 0);
                parcel1.readException();
                parcel1.recycle();
                parcel.recycle();
                return;
                ibinder = null;
                  goto _L1
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
            }

            public void d(em em1, int i1, String s, Bundle bundle)
                throws RemoteException
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                if(em1 == null) goto _L2; else goto _L1
_L1:
                IBinder ibinder = em1.asBinder();
_L5:
                parcel.writeStrongBinder(ibinder);
                parcel.writeInt(i1);
                parcel.writeString(s);
                if(bundle == null) goto _L4; else goto _L3
_L3:
                parcel.writeInt(1);
                bundle.writeToParcel(parcel, 0);
_L6:
                ky.transact(7, parcel, parcel1, 0);
                parcel1.readException();
                parcel1.recycle();
                parcel.recycle();
                return;
_L2:
                ibinder = null;
                  goto _L5
_L4:
                parcel.writeInt(0);
                  goto _L6
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
                  goto _L5
            }

            public void e(em em1, int i1, String s, Bundle bundle)
                throws RemoteException
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                if(em1 == null) goto _L2; else goto _L1
_L1:
                IBinder ibinder = em1.asBinder();
_L5:
                parcel.writeStrongBinder(ibinder);
                parcel.writeInt(i1);
                parcel.writeString(s);
                if(bundle == null) goto _L4; else goto _L3
_L3:
                parcel.writeInt(1);
                bundle.writeToParcel(parcel, 0);
_L6:
                ky.transact(8, parcel, parcel1, 0);
                parcel1.readException();
                parcel1.recycle();
                parcel.recycle();
                return;
_L2:
                ibinder = null;
                  goto _L5
_L4:
                parcel.writeInt(0);
                  goto _L6
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
                  goto _L5
            }

            public void f(em em1, int i1, String s, Bundle bundle)
                throws RemoteException
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                if(em1 == null) goto _L2; else goto _L1
_L1:
                IBinder ibinder = em1.asBinder();
_L5:
                parcel.writeStrongBinder(ibinder);
                parcel.writeInt(i1);
                parcel.writeString(s);
                if(bundle == null) goto _L4; else goto _L3
_L3:
                parcel.writeInt(1);
                bundle.writeToParcel(parcel, 0);
_L6:
                ky.transact(11, parcel, parcel1, 0);
                parcel1.readException();
                parcel1.recycle();
                parcel.recycle();
                return;
_L2:
                ibinder = null;
                  goto _L5
_L4:
                parcel.writeInt(0);
                  goto _L6
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
                  goto _L5
            }

            public void g(em em1, int i1, String s, Bundle bundle)
                throws RemoteException
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                if(em1 == null) goto _L2; else goto _L1
_L1:
                IBinder ibinder = em1.asBinder();
_L5:
                parcel.writeStrongBinder(ibinder);
                parcel.writeInt(i1);
                parcel.writeString(s);
                if(bundle == null) goto _L4; else goto _L3
_L3:
                parcel.writeInt(1);
                bundle.writeToParcel(parcel, 0);
_L6:
                ky.transact(12, parcel, parcel1, 0);
                parcel1.readException();
                parcel1.recycle();
                parcel.recycle();
                return;
_L2:
                ibinder = null;
                  goto _L5
_L4:
                parcel.writeInt(0);
                  goto _L6
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
                  goto _L5
            }

            public void h(em em1, int i1, String s, Bundle bundle)
                throws RemoteException
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                if(em1 == null) goto _L2; else goto _L1
_L1:
                IBinder ibinder = em1.asBinder();
_L5:
                parcel.writeStrongBinder(ibinder);
                parcel.writeInt(i1);
                parcel.writeString(s);
                if(bundle == null) goto _L4; else goto _L3
_L3:
                parcel.writeInt(1);
                bundle.writeToParcel(parcel, 0);
_L6:
                ky.transact(13, parcel, parcel1, 0);
                parcel1.readException();
                parcel1.recycle();
                parcel.recycle();
                return;
_L2:
                ibinder = null;
                  goto _L5
_L4:
                parcel.writeInt(0);
                  goto _L6
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
                  goto _L5
            }

            public void i(em em1, int i1, String s, Bundle bundle)
                throws RemoteException
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                if(em1 == null) goto _L2; else goto _L1
_L1:
                IBinder ibinder = em1.asBinder();
_L5:
                parcel.writeStrongBinder(ibinder);
                parcel.writeInt(i1);
                parcel.writeString(s);
                if(bundle == null) goto _L4; else goto _L3
_L3:
                parcel.writeInt(1);
                bundle.writeToParcel(parcel, 0);
_L6:
                ky.transact(14, parcel, parcel1, 0);
                parcel1.readException();
                parcel1.recycle();
                parcel.recycle();
                return;
_L2:
                ibinder = null;
                  goto _L5
_L4:
                parcel.writeInt(0);
                  goto _L6
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
                  goto _L5
            }

            public void j(em em1, int i1, String s, Bundle bundle)
                throws RemoteException
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                if(em1 == null) goto _L2; else goto _L1
_L1:
                IBinder ibinder = em1.asBinder();
_L5:
                parcel.writeStrongBinder(ibinder);
                parcel.writeInt(i1);
                parcel.writeString(s);
                if(bundle == null) goto _L4; else goto _L3
_L3:
                parcel.writeInt(1);
                bundle.writeToParcel(parcel, 0);
_L6:
                ky.transact(15, parcel, parcel1, 0);
                parcel1.readException();
                parcel1.recycle();
                parcel.recycle();
                return;
_L2:
                ibinder = null;
                  goto _L5
_L4:
                parcel.writeInt(0);
                  goto _L6
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
                  goto _L5
            }

            public void k(em em1, int i1, String s, Bundle bundle)
                throws RemoteException
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                if(em1 == null) goto _L2; else goto _L1
_L1:
                IBinder ibinder = em1.asBinder();
_L5:
                parcel.writeStrongBinder(ibinder);
                parcel.writeInt(i1);
                parcel.writeString(s);
                if(bundle == null) goto _L4; else goto _L3
_L3:
                parcel.writeInt(1);
                bundle.writeToParcel(parcel, 0);
_L6:
                ky.transact(16, parcel, parcel1, 0);
                parcel1.readException();
                parcel1.recycle();
                parcel.recycle();
                return;
_L2:
                ibinder = null;
                  goto _L5
_L4:
                parcel.writeInt(0);
                  goto _L6
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
                  goto _L5
            }

            public void l(em em1, int i1, String s, Bundle bundle)
                throws RemoteException
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                if(em1 == null) goto _L2; else goto _L1
_L1:
                IBinder ibinder = em1.asBinder();
_L5:
                parcel.writeStrongBinder(ibinder);
                parcel.writeInt(i1);
                parcel.writeString(s);
                if(bundle == null) goto _L4; else goto _L3
_L3:
                parcel.writeInt(1);
                bundle.writeToParcel(parcel, 0);
_L6:
                ky.transact(17, parcel, parcel1, 0);
                parcel1.readException();
                parcel1.recycle();
                parcel.recycle();
                return;
_L2:
                ibinder = null;
                  goto _L5
_L4:
                parcel.writeInt(0);
                  goto _L6
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
                  goto _L5
            }

            public void m(em em1, int i1, String s, Bundle bundle)
                throws RemoteException
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                if(em1 == null) goto _L2; else goto _L1
_L1:
                IBinder ibinder = em1.asBinder();
_L5:
                parcel.writeStrongBinder(ibinder);
                parcel.writeInt(i1);
                parcel.writeString(s);
                if(bundle == null) goto _L4; else goto _L3
_L3:
                parcel.writeInt(1);
                bundle.writeToParcel(parcel, 0);
_L6:
                ky.transact(18, parcel, parcel1, 0);
                parcel1.readException();
                parcel1.recycle();
                parcel.recycle();
                return;
_L2:
                ibinder = null;
                  goto _L5
_L4:
                parcel.writeInt(0);
                  goto _L6
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
                  goto _L5
            }

            public void n(em em1, int i1, String s, Bundle bundle)
                throws RemoteException
            {
                Parcel parcel;
                Parcel parcel1;
                parcel = Parcel.obtain();
                parcel1 = Parcel.obtain();
                parcel.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                if(em1 == null) goto _L2; else goto _L1
_L1:
                IBinder ibinder = em1.asBinder();
_L5:
                parcel.writeStrongBinder(ibinder);
                parcel.writeInt(i1);
                parcel.writeString(s);
                if(bundle == null) goto _L4; else goto _L3
_L3:
                parcel.writeInt(1);
                bundle.writeToParcel(parcel, 0);
_L6:
                ky.transact(23, parcel, parcel1, 0);
                parcel1.readException();
                parcel1.recycle();
                parcel.recycle();
                return;
_L2:
                ibinder = null;
                  goto _L5
_L4:
                parcel.writeInt(0);
                  goto _L6
                Exception exception;
                exception;
                parcel1.recycle();
                parcel.recycle();
                throw exception;
                  goto _L5
            }

            private IBinder ky;

            a(IBinder ibinder)
            {
                ky = ibinder;
            }
        }


        public static en z(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            android.os.IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
            if(iinterface != null && (iinterface instanceof en))
                return (en)iinterface;
            else
                return new a(ibinder);
        }

        public boolean onTransact(int i1, Parcel parcel, Parcel parcel1, int j1)
            throws RemoteException
        {
            switch(i1)
            {
            default:
                return super.onTransact(i1, parcel, parcel1, j1);

            case 1598968902: 
                parcel1.writeString("com.google.android.gms.common.internal.IGmsServiceBroker");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                em em17 = com.google.android.gms.internal.em.a.y(parcel.readStrongBinder());
                int j9 = parcel.readInt();
                String s21 = parcel.readString();
                String s22 = parcel.readString();
                String as2[] = parcel.createStringArray();
                String s23 = parcel.readString();
                Bundle bundle17;
                if(parcel.readInt() != 0)
                    bundle17 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle17 = null;
                a(em17, j9, s21, s22, as2, s23, bundle17);
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                em em16 = com.google.android.gms.internal.em.a.y(parcel.readStrongBinder());
                int l8 = parcel.readInt();
                String s20 = parcel.readString();
                int i9 = parcel.readInt();
                Bundle bundle16 = null;
                if(i9 != 0)
                    bundle16 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                a(em16, l8, s20, bundle16);
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                a(com.google.android.gms.internal.em.a.y(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                a(com.google.android.gms.internal.em.a.y(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                em em15 = com.google.android.gms.internal.em.a.y(parcel.readStrongBinder());
                int j8 = parcel.readInt();
                String s19 = parcel.readString();
                int k8 = parcel.readInt();
                Bundle bundle15 = null;
                if(k8 != 0)
                    bundle15 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                b(em15, j8, s19, bundle15);
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                em em14 = com.google.android.gms.internal.em.a.y(parcel.readStrongBinder());
                int l7 = parcel.readInt();
                String s18 = parcel.readString();
                int i8 = parcel.readInt();
                Bundle bundle14 = null;
                if(i8 != 0)
                    bundle14 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                c(em14, l7, s18, bundle14);
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                em em13 = com.google.android.gms.internal.em.a.y(parcel.readStrongBinder());
                int j7 = parcel.readInt();
                String s17 = parcel.readString();
                int k7 = parcel.readInt();
                Bundle bundle13 = null;
                if(k7 != 0)
                    bundle13 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                d(em13, j7, s17, bundle13);
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                em em12 = com.google.android.gms.internal.em.a.y(parcel.readStrongBinder());
                int l6 = parcel.readInt();
                String s16 = parcel.readString();
                int i7 = parcel.readInt();
                Bundle bundle12 = null;
                if(i7 != 0)
                    bundle12 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                e(em12, l6, s16, bundle12);
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                em em11 = com.google.android.gms.internal.em.a.y(parcel.readStrongBinder());
                int k6 = parcel.readInt();
                String s12 = parcel.readString();
                String s13 = parcel.readString();
                String as1[] = parcel.createStringArray();
                String s14 = parcel.readString();
                IBinder ibinder1 = parcel.readStrongBinder();
                String s15 = parcel.readString();
                Bundle bundle11;
                if(parcel.readInt() != 0)
                    bundle11 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle11 = null;
                a(em11, k6, s12, s13, as1, s14, ibinder1, s15, bundle11);
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                a(com.google.android.gms.internal.em.a.y(parcel.readStrongBinder()), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                em em10 = com.google.android.gms.internal.em.a.y(parcel.readStrongBinder());
                int i6 = parcel.readInt();
                String s11 = parcel.readString();
                int j6 = parcel.readInt();
                Bundle bundle10 = null;
                if(j6 != 0)
                    bundle10 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                f(em10, i6, s11, bundle10);
                parcel1.writeNoException();
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                em em9 = com.google.android.gms.internal.em.a.y(parcel.readStrongBinder());
                int k5 = parcel.readInt();
                String s10 = parcel.readString();
                int l5 = parcel.readInt();
                Bundle bundle9 = null;
                if(l5 != 0)
                    bundle9 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                g(em9, k5, s10, bundle9);
                parcel1.writeNoException();
                return true;

            case 13: // '\r'
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                em em8 = com.google.android.gms.internal.em.a.y(parcel.readStrongBinder());
                int i5 = parcel.readInt();
                String s9 = parcel.readString();
                int j5 = parcel.readInt();
                Bundle bundle8 = null;
                if(j5 != 0)
                    bundle8 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                h(em8, i5, s9, bundle8);
                parcel1.writeNoException();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                em em7 = com.google.android.gms.internal.em.a.y(parcel.readStrongBinder());
                int k4 = parcel.readInt();
                String s8 = parcel.readString();
                int l4 = parcel.readInt();
                Bundle bundle7 = null;
                if(l4 != 0)
                    bundle7 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                i(em7, k4, s8, bundle7);
                parcel1.writeNoException();
                return true;

            case 15: // '\017'
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                em em6 = com.google.android.gms.internal.em.a.y(parcel.readStrongBinder());
                int i4 = parcel.readInt();
                String s7 = parcel.readString();
                int j4 = parcel.readInt();
                Bundle bundle6 = null;
                if(j4 != 0)
                    bundle6 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                j(em6, i4, s7, bundle6);
                parcel1.writeNoException();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                em em5 = com.google.android.gms.internal.em.a.y(parcel.readStrongBinder());
                int k3 = parcel.readInt();
                String s6 = parcel.readString();
                int l3 = parcel.readInt();
                Bundle bundle5 = null;
                if(l3 != 0)
                    bundle5 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                k(em5, k3, s6, bundle5);
                parcel1.writeNoException();
                return true;

            case 17: // '\021'
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                em em4 = com.google.android.gms.internal.em.a.y(parcel.readStrongBinder());
                int i3 = parcel.readInt();
                String s5 = parcel.readString();
                int j3 = parcel.readInt();
                Bundle bundle4 = null;
                if(j3 != 0)
                    bundle4 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                l(em4, i3, s5, bundle4);
                parcel1.writeNoException();
                return true;

            case 18: // '\022'
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                em em3 = com.google.android.gms.internal.em.a.y(parcel.readStrongBinder());
                int k2 = parcel.readInt();
                String s4 = parcel.readString();
                int l2 = parcel.readInt();
                Bundle bundle3 = null;
                if(l2 != 0)
                    bundle3 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                m(em3, k2, s4, bundle3);
                parcel1.writeNoException();
                return true;

            case 19: // '\023'
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                em em2 = com.google.android.gms.internal.em.a.y(parcel.readStrongBinder());
                int j2 = parcel.readInt();
                String s3 = parcel.readString();
                IBinder ibinder = parcel.readStrongBinder();
                Bundle bundle2;
                if(parcel.readInt() != 0)
                    bundle2 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle2 = null;
                a(em2, j2, s3, ibinder, bundle2);
                parcel1.writeNoException();
                return true;

            case 20: // '\024'
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                em em1 = com.google.android.gms.internal.em.a.y(parcel.readStrongBinder());
                int i2 = parcel.readInt();
                String s1 = parcel.readString();
                String as[] = parcel.createStringArray();
                String s2 = parcel.readString();
                Bundle bundle1;
                if(parcel.readInt() != 0)
                    bundle1 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle1 = null;
                a(em1, i2, s1, as, s2, bundle1);
                parcel1.writeNoException();
                return true;

            case 21: // '\025'
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                b(com.google.android.gms.internal.em.a.y(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 22: // '\026'
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                c(com.google.android.gms.internal.em.a.y(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 23: // '\027'
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                em em = com.google.android.gms.internal.em.a.y(parcel.readStrongBinder());
                int k1 = parcel.readInt();
                String s = parcel.readString();
                int l1 = parcel.readInt();
                Bundle bundle = null;
                if(l1 != 0)
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                n(em, k1, s, bundle);
                parcel1.writeNoException();
                return true;

            case 24: // '\030'
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                d(com.google.android.gms.internal.em.a.y(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;
            }
        }
    }


    private en()
    {
        a = false;
        k = 0;
        i = new a(this);
        ei ei1 = eh.a();
        g = (Criteria)ei1.a("LocationCriteria");
        ei1.a("LocationCriteria", this);
        ex.a(4, c, (new StringBuilder()).append("initSettings, LocationCriteria = ").append(g).toString());
        b = ((Boolean)ei1.a("ReportLocation")).booleanValue();
        ei1.a("ReportLocation", this);
        ex.a(4, c, (new StringBuilder()).append("initSettings, ReportLocation = ").append(b).toString());
    }

    static Location a(en en1, Location location)
    {
        en1.h = location;
        return location;
    }

    public static en a()
    {
        com/flurry/sdk/en;
        JVM INSTR monitorenter ;
        en en1;
        if(l == null)
            l = new en();
        en1 = l;
        com/flurry/sdk/en;
        JVM INSTR monitorexit ;
        return en1;
        Exception exception;
        exception;
        throw exception;
    }

    private void a(String s)
    {
        if(!TextUtils.isEmpty(s))
            f.requestLocationUpdates(s, 0x1b7740L, 0.0F, i, Looper.getMainLooper());
    }

    private Location b(String s)
    {
        boolean flag = TextUtils.isEmpty(s);
        Location location = null;
        if(!flag)
            location = f.getLastKnownLocation(s);
        return location;
    }

    private void g()
    {
        f.removeUpdates(i);
        a = false;
        ex.a(4, c, "LocationProvider stoped");
    }

    private void h()
    {
        Context context;
        if(b)
            if((context = eg.a().b()).checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0 || context.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0)
            {
                g();
                String s = i();
                a(s);
                h = b(s);
                a = true;
                ex.a(4, c, "LocationProvider started");
                return;
            }
    }

    private String i()
    {
        Criteria criteria = g;
        if(criteria == null)
            criteria = new Criteria();
        String s;
        if(TextUtils.isEmpty(j))
            s = f.getBestProvider(criteria, true);
        else
            s = j;
        ex.a(4, c, (new StringBuilder()).append("provider = ").append(s).toString());
        return s;
    }

    public void a(String s, Object obj)
    {
        if(s.equals("LocationCriteria"))
        {
            g = (Criteria)obj;
            ex.a(4, c, (new StringBuilder()).append("onSettingUpdate, LocationCriteria = ").append(g).toString());
            if(a)
                h();
        } else
        if(s.equals("ReportLocation"))
        {
            b = ((Boolean)obj).booleanValue();
            ex.a(4, c, (new StringBuilder()).append("onSettingUpdate, ReportLocation = ").append(b).toString());
            if(b)
            {
                if(!a && k > 0)
                {
                    h();
                    return;
                }
            } else
            {
                g();
                return;
            }
        } else
        {
            ex.a(6, c, "LocationProvider internal error! Had to be LocationCriteria or ReportLocation key.");
            return;
        }
    }

    public void b()
    {
        this;
        JVM INSTR monitorenter ;
        LocationManager locationmanager = f;
        if(locationmanager == null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        f = (LocationManager)eg.a().b().getSystemService("location");
        if(true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public void c()
    {
        this;
        JVM INSTR monitorenter ;
        ex.a(4, c, "Location provider subscribed");
        k = 1 + k;
        if(!a)
            h();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void d()
    {
        this;
        JVM INSTR monitorenter ;
        ex.a(4, c, "Location provider unsubscribed");
        if(k > 0)
            break MISSING_BLOCK_LABEL_31;
        ex.a(6, c, "Error! Unsubscribed too many times!");
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
        k = -1 + k;
        if(k == 0)
            g();
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    public Location e()
    {
        boolean flag = b;
        Location location = null;
        if(flag)
        {
            Location location1 = b(i());
            if(location1 != null)
                h = location1;
            location = h;
        }
        ex.a(4, c, (new StringBuilder()).append("getLocation() = ").append(location).toString());
        return location;
    }

    public void f()
    {
        k = 0;
        g();
    }

    private static final String c = com/flurry/sdk/en.getSimpleName();
    private static en l;
    boolean a;
    boolean b;
    private final long d = 0x1b7740L;
    private final long e = 0L;
    private LocationManager f;
    private Criteria g;
    private Location h;
    private a i;
    private String j;
    private int k;

}
