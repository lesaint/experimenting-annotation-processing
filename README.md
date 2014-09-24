annotation-processing-explained
===============================


This Maven project is made of a parent POM and several maven projects which references this parent POM.

When importing this project into IntelliJ IDEA, you must do a recursive import from the root directory of this Git project
or import each sub-project individually as a module.

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
````

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

