package ru.track.cypher;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import org.jetbrains.annotations.NotNull;

/**
 * Вспомогательные методы шифрования/дешифрования
 */
public class CypherUtil {

    public static final String SYMBOLS = "abcdefghijklmnopqrstuvwxyz";

    /**
     * Генерирует таблицу подстановки - то есть каждой буква алфавита ставится в соответствие другая буква
     * Не должно быть пересечений (a -> x, b -> x). Маппинг уникальный
     *
     * @return таблицу подстановки шифра
     */
    @NotNull
    public static Map<Character, Character> generateCypher() {
        HashMap<Character, Character> code = new HashMap<>();
        char[] alphabet = new char[26];
        char[] key = new char[alphabet.length];
        alphabet = SYMBOLS.toCharArray();
        System.arraycopy(alphabet, 0, key, 0, alphabet.length);
        shuffle(key);
        for (int i = 0; i < alphabet.length; i++) {
            code.put(key[i], alphabet[i]);
        }

        return code;
    }

    static void shuffle(char[] array) {
        for (int i = array.length - 1; i > 0 ; i--) {
            Random random = new Random();
            int number = random.nextInt(i + 1);
            char ch = array[number];
            array[number] = array[i];
            array[i] = ch;
        }
    }

}
