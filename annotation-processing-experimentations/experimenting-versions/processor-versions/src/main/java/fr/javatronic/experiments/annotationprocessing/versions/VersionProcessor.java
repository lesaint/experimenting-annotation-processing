package fr.javatronic.experiments.annotationprocessing.versions;

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
@SupportedAnnotationTypes("java.lang.Deprecated")
public class VersionProcessor extends AbstractProcessor {
  @Override
  public synchronized void init(ProcessingEnvironment processingEnv) {
    super.init(processingEnv);

    System.out.println(getClass().getSimpleName() + " Current SourceVersion=" + processingEnv.getSourceVersion());
    System.out.println("Supported SourceVersion=" + getSupportedSourceVersion());
  }

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    return false;
  }
}
