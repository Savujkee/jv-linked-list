package core.basesyntax;

import java.util.List;

public class MyLinkedList<T> implements MyLinkedListInterface<T> {
    private class Node<T> {
        private T value;
        private Node<T> next;
        private Node<T> prev;

        private Node(Node<T> prev, T value, Node<T> next) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }

    private int size;
    private Node head;
    private Node tail;

    public MyLinkedList() {
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size == 0) {
            head = new Node(null, value, null);
            tail = head;
        } else {
            Node newNode = new Node(tail, value, null);
            tail.next = newNode;
            tail = newNode;
        }
        size = size + 1;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
        } else {
            Node<T> oldNode = getNode(index);
            Node<T> newNode = new Node<>(oldNode.prev, value, oldNode);
            if (index != 0) {
                oldNode.prev.next = newNode;
            } else {
                head = newNode;
            }
            oldNode.prev = newNode;
            size = size + 1;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (T value : list) {
            add(value);
        }
    }

    @Override
    public T get(int index) {
        return getNode(index).value;
    }

    @Override
    public T set(T value, int index) {
        Node<T> node = getNode(index);
        T returnValue = node.value;
        node.value = value;
        return returnValue;
    }

    @Override
    public T remove(int index) {
        Node<T> node = getNode(index);
        T returnValue = node.value;
        unlink(node);
        size = size - 1;
        return returnValue;
    }

    @Override
    public boolean remove(T object) {
        Node<T> node = getNode(object);
        if (node != null) {
            unlink(node);
            size = size - 1;
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void unlink(Node<T> node) {
        if (node != head) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }
        if (node != tail) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }
    }

    private Node<T> getNode(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    private Node<T> getNode(T value) {
        Node<T> node = head;
        for (int i = 0; i < size; i++) {
            if (node.value == value) {
                return node;
            }
            if (value != null && value.equals(node.value)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }
}
