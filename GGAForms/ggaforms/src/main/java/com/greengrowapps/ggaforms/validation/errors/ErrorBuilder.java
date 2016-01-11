package com.greengrowapps.ggaforms.validation.errors;

import android.content.res.Resources;

public interface ErrorBuilder {
    ValidationError build( Object ... params );
}
