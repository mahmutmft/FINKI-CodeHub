#!/bin/bash

file="$1"

if [ "$#" -ne 1 ]
then
    echo "Потребно е да имате само еден аргумент не повеќе"
    exit 1
fi

if [[ "$1" != *.csv ]]
then
    echo "Фајлот што сакате да го проверите треба да е .csv"
    exit 1
fi

if [ ! -f "$file" ]
then
    echo "Фајлот не постои"
    exit 1
fi

if [ ! -s "$file" ]
then
    echo "Фајлот е празен"
    exit 1

fi

awk -F, '
BEGIN {
    sumMath = sumScience = sumEnglish = sumHistory = 0
    totalStudents = 0
    lowestScores["math"] = lowestScores["science"] = lowestScores["english"] = lowestScores["history"] = 999999
    highestScores["math"] = highestScores["science"] = highestScores["english"] = highestScores["history"] = -1
    lowestScorerName["math"] = lowestScorerName["science"] = lowestScorerName["english"] = lowestScorerName["history"] = ""
    highestScorerName["math"] = highestScorerName["science"] = highestScorerName["english"] = highestScorerName["history"] = ""
}
NR > 1 && NF > 0 {
    sumMath += $3
    sumHistory += $6
    sumEnglish += $5
    sumScience += $4
    totalStudents++

    if ($3 != "" && $4 != "" && $5 != "" &&1 $6 != ""){
        if ($3 > highestScores["math"]) { highestScores["math"] = $3; highestScorerName["math"] = $2 }
        if ($4 > highestScores["science"]) { highestScores["science"] = $4; highestScorerName["science"] = $2 }
        if ($5 > highestScores["english"]) { highestScores["english"] = $5; highestScorerName["english"] = $2 }
        if ($6 > highestScores["history"]) { highestScores["history"] = $6; highestScorerName["history"] = $2 }

        if ($3 < lowestScores["math"]) { lowestScores["math"] = $3; lowestScorerName["math"] = $2 }
        if ($4 < lowestScores["science"]) { lowestScores["science"] = $4; lowestScorerName["science"] = $2 }
        if ($5 < lowestScores["english"]) { lowestScores["english"] = $5; lowestScorerName["english"] = $2 }
        if ($6 < lowestScores["history"]) { lowestScores["history"] = $6; lowestScorerName["history"] = $2 }
    }
    else{
        print "Колоните се празни"
    }
}
END {

    if (totalStudents > 0){
        avgMath = sumMath / totalStudents
        avgScience = sumScience / totalStudents
        avgEnglish = sumEnglish / totalStudents
        avgHistory = sumHistory / totalStudents

        print "Exam Score Analysis"
        print "-------------------"
        print "Total Number of Students: ", totalStudents
        print "\n"
        print "Subject Averages:"
        print "     Math: \t", avgMath
        print "     Science: \t", avgScience
        print "     English: \t", avgEnglish
        print "     History: \t", avgHistory
        print "\n"
        print "Subject Extreme Performers: "
        print "     Math - Highest:", highestScorerName["math"], "(Score:", highestScores["math"]")"", Lowest:", lowestScorerName["math"], "(Score:", lowestScores["math"]")"
        print "     Science - Highest:", highestScorerName["science"], "(Score:",highestScores["science"]")" ", Lowest:", lowestScorerName["science"], "(Score:", lowestScores["science"]")"
        print "     English - Highest:", highestScorerName["english"], "(Score:",highestScores["english"]")" ", Lowest:", lowestScorerName["english"], "(Score:", lowestScores["english"]")"
        print "     History - Highest:", highestScorerName["history"], "(Score:",highestScores["history"]")" ", Lowest:", lowestScorerName["history"], "(Score:", lowestScores["history"]")"
    
    }
    else {
        print "Бројот на студенти е премал:", totalStudents
    }
}
' $file