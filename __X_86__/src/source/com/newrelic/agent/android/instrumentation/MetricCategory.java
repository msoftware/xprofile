// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.instrumentation;

import java.util.HashMap;
import java.util.Map;

public final class MetricCategory extends Enum
{

    private MetricCategory(String s, int i, String s1)
    {
        super(s, i);
        categoryName = s1;
    }

    public static MetricCategory categoryForMethod(String s)
    {
        MetricCategory metriccategory;
        if(s == null)
        {
            metriccategory = NONE;
        } else
        {
            int i = s.indexOf("#");
            String s1 = null;
            if(i >= 0)
                s1 = s.substring(i + 1);
            metriccategory = (MetricCategory)methodMap.get(s1);
            if(metriccategory == null)
                return NONE;
        }
        return metriccategory;
    }

    public static MetricCategory valueOf(String s)
    {
        return (MetricCategory)Enum.valueOf(com/newrelic/agent/android/instrumentation/MetricCategory, s);
    }

    public static MetricCategory[] values()
    {
        return (MetricCategory[])$VALUES.clone();
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    private static final MetricCategory $VALUES[];
    public static final MetricCategory DATABASE;
    public static final MetricCategory IMAGE;
    public static final MetricCategory JSON;
    public static final MetricCategory NETWORK;
    public static final MetricCategory NONE;
    public static final MetricCategory VIEW_LAYOUT;
    public static final MetricCategory VIEW_LOADING;
    private static final Map methodMap = new HashMap() {

            
            {
                put("onCreate", MetricCategory.VIEW_LOADING);
            }
    }
;
    private String categoryName;

    static 
    {
        NONE = new MetricCategory("NONE", 0, "None");
        VIEW_LOADING = new MetricCategory("VIEW_LOADING", 1, "View Loading");
        VIEW_LAYOUT = new MetricCategory("VIEW_LAYOUT", 2, "Layout");
        DATABASE = new MetricCategory("DATABASE", 3, "Database");
        IMAGE = new MetricCategory("IMAGE", 4, "Images");
        JSON = new MetricCategory("JSON", 5, "JSON");
        NETWORK = new MetricCategory("NETWORK", 6, "Network");
        MetricCategory ametriccategory[] = new MetricCategory[7];
        ametriccategory[0] = NONE;
        ametriccategory[1] = VIEW_LOADING;
        ametriccategory[2] = VIEW_LAYOUT;
        ametriccategory[3] = DATABASE;
        ametriccategory[4] = IMAGE;
        ametriccategory[5] = JSON;
        ametriccategory[6] = NETWORK;
        $VALUES = ametriccategory;
    }
}
