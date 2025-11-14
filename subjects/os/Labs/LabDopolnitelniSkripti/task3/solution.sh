grep 2010 ../files/drugs.csv | awk -F, '
BEGIN{max = -9999; drug = "" }
{
    price = $4 + 0; #Конвертираме во број со + 0
    if(price>max){
        max = price;
        drug = $2;
    }
}
END{
    print "Лекот со најголема цена во 2010 година е:", drug, "со цена:", max;
}  
'