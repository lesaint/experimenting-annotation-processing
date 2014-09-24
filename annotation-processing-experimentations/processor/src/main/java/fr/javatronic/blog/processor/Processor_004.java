package fr.javatronic.blog.processor;

import java.util.Collections;
import java.util.Set;
import javax.annotation.processing.Completion;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

/**
 * Processor_004 -
 *
 * @author Sébastien Lesaint
 */
public class Processor_004 implements Processor {
  @Override
  public Set<String> getSupportedOptions() {
    return Collections.emptySet();
  }

  @Override
  public Set<String> getSupportedAnnotationTypes() {
    return Collections.singleton(Annotation_004.class.getCanonicalName());
  }

  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.RELEASE_6;
  }

  @Override
  public void init(ProcessingEnvironment processingEnv) {
    // nothing to do
  }

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    return true; // supported annotations belong to this Annotation Proessor, so we claim them by returning true
  }

  @Override
  public Iterable<? extends Completion> getCompletions(Element element, AnnotationMirror annotation,
                                                       ExecutableElement member, String userText) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }
}
