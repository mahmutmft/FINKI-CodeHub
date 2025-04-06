# Да се напише командна процедура ren што ќе овозможи преименување на повеќе датотеки 
# одеднаш со помош на наредбата sed за пребарување и замена на текст.

if [ "$#" -lt 3 ] 
then
cat << HELP
    zadaca8.sh -- renames a number of files using sed regular expressions

    USAGE: zadaca8.sh 'regexp' 'replacement' files..
    
    EXAMPLE: rename all *.HTM files in *.html:
    ren 'HTM$' 'html' *.HTM

HELP
    exit 0
fi

OLD="$1"
NEW="$2"

shift 
shift

for f in $*
do
    if [ -f "$f" ]
    then
        newFile=`echo "$f" | sed "s/${OLD}/${NEW}/g"`
        if [ -f "$newfile" ]
        then
            echo "error"
        else
            echo "uspeshno preminuvan fajlot2 $f vo $newfile
            mv "$f" "$newFile"
        fi
    fi
done