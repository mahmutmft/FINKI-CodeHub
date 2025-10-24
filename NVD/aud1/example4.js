// Compute total grade sum for students above 10 by
// composing map, filter and reduce

const students = [
    {name: "Nick", grade: 10},
    {name: "John", grade: 15},
    {name: "Julia", grade: 19},
    {name: "Nathalie", grade: 9},
];

let totalGrade = students
    .filter(element => element.grade > 10)
    .map(element => element.grade)
    .reduce((prev, next) => prev + next, 0)

console.log(totalGrade)