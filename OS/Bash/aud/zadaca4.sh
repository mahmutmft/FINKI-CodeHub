# Да се напише командна процедура smartzip што може да врши автоматско декомпресирање
# bzip2, gzip и zip компресирани датотеки.
# • Пример за користење на file командата:
# • $ file lf.gz
# • lf.gz: gzip compressed data, deflated, original filename,last modified: Mon Aug 27 23:09:18 2001, os: Unix

file1=$1

if [ $file1 = *.zip ]
then
    echo "Ova e zip file"
    unzip $file
elif [ $file = *.bz2 ]
then
    echo "Ova e bunzip file"
    bunzip2 $file
elif [ $file = *.gz ]
then 
    echo "Ova e gunzip file"
    gunzip $file
else
    echo "Nikoj od fajlovite ne e ni gunzip bunzip ni zip"
fi