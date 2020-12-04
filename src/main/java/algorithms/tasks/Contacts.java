package algorithms.tasks;

import java.util.Arrays;

public class Contacts {

    /*
We're going to make our own Contacts application! The application must perform two types of operations:

add name, where  is a string denoting a contact name. This must store  as a new contact in the application.
find partial, where  is a string denoting a partial name to search the application for. It must count the number of contacts starting with  and print the count on a new line.
Given  sequential add and find operations, perform each operation in order.
     */

    private static final int OFFSET = 'a';
    private static final int SIZE = 'z' - 'a' + 1;

    static class Node {
        int count = 0;
        Node[] children = new Node[SIZE];
    }

    static int[] contacts(String[][] queries) {
        Node root = new Node();
        return Arrays.stream(queries)
                .map(query -> processQuery(query, root))
                .filter(i -> i != null)
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private static Integer processQuery(String[] strings, Node root) {
        if (strings[0].equals("add")) {
            add(strings[1], root);
            return null;
        } else {
            return find(strings[1], root);
        }
    }

    private static Integer find(String string, Node node) {
        Integer result = null;
        for (char letter : string.toCharArray()) {
            int index = letter - OFFSET;
            if (node.children[index] == null) {
                result = 0;
                break;
            }
            node = node.children[index];
            result = node.count;
        }
        return result;
    }

    public static void add(String string, Node node) {
        for (char letter : string.toCharArray()) {
            int index = letter - OFFSET;
            if (node.children[index] == null) {
                node.children[index] = new Node();
            }
            node.children[index].count++;
            node = node.children[index];
        }
    }

}
