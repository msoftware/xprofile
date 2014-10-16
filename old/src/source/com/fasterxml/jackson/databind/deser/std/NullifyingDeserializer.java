// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;

// Referenced classes of package com.fasterxml.jackson.databind.deser.std:
//            StdDeserializer

public class NullifyingDeserializer extends StdDeserializer
{

    public NullifyingDeserializer()
    {
        super(java/lang/Object);
    }

    public Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        jsonparser.skipChildren();
        return null;
    }

    public Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
        throws IOException, JsonProcessingException
    {
        JsonToken jsontoken = jsonparser.getCurrentToken();
        static class _cls1
        {

            static final int $SwitchMap$com$fasterxml$jackson$core$JsonToken[];

            static 
            {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken = new int[JsonToken.values().length];
                try
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.START_ARRAY.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.START_OBJECT.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.FIELD_NAME.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2)
                {
                    return;
                }
            }
        }

        switch(_cls1..SwitchMap.com.fasterxml.jackson.core.JsonToken[jsontoken.ordinal()])
        {
        default:
            return null;

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
            return typedeserializer.deserializeTypedFromAny(jsonparser, deserializationcontext);
        }
    }

    public static final NullifyingDeserializer instance = new NullifyingDeserializer();
    private static final long serialVersionUID = 1L;

}
