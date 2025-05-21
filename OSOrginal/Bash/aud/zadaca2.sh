# Да се напише командна процедура што ќе провери дали првиот аргумент од командна линија
# е 1, 2 или 3 и ќе испечати соодветни пораки. Доколку не е ниту една од овие вредности да
# испечати порака за грешка. 

if [ "$1" = 1 ]
then
    echo "Prvata opcija e izberena"
elif [ "$1" = 2 ]
then 
    echo "Vtorata opcija e izberena"
elif [ "$1" = 3 ]
then 
    echo "Tretata opcija e izberena"
else
    echo "Izbravte pogreshna opcija"
fi

