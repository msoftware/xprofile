// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.measurement;

import com.newrelic.agent.android.instrumentation.MetricCategory;

// Referenced classes of package com.newrelic.agent.android.measurement:
//            BaseMeasurement, MeasurementType

public class CategorizedMeasurement extends BaseMeasurement
{

    public CategorizedMeasurement(MeasurementType measurementtype)
    {
        BaseMeasurement(measurementtype);
    }

    public MetricCategory getCategory()
    {
        return category;
    }

    public void setCategory(MetricCategory metriccategory)
    {
        category = metriccategory;
    }

    private MetricCategory category;
}
