package shell;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Hashtable;
import java.util.Set;

public class Message {
    protected static Hashtable<String, String> messages;
    protected static Set<String> availableLanguages;
    protected static String currentLanguage = "ru";
    static {
        Hashtable<String, String> hashtable1 = new Hashtable();
                hashtable1.put("console_prefix", ">>> ");
        hashtable1.put("available_command", "%s - недоступная команда. \n Введите \"help\" для справки.\n");
                hashtable1.put("helpCommand_text", "help : вывести справку по доступным командам\ninfo : вывести в стандартный поток вывода информацию о коллекции.\nshow : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\ninsert null {element} : добавить новый элемент с заданным ключом\nupdate id {element} : обновить значение элемента коллекции, id которого равен заданному\nremove_key null : удалить элемент из коллекции по его ключу\nclear : очистить коллекцию\nsave : сохранить коллекцию в файл\nexecute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\nexit : завершить программу (без сохранения в файл)\nremove_greater {element} : удалить из коллекции все элементы, превышающие заданный\nremove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный\nreplace_if_lowe null {element} : заменить значение по ключу, если новое значение меньше старого\ncount_by_start_date startDate : вывести количество элементов, значение поля startDate которых равно заданному\nfilter_contains_name name : вывести элементы, значение поля name которых содержит заданную подстроку\nprint_field_descending_organization : вывести значения поля organization всех элементов в порядке убывания\n");
                hashtable1.put("infoCommand_format", "=== Информация о колекции ===\nПоследнее сохранение: %s;\nКоличество элементов: %d;\nРабочий файл: %s\n");
                hashtable1.put("showCommand_emptyCollection", "Колекция пуста.\n");
                hashtable1.put("shell_incorrectVarName", "Некорректное имя переменной.\nВведите новое имя: ");
                hashtable1.put("insertCommand_invalidData", "Некорректные данные\n");
                hashtable1.put("identifier_does_not_exist", "Такого идентификатора не существует.\n");
                hashtable1.put("executeCommand_Fail", "Не удалось найти файл со скриптом.\n");
                hashtable1.put("countByStartDate", "Количество дат равных заданному равно:");
                hashtable1.put("countByStartDate_fail", "По заданному времени не было найдено ни одного совпадения");
                hashtable1.put("shell_invalidFileFormat", "Неверная структура файла\n");

                hashtable1.put("workerForm_nameField", "Введите имя рабочего: ");
                hashtable1.put("coordinatesForm_XField", "Введите координату x: ");
                hashtable1.put("coordinatesForm_YField", "Введите координату y: ");
                hashtable1.put("workerForm_salaryField", "Введите зарплату рабочего: ");

                hashtable1.put("workerForm_startDateField", "Введите дату, когда рабочего устроили в формате ГГ-ММ-ДД : ");
                hashtable1.put("workerForm_startDateField_year", "Введите год, когда рабочего устроили:");

                hashtable1.put("workerForm_positionField", "Введите должность рабочего: ");
                hashtable1.put("workerForm_statusField", "Введите статус рабочего: ");
                hashtable1.put("workerForm_organizationCountField", "Введите количество сотрудников организации: ");
                hashtable1.put("workerForm_organizationTypeField", "Введите тип организации: ");
                hashtable1.put("workerForm_organizationAddressField", "Введите адрес организации: ");
                hashtable1.put("workerForm_addressStreetField", "Введите название улицы: ");
                hashtable1.put("workerForm_addressTownField", "Введите название города: ");


                hashtable1.put("workerForm_invalidName", "Имя не должно быть пустым или NULL.\n");
                hashtable1.put("workerForm_invalidX", "Неверное значение координаты x.\n");
                hashtable1.put("workerForm_invalidSalary", "Зарплата должна быть больше нуля.\n");
                hashtable1.put("workerForm_invalidPosition", "Неверный тип. Тип может быть null. Доступные типы: MANAGER, LABORER, HEAD_OF_DIVISION, HEAD_OF_DEPARTMENT.\n");
                hashtable1.put( "workerForm_invalidStatus", "Неверный тип. Тип не может быть null. Доступные типы:FIRED, HIRED, RECOMMENDED_FOR_PROMOTION, REGULAR, PROBATION.\n");
                hashtable1.put("organization_invalidCount", "Неверное количество, оно должно быть больше нуля.\n");
                hashtable1.put("organization_invalidType", "Неверный тип. Тип не может быть null. Доступные типы: COMMERCIAL, PUBLIC, GOVERNMENT, TRUST, PRIVATE_LIMITED_COMPANY.\n");
                hashtable1.put("organization_invalidAddress", "The street and the city couldn't be equal to NULL at the same time.\n");
                hashtable1.put("workerForm_invalidStartDate" , "Введен неправильный формат времени!\n");

                hashtable1.put("recursion", "При выполнении скрипта возникает рекурсия");

                messages = hashtable1;
    }

    static String getMessage(String messageCode) {
        return messages.get(messageCode);
    }

    static void changeLanguage(String language) {
        if (availableLanguages.contains(language)) {
            currentLanguage = language;
        }
    }
}
