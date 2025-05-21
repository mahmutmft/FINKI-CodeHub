grep OS ../files/student_grades.csv | awk -F, '
BEGIN{
    average = 0;
    counter = 0;
}
{
    if ($3 > 51){
        average += $4;
        counter++;
        indeksi[counter] = $1;
        grades[counter] = $4;
        points[counter] = $3;
    }
}
END{
    print "Average grade:", average/counter;
    print "The Stundets who passed are with this \n index / grade /points:";
    for (i=1; i<= counter; i++){
        print indeksi[i], " ", grades[i], "\t ", points[i];
    }
}
'