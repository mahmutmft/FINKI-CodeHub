awk -F, '{
    if($4 ~ /Intel Core i3/){
        cpu["i3"] += $7;
        cnt["i3"]++;
    }
    else if($4 ~ /Intel Core i5/){
        cpu["i5"] += $7;
        cnt["i5"]++;
    }
    else if($4 ~ /Intel Core i7/){
        cpu["i7"] += $7;
        cnt["i7"]++;
    }
    else if($4 ~ /Intel Core i9/){
        cpu["i9"] += $7;
        cnt["i9"]++;
    }
}
END{
    for(proc in cpu){
        print proc, cpu[proc]/cnt[proc];
    }
}' ../files/laptops.csv