## Анализа на статусот на датотеките во Git

Папката `SI/prvKolokvium` содржи и друга папка именувана `src`.

├── description.txt
├── README.md
├── Solution.md
├── src
│ ├── data.txt
│ ├── README.md
│ └── SourceCode.java
└── Task1.java

Соодветно потоа се извршуваат следните чекори:

1. Се додава код/текст во сите 6 датотеки.
2. Се извршуваат командите:
   ```sh
   git add "*.txt"
   git add *.java
   git add *.md
   ```
3. Се извршува командата:
   ```sh
   git commit -m "Some message here"
   ```
4. Се менуваат содржините на датотеките:
   - `src/data.txt`
   - `src/SourceCode.java`
   - `Task1.java`
5. Се извршува командата:
   ```sh
   git add *.java
   ```

### Статус на датотеките во Git

| Датотека              | Статус     |
| --------------------- | ---------- |
| `description.txt`     | unmodified |
| `README.md`           | unmodified |
| `Task1.java`          | staged     |
| `src/data.txt`        | modified   |
| `src/README.md`       | untracked  |
| `src/SourceCode.java` | untracked  |

Оваа анализа ги прикажува статусите на датотеките според животниот циклус на Git. Ако имаш дополнителни прашања, кажи ми!
