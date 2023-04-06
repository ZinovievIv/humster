import java.util.ArrayList;
import java.util.Scanner;

public class Practicum {

    public static void main(String[] args) {
        HamsterFactory hamsterFactory = new HamsterFactory();
        hamsterFactory.start();
    }
}

class HamsterFactory {
    Scanner scanner = new Scanner(System.in);

    ArrayList<Hamster> humsterHome = new ArrayList<>(); // нужна структура данных, в которой можно хранить хомяков
    // мы не знаем, сколько будет хомяков: 10 или 10_000, поэтому нужна сущность, которая может изменять свой размер

    public void start() {
        System.out.println("Добро пожаловать на фабрику хомяков!");
        System.out.println("Здесь вы можете создавать, удалять и даже заменять одних хомяков на других");
        System.out.println("Для работы с фабрикой используйте следующие команды:");
        System.out.println("'Печать' — показать всех активных хомяков");
        System.out.println("'Создать [Имя]' — создать хомяка с выбранным именем");
        System.out.println("'Удалить [Индекс]' — удалить хомяка по выбранному индексу");
        System.out.println("'Очистить' — удалить всех, ранее созданных хомяков");
        System.out.println("'Заменить [Индекс] [Имя]' — заменить хомяка под выбранным индексом на нового с выбранным именем");
        System.out.println("'Размер' — вывести на экран текущее количество хомяков в хранилище");
        System.out.println("'Завершить' — завершить работу программы");

        while (true) {
            System.out.println("Введите команду...");

            String command = scanner.nextLine();

            if (command.equals("Завершить")) {
                System.out.println("Программа завершена! Спасибо, что пользуетесь нашей сетью хомячих фабрик");
                break;
            } else {
                executeCommand(command);
            } // если пользователь ввел команду "Завершить", надо закончить работу программы (остановить цикл)
            // при завершении работы вывести сообщение "Программа завершена! Спасибо, что пользуетесь нашей сетью хомячих фабрик"
            // иначе вызвать метод executeCommand(), который распознает команду и вызывает нужный метод для её обработки
        }
    }


    private void executeCommand(String command) {
        try {
            if (command.contains("Печать")) {
                executePrint();
            } else if (command.contains("Создать")) {
                String name = command.split(" ")[1];
                executeCreate(name);
            } else if (command.contains("Удалить")) {
                String param = command.split(" ")[1];
                try {
                    int index = Integer.parseInt(param);
                    executeRemoveByIndex(index);
                } catch (NumberFormatException exception) {
                    showErrorMessage();
                }
            } else if (command.contains("Очистить")) {
                executeClear();
            } else if (command.contains("Заменить")) {
                String[] params = command.split(" ");
                int index = Integer.parseInt(params[1]);
                String name = params[2];
                executeSet(index, name);
            } else if (command.contains("Размер")) {
                executeSize();
            } else {
                showErrorMessage();
            }
        } catch (Exception exception) {
            showErrorMessage();
        }
    }

    private void executePrint() {
        System.out.println("Вывожу актуальный список хомяков в хранилище:"); // выведите сообщение: "Вывожу актуальный список хомяков в хранилище:"
        for (int i = 0; i < humsterHome.size(); i++) {
            Hamster name = humsterHome.get(i);
            System.out.println("Хомяк " + "'" + name + "'" + ".");
        } // нужно напечатать всех хомяков, которые есть в хранилище в формате "Хомяк '[Имя хомяка]'"
    }

    private void executeCreate(String name) {
        Hamster newName = new Hamster(name);// создайте хомяка с именем [name] и добавьте его в ваше хранилище
        humsterHome.add(newName);
        System.out.println("Хомяк " + "'" + name + "'" + " создан и добавлен в хранилище");// выведите сообщение: "Хомяк '[name]' создан и добавлен в хранилище"
    }

    private void executeRemoveByIndex(int index) {
        for (int i = 0; i < humsterHome.size(); i++) {
            if (i == index) {
                System.out.println("Хомяк " + "'" + humsterHome.get(index) + "'" + " удален успешно");
                humsterHome.remove(index);
            } else {
                System.out.println("Хомяк по заданному индексу не существует");
            }
        } // если хомяк существует, надо удалить его и вывести сообщение: "Хомяк '[Имя удаленного хомяка]' удалён успешно"
        // иначе вывести сообщение: "Хомяка по заданному индексу не существует"
    }

    private void executeClear() {
        humsterHome.clear();
        System.out.println("Операция очистки завершена успешно! Все хомяки были удалены из хранилища");// очистить хранилище и вывести в лог сообщение: "Операция очистки завершена успешно! Все хомяки были удалены из хранилища"
    }

    private void executeSet(int index, String name) {
        Hamster newHumster = new Hamster(name); // создайте хомяка с именем [name] и замените им хомяка на позиции [index]
        System.out.println("Хомяк " + "'" + humsterHome.get(index) + "'" + " был успешно заменён на хомяка " + "'" + name + "'");
        humsterHome.set(index, newHumster);// выведите сообщение "Хомяк '[Имя удаленного хомяка]' был успешно заменён на хомяка '[Имя нового хомяка]'"

    }

    private void executeSize() {
        if (humsterHome.size() > 0) {
            System.out.println("Количество хомяков в хранилище равно " + humsterHome.size());
        } else {
            System.out.println("В хранилище нет хомяков");// если в хранилище есть хомяки, надо вывести сообщение: "Количество хомяков в хранилище равно [текущее количество хомяков в хранилище]"
            // иначе вывести: "В хранилище нет хомяков"
        }
    }

    private void showErrorMessage() {
        System.out.println("Неверная команда, попробуйте ещё раз.");// выведите сообщение: "Неверная команда, попробуйте ещё раз."
    }
}

    class Hamster {

        String name;

        public Hamster(String name) {
            this.name = name;
        }
    }

