import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        String fileName = "students.txt";

        List<Student> students = createStudents();
        saveToFile(students, fileName);

        List<Student> loadedStudents = loadFromFile(fileName);

        loadedStudents.stream()
                .peek(System.out::println)
                .flatMap(student -> student.getBooks().stream())
                .sorted(Comparator.comparingInt(Book::getPages))
                .distinct()
                .filter(book -> book.getYear() >= 2000)
                .limit(3)
                .map(Book::getYear)
                .findFirst()
                .ifPresentOrElse(
                        year -> System.out.println("Год выпуска найденной книги: " + year),
                        () -> System.out.println("Подходящая книга отсутствует")
                );
    }

    static List<Student> createStudents() {

        List<Student> students = new ArrayList<>();

        List<Book> books1 = new ArrayList<>();
        books1.add(new Book("Философия Java", "Брюс Эккель", 1168, 2018));
        books1.add(new Book("Чистый код", "Роберт Мартин", 464, 2008));
        books1.add(new Book("Шаблоны проектирования", "Эрик Фримен", 656, 2004));
        books1.add(new Book("Изучаем Java", "Кэти Сьерра", 752, 2022));
        books1.add(new Book("Java 8 в действии", "Рауль-Габриэль Урма", 400, 2015));
        books1.add(new Book("Spring в действии", "Крейг Уоллс", 600, 2020));

        List<Book> books2 = new ArrayList<>();
        books2.add(new Book("Чистый код", "Роберт Мартин", 464, 2008));
        books2.add(new Book("Алгоритмы", "Роберт Седжвик", 800, 2011));
        books2.add(new Book("Микросервисы", "Крис Ричардсон", 500, 2019));
        books2.add(new Book("Паттерны", "Фримен", 450, 2005));
        books2.add(new Book("Effective Java", "Джошуа Блох", 416, 2018));
        books2.add(new Book("Java. Полное руководство", "Герберт Шилдт", 1200, 2021));

        List<Book> books3 = new ArrayList<>();
        books3.add(new Book("Философия Java", "Брюс Эккель", 1168, 2018));
        books3.add(new Book("Алгоритмы", "Роберт Седжвик", 800, 2011));
        books3.add(new Book("Head First Java", "Кэти Сьерра", 720, 2020));
        books3.add(new Book("Spring в действии", "Крейг Уоллс", 600, 2020));
        books3.add(new Book("Java 8 в действии", "Рауль-Габриэль Урма", 400, 2015));
        books3.add(new Book("Java для начинающих", "Барри Бёрд", 500, 2019));

        students.add(new Student("Милана", books1));
        students.add(new Student("Карина", books2));
        students.add(new Student("Настя", books3));

        return students;
    }

    static void saveToFile(List<Student> students, String fileName) {

        try {
            List<String> lines = new ArrayList<>();

            for (Student student : students) {
                for (Book book : student.getBooks()) {
                    lines.add(
                            student.getName() + "|" +
                                    book.getTitle() + "|" +
                                    book.getAuthor() + "|" +
                                    book.getPages() + "|" +
                                    book.getYear()
                    );
                }
            }

            Files.write(Path.of(fileName), lines);

        } catch (IOException e) {
            System.out.println("Ошибка сохранения");
        }
    }

    static List<Student> loadFromFile(String fileName) {

        List<Student> students = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Path.of(fileName));

            for (String line : lines) {

                String[] parts = line.split("\\|");

                if (parts.length < 5) {
                    continue;
                }

                String name = parts[0];
                String title = parts[1];
                String author = parts[2];
                int pages = Integer.parseInt(parts[3]);
                int year = Integer.parseInt(parts[4]);

                Book b = new Book(title, author, pages, year);

                Student s = null;
                for (Student st : students) {
                    if (st.getName().equals(name)) {
                        s = st;
                        break;
                    }
                }

                if (s != null) {
                    s.getBooks().add(b);
                } else {
                    List<Book> books = new ArrayList<>();
                    books.add(b);
                    students.add(new Student(name, books));
                }
            }

        } catch (IOException e) {
            System.out.println("Ошибка чтения");
        }

        return students;
    }
}