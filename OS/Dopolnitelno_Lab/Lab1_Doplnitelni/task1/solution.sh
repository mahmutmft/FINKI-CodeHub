awk -F, '
BEGIN{
    max = -Inf;
}
NR > 1{
    if ($3 > max) {
    id = $1;
    max = $3;
    city = $2;
    month = $4;
    }
}
END {
    print "ID", id;
    print "City:", city;
    print "Max kWh:", max;
    print "Month:", month;
}
' ../files/potroshuvacka.csv
