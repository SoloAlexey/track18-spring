package ru.track;

import java.io.*;


/**
 * Задание 1: Реализовать два метода
 *
 * Формат файла: текстовый, на каждой его строке есть (или/или)
 * - целое число (int)
 * - текстовая строка
 * - пустая строка (пробелы)
 *
 * Числа складываем, строки соединяем через пробел, пустые строки пропускаем
 *
 *
 * Пример файла - words.txt в корне проекта
 *
 * ******************************************************************************************
 *  Пожалуйста, не меняйте сигнатуры методов! (название, аргументы, возвращаемое значение)
 *
 *  Можно дописывать новый код - вспомогательные методы, конструкторы, поля
 *
 * ******************************************************************************************
 *
 */
public class CountWords {

    String skipWord;


    public boolean isNumber(String string) {
        try {
            Integer.parseInt(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    public CountWords(String skipWord) throws FileNotFoundException, IOException, Exception {
        this.skipWord = skipWord;
    }

    /**
     * Метод на вход принимает объект File, изначально сумма = 0
     * Нужно пройти по всем строкам файла, и если в строке стоит целое число,
     * то надо добавить это число к сумме
     * @param file - файл с данными
     * @return - целое число - сумма всех чисел из файла
     */
    public long countNumbers(File file) throws Exception {
        long summ = 0;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        boolean flag = true;
        while(flag == true){
            String line = reader.readLine();
            if (line == null) {
                flag = false;
            }

            if (isNumber(line) == true) {
                summ += Integer.parseInt(line);
            }

        }
        return summ;
    }


    /**
     * Метод на вход принимает объект File, изначально результат= ""
     * Нужно пройти по всем строкам файла, и если в строка не пустая и не число
     * то надо присоединить ее к результату через пробел
     * @param file - файл с данными
     * @return - результирующая строка
     */
    public String concatWords(File file) throws Exception {
        StringBuilder str = new StringBuilder();
        boolean flag = true;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while(flag == true){
            String line = reader.readLine();
            if (line == null) {
                flag = false;
            }

            if (isNumber(line) != true) {
                if(line != null && !line.equals(skipWord) ) {
                    str.append(line + " ");
                }
            }

        }
        String s  = str.toString();
        return s;
    }

}

