#!/bin/bash

if [ $# -lt 1 ]
then
    echo "Please give atleast one argument"
    exit 1
fi

index="$1"

if [ -f "out.txt" ]
then 
    rm -rf out.txt
fi

last | awk -v idx="$index" '
BEGIN{
    timeHours = 0;
}
{
    gsub(/[()]/, "", $0)
    if($1 == idx){
        split($10,array,":")
        array[1] + 0 == array[1]
        array[2] + 0 == array[2]
        if (array[1] == "in"){}
        else{
            timeHours+= (array[1] * 60) + array[2]
        }
    }
}
END{
    print timeHours
}
' >> out.txt