package fr.javatronic.experiments.annotationprocessing.options;

import java.util.Map;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

/**
 * OptionAnnotationProcessor -
 *
 * @author Sébastien Lesaint
 */
@SupportedSourceVersion(SourceVersion.RELEASE_7)
@SupportedAnnotationTypes("*")
public class OptionAnnotationProcessor extends AbstractProcessor {
  @Override
  public synchronized void init(ProcessingEnvironment processingEnv) {
    super.init(processingEnv);

    System.out.println(getClass().getSimpleName() + " Supported options=" + getSupportedOptions());
    System.out.println("Options received:");
    for (Map.Entry<String, String> entry : processingEnv.getOptions().entrySet()) {
      System.out.println(String.format("   %s -> %s", entry.getKey(), entry.getValue()));
    }
  }

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    return false;
  }
}
