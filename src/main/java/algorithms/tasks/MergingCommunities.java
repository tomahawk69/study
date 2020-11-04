package algorithms.tasks;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MergingCommunities {

    public static void main(String[] args) {
        new MergingCommunities().parse();
    }

    public void parse() {
        Scanner scanner = new Scanner(System.in);
        String[] line = scanner.nextLine().split("\\s");
        int size = Integer.parseInt(line[0]);
        int queries = Integer.parseInt(line[1]);
        int i = 0;
        Storage storage = new Storage(size);
        while (i++ < queries) {
            line = scanner.nextLine().split("\\s");
            if (line[0].startsWith("M")) {
                storage.connect(Integer.parseInt(line[1]) - 1, Integer.parseInt(line[2]) - 1);
            } else {
                System.out.println(storage.count(Integer.parseInt(line[1]) - 1));
            }
        }
    }

    public static class Community {
        Set<Integer> members;
    }

    static class Storage {

        private final Community[] communities;

        public Storage(int size) {
            this.communities = new Community[size];
            for (int i = 0; i < size; i++) {
                Community community = new Community();
                community.members = new HashSet<>();
                community.members.add(i);
                this.communities[i] = community;
            }
        }

        public void connect(int a, int b) {
            if (communities[a].members == communities[b].members) {
                return;
            }

            int a1 = a;
            int b1 = b;
            if (communities[a].members.size() < communities[b].members.size()) {
                a1 = b;
                b1 = a;
            }

            for (Integer i : communities[b1].members) {
                if (communities[i].members != communities[a1].members) {
                    communities[a1].members.add(i);
                    communities[i].members = communities[a1].members;
                }
            }
        }

        public int count(int a) {
            return communities[a].members.size();
        }
    }
}
