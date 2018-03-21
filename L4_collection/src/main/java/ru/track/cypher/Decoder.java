package ru.track.cypher;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jetbrains.annotations.NotNull;


public class Decoder {

    // Расстояние между A-Z -> a-z
    public static final int SYMBOL_DIST = 32;

    private Map<Character, Character> cypher;

    /**
     * Конструктор строит гистограммы открытого домена и зашифрованного домена
     * Сортирует буквы в соответствие с их частотой и создает обратный шифр Map<Character, Character>
     *
     * @param domain - текст по кторому строим гистограмму языка
     */
    public Decoder(@NotNull String domain, @NotNull String encryptedDomain) {
        Map<Character, Integer> domainHist = createHist(domain);
        Map<Character, Integer> encryptedDomainHist = createHist(encryptedDomain);

        cypher = new LinkedHashMap<>();

        Iterator<Character> itr1 = domainHist.keySet().iterator();
        Iterator<Character> itr2 = encryptedDomainHist.keySet().iterator();

        while(itr1.hasNext() && itr2.hasNext()) {
            cypher.put(itr2.next(), itr1.next());
        }

    }

    public Map<Character, Character> getCypher() {
        return cypher;
    }

    /**
     * Применяет построенный шифр для расшифровки текста
     *
     * @param encoded зашифрованный текст
     * @return расшифровка
     */
    @NotNull
    public String decode(@NotNull String encoded) {

        String decodText;
        StringBuilder decText = new StringBuilder();

        //System.out.println(cypher);

        for (int i = 0; i < encoded.length(); i++) {
            if (encoded.charAt(i) >= 'a' && encoded.charAt(i) <= 'z'){
                //System.out.print("enc: " + encoded.charAt(i));
                //System.out.println(" dec: " + cypher.get(encoded.charAt(i)));
                decText.append(cypher.get(encoded.charAt(i)));
            }
            else {
                decText.append(encoded.charAt(i));
            }
        }

        decodText = decText.toString();
        //System.out.println("encoded: " + encoded);
        //System.out.println("decoded: " + decodText);

        return decodText;
    }

    /**
     * Считывает входной текст посимвольно, буквы сохраняет в мапу.
     * Большие буквы приводит к маленьким
     *
     *
     * @param text - входной текст
     * @return - мапа с частотой вхождения каждой буквы (Ключ - буква в нижнем регистре)
     * Мапа отсортирована по частоте. При итерировании на первой позиции наиболее частая буква
     */
    @NotNull
    Map<Character, Integer> createHist(@NotNull String text) {

        HashMap<Character, Integer> map = new HashMap<>();
        LinkedHashMap<Character, Integer> sortedMap= new LinkedHashMap<>();

        for (int i = 0; i < CypherUtil.SYMBOLS.length(); i++) {
            map.put(CypherUtil.SYMBOLS.charAt(i), 0);
        }

        for (int i = 0; i < text.length(); i++) {
            if (map.get(text.charAt(i))!= null){
                map.put(Character.toLowerCase(text.charAt(i)), map.get(text.charAt(i)) + 1);
            }
        }

        char[] letters = new char[CypherUtil.SYMBOLS.length()];
        System.arraycopy(CypherUtil.SYMBOLS.toCharArray(), 0, letters,0, CypherUtil.SYMBOLS.length());

        for (int i = 0; i < letters.length; i++) {
            for (int j = 0; j < letters.length - 1; j++) {
                if(map.get(letters[j]) < map.get(letters[j+1])) {
                    char ch = letters[j];
                    letters[j] = letters[j + 1];
                    letters[j + 1] = ch;
                }
            }
        }


        for (char ch: letters) {
            sortedMap.put(ch, map.get(ch));
        }

        return sortedMap;
    }

}
