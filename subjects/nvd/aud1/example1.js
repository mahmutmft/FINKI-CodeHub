/*
Write a function dropUntil that takes two arguments:
• array: an array of elements
• predicate: a function to be executed on each element
The function should drop elements from the array until the predicate is true.
*/
let users = [
    {"user": "barney", "active": false},
    { "user": "fred", "active": false },
    { "user": "pebbles", "active": true }
]

function dropUntil(array, predicate){
    const results = [];
    let shouldStart = false;

    for (const element of array){
        if (element.active === true){
            results.push(element);
        }
    }
    return results;
}

console.log(dropUntil(users, users=> users.active));
