#!/bin/bash
rm $1.class
javac -cp json-simple-1.1.1.jar $1.java
java -cp .:json-simple-1.1.1.jar $1
