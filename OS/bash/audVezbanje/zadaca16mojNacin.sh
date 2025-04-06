#!/bin/bash

idx="$1"

if [ $# -lt 1 ]
then
    echo "USAGE: `basename $0` username"
fi

if [ -f "out.txt" ]
then
    rm -fr out.txt
fi

for prc in `ps -ef | awk -v idx=$idx '$1 == idx{print $2}'`
do  
    counter=0
    for prcs in `ps -ef | awk -v idx=$idx '$1 == idx {print $3}'`
    do
        if [[ "$prc" == "$prcs" ]]
        then
            counter=$(($counter + 1))
        fi
    done
    echo $prc $counter >> out.txt
done
