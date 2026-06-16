// =====================================
// EXERCÍCIO 1 - PILHA
// =====================================

class Stack {
    private Node top;
    private int size;

    private class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public void push(int data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public int pop() {
        if (isEmpty())
            throw new RuntimeException("Pilha vazia!");
        int data = top.data;
        top = top.next;
        size--;
        return data;
    }

    public int peek() {
        if (isEmpty())
            throw new RuntimeException("Pilha vazia!");
        return top.data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }
}

// =====================================
// EXERCÍCIO 2 - FILA
// =====================================

class Queue {
    private Node front;
    private Node rear;
    private int size;

    private class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public void enqueue(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            front = newNode;
        } else {
            rear.next = newNode;
        }
        rear = newNode;
        size++;
    }

    public int dequeue() {
        if (isEmpty())
            throw new RuntimeException("Fila vazia!");
        int data = front.data;
        front = front.next;
        size--;
        if (isEmpty())
            rear = null;
        return data;
    }

    public int peek() {
        if (isEmpty())
            throw new RuntimeException("Fila vazia!");
        return front.data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }
}

// =====================================
// EXERCÍCIO 3 - LISTA ENCADEADA
// =====================================

class LinkedList {
    private Node head;
    private int size;

    private class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public void insert(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public void insertAt(int index, int data) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();

        Node newNode = new Node(data);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
    }

    public int get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public void remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        if (index == 0) {
            head = head.next;
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
        size--;
    }

    public int getSize() {
        return size;
    }
}

// =====================================
// EXERCÍCIO 4 - HASHMAP
// =====================================

class MyHashMap {
    private static final int CAPACITY = 16;
    private Node[] table;
    private int size;

    private class Node {
        String key;
        int value;
        Node next;

        Node(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public MyHashMap() {
        table = new Node[CAPACITY];
        size = 0;
    }

    private int hash(String key) {
        return Math.abs(key.hashCode() % CAPACITY);
    }

    public void put(String key, int value) {
        if (key == null)
            throw new IllegalArgumentException("Chave não pode ser nula");

        int index = hash(key);
        Node newNode = new Node(key, value);

        if (table[index] == null) {
            table[index] = newNode;
            size++;
        } else {
            Node current = table[index];
            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                if (current.next == null) {
                    current.next = newNode;
                    size++;
                    return;
                }
                current = current.next;
            }
        }
    }

    public int get(String key) {
        int index = hash(key);
        Node current = table[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        throw new RuntimeException("Chave não encontrada");
    }

    public void remove(String key) {
        int index = hash(key);
        Node current = table[index];

        if (current != null && current.key.equals(key)) {
            table[index] = current.next;
            size--;
            return;
        }

        while (current != null) {
            if (current.next != null && current.next.key.equals(key)) {
                current.next = current.next.next;
                size--;
                return;
            }
            current = current.next;
        }
    }

    public int getSize() {
        return size;
    }
}

// =====================================
// TESTES
// =====================================

public class Main {
    public static void main(String[] args) {
        // Testando PILHA
        System.out.println("=== PILHA ===");
        Stack stack = new Stack();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("Pop: " + stack.pop()); // 30
        System.out.println("Peek: " + stack.peek()); // 20
        System.out.println("Tamanho: " + stack.getSize()); // 2

        // Testando FILA
        System.out.println("\n=== FILA ===");
        Queue queue = new Queue();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println("Dequeue: " + queue.dequeue()); // 1
        System.out.println("Peek: " + queue.peek()); // 2
        System.out.println("Tamanho: " + queue.getSize()); // 2

        // Testando LISTA ENCADEADA
        System.out.println("\n=== LISTA ENCADEADA ===");
        LinkedList list = new LinkedList();
        list.insert(100);
        list.insert(200);
        list.insertAt(1, 150);
        System.out.println("Elemento no índice 1: " + list.get(1)); // 150
        System.out.println("Tamanho: " + list.getSize()); // 3
        list.remove(1);
        System.out.println("Tamanho após remoção: " + list.getSize()); // 2

        // Testando HASHMAP
        System.out.println("\n=== HASHMAP ===");
        MyHashMap map = new MyHashMap();
        map.put("nome", 100);
        map.put("idade", 25);
        map.put("ano", 2026);
        System.out.println("Valor de 'nome': " + map.get("nome")); // 100
        System.out.println("Valor de 'idade': " + map.get("idade")); // 25
        System.out.println("Tamanho: " + map.getSize()); // 3
        map.remove("idade");
        System.out.println("Tamanho após remoção: " + map.getSize()); // 2
    }
}