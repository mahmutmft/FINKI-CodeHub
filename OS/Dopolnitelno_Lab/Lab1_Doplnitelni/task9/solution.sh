awk -F, '{
    if ($4 ~ /^Intel Core i3/) {
        procesori["i3"] +=$7;
        counter1++;
    }
    else if ($4 ~ /^Intel Core i5/) {
        procesori["i5"] +=$7;
        counter2++;
    }
    else if ($4 ~ /^Intel Core i7/) {
        procesori["i7"] +=$7;
        counter3++;
    }
    else if ($4 ~ /^Intel Core i9/) {
        procesori["i9"] +=$7;
        counter4++;
    }
    else{
        counter5++;
    }
}
    END {
        procesori["i3"] /= counter1;
        procesori["i5"] /= counter2;
        procesori["i7"] /= counter3;
        procesori["i9"] /= counter4;
        for(proc in procesori){
            print proc, procesori[proc];
        }
    }
' ../files/laptops.csv | sort
