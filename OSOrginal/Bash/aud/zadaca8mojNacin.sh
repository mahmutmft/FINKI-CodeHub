#!/bin/bash

echo "help for command example"

read input

if [ "$input" == "help" ]
then
cat << HELP
    EXAMPLE: bash ren.sh '.htm' 'html' *.htm
HELP
    exit 0
fi

OLD="$1"
NEW="$2"

shift 
shift

for f in $*
do
    if [ -f "$f" ]
    then
        currentfile=$(echo $f)
        newfile=$(echo $f | awk -v old="$OLD" -v new="$NEW" '
        {
            if ($0 ~ old "$"){
                split($0, array, ".");
                array[2] = new
                print array[1] "." array[2];
            }
        }
        ')
    fi
    echo $newfile
    mv $currentfile $newfile
done