// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import android.os.*;

public interface ds
    extends IInterface
{
    public static abstract class a extends Binder
        implements ds
    {

        public static ds w(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.cast.internal.ICastDeviceController");
            if(iinterface != null && (iinterface instanceof ds))
                return (ds)iinterface;
            else
                return new a(ibinder);
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            throws RemoteException
        {
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("com.google.android.gms.cast.internal.ICastDeviceController");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
                disconnect();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
                String s = parcel.readString();
                int l = parcel.readInt();
                boolean flag3 = false;
                if(l != 0)
                    flag3 = true;
                e(s, flag3);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
                e(parcel.readString(), parcel.readString());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
                df();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
                R(parcel.readString());
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
                cZ();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
                double d1 = parcel.readDouble();
                double d2 = parcel.readDouble();
                boolean flag2;
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                a(d1, d2, flag2);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
                boolean flag;
                double d;
                int k;
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                d = parcel.readDouble();
                k = parcel.readInt();
                flag1 = false;
                if(k != 0)
                    flag1 = true;
                a(flag, d, flag1);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
                a(parcel.readString(), parcel.readString(), parcel.readLong());
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
                a(parcel.readString(), parcel.createByteArray(), parcel.readLong());
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
                S(parcel.readString());
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
                T(parcel.readString());
                return true;
            }
        }
    }

    private static class a.a
        implements ds
    {

        public void R(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
            parcel.writeString(s);
            ky.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void S(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
            parcel.writeString(s);
            ky.transact(11, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void T(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
            parcel.writeString(s);
            ky.transact(12, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void a(double d, double d1, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
            parcel.writeDouble(d);
            parcel.writeDouble(d1);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            ky.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void a(String s, String s1, long l)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeLong(l);
            ky.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void a(String s, byte abyte0[], long l)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
            parcel.writeString(s);
            parcel.writeByteArray(abyte0);
            parcel.writeLong(l);
            ky.transact(10, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void a(boolean flag, double d, boolean flag1)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
            int j;
            if(flag)
                j = i;
            else
                j = 0;
            parcel.writeInt(j);
            parcel.writeDouble(d);
            if(!flag1)
                i = 0;
            parcel.writeInt(i);
            ky.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public IBinder asBinder()
        {
            return ky;
        }

        public void cZ()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
            ky.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void df()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
            ky.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void disconnect()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
            ky.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void e(String s, String s1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
            parcel.writeString(s);
            parcel.writeString(s1);
            ky.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void e(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
            parcel.writeString(s);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            ky.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        private IBinder ky;

        a.a(IBinder ibinder)
        {
            ky = ibinder;
        }
    }


    public abstract void R(String s)
        throws RemoteException;

    public abstract void S(String s)
        throws RemoteException;

    public abstract void T(String s)
        throws RemoteException;

    public abstract void a(double d, double d1, boolean flag)
        throws RemoteException;

    public abstract void a(String s, String s1, long l)
        throws RemoteException;

    public abstract void a(String s, byte abyte0[], long l)
        throws RemoteException;

    public abstract void a(boolean flag, double d, boolean flag1)
        throws RemoteException;

    public abstract void cZ()
        throws RemoteException;

    public abstract void df()
        throws RemoteException;

    public abstract void disconnect()
        throws RemoteException;

    public abstract void e(String s, String s1)
        throws RemoteException;

    public abstract void e(String s, boolean flag)
        throws RemoteException;
}
