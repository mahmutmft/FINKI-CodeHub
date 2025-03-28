awk -F, '
BEGIN{
    max = -Inf;
}
NR > 1{
    if ($3 > max) {
    max = $3;
    city = $2;
    month = $4;
    }
}
END {
    print "City:", city;
    print "Max kWh:", max;
    print "Month:", month;
}
' potroshuvacka.csv
