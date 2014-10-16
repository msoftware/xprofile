// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import java.util.*;

// Referenced classes of package com.google.android.gms.tagmanager:
//            dg, df, bh, DataLayer, 
//            di

class dj extends dg
{

    public dj(Context context, DataLayer datalayer)
    {
        this(context, datalayer, new df(context));
    }

    dj(Context context, DataLayer datalayer, df df1)
    {
        super(ID, new String[0]);
        TN = datalayer;
        Ya = df1;
        XZ = new HashSet();
        XZ.add("");
        XZ.add("0");
        XZ.add("false");
    }

    private Map E(Map map)
    {
        com.google.android.gms.internal.d.a a1 = (com.google.android.gms.internal.d.a)map.get(XV);
        if(a1 != null)
            return c(a1);
        if(XX == null)
        {
            HashMap hashmap = new HashMap();
            hashmap.put("transactionId", "&ti");
            hashmap.put("transactionAffiliation", "&ta");
            hashmap.put("transactionTax", "&tt");
            hashmap.put("transactionShipping", "&ts");
            hashmap.put("transactionTotal", "&tr");
            hashmap.put("transactionCurrency", "&cu");
            XX = hashmap;
        }
        return XX;
    }

    private Map F(Map map)
    {
        com.google.android.gms.internal.d.a a1 = (com.google.android.gms.internal.d.a)map.get(XW);
        if(a1 != null)
            return c(a1);
        if(XY == null)
        {
            HashMap hashmap = new HashMap();
            hashmap.put("name", "&in");
            hashmap.put("sku", "&ic");
            hashmap.put("category", "&iv");
            hashmap.put("price", "&ip");
            hashmap.put("quantity", "&iq");
            hashmap.put("currency", "&cu");
            XY = hashmap;
        }
        return XY;
    }

    private void a(Tracker tracker, Map map)
    {
        String s = bN("transactionId");
        if(s != null) goto _L2; else goto _L1
_L1:
        bh.t("Cannot find transactionId in data layer.");
_L4:
        return;
_L2:
        LinkedList linkedlist;
        Map map1;
        linkedlist = new LinkedList();
        try
        {
            map1 = p((com.google.android.gms.internal.d.a)map.get(XT));
            map1.put("&t", "transaction");
            java.util.Map.Entry entry1;
            for(Iterator iterator = E(map).entrySet().iterator(); iterator.hasNext(); b(map1, (String)entry1.getValue(), bN((String)entry1.getKey())))
                entry1 = (java.util.Map.Entry)iterator.next();

        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            bh.c("Unable to send transaction", illegalargumentexception);
            return;
        }
        List list;
        linkedlist.add(map1);
        list = kv();
        if(list == null)
            break MISSING_BLOCK_LABEL_338;
        Iterator iterator1 = list.iterator();
_L3:
        Map map2;
        if(!iterator1.hasNext())
            break MISSING_BLOCK_LABEL_338;
        map2 = (Map)iterator1.next();
        if(map2.get("name") == null)
        {
            bh.t("Unable to send transaction item hit due to missing 'name' field.");
            return;
        }
        Map map3;
        map3 = p((com.google.android.gms.internal.d.a)map.get(XT));
        map3.put("&t", "item");
        map3.put("&ti", s);
        java.util.Map.Entry entry;
        for(Iterator iterator3 = F(map).entrySet().iterator(); iterator3.hasNext(); b(map3, (String)entry.getValue(), (String)map2.get(entry.getKey())))
            entry = (java.util.Map.Entry)iterator3.next();

        linkedlist.add(map3);
          goto _L3
        Iterator iterator2 = linkedlist.iterator();
        while(iterator2.hasNext()) 
            tracker.send((Map)iterator2.next());
          goto _L4
    }

    private void b(Map map, String s, String s1)
    {
        if(s1 != null)
            map.put(s, s1);
    }

    private String bN(String s)
    {
        Object obj = TN.get(s);
        if(obj == null)
            return null;
        else
            return obj.toString();
    }

    private Map c(com.google.android.gms.internal.d.a a1)
    {
        Object obj = di.o(a1);
        if(!(obj instanceof Map))
            return null;
        Map map = (Map)obj;
        LinkedHashMap linkedhashmap = new LinkedHashMap();
        java.util.Map.Entry entry;
        for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); linkedhashmap.put(entry.getKey().toString(), entry.getValue().toString()))
            entry = (java.util.Map.Entry)iterator.next();

        return linkedhashmap;
    }

    private boolean d(Map map, String s)
    {
        com.google.android.gms.internal.d.a a1 = (com.google.android.gms.internal.d.a)map.get(s);
        if(a1 == null)
            return false;
        else
            return di.n(a1).booleanValue();
    }

    private List kv()
    {
        Object obj = TN.get("transactionProducts");
        if(obj == null)
            return null;
        if(!(obj instanceof List))
            throw new IllegalArgumentException("transactionProducts should be of type List.");
        for(Iterator iterator = ((List)obj).iterator(); iterator.hasNext();)
            if(!(iterator.next() instanceof Map))
                throw new IllegalArgumentException("Each element of transactionProducts should be of type Map.");

        return (List)obj;
    }

    private Map p(com.google.android.gms.internal.d.a a1)
    {
        if(a1 == null)
            return new HashMap();
        Map map = c(a1);
        if(map == null)
            return new HashMap();
        String s = (String)map.get("&aip");
        if(s != null && XZ.contains(s.toLowerCase()))
            map.remove("&aip");
        return map;
    }

    public void w(Map map)
    {
        Tracker tracker = Ya.bF("_GTM_DEFAULT_TRACKER_");
        if(d(map, XS))
        {
            tracker.send(p((com.google.android.gms.internal.d.a)map.get(XT)));
            return;
        }
        if(d(map, XU))
        {
            a(tracker, map);
            return;
        } else
        {
            bh.w("Ignoring unknown tag.");
            return;
        }
    }

    private static final String ID;
    private static final String XR;
    private static final String XS;
    private static final String XT;
    private static final String XU;
    private static final String XV;
    private static final String XW;
    private static Map XX;
    private static Map XY;
    private final DataLayer TN;
    private final Set XZ;
    private final df Ya;

    static 
    {
        ID = a.aQ.toString();
        XR = b.bg.toString();
        XS = b.bp.toString();
        XT = b.bo.toString();
        XU = b.er.toString();
        XV = b.et.toString();
        XW = b.ev.toString();
    }
}
