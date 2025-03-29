awk -F, '
BEGIN{
    average = 0;
    counter = 0;
}
{
    if($3 == "Finance"){
        average += $5;
        counter++;
    }
    else if ($3 == "Sales"){
        average += $5;
        counter++;
    }
}
END{
    if (counter > 0) {
        print "Просечна плата на вработените во Sales и Finance е:", average / counter;
    } else {
        print "Нема вработени во Sales или Finance.";
    }
}' ../files/employees.csv
