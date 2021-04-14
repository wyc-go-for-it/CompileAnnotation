package com.example.lib_myprocessor;

import com.example.lib_annotation.annotation.DefaultAnnotation;
import com.example.lib_annotation.annotation.ViewById;
import com.example.lib_annotation.annotation.ViewMethod;
import com.google.auto.service.AutoService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

@AutoService(Processor.class)
public class DefaultProcessor extends AbstractProcessor {

    private Filer mFiler;
    private Elements elements;
    private Messager messager;
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv){
        mFiler = processingEnv.getFiler();
        elements = processingEnv.getElementUtils();
        messager = processingEnv.getMessager();
    }
    @Override
    public Set<String> getSupportedAnnotationTypes(){
        final HashSet<String> hashSet = new HashSet<>();
        hashSet.add("com.example.lib_annotation.annotation.DefaultAnnotation");
        hashSet.add("com.example.lib_annotation.annotation.ViewById");
        hashSet.add("com.example.lib_annotation.annotation.ViewMethod");
        return hashSet;
    }
    @Override
    public SourceVersion getSupportedSourceVersion(){
        return SourceVersion.latestSupported();
    }
    @Override
    public synchronized boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        messager.printMessage(Diagnostic.Kind.WARNING,"set--" + set.toString());

        for (TypeElement annotationType : set){
            messager.printMessage(Diagnostic.Kind.WARNING,"annotationType--" + annotationType.toString());

            final Set<? extends Element> hashSet = roundEnvironment.getElementsAnnotatedWith(annotationType);
            for (Element element : hashSet){
                ElementKind ik = element.getKind();
                if (ik == ElementKind.FIELD){
                    VariableElement variableElement = (VariableElement) element;

                    Set<? extends Modifier> modifiers = variableElement.getModifiers();

                    for (Modifier modifier : modifiers){
                        messager.printMessage(Diagnostic.Kind.WARNING,"Modifier" + "---" + modifier);
                    }

                    TypeMirror mirror = variableElement.asType();

                    messager.printMessage(Diagnostic.Kind.WARNING,"TypeMirror" + "---" + mirror);

                    messager.printMessage(Diagnostic.Kind.WARNING,"name" + "---" + variableElement.getSimpleName());

/*                    ViewById annotation = variableElement.getAnnotation(ViewById.class);
                    final int id = annotation.id();
                    messager.printMessage(Diagnostic.Kind.WARNING,"wyc" + id + "---" + variableElement.getSimpleName());*/

                    TypeElement typeElement = (TypeElement) variableElement.getEnclosingElement();
                    messager.printMessage(Diagnostic.Kind.WARNING,"typeElement--"  + typeElement.getQualifiedName());


                }else if (ik == ElementKind.METHOD){
                    ExecutableElement executableElement = (ExecutableElement) element;
                    messager.printMessage(Diagnostic.Kind.WARNING,"ClassTypeElement--"  + executableElement.getEnclosingElement());

                    Set<? extends Modifier> modifiers = executableElement.getModifiers();

                    for (Modifier modifier : modifiers){
                        messager.printMessage(Diagnostic.Kind.WARNING,"Modifier" + "---" + modifier);
                    }
                    List<? extends VariableElement> parameters  = executableElement.getParameters();

                    for (VariableElement parameter : parameters){
                        messager.printMessage(Diagnostic.Kind.WARNING,"parameterName ---" + parameter +
                                "---parametersTypeMirror--" + parameter.asType() + "--EnclosingElement--" + parameter.getEnclosingElement());

                        messager.printMessage(Diagnostic.Kind.WARNING,"TypeMirror" + "---" + parameter.asType() + "---TypeMirror DeclaredType--" + (parameter.asType() instanceof DeclaredType));
                    }


                    TypeMirror mirror = executableElement.asType();

                    messager.printMessage(Diagnostic.Kind.WARNING,"TypeMirror" + "---" + mirror + "---TypeMirror DeclaredType--" + (mirror instanceof DeclaredType));

                    messager.printMessage(Diagnostic.Kind.WARNING,"name" + "---" + executableElement.getSimpleName());
                }
            }
        }

        return false;
    }
}