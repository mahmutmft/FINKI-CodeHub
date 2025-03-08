#!/bin/bash

shell=$SHELL

if [ shell = '/bin/bash' ]; 
then
  echo "Vasata shell okolina e BASH"
else
 echo "Vasata shell okolina ne e BASH, tuku e $shell" 
fi
