#!/bin/bash

javac -d ./build -cp .:./lib/* *.java && java -cp ./build:./lib/* ApplicationManager