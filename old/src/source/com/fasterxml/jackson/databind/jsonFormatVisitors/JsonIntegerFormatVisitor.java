// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.jsonFormatVisitors;


// Referenced classes of package com.fasterxml.jackson.databind.jsonFormatVisitors:
//            JsonValueFormatVisitor

public interface JsonIntegerFormatVisitor
    extends JsonValueFormatVisitor
{
    public static class Base extends JsonValueFormatVisitor.Base
        implements JsonIntegerFormatVisitor
    {

        public void numberType(com.fasterxml.jackson.core.JsonParser.NumberType numbertype)
        {
        }

        public Base()
        {
        }
    }


    public abstract void numberType(com.fasterxml.jackson.core.JsonParser.NumberType numbertype);
}
