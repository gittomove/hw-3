package com.company;

import java.util.ArrayList;
import java.util.Objects;

public class Main {

    public static void main(String[] args)
    {
        Map<String, Integer> table = new  Map<>();
        table.add("Hey", 1);
        table.add("You", 2);
        table.add("Hello", 4);
        table.add("Nice", 5);
        System.out.println(table.size()+ "size");
        System.out.println(table.remove("you") + "Removed - you -- not found");
        System.out.println(table.remove("You") + "Retrives index");
        System.out.println(table.size()+ "New size after delition");
        System.out.println(table.isEmpty() + "Is empty?");
    }

    // A node of chains
    static class NewHashNode<K, V> {
        K key;
        V value;
        int hashCode;

        NewHashNode<K, V> next;

        public NewHashNode(K key, V value, int hashCode)
        {
            this.key = key;
            this.value = value;
            this.hashCode = hashCode;
        }
    }

    static class Map<K, V> {

        private ArrayList<NewHashNode<K, V>> myArray;

        private int CapArray;
        private int size;


        public Map()
        {
            myArray = new ArrayList<>();
            CapArray = 10;
            size = 0;

            for (int i = 0; i < CapArray; i++)
                myArray.add(null);
        }

        public int size() { return size; }
        public boolean isEmpty() { return size() == 0; }

        private final int hashCode (K key) {
            return Objects.hashCode(key);
        }


        private int getArrayIndex(K key)
        {
            int hashCode = hashCode(key);
            int index = hashCode % CapArray;

            index = index < 0 ? index * -1 : index;
            return index;
        }


        public V remove(K key)
        {

            int arrayIndex = getArrayIndex(key);
            int hashCode = hashCode(key);

            NewHashNode<K, V> head = myArray.get(arrayIndex);

            NewHashNode<K, V> prev = null;
            while (head != null) {

                if (head.key.equals(key) && hashCode == head.hashCode)
                    break;

                prev = head;
                head = head.next;
            }


            if (head == null) return null;

            size--;

            if (prev != null) prev.next = head.next;
            else myArray.set(arrayIndex, head.next);

            return head.value;
        }

        public V get(K key)
        {
            int arrayIndex = getArrayIndex(key);
            int hashCode = hashCode(key);

            NewHashNode<K, V> head = myArray.get(arrayIndex);

            while (head != null) {
                if (head.key.equals(key) && head.hashCode == hashCode)
                    return head.value;
                head = head.next;
            }

            return null;
        }

        public void add(K key, V value)
        {

            int arrayIndex = getArrayIndex(key);
            int hashCode = hashCode(key);
            NewHashNode<K, V> head = myArray.get(arrayIndex);

            while (head != null) {
                if (head.key.equals(key) && head.hashCode == hashCode) {
                    head.value = value;
                    return;
                }
                head = head.next;
            }

            size++;
            head = myArray.get(arrayIndex);
            NewHashNode<K, V> newNode = new NewHashNode<K, V>(key, value, hashCode);
            newNode.next = head;
            myArray.set(arrayIndex, newNode);

            if ((1.0 * size) / CapArray >= 0.7) {
                ArrayList<NewHashNode<K, V>> temp = myArray;
                myArray = new ArrayList<>();
                CapArray = 2 * CapArray;
                size = 0;
                for (int i = 0; i < CapArray; i++)
                    myArray.add(null);

                for (NewHashNode<K, V> headNode : temp) {
                    while (headNode != null) {
                        add(headNode.key, headNode.value);
                        headNode = headNode.next;
                    }
                }
            }
        }
    }
}
