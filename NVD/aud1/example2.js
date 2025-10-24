/*
Create a script that filters out products that are out of stock
and uses array functions to:
• list the names of the available items
• calculate the total quantity of all available items
*/

let data = [
    { id: 1, name: "Keyboard", price: 30, qty: 2, inStock: true },
    { id: 2, name: "Mouse", price: 15, qty: 1, inStock: false },
    { id: 3, name: "Monitor", price: 120, qty: 1, inStock: true },
    { id: 4, name: "Headphones", price: 45, qty: 3, inStock: true },
    { id: 5, name: "USB Cable", price: 8, qty: 5, inStock: false }
];

function filterOut(array){
    // ги филтрираме тие продукти што се inStock
    const available = array.filter(element => element.inStock);

    // ги земаме имињата на тие продукти
    const names = available.map(element => element.name);

    // собираме колку има од нив вкупно
    const total = available.reduce((sum, available) => sum + available.qty, 0);

    console.log("Available items:", names);
    console.log("Total quantity:", total);
}

filterOut(data);
