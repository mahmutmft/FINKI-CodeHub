awk -F, 'NR>1 {
    if ($5 ~ /^Nvidia/){
        gpu["Nvidia"] += $7;
    }
    else if ($5 ~ /^AMD/){
        gpu["AMD"] += $7;
    }
}
END {
    print "AMD Grafickite:", gpu["AMD"];
    print "Nvidia Grafickite:", gpu["Nvidia"];
}' ../files/laptops.csv
