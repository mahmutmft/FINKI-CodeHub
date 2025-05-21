awk -F, '
BEGIN{
    max = -Inf;
    average = 0;
}
NR > 1{
    if ($3 > max) {
    id = $1;
    max = $3;
    city = $2;
    month = $4;
    counter++;
    average += $3;
    }
}
END {
    print "ID", id;
    print "City:", city;
    print "Max kWh:", max;
    print "Month:", month;
    print "Prosecnata potroshuvacka e", average/counter;
}
' ../files/potroshuvacka.csv
