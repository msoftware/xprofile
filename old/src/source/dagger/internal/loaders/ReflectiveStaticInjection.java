// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package dagger.internal.loaders;

import dagger.internal.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public final class ReflectiveStaticInjection extends StaticInjection
{

    private ReflectiveStaticInjection(ClassLoader classloader, Field afield[])
    {
        fields = afield;
        loader = classloader;
    }

    public static StaticInjection create(Class class1)
    {
        ArrayList arraylist = new ArrayList();
        Field afield[] = class1.getDeclaredFields();
        int i = afield.length;
        for(int j = 0; j < i; j++)
        {
            Field field = afield[j];
            if(Modifier.isStatic(field.getModifiers()) && field.isAnnotationPresent(javax/inject/Inject))
            {
                field.setAccessible(true);
                arraylist.add(field);
            }
        }

        if(arraylist.isEmpty())
            throw new IllegalArgumentException((new StringBuilder()).append("No static injections: ").append(class1.getName()).toString());
        else
            return new ReflectiveStaticInjection(class1.getClassLoader(), (Field[])arraylist.toArray(new Field[arraylist.size()]));
    }

    public void attach(Linker linker)
    {
        bindings = new Binding[fields.length];
        for(int i = 0; i < fields.length; i++)
        {
            Field field = fields[i];
            String s = Keys.get(field.getGenericType(), field.getAnnotations(), field);
            bindings[i] = linker.requestBinding(s, field, loader);
        }

    }

    public void inject()
    {
        int i = 0;
        do
        {
            try
            {
                if(i >= fields.length)
                    break;
                fields[i].set(null, bindings[i].get());
            }
            catch(IllegalAccessException illegalaccessexception)
            {
                throw new AssertionError(illegalaccessexception);
            }
            i++;
        } while(true);
    }

    private Binding bindings[];
    private final Field fields[];
    private final ClassLoader loader;
}
