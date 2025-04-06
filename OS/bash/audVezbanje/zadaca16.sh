#!/bin/bash

index="$1"

if [ $# -lt 1 ]
then
    echo "USAGE: `basename $0` username"
    exit 1
fi

if [ -f "out.txt" ]
then
    rm -fr out.txt
fi

for proc in `ps -ef | grep "$index" | awk '{print $2}'` 
do 
    count=0
    for procs in `ps -ef | grep "$index" | awk '{print $3}'`
    do
        if [ "$proc" == "$procs" ]
        then
            count=$(($count+1))
        fi 
    done
    echo "$proc $count" >> out.txt
done

cat out.txt