package algorithms.tasks;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


/*
There is a tree with b/r vertices. You need to count all possible triplex combinations with at least 1 red vertex on every shortest path.
The idea is to count all possible combinations and subtract non-r combinations from there.
By definition we can have only 1 path from node1 to node2, so combinatorics can be used there:
- permutations: count of all possible combinations within the set: !n. We need to count triplexes, so consider only triplexes and vertexes:
triplexes can have !3 combinations (ABC, ACB, BAC, BCA, CAB, CBA; 1 * 2 * 3)
vertexes can have only !2 combinations (AB, BA, thus 1 * 2)
and now we need to calculate combinations of triplexes:
n * (n-1) * (n-2), divided by count of combinations: n * (n-1) * (n-2) / 6
and combinations of vertexes:
n * (n-1), divided by count of combinations: n * (n-1) / 2
Algorithm:
1) group nodes by one common node (the node with most connections)
2) count all possible combinations of triplexes based on count of nodes:
- count * (count - 1) * (count - 2) / 6
3) for every group of nodes subtract all possible combinations of triplexes within group
- count * (count - 1) * (count - 2) / 6
4) for every group of nodes subtract all possible combinations of vertexes within group AND multiply on the every node in other groups
- (count * (count - 1) / 2) * (n - count)
5) the remain is the goal

Example:
5 nodes
1-2 b
2-3 r
3-4 r
4-5 b

1) 5 * 4 * 3 / 6 = 10
2) 2 nodes with 2 size: 0
3) 2 nodes with size 2: 2 * 3 = 6
4) result: 4

 */
public class KunduAndTree {

    enum Color {r, b}

    static class Node {
        Node parent;
        int size = 1;

        public Node() {
            this.parent = this;
        }

        Node findParent() {
            if (parent != this) {
                parent = parent.findParent();
            }
            return parent;
        }
    }

    void union(Node a, Node b) {
        Node aParent = a.findParent();
        Node bParent = b.findParent();

        if (aParent == bParent) {
            return;
        }
        if (a.size >= b.size) {
            aParent.size += bParent.size;
            bParent.parent = aParent;
        } else {
            bParent.size += aParent.size;
            aParent.parent = bParent;
        }
    }

    private final Node[] nodes;

    KunduAndTree(int n) {
        nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node();
        }
    }

    void addVertex(int a, int b, Color color) {
        if (color == Color.r) {
            return;
        }
        union(nodes[a], nodes[b]);
    }

    private long countNodes() {
        Set<Node> liveNodes = new HashSet<>();
        for (Node node : nodes) {
            if (node.size > 1) {
                liveNodes.add(node.findParent());
            }
        }
        long result = countTriples(nodes.length);

        for (Node node : liveNodes) {
            result -= countTriples(node.size);
            result -= countVertices(node.size) * (nodes.length - node.size);
        }
        return result % 1_000_000_007;
    }

    private static long countVertices(long size) {
        return size * (size - 1) / 2;
    }

    private static long countTriples(long size) {
        return size * (size - 1) * (size - 2) / 6;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cnt = scanner.nextInt();
        scanner.nextLine();

        KunduAndTree component = new KunduAndTree(cnt);

        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split("\\s");
            component.addVertex(Integer.parseInt(line[0]) - 1, Integer.parseInt(line[1]) - 1, Color.valueOf(line[2]));
        }

        long result = component.countNodes();

        System.out.println(result % 1_000_000_007);
    }

}
