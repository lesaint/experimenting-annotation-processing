#!/bin/bash

for i in {2..9999}; do
  printf -v j "Class_%04d" $i
  echo "generating $j"
  cp Class_0001.java $j.java
  sed -i "s/Class_0001/$j/g" $j.java
done
