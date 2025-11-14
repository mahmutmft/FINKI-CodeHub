# C++ OOP Cheatsheet

## 1. Classes and Encapsulation

### Basic Class Structure
```cpp
class ClassName {
private:
    // Private data members
    int privateMember;
    char name[50];
    
public:
    // Constructors, destructors, methods
    ClassName(); // Default constructor
    ClassName(int value, const char* name); // Parameterized constructor
    ClassName(const ClassName &other); // Copy constructor
    ~ClassName(); // Destructor
    
    // Operator overloads
    ClassName& operator=(const ClassName &other);
    
    // Methods
    void display();
};
```

### Enum
```cpp
enum Type {
    POP, RAP, ROCK // POP=0, RAP=1, ROCK=2
};
```

## 2. Constructors & Destructors

### Default Constructor
```cpp
ClassName::ClassName() {
    privateMember = 0;
    strcpy(name, "");
}
```

### Constructor with Parameters
```cpp
ClassName::ClassName(int value, const char* n) {
    privateMember = value;
    strcpy(name, n); // For char arrays use strcpy
}
```

### Constructor with Default Parameters
```cpp
ClassName(const char *name = "", int value = 0, Type type = Type(RAP)) {
    strcpy(this->name, name);
    this->value = value;
    this->type = type;
}
```

### Copy Constructor
```cpp
ClassName::ClassName(const ClassName &other) {
    this->privateMember = other.privateMember;
    strcpy(this->name, other.name);
    
    // For dynamic arrays:
    // this->array = new Type[other.size];
    // for (int i = 0; i < other.size; i++) {
    //     this->array[i] = other.array[i];
    // }
}
```

### Destructor
```cpp
ClassName::~ClassName() {
    // Free dynamic memory if needed
    // delete[] array;
}
```

## 3. Operators

### Assignment Operator (=)
```cpp
ClassName& ClassName::operator=(const ClassName &other) {
    if (this != &other) {  // Self-assignment check
        // Free existing resources if needed
        // delete[] array;
        
        // Copy simple members
        this->privateMember = other.privateMember;
        strcpy(this->name, other.name);
        
        // Deep copy for dynamic memory
        // this->array = new Type[other.size];
        // for (int i = 0; i < other.size; i++) {
        //     this->array[i] = other.array[i];
        // }
    }
    return *this;  // Return reference to allow chaining
}
```

### Output Operator (<<)
```cpp
friend ostream& operator<<(ostream &out, const ClassName &obj) {
    out << obj.name << " " << obj.privateMember;
    return out;
}
```

### Input Operator (>>)
```cpp
friend istream& operator>>(istream &in, ClassName &obj) {
    in >> obj.name >> obj.privateMember;
    return in;
}
```

### Addition Assignment (+=) for Collections
```cpp
ClassName& ClassName::operator+=(const Element &element) {
    // Create new array with one more element
    Element *temp = new Element[size + 1];
    
    // Copy old elements
    for (int i = 0; i < size; i++) {
        temp[i] = array[i];
    }
    
    // Add new element at the end
    temp[size] = element;
    size++;
    
    // Replace old array with new one
    delete[] array;
    array = temp;
    
    return *this;
}
```

### Comparison Operators
```cpp
// Equality
bool ClassName::operator==(const ClassName &other) const {
    return this->privateMember == other.privateMember && 
           strcmp(this->name, other.name) == 0;
}

// String comparison
bool isSame(const ClassName &other) {
    return strcmp(this->name, other.name) == 0;
}

// Less than
bool ClassName::operator<(const ClassName &other) const {
    return this->privateMember < other.privateMember;
}

// Greater than
bool ClassName::operator>(const ClassName &other) const {
    return other < *this;
}
```

### Increment/Decrement Operators
```cpp
// Pre-increment
ClassName& ClassName::operator++() {
    ++this->privateMember;
    return *this;
}

// Post-increment
ClassName ClassName::operator++(int) {
    ClassName temp = *this;
    ++(*this);
    return temp;
}
```

## 4. Class with Array of Objects

### Class Definition
```cpp
class Container {
private:
    Element *elements;  // Dynamic array of objects
    int size;
    
public:
    Container();
    Container(int size);
    Container(const Container &other);
    ~Container();
    
    Container& operator=(const Container &other);
    void addElement(const Element &element);
};
```

### Constructor
```cpp
Container::Container(int size = 0) {
    this->size = size;
    this->elements = new Element[size];
    // Note: Elements will be initialized with their default constructor
}
```

### Copy Constructor
```cpp
Container::Container(const Container &other) {
    this->size = other.size;
    this->elements = new Element[other.size];
    
    for (int i = 0; i < other.size; i++) {
        this->elements[i] = other.elements[i];
    }
}
```

### Assignment Operator
```cpp
Container& Container::operator=(const Container &other) {
    if (this != &other) {
        delete[] this->elements;
        
        this->size = other.size;
        this->elements = new Element[other.size];
        
        for (int i = 0; i < other.size; i++) {
            this->elements[i] = other.elements[i];
        }
    }
    return *this;
}
```

### Add Element Method
```cpp
void Container::addElement(const Element &element) {
    Element *temp = new Element[size + 1];
    
    for (int i = 0; i < size; i++) {
        temp[i] = elements[i];
    }
    
    temp[size] = element;
    size++;
    
    delete[] elements;
    elements = temp;
}
```

## 5. Getters and Setters

```cpp
// Getter
int getValue() const {
    return privateMember;
}

// Setter
void setValue(int value) {
    this->privateMember = value;
}
```

## 6. Static Members

### Declaration in Class
```cpp
class ClassName {
private:
    static int count;  // Static member declaration
public:
    static int getCount();  // Static method declaration
};
```

### Definition Outside Class
```cpp
int ClassName::count = 0;  // Static member initialization

int ClassName::getCount() {
    return count;
}
```

## 7. Inheritance

```cpp
class Base {
protected:
    int baseValue;
public:
    Base(int value) : baseValue(value) {}
    virtual void display() {
        cout << "Base: " << baseValue << endl;
    }
};

class Derived : public Base {
private:
    int derivedValue;
public:
    Derived(int baseVal, int derivedVal) : Base(baseVal), derivedValue(derivedVal) {}
    void display() override {
        cout << "Derived: " << baseValue << ", " << derivedValue << endl;
    }
};
```

## 8. Common Mistakes to Avoid

1. Not using `strcpy()` for character arrays
2. Not checking for self-assignment in `operator=`
3. Forgetting to free memory in destructor
4. Not implementing deep copy in copy constructor and assignment operator
5. Forgetting to return `*this` in assignment operators
6. Not handling dynamic memory correctly when resizing arrays