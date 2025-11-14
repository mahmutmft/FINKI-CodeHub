# Да се напише командна процедура што ќе ги прикаже на екран броевите од 0 до 20. 

counter=0

while [ $counter -le 20 ]
do
    echo "$counter"
    counter=$(($counter+1))
    sleep 0.5
done