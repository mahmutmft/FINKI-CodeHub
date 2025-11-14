#!/bin/bash

# Напишете командна процедура која вредноста на првиот аргумент ќе ја зголеми 3.5 пати.
# Дозволете две места после децималната точка.

PRICE="$1"

newprice=$(echo "$PRICE * 3.5" | bc)

echo $newprice

otherprice=`echo "scale=2; 3.5 * $PRICE" | bc `

echo $otherprice