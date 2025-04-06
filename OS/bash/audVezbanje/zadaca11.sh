#!/bin/bash

# Напишете select јамка која ќе ја излиста секоја датотека во тековниот именик и ќе му
# овозможи на корисникот да ја погледне датотеката со одбирање на нејзиниот број.
# • Користете го стрингот “Exit Program” за прекинување на јамката.
# • Доколку корисникот одбере нешто што не е регуларна датотека, програмата треба да го
# идентификува тоа.

allfiles=`ls`

select FILE in $allfiles "Exit Program" "Test"
do
    if [ "$FILE" == "Exit Program" ]
    then
        break
    fi

    if [ "$FILE" == "Test" ]
    then
        echo "test"
        continue
    fi
    echo $FILE
    cat $FILE
done