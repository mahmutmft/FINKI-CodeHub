#!/bin/bash

file="$1"

if [ "$#" -ne 1 ]; then
    echo "Usage: $0 <csvfile>"
    exit 1
fi

if [ ! -f "$file" ]; then
    echo "Error: File '$file' not found."
    exit 1
fi

if [[ "$file" != *.csv ]]; then
    echo "Error: The file must have .csv extension."
    exit 1
fi

awk -F, 'BEGIN{
    math = 0;
    history = 0;
    english = 0;
    science = 0;
    studentCount=0;
    studentsMin["math"] = studentsMin["science"] = studentsMin["english"] = studentsMin["history"] = 999999;
    studentsMaxName["math"] = studentsMaxName["science"] = studentsMaxName["english"] = studentsMaxName["history"] = " ";
}
NR>1{
    math += $3;
    history += $6;
    english += $5;
    science += $4;
    studentCount++;
    if($3 > studentsMax["math"]){studentsMax["math"]=$3;studentsMaxName["math"]=$2;}
    if($4 > studentsMax["science"]){studentsMax["science"]=$4;studentsMaxName["science"]=$2;}
    if($5 > studentsMax["english"]){studentsMax["english"]=$5;studentsMaxName["english"]=$2;}
    if($6 > studentsMax["history"]){studentsMax["history"]=$6;studentsMaxName["history"]=$2;}
    if($3 < studentsMin["math"]){studentsMin["math"]=$3;studentsMinName["math"]=$2;}
    if($4 < studentsMin["science"]){studentsMin["science"]=$4;studentsMinName["science"]=$2}
    if($5 < studentsMin["english"]){studentsMin["english"]=$5;studentsMinName["english"]=$2}
    if($6 < studentsMin["history"]){studentsMin["history"]=$6;studentsMinName["history"]=$2}

}
END{
    math /= studentCount;science /= studentCount;english /= studentCount;history /= studentCount;
    print "Exam Score Analysis";
    print "-------------------";
    print "Total Number of Students: ", studentCount; 
    print "\n";
    print "Subject Averages:";
    print "     Math: \t", math;
    print "     Science: \t", science;
    print "     English: \t", english; 
    print "     History: \t", history; 
    print "\n";
    print "Subject Extreme Performers: ";
    print "     Math - Highest:", studentsMaxName["math"],"(Score: "studentsMax["math"]"),", "Lowest:", studentsMinName["math"],"(Score:",studentsMin["math"]")";
    print "     Science - Highest:", studentsMaxName["science"],"(Score: "studentsMax["science"]"),", "Lowest:", studentsMinName["science"],"(Score:",studentsMin["science"]")"; 
    print "     English - Highest:", studentsMaxName["english"],"(Score: "studentsMax["english"]"),", "Lowest:", studentsMinName["english"],"(Score:",studentsMin["english"]")"; 
    print "     History - Highest:", studentsMaxName["history"],"(Score: "studentsMax["history"]"),", "Lowest:", studentsMinName["history"],"(Score:",studentsMin["history"]")"; 
    }
' $file