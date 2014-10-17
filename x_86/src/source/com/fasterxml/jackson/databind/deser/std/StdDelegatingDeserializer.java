// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.Converter;
import java.io.IOException;

// Referenced classes of package com.fasterxml.jackson.databind.deser.std:
//            StdDeserializer

public class StdDelegatingDeserializer extends StdDeserializer
    implements ContextualDeserializer, ResolvableDeserializer
{

    public StdDelegatingDeserializer(Converter converter)
    {
        super(java/lang/Object);
        _converter = converter;
        _delegateType = null;
        _delegateDeserializer = null;
    }

    public StdDelegatingDeserializer(Converter converter, JavaType javatype, JsonDeserializer jsondeserializer)
    {
        super(javatype);
        _converter = converter;
        _delegateType = javatype;
        _delegateDeserializer = jsondeserializer;
    }

    protected Object convertValue(Object obj)
    {
        return _converter.convert(obj);
    }

    public JsonDeserializer createContextual(DeserializationContext deserializationcontext, BeanProperty beanproperty)
        throws JsonMappingException
    {
        if(_delegateDeserializer != null)
        {
            JsonDeserializer jsondeserializer = deserializationcontext.handleSecondaryContextualization(_delegateDeserializer, beanproperty);
            if(jsondeserializer != _delegateDeserializer)
                this = withDelegate(_converter, _delegateType, jsondeserializer);
            return this;
        } else
        {
            JavaType javatype = _converter.getInputType(deserializationcontext.getTypeFactory());
            return withDelegate(_converter, javatype, deserializationcontext.findContextualValueDeserializer(javatype, beanproperty));
        }
    }

    public Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
        throws IOException, JsonProcessingException
    {
        Object obj = _delegateDeserializer.deserialize(jsonparser, deserializationcontext);
        if(obj == null)
            return null;
        else
            return convertValue(obj);
    }

    public Object deserializeWithType(JsonParser jsonparser, DeserializationContext deserializationcontext, TypeDeserializer typedeserializer)
        throws IOException, JsonProcessingException
    {
        Object obj = _delegateDeserializer.deserializeWithType(jsonparser, deserializationcontext, typedeserializer);
        if(obj == null)
            return null;
        else
            return convertValue(obj);
    }

    public JsonDeserializer getDelegatee()
    {
        return _delegateDeserializer;
    }

    public Class handledType()
    {
        return _delegateDeserializer.handledType();
    }

    public void resolve(DeserializationContext deserializationcontext)
        throws JsonMappingException
    {
        if(_delegateDeserializer != null && (_delegateDeserializer instanceof ResolvableDeserializer))
            ((ResolvableDeserializer)_delegateDeserializer).resolve(deserializationcontext);
    }

    protected StdDelegatingDeserializer withDelegate(Converter converter, JavaType javatype, JsonDeserializer jsondeserializer)
    {
        if(getClass() != com/fasterxml/jackson/databind/deser/std/StdDelegatingDeserializer)
            throw new IllegalStateException((new StringBuilder()).append("Sub-class ").append(getClass().getName()).append(" must override 'withDelegate'").toString());
        else
            return new StdDelegatingDeserializer(converter, javatype, jsondeserializer);
    }

    private static final long serialVersionUID = 1L;
    protected final Converter _converter;
    protected final JsonDeserializer _delegateDeserializer;
    protected final JavaType _delegateType;
}
