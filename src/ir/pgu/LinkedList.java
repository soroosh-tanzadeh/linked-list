package ir.pgu;

import java.util.*;

public class LinkedList<E> implements List<E> {

    public Node<E> lastNode;
    private transient int size;
    private Node<E> root;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.root == null;
    }

    @Override
    public boolean contains(Object o) {
        Node<E> currentNode = this.root;
        while (currentNode.getNext() != null) {
            if (currentNode.equals(o)) {
                return true;
            }
            currentNode = currentNode.getNext();
        }
        return false;
    }


    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("iterator");
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[this.size];
        int counter = 0;
        Node<E> currentNode = this.root;
        while (currentNode != null) {
            arr[counter] = currentNode.getData();
            counter++;
            currentNode = currentNode.getNext();
        }
        return arr;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    /**
     * O(1)
     *
     * @param e
     * @return boolean
     */
    @Override
    public boolean add(E e) {
        if (this.root == null) {
            this.root = new Node<>(e);
            this.lastNode = this.root;
        } else {
            Node<E> newNode = new Node<>(e);
            this.lastNode.setNext(newNode);
            this.lastNode = newNode;
        }
        this.size++;
        return true;
    }


    @Override
    public boolean remove(Object o) {
        // O(n)
        if (this.root.getData().equals(o)) {
            this.root = this.root.getNext();
            this.size--;
            if (this.size <= 1) {
                this.lastNode = this.root;
            }
        }

        for (Node<E> x = root; x != null; x = x.getNext()) {
            if (x.getNext() != null) {
                if (x.getNext().getData().equals(o)) {
                    if (x.getNext().equals(this.lastNode)) {
                        this.lastNode = x;
                    }
                    x.setNext(x.getNext().getNext());
                    this.size--;
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public boolean containsAll(Collection<?> c) {
        return c.stream().map(this::contains).reduce(true, (a, b) -> a && b);
    }


    @Override
    public boolean addAll(Collection<? extends E> c) {
        return c.stream().map(this::add).reduce(true, (a, b) -> a && b);
    }


    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("addAll");
    }


    @Override
    public boolean removeAll(Collection<?> c) {
        return c.stream().map(this::remove).reduce(true, (a, b) -> a && b);
    }


    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("retainAll");
    }

    @Override
    public void clear() {
        this.root = null;
        this.lastNode = null;
    }

    @Override
    public E get(int index) {
        // O(n)
        if (index <= this.size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Node<E> currentNode = root;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }
        return currentNode.getData();
    }


    @Override
    public E set(int index, E element) {
        // O(n)
        int i = 0;
        for (Node<E> x = root; x != null; x = x.getNext()) {
            if (i == index) {
                x.setData(element);
                return element;
            }
            i++;
        }
        return null;
    }


    @Override
    public void add(int index, E element) {
        // O(n)
        int i = 0;
        for (Node<E> x = root; x != null; x = x.getNext()) {
            if (i == index) {
                Node<E> node = new Node<>(element);
                if (x.getNext() != null) {
                    node.setNext(x.getNext());
                }
                x.setNext(node);
                size++;
                return;
            }
            i++;
        }
    }

    public void addAfter(E item, E element) {
        // O(n)
        for (Node<E> x = root; x != null; x = x.getNext()) {
            if (x.getData().equals(item)) {
                Node<E> node = new Node<>(element);
                node.setNext(x.getNext());
                boolean isLastNode = (x.getNext() == null);
                x.setNext(node);

                if (isLastNode) {
                    this.lastNode = node;
                }
                size++;
                return;
            }
        }
    }


    @Override
    public E remove(int index) {
        // O(n)
        if (index == 0) {
            this.root = this.root.getNext();
            this.size--;
            if (this.size <= 1) {
                this.lastNode = this.root;
            }
        } else if (index >= this.size) {
            throw new ArrayIndexOutOfBoundsException();
        }

        int i = 0;
        Node<E> deletedNode = null;
        for (Node<E> x = root; x != null; x = x.getNext()) {
            if ((i + 1) == index) {
                deletedNode = x.getNext();
                if (x.getNext().equals(this.lastNode)) {
                    this.lastNode = x;
                }
                x.setNext(x.getNext().getNext());
                this.size--;
            }
            i++;
        }
        assert deletedNode != null;
        return deletedNode.getData();
    }


    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("indexOf");
    }


    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("lastIndexOf");
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("listIterator");
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("listIterator");
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("subList");
    }
}

class Node<T> {
    private final UUID uuid;
    private T data;
    private Node<T> next;

    public Node(T data) {
        this.data = data;
        this.uuid = UUID.randomUUID();
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node<?> node = (Node<?>) o;

        return uuid.equals(node.uuid);
    }

    @Override
    public int hashCode() {
        int result = data.hashCode();
        result = 31 * result + (next != null ? next.hashCode() : 0);
        return result;
    }
}