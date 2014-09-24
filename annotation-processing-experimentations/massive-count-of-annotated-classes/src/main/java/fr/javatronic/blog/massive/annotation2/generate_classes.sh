#!/bin/bash

for i in {2..999}; do
  printf -v j "Class_%03d" $i
  echo "generating $j"
  cp Class_001.java $j.java
  sed -i "s/Class_001/$j/g" $j.java
done
