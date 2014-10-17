// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.com.google.gson.internal.bind;

import com.newrelic.com.google.gson.*;
import com.newrelic.com.google.gson.internal.LinkedTreeMap;
import com.newrelic.com.google.gson.reflect.TypeToken;
import com.newrelic.com.google.gson.stream.*;
import java.io.IOException;
import java.util.*;

public final class ObjectTypeAdapter extends TypeAdapter
{

    private ObjectTypeAdapter(Gson gson1)
    {
        gson = gson1;
    }


    public Object read(JsonReader jsonreader)
        throws IOException
    {
        JsonToken jsontoken = jsonreader.peek();
        static class _cls2
        {

            static final int $SwitchMap$com$google$gson$stream$JsonToken[];

            static 
            {
                $SwitchMap$com$google$gson$stream$JsonToken = new int[JsonToken.values().length];
                try
                {
                    $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BEGIN_ARRAY.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BEGIN_OBJECT.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.STRING.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NUMBER.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BOOLEAN.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NULL.ordinal()] = 6;
                }
                catch(NoSuchFieldError nosuchfielderror5)
                {
                    return;
                }
            }
        }

        switch(_cls2..SwitchMap.com.google.gson.stream.JsonToken[jsontoken.ordinal()])
        {
        default:
            throw new IllegalStateException();

        case 1: // '\001'
            ArrayList arraylist = new ArrayList();
            jsonreader.beginArray();
            for(; jsonreader.hasNext(); arraylist.add(read(jsonreader)));
            jsonreader.endArray();
            return arraylist;

        case 2: // '\002'
            LinkedTreeMap linkedtreemap = new LinkedTreeMap();
            jsonreader.beginObject();
            for(; jsonreader.hasNext(); linkedtreemap.put(jsonreader.nextName(), read(jsonreader)));
            jsonreader.endObject();
            return linkedtreemap;

        case 3: // '\003'
            return jsonreader.nextString();

        case 4: // '\004'
            return Double.valueOf(jsonreader.nextDouble());

        case 5: // '\005'
            return Boolean.valueOf(jsonreader.nextBoolean());

        case 6: // '\006'
            jsonreader.nextNull();
            return null;
        }
    }

    public void write(JsonWriter jsonwriter, Object obj)
        throws IOException
    {
        if(obj == null)
        {
            jsonwriter.nullValue();
            return;
        }
        TypeAdapter typeadapter = gson.getAdapter(obj.getClass());
        if(typeadapter instanceof ObjectTypeAdapter)
        {
            jsonwriter.beginObject();
            jsonwriter.endObject();
            return;
        } else
        {
            typeadapter.write(jsonwriter, obj);
            return;
        }
    }

    public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {

        public TypeAdapter create(Gson gson1, TypeToken typetoken)
        {
            if(typetoken.getRawType() == java/lang/Object)
                return new ObjectTypeAdapter(gson1);
            else
                return null;
        }

    }
;
    private final Gson gson;

}
