# Citanje broj pravo od terminal

echo "Guess a number"
read number
echo "You guessed $number"

# Ova se koristi so ./script.sh i mozesh da stavash 
# promenlivi i mozesh da stavash kolku sakash primer:
# ./scripta.sh test color boja
# i ke gi dobieme na terminal

for color in "$@"
do
    echo $color
done

# Ova gi lista site fajlovi vo aud3 folderot
files=aud3/*

for file in $files
do
  echo $file
done

