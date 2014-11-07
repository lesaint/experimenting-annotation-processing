#!/bin/bash

for i in {2..50}; do
  printf -v j "Class_%03d_001" $i
  printf -v k "Annotation_%03d" $i
  printf -v l "annotation_%03d" $i
  echo "generating class $j for annotation $k in directory $l"
  mkdir -p $l
  cp annotation_001/Class_001_001.java $l/$j.java
  sed -i "s/Class_001_001/$j/g" $l/$j.java
  sed -i "s/annotation_001/$l/g" $l/$j.java
  sed -i "s/Annotation_001/$k/g" $l/$j.java
done
