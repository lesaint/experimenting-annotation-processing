package fr.javatronic.experiments.annotationprocessing.versions;

/**
 * SomeClass - This class is not annotated with any specific annotation, only {@link Deprecated}. This should be
 * enough to trigger Annotation Processing and call to {@link VersionProcessor}
 *
 * @author Sébastien Lesaint
 */
@Deprecated
public class SomeClass {
}
