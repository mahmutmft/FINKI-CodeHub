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

awk -F, 'BEGIN {
    math = science = english = history = 0;
    studentCount = 0;

    # Initialize minimum scores to a very high number
    studentsMin["math"] = studentsMin["science"] = studentsMin["english"] = studentsMin["history"] = 999999;

    # Initialize maximum scores to a very low number
    studentsMax["math"] = studentsMax["science"] = studentsMax["english"] = studentsMax["history"] = -999999;

    # Initialize names for maximum and minimum performers
    studentsMaxName["math"] = studentsMaxName["science"] = studentsMaxName["english"] = studentsMaxName["history"] = "";
    studentsMinName["math"] = studentsMinName["science"] = studentsMinName["english"] = studentsMinName["history"] = "";
}
NR > 1 {
    math += $3;
    science += $4;
    english += $5;
    history += $6;
    studentCount++;

    # Update maximum scores and corresponding student names
    if ($3 > studentsMax["math"]) { studentsMax["math"] = $3; studentsMaxName["math"] = $2; }
    if ($4 > studentsMax["science"]) { studentsMax["science"] = $4; studentsMaxName["science"] = $2; }
    if ($5 > studentsMax["english"]) { studentsMax["english"] = $5; studentsMaxName["english"] = $2; }
    if ($6 > studentsMax["history"]) { studentsMax["history"] = $6; studentsMaxName["history"] = $2; }

    # Update minimum scores and corresponding student names
    if ($3 < studentsMin["math"]) { studentsMin["math"] = $3; studentsMinName["math"] = $2; }
    if ($4 < studentsMin["science"]) { studentsMin["science"] = $4; studentsMinName["science"] = $2; }
    if ($5 < studentsMin["english"]) { studentsMin["english"] = $5; studentsMinName["english"] = $2; }
    if ($6 < studentsMin["history"]) { studentsMin["history"] = $6; studentsMinName["history"] = $2; }
}
END {
    # Calculate averages
    math /= studentCount;
    science /= studentCount;
    english /= studentCount;
    history /= studentCount;

    # Print results
    print "Exam Score Analysis";
    print "-------------------";
    printf "Total Number of Students: %d\n\n", studentCount;

    print "Subject Averages:";
    printf "     Math: \t %.2f\n", math;
    printf "     Science: \t %.2f\n", science;
    printf "     English: \t %.2f\n", english;
    printf "     History: \t %.2f\n\n", history;

    print "Subject Extreme Performers:";
    printf "     Math - Highest: %s (Score: %d), Lowest: %s (Score: %d)\n",
           studentsMaxName["math"], studentsMax["math"], studentsMinName["math"], studentsMin["math"];
    printf "     Science - Highest: %s (Score: %d), Lowest: %s (Score: %d)\n",
           studentsMaxName["science"], studentsMax["science"], studentsMinName["science"], studentsMin["science"];
    printf "     English - Highest: %s (Score: %d), Lowest: %s (Score: %d)\n",
           studentsMaxName["english"], studentsMax["english"], studentsMinName["english"], studentsMin["english"];
    printf "     History - Highest: %s (Score: %d), Lowest: %s (Score: %d)\n",
           studentsMaxName["history"], studentsMax["history"], studentsMinName["history"], studentsMin["history"];
}' "$file"