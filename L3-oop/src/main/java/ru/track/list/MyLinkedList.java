package ru.track.list;

import java.util.NoSuchElementException;


/**
 * Должен наследовать List
 * Односвязный список
 */
public class MyLinkedList extends List {

    Node start;
    Node end;
    /**
     * private - используется для сокрытия этого класса от других.
     * Класс доступен только изнутри того, где он объявлен
     * <p>
     * static - позволяет использовать Node без создания экземпляра внешнего класса
     */
    private static class Node {
        Node prev;
        Node next;
        int val;

        Node(Node prev, Node next, int val) {
            this.prev = prev;
            this.next = next;
            this.val = val;
        }
    }

    @Override
    void add(int item) {
        if(size == 0) {
            start = new Node(null, null, item);
            end = start;

        }
        else {
            Node node = new Node(end, null, item);
            end.next = node;
            end = node;
        }
        size++;
    }

    @Override
    int remove(int idx) throws NoSuchElementException {
        Node next = start;
        if ((idx < 0) || (idx > size - 1)) {
            throw new NoSuchElementException();
        }
        else {
            if (size == 1) {
                Node node = start;
                start = null;
                end = null;
                size--;
                return node.val;
            }
            else {
                for (int i = 0; i < idx; i++) {
                    next = next.next;
                }
                next.prev.next = next.next;
                if (next.next != null) {
                    next.next.prev = next.prev;
                }
                size --;
                return next.val;
            }
        }
    }

    @Override
    int get(int idx) throws NoSuchElementException {
        Node next = start;
        if ((idx < 0) || (idx > size - 1)) {
            throw new NoSuchElementException();
        }
        else{
            for (int i = 0; i < idx; i++) {
                next = next.next;
            }
            return next.val;
        }
    }

    @Override
    int size() {
        return size;
    }
}
