// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.*;

public class SimpleBeanPropertyDefinition extends BeanPropertyDefinition
{

    public SimpleBeanPropertyDefinition(AnnotatedMember annotatedmember)
    {
        this(annotatedmember, annotatedmember.getName(), null);
    }

    public SimpleBeanPropertyDefinition(AnnotatedMember annotatedmember, String s)
    {
        this(annotatedmember, s, null);
    }

    private SimpleBeanPropertyDefinition(AnnotatedMember annotatedmember, String s, AnnotationIntrospector annotationintrospector)
    {
        _introspector = annotationintrospector;
        _member = annotatedmember;
        _name = s;
    }

    public static SimpleBeanPropertyDefinition construct(MapperConfig mapperconfig, AnnotatedMember annotatedmember)
    {
        String s = annotatedmember.getName();
        AnnotationIntrospector annotationintrospector;
        if(mapperconfig == null)
            annotationintrospector = null;
        else
            annotationintrospector = mapperconfig.getAnnotationIntrospector();
        return new SimpleBeanPropertyDefinition(annotatedmember, s, annotationintrospector);
    }

    public static SimpleBeanPropertyDefinition construct(MapperConfig mapperconfig, AnnotatedMember annotatedmember, String s)
    {
        AnnotationIntrospector annotationintrospector;
        if(mapperconfig == null)
            annotationintrospector = null;
        else
            annotationintrospector = mapperconfig.getAnnotationIntrospector();
        return new SimpleBeanPropertyDefinition(annotatedmember, s, annotationintrospector);
    }

    public AnnotatedMember getAccessor()
    {
        Object obj = getGetter();
        if(obj == null)
            obj = getField();
        return ((AnnotatedMember) (obj));
    }

    public AnnotatedParameter getConstructorParameter()
    {
        if(_member instanceof AnnotatedParameter)
            return (AnnotatedParameter)_member;
        else
            return null;
    }

    public AnnotatedField getField()
    {
        if(_member instanceof AnnotatedField)
            return (AnnotatedField)_member;
        else
            return null;
    }

    public PropertyName getFullName()
    {
        return new PropertyName(_name);
    }

    public AnnotatedMethod getGetter()
    {
        if((_member instanceof AnnotatedMethod) && ((AnnotatedMethod)_member).getParameterCount() == 0)
            return (AnnotatedMethod)_member;
        else
            return null;
    }

    public String getInternalName()
    {
        return getName();
    }

    public PropertyMetadata getMetadata()
    {
        return PropertyMetadata.STD_OPTIONAL;
    }

    public AnnotatedMember getMutator()
    {
        Object obj = getConstructorParameter();
        if(obj == null)
        {
            obj = getSetter();
            if(obj == null)
                obj = getField();
        }
        return ((AnnotatedMember) (obj));
    }

    public String getName()
    {
        return _name;
    }

    public AnnotatedMember getNonConstructorMutator()
    {
        Object obj = getSetter();
        if(obj == null)
            obj = getField();
        return ((AnnotatedMember) (obj));
    }

    public AnnotatedMember getPrimaryMember()
    {
        return _member;
    }

    public AnnotatedMethod getSetter()
    {
        if((_member instanceof AnnotatedMethod) && ((AnnotatedMethod)_member).getParameterCount() == 1)
            return (AnnotatedMethod)_member;
        else
            return null;
    }

    public PropertyName getWrapperName()
    {
        if(_introspector == null)
            return null;
        else
            return _introspector.findWrapperName(_member);
    }

    public boolean hasConstructorParameter()
    {
        return _member instanceof AnnotatedParameter;
    }

    public boolean hasField()
    {
        return _member instanceof AnnotatedField;
    }

    public boolean hasGetter()
    {
        return getGetter() != null;
    }

    public boolean hasSetter()
    {
        return getSetter() != null;
    }

    public boolean isExplicitlyIncluded()
    {
        return false;
    }

    public volatile BeanPropertyDefinition withName(PropertyName propertyname)
    {
        return withName(propertyname);
    }

    public volatile BeanPropertyDefinition withName(String s)
    {
        return withName(s);
    }

    public SimpleBeanPropertyDefinition withName(PropertyName propertyname)
    {
        return withSimpleName(propertyname.getSimpleName());
    }

    public SimpleBeanPropertyDefinition withName(String s)
    {
        return withSimpleName(s);
    }

    public volatile BeanPropertyDefinition withSimpleName(String s)
    {
        return withSimpleName(s);
    }

    public SimpleBeanPropertyDefinition withSimpleName(String s)
    {
        if(_name.equals(s))
            return this;
        else
            return new SimpleBeanPropertyDefinition(_member, s, _introspector);
    }

    protected final AnnotationIntrospector _introspector;
    protected final AnnotatedMember _member;
    protected final String _name;
}
