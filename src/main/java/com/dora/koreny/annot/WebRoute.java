package com.dora.koreny.annot;


import javax.swing.text.html.FormSubmitEvent;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface WebRoute {
    public String path() default "/test";

    FormSubmitEvent.MethodType method() default FormSubmitEvent.MethodType.GET;
}
