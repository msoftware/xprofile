// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.annotation;

import java.lang.annotation.Annotation;
import java.util.Locale;
import java.util.TimeZone;

public interface JsonFormat
    extends Annotation
{
    public static final class Shape extends Enum
    {

        public static Shape valueOf(String s)
        {
            return (Shape)Enum.valueOf(com/fasterxml/jackson/annotation/JsonFormat$Shape, s);
        }

        public static Shape[] values()
        {
            return (Shape[])$VALUES.clone();
        }

        public boolean isNumeric()
        {
            return this == NUMBER || this == NUMBER_INT || this == NUMBER_FLOAT;
        }

        public boolean isStructured()
        {
            return this == OBJECT || this == ARRAY;
        }

        private static final Shape $VALUES[];
        public static final Shape ANY;
        public static final Shape ARRAY;
        public static final Shape BOOLEAN;
        public static final Shape NUMBER;
        public static final Shape NUMBER_FLOAT;
        public static final Shape NUMBER_INT;
        public static final Shape OBJECT;
        public static final Shape SCALAR;
        public static final Shape STRING;

        static 
        {
            ANY = new Shape("ANY", 0);
            SCALAR = new Shape("SCALAR", 1);
            ARRAY = new Shape("ARRAY", 2);
            OBJECT = new Shape("OBJECT", 3);
            NUMBER = new Shape("NUMBER", 4);
            NUMBER_FLOAT = new Shape("NUMBER_FLOAT", 5);
            NUMBER_INT = new Shape("NUMBER_INT", 6);
            STRING = new Shape("STRING", 7);
            BOOLEAN = new Shape("BOOLEAN", 8);
            Shape ashape[] = new Shape[9];
            ashape[0] = ANY;
            ashape[1] = SCALAR;
            ashape[2] = ARRAY;
            ashape[3] = OBJECT;
            ashape[4] = NUMBER;
            ashape[5] = NUMBER_FLOAT;
            ashape[6] = NUMBER_INT;
            ashape[7] = STRING;
            ashape[8] = BOOLEAN;
            $VALUES = ashape;
        }

        private Shape(String s, int i)
        {
            super(s, i);
        }
    }

    public static class Value
    {

        public Locale getLocale()
        {
            return locale;
        }

        public String getPattern()
        {
            return pattern;
        }

        public Shape getShape()
        {
            return shape;
        }

        public TimeZone getTimeZone()
        {
            return timezone;
        }

        public Value withLocale(Locale locale1)
        {
            return new Value(pattern, shape, locale1, timezone);
        }

        public Value withPattern(String s)
        {
            return new Value(s, shape, locale, timezone);
        }

        public Value withShape(Shape shape1)
        {
            return new Value(pattern, shape1, locale, timezone);
        }

        public Value withTimeZone(TimeZone timezone1)
        {
            return new Value(pattern, shape, locale, timezone1);
        }

        private final Locale locale;
        private final String pattern;
        private final Shape shape;
        private final TimeZone timezone;

        public Value()
        {
            this("", Shape.ANY, "", "");
        }

        public Value(JsonFormat jsonformat)
        {
            this(jsonformat.pattern(), jsonformat.shape(), jsonformat.locale(), jsonformat.timezone());
        }

        public Value(String s, Shape shape1, String s1, String s2)
        {
            Locale locale1;
            TimeZone timezone1;
            if(s1 == null || s1.length() == 0 || "##default".equals(s1))
                locale1 = null;
            else
                locale1 = new Locale(s1);
            timezone1 = null;
            if(s2 != null)
            {
                int i = s2.length();
                timezone1 = null;
                if(i != 0)
                {
                    boolean flag = "##default".equals(s2);
                    timezone1 = null;
                    if(!flag)
                        timezone1 = TimeZone.getTimeZone(s2);
                }
            }
            this(s, shape1, locale1, timezone1);
        }

        public Value(String s, Shape shape1, Locale locale1, TimeZone timezone1)
        {
            pattern = s;
            shape = shape1;
            locale = locale1;
            timezone = timezone1;
        }
    }


    public abstract String locale();

    public abstract String pattern();

    public abstract Shape shape();

    public abstract String timezone();

    public static final String DEFAULT_LOCALE = "##default";
    public static final String DEFAULT_TIMEZONE = "##default";
}
