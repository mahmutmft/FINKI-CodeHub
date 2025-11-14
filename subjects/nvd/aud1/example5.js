let data = [];

async function fetchData() {
    const response = await fetch('https://dummyjson.com/products');
    const json = await response.json();
    data = json.products;

    addAverageRatings(data);
    printCategories();
    sortArray(data);
}

function printCategories() {
    const categories = [...new Set(data.map(element => element.category))].sort();
    console.log(categories)
}

function addAverageRatings(array) {

    array.forEach(element => {
        if (element.reviews.length > 0) {
            let sum = 0;
            element.reviews.forEach(review => {
                sum += review.rating;
            })
            element.averageRating = sum / element.reviews.length;
        }
    })
}

function sortArray(array) {
    const top3 = [...array].sort((a, b) => b.averageRating - a.averageRating).slice(0, 3);
    const last3 = [...array].sort((a, b) => a.averageRating - b.averageRating).slice(0, 3);
    console.log("\n⬆️ Highest avg rating:");
    console.table(top3.map(element => ({
        id: element.id,
        title: element.title,
        category: element.category,
        avg: +element.averageRating.toFixed(2)
    })));

    console.log("\n⬇️ Lowest avg rating:");
    console.table(last3.map(p => ({
        id: p.id, title: p.title, category: p.category, avg: +p.averageRating.toFixed(2)
    })));
}

function preview() {
    console.table(
        data.slice(0, 5).map(p => ({
            title: p.title,
            category: p.category,
            avg: Number(p.averageRating?.toFixed(2) || 0)
        }))
    );
}

fetchData().then(preview);