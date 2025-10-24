/*
For the given array of user objects, write a function that:
• Removes duplicate users while keeping only the first occurrence of each email.
• After cleaning the array, extracts only the users’ names.
• Sorts the names alphabetically.
• Prints the result as a single comma-separated string.
*/

const data = [
    {id: 1, name: "Ana", email: "ana@example.com"},
    {id: 2, name: "Marko", email: "marko@example.com"},
    {id: 3, name: "Ana", email: "ana@example.com"},
    {id: 4, name: "Mila", email: "mila@example.com"},
    {id: 5, name: "John", email: "john@example.com"},
    {id: 6, name: "Jane", email: "jane@example.com"}
];

function filterOut(data) {
    // место каде ке проверуваме за дупликати
    const seen = new Set();

    const unique = data.filter(element => {
        if (seen.has(element.email)) {
            return false;
        } else {
            seen.add(element.email);
            return true;
        }
    });

    // мапирање на имињата 
    const name = unique.map(element => element.name);

    // сортирање по азбука
    const sortNames = name.sort();

    // спојување во една низа со запирка
    const result = sortNames.join(", ");

    console.log(result);
}

filterOut(data);
