// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.Converter;
import java.lang.reflect.*;
import java.util.*;

// Referenced classes of package com.fasterxml.jackson.databind:
//            JavaType

public abstract class BeanDescription
{

    protected BeanDescription(JavaType javatype)
    {
        _type = javatype;
    }

    public abstract TypeBindings bindingsForBeanType();

    public abstract AnnotatedMember findAnyGetter();

    public abstract AnnotatedMethod findAnySetter();

    public abstract Map findBackReferenceProperties();

    public abstract AnnotatedConstructor findDefaultConstructor();

    public abstract Converter findDeserializationConverter();

    public abstract com.fasterxml.jackson.annotation.JsonFormat.Value findExpectedFormat(com.fasterxml.jackson.annotation.JsonFormat.Value value);

    public transient abstract Method findFactoryMethod(Class aclass[]);

    public abstract Map findInjectables();

    public abstract AnnotatedMethod findJsonValueMethod();

    public abstract AnnotatedMethod findMethod(String s, Class aclass[]);

    public abstract Class findPOJOBuilder();

    public abstract com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder.Value findPOJOBuilderConfig();

    public abstract List findProperties();

    public abstract Converter findSerializationConverter();

    public abstract com.fasterxml.jackson.annotation.JsonInclude.Include findSerializationInclusion(com.fasterxml.jackson.annotation.JsonInclude.Include include);

    public transient abstract Constructor findSingleArgConstructor(Class aclass[]);

    public Class getBeanClass()
    {
        return _type.getRawClass();
    }

    public abstract Annotations getClassAnnotations();

    public abstract AnnotatedClass getClassInfo();

    public abstract List getConstructors();

    public abstract List getFactoryMethods();

    public abstract Set getIgnoredPropertyNames();

    public abstract ObjectIdInfo getObjectIdInfo();

    public JavaType getType()
    {
        return _type;
    }

    public abstract boolean hasKnownClassAnnotations();

    public abstract Object instantiateBean(boolean flag);

    public abstract JavaType resolveType(Type type);

    protected final JavaType _type;
}
