# ShapesApplication - Спецификација

## Опис на проблемот
Да се креира класа `ShapesApplication` која управува со податоци за повеќе прозорци каде се исцртуваат геометриски слики (квадрати).

## Барања за класата

### Конструктор
- `ShapesApplication()` - стандарден конструктор

### Методи

#### `int readCanvases(InputStream inputStream)`
- Чита информации за прозорците од влезен поток
- Секој ред содржи: `canvas_id size_1 size_2 size_3 ... size_n`
- Каде:
  - `canvas_id` = идентификатор на прозорецот
  - `size_1, size_2, ...` = должини на страните на квадратите исцртани на тој прозорец
- Враќа: вкупен број на успешно прочитани квадрати за сите прозорци

#### `void printLargestCanvasTo(OutputStream outputStream)`
- Го печати прозорецот чии квадрати имаат најголем вкупен периметар
- Формат на печатење: `canvas_id squares_count total_squares_perimeter`

## Примери за влез/излез

### Пример влез
```
canvas1 10 20 30
canvas2 15 25 35 45
canvas3 5 10 15 20 25
```

### Очекувана обработка
- За `canvas1`: 3 квадрати со периметри 40, 80, 120 → вкупно периметар = 240
- За `canvas2`: 4 квадрати со периметри 60, 100, 140, 180 → вкупно периметар = 480  
- За `canvas3`: 5 квадрати со периметри 20, 40, 60, 80, 100 → вкупно периметар = 300

### Очекуван излез
```
canvas2 4 480
```

## Белешки за имплементација

### Клучни пресметки
- Периметар на квадрат = 4 × должина_на_страна
- Вкупно број на квадрати = број на големини за тој прозорец
- Вкупно периметар = збир на сите индивидуални периметри на квадрати

### Специјални случаи за разгледување
- Празен влезен поток
- Прозорци без квадрати
- Повеќе прозорци со ист вкупен периметар
- Невалидни нумерички вредности

### Структури на податоци
- Потребно е чување на информации за прозорците (ID, број на квадрати, вкупен периметар)
- Користење на соодветна колекција за следење на прозорците
- Споредба на прозорците по вкупен периметар за наоѓање на најголемиот

---

# ShapesApplication - Specification

## Problem Description
Create a class `ShapesApplication` that manages data about multiple canvases where geometric shapes (squares) are drawn.

## Class Requirements

### Constructor
- `ShapesApplication()` - default constructor

### Methods

#### `int readCanvases(InputStream inputStream)`
- Reads canvas information from an input stream
- Each line contains: `canvas_id size_1 size_2 size_3 ... size_n`
- Where:
  - `canvas_id` = identifier of the canvas
  - `size_1, size_2, ...` = side lengths of squares drawn on that canvas
- Returns: total number of successfully read squares across all canvases

#### `void printLargestCanvasTo(OutputStream outputStream)`
- Prints the canvas with squares that have the largest total perimeter
- Output format: `canvas_id squares_count total_squares_perimeter`

## Input/Output Examples

### Example Input
```
canvas1 10 20 30
canvas2 15 25 35 45
canvas3 5 10 15 20 25
```

### Expected Processing
- For `canvas1`: 3 squares with perimeters 40, 80, 120 → total perimeter = 240
- For `canvas2`: 4 squares with perimeters 60, 100, 140, 180 → total perimeter = 480  
- For `canvas3`: 5 squares with perimeters 20, 40, 60, 80, 100 → total perimeter = 300

### Expected Output
```
canvas2 4 480
```

## Implementation Notes

### Key Calculations
- Perimeter of a square = 4 × side_length
- Total squares count = number of sizes for that canvas
- Total perimeter = sum of all individual square perimeters

### Edge Cases to Consider
- Empty input stream
- Canvases with no squares
- Multiple canvases with same total perimeter
- Invalid numeric values

### Data Structures
- Need to store canvas information (ID, square count, total perimeter)
- Use appropriate collection to track canvases
- Compare canvases by total perimeter to find largest