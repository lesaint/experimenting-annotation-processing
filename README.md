annotation-processing-explained
===============================


This Maven project is made of a parent POM and several maven projects which references this parent POM.

When importing this project into IntelliJ IDEA, you must do a recursive import from the root directory of this Git project or import each sub-project individually as a module.

## parent POM

Defines some common POM description elements such as dependency management, plugin management, ...

```sh
cd [clone of this Git project]
mvn clean install
```

## referencing-generated-code/failing-example

This is a single module project that **does not compile**. This is the point of this project.

```sh
cd [clone of this Git project]/referencing-generated-code/failing-example
mvn clean install
```

## experimentations on annotation processing

Located in the ```annotation-processing-experimentations``` subdirectory of the Git Project, this Maven multi-module
project is experimenting with annotation processing.

### Massive count of annotated classes

I have used this project to investigate how the Javac compiler (1.7) builds rounds of annotation processing and trying
to figure out if given a lot of source files, they will be split in more than one round.

#### Test 1

* 999 classes with annotation ```@Annotation1``` in a single package
* 999 classes with annotation ```@Annotation2``` in a single package
* ```Processor1``` is registered to process ```@Annotation1```
* ```Processor2``` is registered to process ```@Annotation2```

Result : only one round with all source files.

#### Test 2

* same clases as above
* 9999 classes with annotation ```@Annotation1``` in a addition subpackage

Result : the same, only one round with all source files.

### Massive count of annotation processors

I have used this project to investigate how the Javac compiler (1.7) builds rounds of annotation processing and trying
to figure out if given a lot of annotation processors, they will be split in more than one round.

#### Test 1

* 50 annotations
* one annotation pocessor for each one
* one class for each annotation on its own subpackage

Result : only one round with all source files and all annotation processors

## Experimentations on Annotation Processor options

### annotation-processing-experimentations/experimenting-options

This is a multi-module Maven project composed of two modules.

#### processor-options module

Defines a single Annotation Processor ```OptionAnnotationProcessor```.

This Annotation Processor extends ```AbstractProcessor``` and uses annotations to defines that is supports all annotations and ```SourceVersion.RELEASE_7```.

It *does not* defines any supported options, as, we will see later, this is just useless with ```Javac```.

#### test-options module

Defines a single class ```SomeClass```, annotated with ```@Deprecated``` so that annotation processing actually occurs.

The Maven compiler plugin is configured to display annotation processing logs and to pass several options to the Annotation Processor using the ```-A``` argument of ```Javac```:

```
-Aoption1 -AOption2=valueOfOption2 -AA -AB= -Acom.acme.Processor.enable
```

#### observed ```Javac``` behavior

I initially wrote ```OptionAnnotationProcessor``` with a ```@SupportedOptions``` annotation which declared several option names. I intentionally used various case flavours to test case sensitivity. I compiled the project and noticed the ```Map``` returned by ```ProcessingEnvironment#getOptions()``` contained all the values above.

I added extra ones, all there.

I then removed the ```@SupportedOptions``` completely and the content of the ```ProcessingEnvironment#getOptions()``` was still the same.

I ran this test with Java 7 and Java 8, same result.

To make sure the filtering of the options is not disabled when there is only one Annotation Processor, I added another Annotation Processor to the build. ```SecondProcessor``` does declare supporting some specific options. Behavior observed: both Annotation Processor have access to a ```Map``` with the same content: all the options.

```
Round 1:
    input files: {fr.javatronic.experiments.annotationprocessing.options.SomeClass}
    annotations: [java.lang.Deprecated]
    last round: false
OptionAnnotationProcessor Supported options=[]
Options received:
   option1 -> null
   Option2 -> valueOfOption2
   A -> null
   B -> null
   com.acme.Processor.enable -> null
Processor fr.javatronic.experiments.annotationprocessing.options.OptionAnnotationProcessor matches [java.lang.Deprecated] and returns false.
SecondProcessor Supported options=[com.acme.Processor]
Options received:
   option1 -> null
   Option2 -> valueOfOption2
   A -> null
   B -> null
   com.acme.Processor.enable -> null
Processor fr.javatronic.experiments.annotationprocessing.options.SecondProcessor matches [java.lang.Deprecated] and returns false.
Round 2:
    input files: {}
    annotations: []
    last round: true
```

Conclusion: the value returned by ```Processor#getSupportedOptions``` is simply ignored by ```Javac``` and the ```Map``` returned by ```ProcessingEnvironment#getOptions()``` containes the values of all the ```Javac``` arguments starting with ```-A```.

## Experimentations on versions

### annotation-processing-experimentations/experimenting-versions

This module is made of two submodules.

* processor-versions: defines a single Annotation Processor: `VersionProcessor`
  - supports annotation `@Deprecated`
* test-versions: module compiled with the `VersionProcessor` to test versions
  - just defines a single class `SomeClass` annotated with `@Deprecated` to trigger annotation processing

Here is how to experiment with versions using these two modules. The following are supposed to be done in sequence. You will need a JDK 1.7 and JDK 1.8 installed.

#### compiling the processor with the declared version

Run `mvn clean install` directly in the `experimenting-versions` directory.

If you're are compiling with a Java 1.7 JDK, compilation will be ok as `VersionProcessor` declares supported `SourceVersion.RELEASE_7`.

#### compiling the processor with a more recent version

Modify `VersionProcessor` to support `SourceVersion.RELEASE_6` and recompile using JDK 1.7.

Build is also ok as Java is fully backward compatible.

#### compiling the processor with an older version

Change it to `SourceVersion.RELEASE_8`.

You get a compilation error because value `RELEASE_8` does not exist in enum `SourceVersion` in JDK 1.7.

```
annotation-processing-experimentations/experimenting-versions/processor-versions/src/main/java/fr/javatronic/experiments/annotationprocessing/versions/VersionProcessor.java:[17,38] cannot find symbol
```

Change your `JAVA_HOME` to point to a Java 8 JDK and recompile.

It works (of course!).

#### compiling source with a processor declaring a more recent version

Change your `JAVA_HOME` back to JDK 1.7 and compile only the `test-versions` module.

You are going to compile a module with Java 1.7 using an Annotation Processor `VersionProcessor` compile using Java 1.8 and declaring supporting `SourceVersion.RELEASE_8`.

You get another compilation error!

```
Fatal error compiling: java.lang.EnumConstantNotPresentException: javax.lang.model.SourceVersion.RELEASE_8
```

Here we can really wonder what is the point of declaring a suppported version:

* declare a lower version that the one used to compile, it works anyway since Java is backward compatible
* declare a higher version the the one used to compile, the compilation will fail because the version is indicated via an enum value, which can not exist in the current of Java since it is older.

