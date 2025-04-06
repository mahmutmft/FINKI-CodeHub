#!/bin/bash

statusInfo="$1"
yearInfo="$2"


if [ "$#" -lt 2 ]
then
    echo "USAGE: `basename $0` <STATUS> <MONTH in YYYY-MM>"
    exit 1
fi

if [[ ! $2 =~ ^[0-9]{4}-[0-9]{2}$ ]]
then
    echo "ERROR: Invalid date format. Please use YYYY-MM."
    exit 1
fi


# Variables

countDEBUG=0
countError=0
countWarn=0
countInfo=0
currentStatus=0

for type in `grep "$2" files/system_logs.tsv | awk '{print $3}'`
do
    if [[ "$type" == "DEBUG" ]]
    then
        countDEBUG=$(($countDEBUG + 1))
    elif [[ "$type" == "INFO" ]]
    then
        countInfo=$(($countInfo + 1))
    elif [[ "$type" == "ERROR" ]]
    then
        countError=$(($countError + 1))
    elif [[ "$type" == "WARN" ]]
    then
        countWarn=$(($countWarn + 1))
    fi

    if [[ "$type" == "$1" ]]
    then
        currentStatus=$(($currentStatus + 1))
    fi
done
commonStatus=0;
ip=""
for ip in `grep "$1" files/system_logs.tsv | awk '{print $4}'`
do
    counter=0;
    for ips in `grep "$1" files/system_logs.tsv | awk '{print $4}'`
    do
        if [[ "$ip" == "$ips" ]]
        then
            counter=$(($counter + 1))
        fi
    done

    if [ $counter -gt $commonStatus ]
    then
        commonStatus=$counter
        ip="$ip"
    fi
done

echo "Number of logs with status '$1' in month $2: $currentStatus"
echo "Most common IP address for logs with status '$1':" $ip
echo ""
echo "Count per status for month $2"
echo "    WARN   : $countWarn"
echo "    ERROR  : $countError"
echo "    DEBUG  : $countDEBUG"
echo "    INFO   : $countInfo"