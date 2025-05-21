#!/bin/bash

mkdir even odd

for file in *
do
    if [[ "$file" != "odd" && "$file" != "even" ]]
    then
        wccount=`wc -c $file | awk '{print $1}'`
        wccount=$(($wccount%2))
        if [ "$wccount" == 0 ]
        then
            mv $file even/
        else
            mv $file odd/
        fi
    else
        echo "dir"
    fi
done 