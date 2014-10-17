// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.io.NumberOutput;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

// Referenced classes of package com.fasterxml.jackson.databind.node:
//            NumericNode

public class IntNode extends NumericNode
{

    public IntNode(int i)
    {
        _value = i;
    }

    public static IntNode valueOf(int i)
    {
        if(i > 10 || i < -1)
            return new IntNode(i);
        else
            return CANONICALS[i + 1];
    }

    public boolean asBoolean(boolean flag)
    {
        return _value != 0;
    }

    public String asText()
    {
        return NumberOutput.toString(_value);
    }

    public JsonToken asToken()
    {
        return JsonToken.VALUE_NUMBER_INT;
    }

    public BigInteger bigIntegerValue()
    {
        return BigInteger.valueOf(_value);
    }

    public boolean canConvertToInt()
    {
        return true;
    }

    public boolean canConvertToLong()
    {
        return true;
    }

    public BigDecimal decimalValue()
    {
        return BigDecimal.valueOf(_value);
    }

    public double doubleValue()
    {
        return (double)_value;
    }

    public boolean equals(Object obj)
    {
        if(obj != this)
        {
            if(obj == null)
                return false;
            if(obj instanceof IntNode)
            {
                if(((IntNode)obj)._value != _value)
                    return false;
            } else
            {
                return false;
            }
        }
        return true;
    }

    public float floatValue()
    {
        return (float)_value;
    }

    public int hashCode()
    {
        return _value;
    }

    public int intValue()
    {
        return _value;
    }

    public boolean isInt()
    {
        return true;
    }

    public boolean isIntegralNumber()
    {
        return true;
    }

    public long longValue()
    {
        return (long)_value;
    }

    public com.fasterxml.jackson.core.JsonParser.NumberType numberType()
    {
        return com.fasterxml.jackson.core.JsonParser.NumberType.INT;
    }

    public Number numberValue()
    {
        return Integer.valueOf(_value);
    }

    public final void serialize(JsonGenerator jsongenerator, SerializerProvider serializerprovider)
        throws IOException, JsonProcessingException
    {
        jsongenerator.writeNumber(_value);
    }

    public short shortValue()
    {
        return (short)_value;
    }

    private static final IntNode CANONICALS[];
    static final int MAX_CANONICAL = 10;
    static final int MIN_CANONICAL = -1;
    protected final int _value;

    static 
    {
        CANONICALS = new IntNode[12];
        for(int i = 0; i < 12; i++)
            CANONICALS[i] = new IntNode(i - 1);

    }
}
