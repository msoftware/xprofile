// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.content.Context;
import android.support.v7.internal.view.SupportMenuInflater;
import android.support.v7.internal.view.menu.*;
import android.view.*;

public class PopupMenu
    implements android.support.v7.internal.view.menu.MenuBuilder.Callback, android.support.v7.internal.view.menu.MenuPresenter.Callback
{
    public static interface OnDismissListener
    {

        public abstract void onDismiss(PopupMenu popupmenu);
    }

    public static interface OnMenuItemClickListener
    {

        public abstract boolean onMenuItemClick(MenuItem menuitem);
    }


    public PopupMenu(Context context, View view)
    {
        mContext = context;
        mMenu = new MenuBuilder(context);
        mMenu.setCallback(this);
        mAnchor = view;
        mPopup = new MenuPopupHelper(context, mMenu, view);
        mPopup.setCallback(this);
    }

    public void dismiss()
    {
        mPopup.dismiss();
    }

    public Menu getMenu()
    {
        return mMenu;
    }

    public MenuInflater getMenuInflater()
    {
        return new SupportMenuInflater(mContext);
    }

    public void inflate(int i)
    {
        getMenuInflater().inflate(i, mMenu);
    }

    public void onCloseMenu(MenuBuilder menubuilder, boolean flag)
    {
        if(mDismissListener != null)
            mDismissListener.onDismiss(this);
    }

    public void onCloseSubMenu(SubMenuBuilder submenubuilder)
    {
    }

    public boolean onMenuItemSelected(MenuBuilder menubuilder, MenuItem menuitem)
    {
        if(mMenuItemClickListener != null)
            return mMenuItemClickListener.onMenuItemClick(menuitem);
        else
            return false;
    }

    public void onMenuModeChange(MenuBuilder menubuilder)
    {
    }

    public boolean onOpenSubMenu(MenuBuilder menubuilder)
    {
        boolean flag = true;
        if(menubuilder == null)
            flag = false;
        else
        if(menubuilder.hasVisibleItems())
        {
            (new MenuPopupHelper(mContext, menubuilder, mAnchor)).show();
            return flag;
        }
        return flag;
    }

    public void setOnDismissListener(OnDismissListener ondismisslistener)
    {
        mDismissListener = ondismisslistener;
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onmenuitemclicklistener)
    {
        mMenuItemClickListener = onmenuitemclicklistener;
    }

    public void show()
    {
        mPopup.show();
    }

    private View mAnchor;
    private Context mContext;
    private OnDismissListener mDismissListener;
    private MenuBuilder mMenu;
    private OnMenuItemClickListener mMenuItemClickListener;
    private MenuPopupHelper mPopup;
}
