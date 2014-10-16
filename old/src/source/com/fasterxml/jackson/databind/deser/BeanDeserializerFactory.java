// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.deser.impl.FieldProperty;
import com.fasterxml.jackson.databind.deser.impl.MethodProperty;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedObjectIdGenerator;
import com.fasterxml.jackson.databind.deser.impl.SetterlessProperty;
import com.fasterxml.jackson.databind.deser.std.AtomicReferenceDeserializer;
import com.fasterxml.jackson.databind.deser.std.ThrowableDeserializer;
import com.fasterxml.jackson.databind.ext.OptionalHandlerFactory;
import com.fasterxml.jackson.databind.introspect.*;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.*;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

// Referenced classes of package com.fasterxml.jackson.databind.deser:
//            BasicDeserializerFactory, Deserializers, BeanDeserializerBuilder, ValueInstantiator, 
//            BeanDeserializerModifier, SettableBeanProperty, CreatorProperty, BeanDeserializer, 
//            SettableAnyProperty, DeserializerFactory

public class BeanDeserializerFactory extends BasicDeserializerFactory
    implements Serializable
{

    public BeanDeserializerFactory(DeserializerFactoryConfig deserializerfactoryconfig)
    {
        super(deserializerfactoryconfig);
    }

    protected JsonDeserializer _findCustomBeanDeserializer(JavaType javatype, DeserializationConfig deserializationconfig, BeanDescription beandescription)
        throws JsonMappingException
    {
        for(Iterator iterator = _factoryConfig.deserializers().iterator(); iterator.hasNext();)
        {
            JsonDeserializer jsondeserializer = ((Deserializers)iterator.next()).findBeanDeserializer(javatype, deserializationconfig, beandescription);
            if(jsondeserializer != null)
                return jsondeserializer;
        }

        return null;
    }

    protected void addBeanProps(DeserializationContext deserializationcontext, BeanDescription beandescription, BeanDeserializerBuilder beandeserializerbuilder)
        throws JsonMappingException
    {
        SettableBeanProperty asettablebeanproperty[];
        boolean flag;
        Iterator iterator1;
        asettablebeanproperty = beandeserializerbuilder.getValueInstantiator().getFromObjectArguments(deserializationcontext.getConfig());
        AnnotationIntrospector annotationintrospector = deserializationcontext.getAnnotationIntrospector();
        Boolean boolean1 = annotationintrospector.findIgnoreUnknownProperties(beandescription.getClassInfo());
        if(boolean1 != null)
            beandeserializerbuilder.setIgnoreUnknownProperties(boolean1.booleanValue());
        java.util.HashSet hashset = ArrayBuilders.arrayToSet(annotationintrospector.findPropertiesToIgnore(beandescription.getClassInfo()));
        for(Iterator iterator = hashset.iterator(); iterator.hasNext(); beandeserializerbuilder.addIgnorable((String)iterator.next()));
        AnnotatedMethod annotatedmethod = beandescription.findAnySetter();
        if(annotatedmethod != null)
            beandeserializerbuilder.setAnySetter(constructAnySetter(deserializationcontext, beandescription, annotatedmethod));
        if(annotatedmethod == null)
        {
            Set set = beandescription.getIgnoredPropertyNames();
            if(set != null)
            {
                for(Iterator iterator3 = set.iterator(); iterator3.hasNext(); beandeserializerbuilder.addIgnorable((String)iterator3.next()));
            }
        }
        List list;
        List list1;
        if(deserializationcontext.isEnabled(MapperFeature.USE_GETTERS_AS_SETTERS) && deserializationcontext.isEnabled(MapperFeature.AUTO_DETECT_GETTERS))
            flag = true;
        else
            flag = false;
        list = filterBeanProps(deserializationcontext, beandescription, beandeserializerbuilder, beandescription.findProperties(), hashset);
        if(_factoryConfig.hasDeserializerModifiers())
        {
            Iterator iterator2 = _factoryConfig.deserializerModifiers().iterator();
            for(list1 = list; iterator2.hasNext(); list1 = ((BeanDeserializerModifier)iterator2.next()).updateProperties(deserializationcontext.getConfig(), beandescription, list1));
        } else
        {
            list1 = list;
        }
        iterator1 = list1.iterator();
_L5:
        if(!iterator1.hasNext()) goto _L2; else goto _L1
_L1:
        BeanPropertyDefinition beanpropertydefinition;
        SettableBeanProperty settablebeanproperty;
        beanpropertydefinition = (BeanPropertyDefinition)iterator1.next();
        String s;
        int i;
        SettableBeanProperty settablebeanproperty1;
        if(beanpropertydefinition.hasSetter())
            settablebeanproperty = constructSettableProperty(deserializationcontext, beandescription, beanpropertydefinition, beanpropertydefinition.getSetter().getGenericParameterType(0));
        else
        if(beanpropertydefinition.hasField())
        {
            settablebeanproperty = constructSettableProperty(deserializationcontext, beandescription, beanpropertydefinition, beanpropertydefinition.getField().getGenericType());
        } else
        {
            if(!flag || !beanpropertydefinition.hasGetter())
                break MISSING_BLOCK_LABEL_620;
            Class class1 = beanpropertydefinition.getGetter().getRawType();
            if(!java/util/Collection.isAssignableFrom(class1) && !java/util/Map.isAssignableFrom(class1))
                break MISSING_BLOCK_LABEL_620;
            settablebeanproperty = constructSetterlessProperty(deserializationcontext, beandescription, beanpropertydefinition);
        }
_L7:
        if(!beanpropertydefinition.hasConstructorParameter()) goto _L4; else goto _L3
_L6:
        if(creatorproperty == null)
            throw deserializationcontext.mappingException((new StringBuilder()).append("Could not find creator property with name '").append(s).append("' (in class ").append(beandescription.getBeanClass().getName()).append(")").toString());
        if(settablebeanproperty != null)
            creatorproperty = creatorproperty.withFallbackSetter(settablebeanproperty);
        beandeserializerbuilder.addCreatorProperty(creatorproperty);
          goto _L5
_L4:
        if(settablebeanproperty != null)
        {
            Class aclass[] = beanpropertydefinition.findViews();
            if(aclass == null && !deserializationcontext.isEnabled(MapperFeature.DEFAULT_VIEW_INCLUSION))
                aclass = NO_VIEWS;
            settablebeanproperty.setViews(aclass);
            beandeserializerbuilder.addProperty(settablebeanproperty);
        }
          goto _L5
_L2:
        return;
_L3:
        s = beanpropertydefinition.getName();
        CreatorProperty creatorproperty;
        if(asettablebeanproperty != null)
        {
            i = asettablebeanproperty.length;
            for(int j = 0; j < i; j++)
            {
                settablebeanproperty1 = asettablebeanproperty[j];
                if(s.equals(settablebeanproperty1.getName()))
                {
                    creatorproperty = (CreatorProperty)settablebeanproperty1;
                    break MISSING_BLOCK_LABEL_400;
                }
            }

        }
        creatorproperty = null;
          goto _L6
        settablebeanproperty = null;
          goto _L7
    }

    protected void addInjectables(DeserializationContext deserializationcontext, BeanDescription beandescription, BeanDeserializerBuilder beandeserializerbuilder)
        throws JsonMappingException
    {
        Map map = beandescription.findInjectables();
        if(map != null)
        {
            boolean flag = deserializationcontext.canOverrideAccessModifiers();
            java.util.Map.Entry entry;
            AnnotatedMember annotatedmember;
            for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); beandeserializerbuilder.addInjectable(new PropertyName(annotatedmember.getName()), beandescription.resolveType(annotatedmember.getGenericType()), beandescription.getClassAnnotations(), annotatedmember, entry.getKey()))
            {
                entry = (java.util.Map.Entry)iterator.next();
                annotatedmember = (AnnotatedMember)entry.getValue();
                if(flag)
                    annotatedmember.fixAccess();
            }

        }
    }

    protected void addObjectIdReader(DeserializationContext deserializationcontext, BeanDescription beandescription, BeanDeserializerBuilder beandeserializerbuilder)
        throws JsonMappingException
    {
        ObjectIdInfo objectidinfo = beandescription.getObjectIdInfo();
        if(objectidinfo == null)
            return;
        Class class1 = objectidinfo.getGeneratorType();
        JavaType javatype1;
        Object obj;
        SettableBeanProperty settablebeanproperty;
        JsonDeserializer jsondeserializer;
        if(class1 == com/fasterxml/jackson/annotation/ObjectIdGenerators$PropertyGenerator)
        {
            PropertyName propertyname = objectidinfo.getPropertyName();
            settablebeanproperty = beandeserializerbuilder.findProperty(propertyname);
            if(settablebeanproperty == null)
                throw new IllegalArgumentException((new StringBuilder()).append("Invalid Object Id definition for ").append(beandescription.getBeanClass().getName()).append(": can not find property with name '").append(propertyname).append("'").toString());
            javatype1 = settablebeanproperty.getType();
            obj = new PropertyBasedObjectIdGenerator(objectidinfo.getScope());
        } else
        {
            JavaType javatype = deserializationcontext.constructType(class1);
            javatype1 = deserializationcontext.getTypeFactory().findTypeParameters(javatype, com/fasterxml/jackson/annotation/ObjectIdGenerator)[0];
            obj = deserializationcontext.objectIdGeneratorInstance(beandescription.getClassInfo(), objectidinfo);
            settablebeanproperty = null;
        }
        jsondeserializer = deserializationcontext.findRootValueDeserializer(javatype1);
        beandeserializerbuilder.setObjectIdReader(ObjectIdReader.construct(javatype1, objectidinfo.getPropertyName(), ((ObjectIdGenerator) (obj)), jsondeserializer, settablebeanproperty));
    }

    protected void addReferenceProperties(DeserializationContext deserializationcontext, BeanDescription beandescription, BeanDeserializerBuilder beandeserializerbuilder)
        throws JsonMappingException
    {
        Map map = beandescription.findBackReferenceProperties();
        if(map != null)
        {
            Iterator iterator = map.entrySet().iterator();
            while(iterator.hasNext()) 
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                String s = (String)entry.getKey();
                AnnotatedMember annotatedmember = (AnnotatedMember)entry.getValue();
                Object obj;
                if(annotatedmember instanceof AnnotatedMethod)
                    obj = ((AnnotatedMethod)annotatedmember).getGenericParameterType(0);
                else
                    obj = annotatedmember.getRawType();
                beandeserializerbuilder.addBackReferenceProperty(s, constructSettableProperty(deserializationcontext, beandescription, SimpleBeanPropertyDefinition.construct(deserializationcontext.getConfig(), annotatedmember), ((Type) (obj))));
            }
        }
    }

    public JsonDeserializer buildBeanDeserializer(DeserializationContext deserializationcontext, JavaType javatype, BeanDescription beandescription)
        throws JsonMappingException
    {
        ValueInstantiator valueinstantiator = findValueInstantiator(deserializationcontext, beandescription);
        BeanDeserializerBuilder beandeserializerbuilder = constructBeanDeserializerBuilder(deserializationcontext, beandescription);
        beandeserializerbuilder.setValueInstantiator(valueinstantiator);
        addBeanProps(deserializationcontext, beandescription, beandeserializerbuilder);
        addObjectIdReader(deserializationcontext, beandescription, beandeserializerbuilder);
        addReferenceProperties(deserializationcontext, beandescription, beandeserializerbuilder);
        addInjectables(deserializationcontext, beandescription, beandeserializerbuilder);
        DeserializationConfig deserializationconfig = deserializationcontext.getConfig();
        BeanDeserializerBuilder beandeserializerbuilder1;
        if(_factoryConfig.hasDeserializerModifiers())
        {
            Iterator iterator1 = _factoryConfig.deserializerModifiers().iterator();
            for(beandeserializerbuilder1 = beandeserializerbuilder; iterator1.hasNext(); beandeserializerbuilder1 = ((BeanDeserializerModifier)iterator1.next()).updateBuilder(deserializationconfig, beandescription, beandeserializerbuilder1));
        } else
        {
            beandeserializerbuilder1 = beandeserializerbuilder;
        }
        Object obj;
        Object obj1;
        if(javatype.isAbstract() && !valueinstantiator.canInstantiate())
            obj = beandeserializerbuilder1.buildAbstract();
        else
            obj = beandeserializerbuilder1.build();
        if(_factoryConfig.hasDeserializerModifiers())
        {
            Iterator iterator = _factoryConfig.deserializerModifiers().iterator();
            for(obj1 = obj; iterator.hasNext(); obj1 = ((BeanDeserializerModifier)iterator.next()).modifyDeserializer(deserializationconfig, beandescription, ((JsonDeserializer) (obj1))));
        } else
        {
            obj1 = obj;
        }
        return ((JsonDeserializer) (obj1));
    }

    protected JsonDeserializer buildBuilderBasedDeserializer(DeserializationContext deserializationcontext, JavaType javatype, BeanDescription beandescription)
        throws JsonMappingException
    {
        ValueInstantiator valueinstantiator = findValueInstantiator(deserializationcontext, beandescription);
        DeserializationConfig deserializationconfig = deserializationcontext.getConfig();
        BeanDeserializerBuilder beandeserializerbuilder = constructBeanDeserializerBuilder(deserializationcontext, beandescription);
        beandeserializerbuilder.setValueInstantiator(valueinstantiator);
        addBeanProps(deserializationcontext, beandescription, beandeserializerbuilder);
        addObjectIdReader(deserializationcontext, beandescription, beandeserializerbuilder);
        addReferenceProperties(deserializationcontext, beandescription, beandeserializerbuilder);
        addInjectables(deserializationcontext, beandescription, beandeserializerbuilder);
        com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder.Value value = beandescription.findPOJOBuilderConfig();
        String s;
        AnnotatedMethod annotatedmethod;
        if(value == null)
            s = "build";
        else
            s = value.buildMethodName;
        annotatedmethod = beandescription.findMethod(s, null);
        if(annotatedmethod != null && deserializationconfig.canOverrideAccessModifiers())
            ClassUtil.checkAndFixAccess(annotatedmethod.getMember());
        beandeserializerbuilder.setPOJOBuilder(annotatedmethod, value);
        if(_factoryConfig.hasDeserializerModifiers())
        {
            for(Iterator iterator1 = _factoryConfig.deserializerModifiers().iterator(); iterator1.hasNext();)
                beandeserializerbuilder = ((BeanDeserializerModifier)iterator1.next()).updateBuilder(deserializationconfig, beandescription, beandeserializerbuilder);

        }
        JsonDeserializer jsondeserializer = beandeserializerbuilder.buildBuilderBased(javatype, s);
        JsonDeserializer jsondeserializer1;
        if(_factoryConfig.hasDeserializerModifiers())
        {
            Iterator iterator = _factoryConfig.deserializerModifiers().iterator();
            for(jsondeserializer1 = jsondeserializer; iterator.hasNext(); jsondeserializer1 = ((BeanDeserializerModifier)iterator.next()).modifyDeserializer(deserializationconfig, beandescription, jsondeserializer1));
        } else
        {
            jsondeserializer1 = jsondeserializer;
        }
        return jsondeserializer1;
    }

    public JsonDeserializer buildThrowableDeserializer(DeserializationContext deserializationcontext, JavaType javatype, BeanDescription beandescription)
        throws JsonMappingException
    {
        DeserializationConfig deserializationconfig = deserializationcontext.getConfig();
        BeanDeserializerBuilder beandeserializerbuilder = constructBeanDeserializerBuilder(deserializationcontext, beandescription);
        beandeserializerbuilder.setValueInstantiator(findValueInstantiator(deserializationcontext, beandescription));
        addBeanProps(deserializationcontext, beandescription, beandeserializerbuilder);
        AnnotatedMethod annotatedmethod = beandescription.findMethod("initCause", INIT_CAUSE_PARAMS);
        if(annotatedmethod != null)
        {
            SettableBeanProperty settablebeanproperty = constructSettableProperty(deserializationcontext, beandescription, SimpleBeanPropertyDefinition.construct(deserializationcontext.getConfig(), annotatedmethod, "cause"), annotatedmethod.getGenericParameterType(0));
            if(settablebeanproperty != null)
                beandeserializerbuilder.addOrReplaceProperty(settablebeanproperty, true);
        }
        beandeserializerbuilder.addIgnorable("localizedMessage");
        beandeserializerbuilder.addIgnorable("suppressed");
        beandeserializerbuilder.addIgnorable("message");
        BeanDeserializerBuilder beandeserializerbuilder1;
        if(_factoryConfig.hasDeserializerModifiers())
        {
            Iterator iterator1 = _factoryConfig.deserializerModifiers().iterator();
            for(beandeserializerbuilder1 = beandeserializerbuilder; iterator1.hasNext(); beandeserializerbuilder1 = ((BeanDeserializerModifier)iterator1.next()).updateBuilder(deserializationconfig, beandescription, beandeserializerbuilder1));
        } else
        {
            beandeserializerbuilder1 = beandeserializerbuilder;
        }
        Object obj = beandeserializerbuilder1.build();
        if(obj instanceof BeanDeserializer)
            obj = new ThrowableDeserializer((BeanDeserializer)obj);
        Object obj1;
        if(_factoryConfig.hasDeserializerModifiers())
        {
            Iterator iterator = _factoryConfig.deserializerModifiers().iterator();
            for(obj1 = obj; iterator.hasNext(); obj1 = ((BeanDeserializerModifier)iterator.next()).modifyDeserializer(deserializationconfig, beandescription, ((JsonDeserializer) (obj1))));
        } else
        {
            obj1 = obj;
        }
        return ((JsonDeserializer) (obj1));
    }

    protected SettableAnyProperty constructAnySetter(DeserializationContext deserializationcontext, BeanDescription beandescription, AnnotatedMethod annotatedmethod)
        throws JsonMappingException
    {
        if(deserializationcontext.canOverrideAccessModifiers())
            annotatedmethod.fixAccess();
        JavaType javatype = beandescription.bindingsForBeanType().resolveType(annotatedmethod.getGenericParameterType(1));
        com.fasterxml.jackson.databind.BeanProperty.Std std = new com.fasterxml.jackson.databind.BeanProperty.Std(new PropertyName(annotatedmethod.getName()), javatype, null, beandescription.getClassAnnotations(), annotatedmethod, PropertyMetadata.STD_OPTIONAL);
        JavaType javatype1 = resolveType(deserializationcontext, beandescription, javatype, annotatedmethod);
        JsonDeserializer jsondeserializer = findDeserializerFromAnnotation(deserializationcontext, annotatedmethod);
        JavaType javatype2 = modifyTypeByAnnotation(deserializationcontext, annotatedmethod, javatype1);
        if(jsondeserializer == null)
            jsondeserializer = (JsonDeserializer)javatype2.getValueHandler();
        return new SettableAnyProperty(std, annotatedmethod, javatype2, jsondeserializer, (TypeDeserializer)javatype2.getTypeHandler());
    }

    protected BeanDeserializerBuilder constructBeanDeserializerBuilder(DeserializationContext deserializationcontext, BeanDescription beandescription)
    {
        return new BeanDeserializerBuilder(beandescription, deserializationcontext.getConfig());
    }

    protected SettableBeanProperty constructSettableProperty(DeserializationContext deserializationcontext, BeanDescription beandescription, BeanPropertyDefinition beanpropertydefinition, Type type)
        throws JsonMappingException
    {
        AnnotatedMember annotatedmember = beanpropertydefinition.getNonConstructorMutator();
        if(deserializationcontext.canOverrideAccessModifiers())
            annotatedmember.fixAccess();
        JavaType javatype = beandescription.resolveType(type);
        com.fasterxml.jackson.databind.BeanProperty.Std std = new com.fasterxml.jackson.databind.BeanProperty.Std(beanpropertydefinition.getFullName(), javatype, beanpropertydefinition.getWrapperName(), beandescription.getClassAnnotations(), annotatedmember, beanpropertydefinition.getMetadata());
        JavaType javatype1 = resolveType(deserializationcontext, beandescription, javatype, annotatedmember);
        if(javatype1 != javatype)
            std.withType(javatype1);
        JsonDeserializer jsondeserializer = findDeserializerFromAnnotation(deserializationcontext, annotatedmember);
        JavaType javatype2 = modifyTypeByAnnotation(deserializationcontext, annotatedmember, javatype1);
        TypeDeserializer typedeserializer = (TypeDeserializer)javatype2.getTypeHandler();
        Object obj;
        com.fasterxml.jackson.databind.AnnotationIntrospector.ReferenceProperty referenceproperty;
        if(annotatedmember instanceof AnnotatedMethod)
            obj = new MethodProperty(beanpropertydefinition, javatype2, typedeserializer, beandescription.getClassAnnotations(), (AnnotatedMethod)annotatedmember);
        else
            obj = new FieldProperty(beanpropertydefinition, javatype2, typedeserializer, beandescription.getClassAnnotations(), (AnnotatedField)annotatedmember);
        if(jsondeserializer != null)
            obj = ((SettableBeanProperty) (obj)).withValueDeserializer(jsondeserializer);
        referenceproperty = beanpropertydefinition.findReferenceType();
        if(referenceproperty != null && referenceproperty.isManagedReference())
            ((SettableBeanProperty) (obj)).setManagedReferenceName(referenceproperty.getName());
        return ((SettableBeanProperty) (obj));
    }

    protected SettableBeanProperty constructSetterlessProperty(DeserializationContext deserializationcontext, BeanDescription beandescription, BeanPropertyDefinition beanpropertydefinition)
        throws JsonMappingException
    {
        AnnotatedMethod annotatedmethod = beanpropertydefinition.getGetter();
        if(deserializationcontext.canOverrideAccessModifiers())
            annotatedmethod.fixAccess();
        JavaType javatype = annotatedmethod.getType(beandescription.bindingsForBeanType());
        JsonDeserializer jsondeserializer = findDeserializerFromAnnotation(deserializationcontext, annotatedmethod);
        JavaType javatype1 = modifyTypeByAnnotation(deserializationcontext, annotatedmethod, javatype);
        Object obj = new SetterlessProperty(beanpropertydefinition, javatype1, (TypeDeserializer)javatype1.getTypeHandler(), beandescription.getClassAnnotations(), annotatedmethod);
        if(jsondeserializer != null)
            obj = ((SettableBeanProperty) (obj)).withValueDeserializer(jsondeserializer);
        return ((SettableBeanProperty) (obj));
    }

    public JsonDeserializer createBeanDeserializer(DeserializationContext deserializationcontext, JavaType javatype, BeanDescription beandescription)
        throws JsonMappingException
    {
        DeserializationConfig deserializationconfig = deserializationcontext.getConfig();
        JsonDeserializer jsondeserializer = _findCustomBeanDeserializer(javatype, deserializationconfig, beandescription);
        if(jsondeserializer == null)
        {
            if(javatype.isThrowable())
                return buildThrowableDeserializer(deserializationcontext, javatype, beandescription);
            if(javatype.isAbstract())
            {
                JavaType javatype1 = materializeAbstractType(deserializationcontext, javatype, beandescription);
                if(javatype1 != null)
                    return buildBeanDeserializer(deserializationcontext, javatype1, deserializationconfig.introspect(javatype1));
            }
            jsondeserializer = findStdDeserializer(deserializationcontext, javatype, beandescription);
            if(jsondeserializer == null)
                if(!isPotentialBeanType(javatype.getRawClass()))
                    return null;
                else
                    return buildBeanDeserializer(deserializationcontext, javatype, beandescription);
        }
        return jsondeserializer;
    }

    public JsonDeserializer createBuilderBasedDeserializer(DeserializationContext deserializationcontext, JavaType javatype, BeanDescription beandescription, Class class1)
        throws JsonMappingException
    {
        JavaType javatype1 = deserializationcontext.constructType(class1);
        return buildBuilderBasedDeserializer(deserializationcontext, javatype, deserializationcontext.getConfig().introspectForBuilder(javatype1));
    }

    protected List filterBeanProps(DeserializationContext deserializationcontext, BeanDescription beandescription, BeanDeserializerBuilder beandeserializerbuilder, List list, Set set)
        throws JsonMappingException
    {
        ArrayList arraylist;
        HashMap hashmap;
        Iterator iterator;
        arraylist = new ArrayList(Math.max(4, list.size()));
        hashmap = new HashMap();
        iterator = list.iterator();
_L7:
        BeanPropertyDefinition beanpropertydefinition;
        String s;
        if(!iterator.hasNext())
            break; /* Loop/switch isn't completed */
        beanpropertydefinition = (BeanPropertyDefinition)iterator.next();
        s = beanpropertydefinition.getName();
        if(set.contains(s))
            continue; /* Loop/switch isn't completed */
        if(beanpropertydefinition.hasConstructorParameter()) goto _L2; else goto _L1
_L1:
        if(!beanpropertydefinition.hasSetter()) goto _L4; else goto _L3
_L3:
        Class class1 = beanpropertydefinition.getSetter().getRawParameterType(0);
_L5:
        if(class1 == null || !isIgnorableType(deserializationcontext.getConfig(), beandescription, class1, hashmap))
            break; /* Loop/switch isn't completed */
        beandeserializerbuilder.addIgnorable(s);
        continue; /* Loop/switch isn't completed */
_L4:
        boolean flag = beanpropertydefinition.hasField();
        class1 = null;
        if(flag)
            class1 = beanpropertydefinition.getField().getRawType();
        if(true) goto _L5; else goto _L2
_L2:
        arraylist.add(beanpropertydefinition);
        if(true) goto _L7; else goto _L6
_L6:
        return arraylist;
    }

    protected JsonDeserializer findOptionalStdDeserializer(DeserializationContext deserializationcontext, JavaType javatype, BeanDescription beandescription)
        throws JsonMappingException
    {
        return OptionalHandlerFactory.instance.findDeserializer(javatype, deserializationcontext.getConfig(), beandescription);
    }

    protected JsonDeserializer findStdDeserializer(DeserializationContext deserializationcontext, JavaType javatype, BeanDescription beandescription)
        throws JsonMappingException
    {
        JsonDeserializer jsondeserializer = findDefaultDeserializer(deserializationcontext, javatype, beandescription);
        if(jsondeserializer != null)
            return jsondeserializer;
        if(java/util/concurrent/atomic/AtomicReference.isAssignableFrom(javatype.getRawClass()))
        {
            JavaType ajavatype[] = deserializationcontext.getTypeFactory().findTypeParameters(javatype, java/util/concurrent/atomic/AtomicReference);
            JavaType javatype1;
            if(ajavatype == null || ajavatype.length < 1)
                javatype1 = TypeFactory.unknownType();
            else
                javatype1 = ajavatype[0];
            return new AtomicReferenceDeserializer(javatype1, findTypeDeserializer(deserializationcontext.getConfig(), javatype1), findDeserializerFromAnnotation(deserializationcontext, deserializationcontext.getConfig().introspectClassAnnotations(javatype1).getClassInfo()));
        } else
        {
            return findOptionalStdDeserializer(deserializationcontext, javatype, beandescription);
        }
    }

    protected boolean isIgnorableType(DeserializationConfig deserializationconfig, BeanDescription beandescription, Class class1, Map map)
    {
        Boolean boolean1 = (Boolean)map.get(class1);
        if(boolean1 == null)
        {
            BeanDescription beandescription1 = deserializationconfig.introspectClassAnnotations(class1);
            boolean1 = deserializationconfig.getAnnotationIntrospector().isIgnorableType(beandescription1.getClassInfo());
            if(boolean1 == null)
                boolean1 = Boolean.FALSE;
        }
        return boolean1.booleanValue();
    }

    protected boolean isPotentialBeanType(Class class1)
    {
        String s = ClassUtil.canBeABeanType(class1);
        if(s != null)
            throw new IllegalArgumentException((new StringBuilder()).append("Can not deserialize Class ").append(class1.getName()).append(" (of type ").append(s).append(") as a Bean").toString());
        if(ClassUtil.isProxyType(class1))
            throw new IllegalArgumentException((new StringBuilder()).append("Can not deserialize Proxy class ").append(class1.getName()).append(" as a Bean").toString());
        String s1 = ClassUtil.isLocalType(class1, true);
        if(s1 != null)
            throw new IllegalArgumentException((new StringBuilder()).append("Can not deserialize Class ").append(class1.getName()).append(" (of type ").append(s1).append(") as a Bean").toString());
        else
            return true;
    }

    protected JavaType materializeAbstractType(DeserializationContext deserializationcontext, JavaType javatype, BeanDescription beandescription)
        throws JsonMappingException
    {
        JavaType javatype1 = beandescription.getType();
        for(Iterator iterator = _factoryConfig.abstractTypeResolvers().iterator(); iterator.hasNext();)
        {
            JavaType javatype2 = ((AbstractTypeResolver)iterator.next()).resolveAbstractType(deserializationcontext.getConfig(), javatype1);
            if(javatype2 != null)
                return javatype2;
        }

        return null;
    }

    public DeserializerFactory withConfig(DeserializerFactoryConfig deserializerfactoryconfig)
    {
        if(_factoryConfig == deserializerfactoryconfig)
            return this;
        if(getClass() != com/fasterxml/jackson/databind/deser/BeanDeserializerFactory)
            throw new IllegalStateException((new StringBuilder()).append("Subtype of BeanDeserializerFactory (").append(getClass().getName()).append(") has not properly overridden method 'withAdditionalDeserializers': can not instantiate subtype with ").append("additional deserializer definitions").toString());
        else
            return new BeanDeserializerFactory(deserializerfactoryconfig);
    }

    private static final Class INIT_CAUSE_PARAMS[] = {
        java/lang/Throwable
    };
    private static final Class NO_VIEWS[] = new Class[0];
    public static final BeanDeserializerFactory instance = new BeanDeserializerFactory(new DeserializerFactoryConfig());
    private static final long serialVersionUID = 1L;

}
