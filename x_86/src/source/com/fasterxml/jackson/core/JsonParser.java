// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;

// Referenced classes of package com.fasterxml.jackson.core:
//            Versioned, JsonParseException, Base64Variants, JsonToken, 
//            JsonGenerationException, SerializableString, JsonProcessingException, ObjectCodec, 
//            FormatSchema, Base64Variant, JsonLocation, JsonStreamContext, 
//            TreeNode, Version

public abstract class JsonParser
    implements Closeable, Versioned
{
    public static final class Feature extends Enum
    {

        public static int collectDefaults()
        {
            int i = 0;
            Feature afeature[] = values();
            int j = afeature.length;
            for(int k = 0; k < j; k++)
            {
                Feature feature = afeature[k];
                if(feature.enabledByDefault())
                    i |= feature.getMask();
            }

            return i;
        }

        public static Feature valueOf(String s)
        {
            return (Feature)Enum.valueOf(com/fasterxml/jackson/core/JsonParser$Feature, s);
        }

        public static Feature[] values()
        {
            return (Feature[])$VALUES.clone();
        }

        public boolean enabledByDefault()
        {
            return _defaultState;
        }

        public boolean enabledIn(int i)
        {
            return (i & _mask) != 0;
        }

        public int getMask()
        {
            return 1 << ordinal();
        }

        private static final Feature $VALUES[];
        public static final Feature ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER;
        public static final Feature ALLOW_COMMENTS;
        public static final Feature ALLOW_NON_NUMERIC_NUMBERS;
        public static final Feature ALLOW_NUMERIC_LEADING_ZEROS;
        public static final Feature ALLOW_SINGLE_QUOTES;
        public static final Feature ALLOW_UNQUOTED_CONTROL_CHARS;
        public static final Feature ALLOW_UNQUOTED_FIELD_NAMES;
        public static final Feature ALLOW_YAML_COMMENTS;
        public static final Feature AUTO_CLOSE_SOURCE;
        public static final Feature STRICT_DUPLICATE_DETECTION;
        private final boolean _defaultState;
        private final int _mask = 1 << ordinal();

        static 
        {
            AUTO_CLOSE_SOURCE = new Feature("AUTO_CLOSE_SOURCE", 0, true);
            ALLOW_COMMENTS = new Feature("ALLOW_COMMENTS", 1, false);
            ALLOW_YAML_COMMENTS = new Feature("ALLOW_YAML_COMMENTS", 2, false);
            ALLOW_UNQUOTED_FIELD_NAMES = new Feature("ALLOW_UNQUOTED_FIELD_NAMES", 3, false);
            ALLOW_SINGLE_QUOTES = new Feature("ALLOW_SINGLE_QUOTES", 4, false);
            ALLOW_UNQUOTED_CONTROL_CHARS = new Feature("ALLOW_UNQUOTED_CONTROL_CHARS", 5, false);
            ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER = new Feature("ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER", 6, false);
            ALLOW_NUMERIC_LEADING_ZEROS = new Feature("ALLOW_NUMERIC_LEADING_ZEROS", 7, false);
            ALLOW_NON_NUMERIC_NUMBERS = new Feature("ALLOW_NON_NUMERIC_NUMBERS", 8, false);
            STRICT_DUPLICATE_DETECTION = new Feature("STRICT_DUPLICATE_DETECTION", 9, false);
            Feature afeature[] = new Feature[10];
            afeature[0] = AUTO_CLOSE_SOURCE;
            afeature[1] = ALLOW_COMMENTS;
            afeature[2] = ALLOW_YAML_COMMENTS;
            afeature[3] = ALLOW_UNQUOTED_FIELD_NAMES;
            afeature[4] = ALLOW_SINGLE_QUOTES;
            afeature[5] = ALLOW_UNQUOTED_CONTROL_CHARS;
            afeature[6] = ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER;
            afeature[7] = ALLOW_NUMERIC_LEADING_ZEROS;
            afeature[8] = ALLOW_NON_NUMERIC_NUMBERS;
            afeature[9] = STRICT_DUPLICATE_DETECTION;
            $VALUES = afeature;
        }

        private Feature(String s, int i, boolean flag)
        {
            super(s, i);
            _defaultState = flag;
        }
    }

    public static final class NumberType extends Enum
    {

        public static NumberType valueOf(String s)
        {
            return (NumberType)Enum.valueOf(com/fasterxml/jackson/core/JsonParser$NumberType, s);
        }

        public static NumberType[] values()
        {
            return (NumberType[])$VALUES.clone();
        }

        private static final NumberType $VALUES[];
        public static final NumberType BIG_DECIMAL;
        public static final NumberType BIG_INTEGER;
        public static final NumberType DOUBLE;
        public static final NumberType FLOAT;
        public static final NumberType INT;
        public static final NumberType LONG;

        static 
        {
            INT = new NumberType("INT", 0);
            LONG = new NumberType("LONG", 1);
            BIG_INTEGER = new NumberType("BIG_INTEGER", 2);
            FLOAT = new NumberType("FLOAT", 3);
            DOUBLE = new NumberType("DOUBLE", 4);
            BIG_DECIMAL = new NumberType("BIG_DECIMAL", 5);
            NumberType anumbertype[] = new NumberType[6];
            anumbertype[0] = INT;
            anumbertype[1] = LONG;
            anumbertype[2] = BIG_INTEGER;
            anumbertype[3] = FLOAT;
            anumbertype[4] = DOUBLE;
            anumbertype[5] = BIG_DECIMAL;
            $VALUES = anumbertype;
        }

        private NumberType(String s, int i)
        {
            super(s, i);
        }
    }


    protected JsonParser()
    {
    }

    protected JsonParser(int i)
    {
        _features = i;
    }

    protected JsonParseException _constructError(String s)
    {
        return new JsonParseException(s, getCurrentLocation());
    }

    protected void _reportUnsupportedOperation()
    {
        throw new UnsupportedOperationException((new StringBuilder()).append("Operation not supported by parser of type ").append(getClass().getName()).toString());
    }

    public boolean canReadObjectId()
    {
        return false;
    }

    public boolean canReadTypeId()
    {
        return false;
    }

    public boolean canUseSchema(FormatSchema formatschema)
    {
        return false;
    }

    public abstract void clearCurrentToken();

    public abstract void close()
        throws IOException;

    public JsonParser configure(Feature feature, boolean flag)
    {
        if(flag)
        {
            enable(feature);
            return this;
        } else
        {
            disable(feature);
            return this;
        }
    }

    public JsonParser disable(Feature feature)
    {
        _features = _features & (-1 ^ feature.getMask());
        return this;
    }

    public JsonParser enable(Feature feature)
    {
        _features = _features | feature.getMask();
        return this;
    }

    public abstract BigInteger getBigIntegerValue()
        throws IOException, JsonParseException;

    public byte[] getBinaryValue()
        throws IOException, JsonParseException
    {
        return getBinaryValue(Base64Variants.getDefaultVariant());
    }

    public abstract byte[] getBinaryValue(Base64Variant base64variant)
        throws IOException, JsonParseException;

    public boolean getBooleanValue()
        throws IOException, JsonParseException
    {
        JsonToken jsontoken = getCurrentToken();
        if(jsontoken == JsonToken.VALUE_TRUE)
            return true;
        if(jsontoken == JsonToken.VALUE_FALSE)
            return false;
        else
            throw new JsonParseException((new StringBuilder()).append("Current token (").append(jsontoken).append(") not of boolean type").toString(), getCurrentLocation());
    }

    public byte getByteValue()
        throws IOException, JsonParseException
    {
        int i = getIntValue();
        if(i < -128 || i > 255)
            throw _constructError((new StringBuilder()).append("Numeric value (").append(getText()).append(") out of range of Java byte").toString());
        else
            return (byte)i;
    }

    public abstract ObjectCodec getCodec();

    public abstract JsonLocation getCurrentLocation();

    public abstract String getCurrentName()
        throws IOException, JsonParseException;

    public abstract JsonToken getCurrentToken();

    public abstract int getCurrentTokenId();

    public abstract BigDecimal getDecimalValue()
        throws IOException, JsonParseException;

    public abstract double getDoubleValue()
        throws IOException, JsonParseException;

    public abstract Object getEmbeddedObject()
        throws IOException, JsonParseException;

    public int getFeatureMask()
    {
        return _features;
    }

    public abstract float getFloatValue()
        throws IOException, JsonParseException;

    public Object getInputSource()
    {
        return null;
    }

    public abstract int getIntValue()
        throws IOException, JsonParseException;

    public abstract JsonToken getLastClearedToken();

    public abstract long getLongValue()
        throws IOException, JsonParseException;

    public abstract NumberType getNumberType()
        throws IOException, JsonParseException;

    public abstract Number getNumberValue()
        throws IOException, JsonParseException;

    public Object getObjectId()
        throws IOException, JsonGenerationException
    {
        return null;
    }

    public abstract JsonStreamContext getParsingContext();

    public FormatSchema getSchema()
    {
        return null;
    }

    public short getShortValue()
        throws IOException, JsonParseException
    {
        int i = getIntValue();
        if(i < -32768 || i > 32767)
            throw _constructError((new StringBuilder()).append("Numeric value (").append(getText()).append(") out of range of Java short").toString());
        else
            return (short)i;
    }

    public abstract String getText()
        throws IOException, JsonParseException;

    public abstract char[] getTextCharacters()
        throws IOException, JsonParseException;

    public abstract int getTextLength()
        throws IOException, JsonParseException;

    public abstract int getTextOffset()
        throws IOException, JsonParseException;

    public abstract JsonLocation getTokenLocation();

    public Object getTypeId()
        throws IOException, JsonGenerationException
    {
        return null;
    }

    public boolean getValueAsBoolean()
        throws IOException, JsonParseException
    {
        return getValueAsBoolean(false);
    }

    public boolean getValueAsBoolean(boolean flag)
        throws IOException, JsonParseException
    {
        return flag;
    }

    public double getValueAsDouble()
        throws IOException, JsonParseException
    {
        return getValueAsDouble(0.0D);
    }

    public double getValueAsDouble(double d)
        throws IOException, JsonParseException
    {
        return d;
    }

    public int getValueAsInt()
        throws IOException, JsonParseException
    {
        return getValueAsInt(0);
    }

    public int getValueAsInt(int i)
        throws IOException, JsonParseException
    {
        return i;
    }

    public long getValueAsLong()
        throws IOException, JsonParseException
    {
        return getValueAsLong(0L);
    }

    public long getValueAsLong(long l)
        throws IOException, JsonParseException
    {
        return l;
    }

    public String getValueAsString()
        throws IOException, JsonParseException
    {
        return getValueAsString(null);
    }

    public abstract String getValueAsString(String s)
        throws IOException, JsonParseException;

    public abstract boolean hasCurrentToken();

    public abstract boolean hasTextCharacters();

    public abstract boolean isClosed();

    public boolean isEnabled(Feature feature)
    {
        return (_features & feature.getMask()) != 0;
    }

    public boolean isExpectedStartArrayToken()
    {
        return getCurrentToken() == JsonToken.START_ARRAY;
    }

    public Boolean nextBooleanValue()
        throws IOException, JsonParseException
    {
        JsonToken jsontoken = nextToken();
        if(jsontoken == JsonToken.VALUE_TRUE)
            return Boolean.TRUE;
        if(jsontoken == JsonToken.VALUE_FALSE)
            return Boolean.FALSE;
        else
            return null;
    }

    public boolean nextFieldName(SerializableString serializablestring)
        throws IOException, JsonParseException
    {
        return nextToken() == JsonToken.FIELD_NAME && serializablestring.getValue().equals(getCurrentName());
    }

    public int nextIntValue(int i)
        throws IOException, JsonParseException
    {
        if(nextToken() == JsonToken.VALUE_NUMBER_INT)
            i = getIntValue();
        return i;
    }

    public long nextLongValue(long l)
        throws IOException, JsonParseException
    {
        if(nextToken() == JsonToken.VALUE_NUMBER_INT)
            l = getLongValue();
        return l;
    }

    public String nextTextValue()
        throws IOException, JsonParseException
    {
        if(nextToken() == JsonToken.VALUE_STRING)
            return getText();
        else
            return null;
    }

    public abstract JsonToken nextToken()
        throws IOException, JsonParseException;

    public abstract JsonToken nextValue()
        throws IOException, JsonParseException;

    public abstract void overrideCurrentName(String s);

    public int readBinaryValue(Base64Variant base64variant, OutputStream outputstream)
        throws IOException, JsonParseException
    {
        _reportUnsupportedOperation();
        return 0;
    }

    public int readBinaryValue(OutputStream outputstream)
        throws IOException, JsonParseException
    {
        return readBinaryValue(Base64Variants.getDefaultVariant(), outputstream);
    }

    public Object readValueAs(TypeReference typereference)
        throws IOException, JsonProcessingException
    {
        ObjectCodec objectcodec = getCodec();
        if(objectcodec == null)
            throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into Java objects");
        else
            return objectcodec.readValue(this, typereference);
    }

    public Object readValueAs(Class class1)
        throws IOException, JsonProcessingException
    {
        ObjectCodec objectcodec = getCodec();
        if(objectcodec == null)
            throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into Java objects");
        else
            return objectcodec.readValue(this, class1);
    }

    public TreeNode readValueAsTree()
        throws IOException, JsonProcessingException
    {
        ObjectCodec objectcodec = getCodec();
        if(objectcodec == null)
            throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into JsonNode tree");
        else
            return objectcodec.readTree(this);
    }

    public Iterator readValuesAs(TypeReference typereference)
        throws IOException, JsonProcessingException
    {
        ObjectCodec objectcodec = getCodec();
        if(objectcodec == null)
            throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into Java objects");
        else
            return objectcodec.readValues(this, typereference);
    }

    public Iterator readValuesAs(Class class1)
        throws IOException, JsonProcessingException
    {
        ObjectCodec objectcodec = getCodec();
        if(objectcodec == null)
            throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into Java objects");
        else
            return objectcodec.readValues(this, class1);
    }

    public int releaseBuffered(OutputStream outputstream)
        throws IOException
    {
        return -1;
    }

    public int releaseBuffered(Writer writer)
        throws IOException
    {
        return -1;
    }

    public boolean requiresCustomCodec()
    {
        return false;
    }

    public abstract void setCodec(ObjectCodec objectcodec);

    public JsonParser setFeatureMask(int i)
    {
        _features = i;
        return this;
    }

    public void setSchema(FormatSchema formatschema)
    {
        throw new UnsupportedOperationException((new StringBuilder()).append("Parser of type ").append(getClass().getName()).append(" does not support schema of type '").append(formatschema.getSchemaType()).append("'").toString());
    }

    public abstract JsonParser skipChildren()
        throws IOException, JsonParseException;

    public abstract Version version();

    private static final int MAX_BYTE_I = 255;
    private static final int MAX_SHORT_I = 32767;
    private static final int MIN_BYTE_I = -128;
    private static final int MIN_SHORT_I = -32768;
    protected int _features;
}
