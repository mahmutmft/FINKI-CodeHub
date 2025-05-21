#!/bin/bash

# Да се напише командна процедура што ќе провери дали shell околината на најавениот
# корисник е BASH. Доколку не е, да ја испише работната shell околина преку променливата $SHELL

if [ "$SHELL" != "/usr/bin/bash" ]
then
    echo "Okolinata netie bin/bash tuku ti e $SHELL"
else
    echo "Okolinata ti e $SHELL"
fi

