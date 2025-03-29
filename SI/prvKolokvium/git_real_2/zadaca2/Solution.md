# Задача 2

Претпоставете дека работите на некој git репозиториум чии последни промени изгледаат како што е прикажано подолу. За секој изглед на едиторот во кој би ја промениле историјата на репозиториумот, изберете ја точната команда со која е повикан interactive rebase.

# Git Commit History

# Задача 1

Претпоставете дека работите на некој git репозиториум чии последни промени изгледаат како што е прикажано подолу. За секој изглед на едиторот во кој би ја промениле историјата на репозиториумот, изберете ја точната команда со која е повикан interactive rebase.

# Git Commit History

- 7890ghi Compute correlation

- 6789fgh Calculate similarities

- 5678efg Extract embeddings

- 4567def Read word pairs

- 3456cde Create vocabulary

- 2345bcd Add ReadMe

- 1234abc Initial commit

## Scenario 1:

pick 5678efg Extract embeddings
pick 6789fgh Calculate similarities

**Correct Command:**

не може да се добие овој изглед,

---

## Scenario 2:

pick 2345bcd Add ReadMe
pick 3456cde Create vocabulary
pick 4567def Read word pairs
pick 5678efg Extract embeddings
pick 6789fgh Calculate similarities
pick 7890ghi Compute correlation

**Correct Command:**

git rebase -i HEAD~6

---

## Scenario 3:

pick 1234abc Initial commit
pick 2345bcd Add ReadMe
pick 3456cde Create vocabulary
pick 4567def Read word pairs
pick 5678efg Extract embeddings
pick 6789fgh Calculate similarities
pick 7890ghi Compute correlation

**Correct Command:**

git rebase -i --root

---

## Scenario 4:

pick 4567def Read word pairs
pick 5678efg Extract embeddings
pick 6789fgh Calculate similarities
pick 7890ghi Compute correlation

**Correct Command:**

git rebase -i HEAD~4
