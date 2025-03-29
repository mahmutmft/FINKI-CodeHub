awk -F, 'BEGIN{
    average=0;
}
{	
    if($1 ~ /-05-/){
        if($3 == "Luxury"){
            average+= $5;
            cnt++;
        }	
    }
}
END{
    print "Просечно време на изнајмување: "average/cnt;
}' ../files/rental_sys.csv
