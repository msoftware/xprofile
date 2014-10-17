// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.internal.view.menu;

import android.support.v4.internal.view.*;
import android.view.*;

// Referenced classes of package android.support.v7.internal.view.menu:
//            MenuItemWrapperJB, MenuItemWrapperICS, MenuWrapperICS, SubMenuWrapperICS

public final class MenuWrapperFactory
{

    private MenuWrapperFactory()
    {
    }

    public static MenuItem createMenuItemWrapper(MenuItem menuitem)
    {
        if(android.os.Build.VERSION.SDK_INT >= 16)
            menuitem = new MenuItemWrapperJB(menuitem);
        else
        if(android.os.Build.VERSION.SDK_INT >= 14)
            return new MenuItemWrapperICS(menuitem);
        return menuitem;
    }

    public static Menu createMenuWrapper(Menu menu)
    {
        if(android.os.Build.VERSION.SDK_INT >= 14)
            menu = new MenuWrapperICS(menu);
        return menu;
    }

    public static SupportMenuItem createSupportMenuItemWrapper(MenuItem menuitem)
    {
        if(android.os.Build.VERSION.SDK_INT >= 16)
            return new MenuItemWrapperJB(menuitem);
        if(android.os.Build.VERSION.SDK_INT >= 14)
            return new MenuItemWrapperICS(menuitem);
        else
            throw new UnsupportedOperationException();
    }

    public static SupportMenu createSupportMenuWrapper(Menu menu)
    {
        if(android.os.Build.VERSION.SDK_INT >= 14)
            return new MenuWrapperICS(menu);
        else
            throw new UnsupportedOperationException();
    }

    public static SupportSubMenu createSupportSubMenuWrapper(SubMenu submenu)
    {
        if(android.os.Build.VERSION.SDK_INT >= 14)
            return new SubMenuWrapperICS(submenu);
        else
            throw new UnsupportedOperationException();
    }
}
