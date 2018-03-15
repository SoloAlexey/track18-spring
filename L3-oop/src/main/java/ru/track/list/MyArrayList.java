package ru.track.list;

import java.util.NoSuchElementException;

/**
 * Должен наследовать List
 *
 * Должен иметь 2 конструктора
 * - без аргументов - создает внутренний массив дефолтного размера на ваш выбор
 * - с аргументом - начальный размер массива
 */
public class MyArrayList extends List {
    private final int defaultSize = 10;
    private int[] array;

    public MyArrayList() {
        array = new int[defaultSize];
    }

    public MyArrayList(int capacity) {
        array = new int[capacity];
    }

    @Override
    void add(int item) {
        if (array.length < size() + 1) {
            resize(array.length * 2 + 1);
        }
        array[size] = item;
        size++;
    }

    @Override
    int remove(int idx) throws NoSuchElementException {
        int removedItem;
        if ((idx < 0) || (idx > size - 1)){
            throw new NoSuchElementException();
        }
        else {
           removedItem = array[idx];

            for (int i = idx; i < size - 1; i++) {
                array[i] = array[i + 1];
            }
            size--;
            return  removedItem;
        }
    }

    @Override
    int get(int idx) throws NoSuchElementException {
        if ((idx < 0) || (idx > size - 1)) {
            throw new NoSuchElementException();
        }
        else {
            return array[idx];
        }
    }

    private void resize(int newLength) {
        int[] newArray = new int[newLength];
        System.arraycopy(array, 0, newArray, 0,array.length);
        array = newArray;
    }

    @Override
    int size() {
        return size;
    }
}
