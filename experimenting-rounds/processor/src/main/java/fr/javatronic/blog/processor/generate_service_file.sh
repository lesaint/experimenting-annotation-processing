#!/bin/bash

SERVICE_FILE="/home/lesaint/DEV/annotation-processing-explained/annotation-processing-experimentations/processor/src/main/resources/META-INF/services/javax.annotation.processing.Processor"
echo "fr.javatronic.blog.processor.Processor_001" > $SERVICE_FILE
for i in {2..50}; do
  printf -v j "fr.javatronic.blog.processor.Processor_%03d" $i
  echo "adding $j"
  echo "$j" >> $SERVICE_FILE
done
