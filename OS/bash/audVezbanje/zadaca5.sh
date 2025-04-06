#!/bin/bash

# Да се напише командна процедура што ќе одбројува одреден број секунди до почетокот на натпреварот. 

countdown="$1"
counter=0

while [ "$1" -gt $counter ]
do
    counter=$(($counter + 1))
    echo "$counter"
    sleep 0.1
done
