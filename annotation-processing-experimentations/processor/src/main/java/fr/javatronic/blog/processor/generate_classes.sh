#!/bin/bash

for i in {2..50}; do
  printf -v j "Annotation_%03d" $i
  printf -v k "Processor_%03d" $i
  echo "generating $j and $k"
  cp Annotation_001.java $j.java
  cp Processor_001.java $k.java
  sed -i "s/Annotation_001/$j/g" $j.java
  sed -i "s/Annotation_001/$j/g" $k.java
  sed -i "s/Processor_001/$k/g" $k.java
done
