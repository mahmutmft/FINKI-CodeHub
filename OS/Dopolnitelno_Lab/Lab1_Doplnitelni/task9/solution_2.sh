awk -F, 'BEGIN{
    i3=0;i5=0;i7=0;i9=0;
    cnt3=0;cnt5=0;cnt7=0;cnt9=0;
}
{
    if($4 ~ /Intel Core i3/ ){
        i3 += $7;
        cnt3++;
    }
    else if ($4 ~ /Intel Core i5/){
        i5 += $7;
        cnt5++;
    }
    else if ($4 ~ /Intel Core i7/){
        i7 += $7;
        cnt7++;
    }
    else if ($4 ~ /Intel Core i9/){
        i9 += $7;
        cnt9++;
    }
}
END{
    print "Intel I3 просечна цена: ", i3/cnt3;
    print "Intel I5 просечна цена: ", i5/cnt5;
    print "Intel I7 просечна цена: ", i7/cnt7;
    print "Intel I9 просечна цена: ", i9/cnt9;
}
' ../files/laptops.csv