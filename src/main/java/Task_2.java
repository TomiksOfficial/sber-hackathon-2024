import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task_2 {

    static Scanner sc = new Scanner(System.in);

    static List<List<Integer>> distances = new ArrayList<>();
    static List<String> addString = new ArrayList<>();
    static List<Integer> addH = new ArrayList<>();
    static List<Integer> addV = new ArrayList<>();
    static List<Integer> finalOrder = new ArrayList<>();

    static int minDist = Integer.MAX_VALUE;
    static int K;

    static public void main(String[] args) {

        final int N = sc.nextInt();
        final int M = sc.nextInt();

        String firmaStreet = sc.next();
        Integer firmaHouse = sc.nextInt();

        K = sc.nextInt();

        List<Boolean> visited = new ArrayList<>(K + 1);
        visited.add(true);

        addAddress(firmaStreet, firmaHouse);

        for (int i = 0; i < K; ++i) {
            visited.add(false);
            String clientStreet = sc.next();
            Integer clientHouse = sc.nextInt();
            addAddress(clientStreet, clientHouse);
        }

        int lastFrontInd = 0;
        int lastBackInd = 0;

        for (int i = 0; i < K / 2 + 1; ++i) {
            int indOfMinDist = -1;
            for (int j = 1; j < distances.size(); ++j) {
                if (visited.get(j)){
                    continue;
                }
                if (indOfMinDist == -1 ||distances.get(lastFrontInd).get(j) < distances.get(lastFrontInd).get(indOfMinDist)) {
                    indOfMinDist = j;
                }
            }

            if (indOfMinDist != -1) {
                lastFrontInd = indOfMinDist;
                visited.set(indOfMinDist, true);
                finalOrder.add(i, indOfMinDist);
            }
            indOfMinDist = -1;
            for (int j = 1; j < distances.size(); ++j) {
                if (visited.get(j)){
                    continue;
                }
                if (indOfMinDist == -1 ||distances.get(lastBackInd).get(j) < distances.get(lastBackInd).get(indOfMinDist)) {
                    indOfMinDist = j;
                }
            }
            if (indOfMinDist != -1) {
                lastBackInd = indOfMinDist;
                visited.set(indOfMinDist, true);
                finalOrder.add(i + 1, indOfMinDist);
            }
        }


//        findMinDist(0, visited, 0, new ArrayList<>());

        for (int i = 0; i < finalOrder.size(); ++i) {
            System.out.println(addString.get(finalOrder.get(i)));
        }

    }

//    private static Integer findMinDist(int cur, List<Boolean> visited, Integer curDist, List<Integer> order) {
//
//        int dist = Integer.MAX_VALUE;
//        for (int i = 1; i < addH.size(); i++) {
//
//            if (visited.get(i)) {
//                continue;
//            }
//
//            visited.set(i, true);
//            order.add(i);
//
//            dist = Math.min(dist, findMinDist(i, visited, curDist + distances.get(cur).get(i), order));
//
//            visited.set(i, false);
//            order.removeLast();
//
//        }
//
//        if (order.size() == K && curDist + findDist(cur, 0) < minDist) {
//
//            minDist = curDist + distances.get(cur).getFirst();
//            finalOrder = new ArrayList<>(order);
//            return minDist;
//        }
//
//        if (dist >= minDist) {
//            return 999999999;
//        }
//        return dist;
//    }

    private static void addAddress(String street, Integer house) {

        if (street.charAt(0) == 'H') {
            addH.add(Integer.parseInt(street.substring(1)) - 1);
            addV.add((house + 1) / 4);
        } else {
            addH.add((house + 1) / 4);
            addV.add(Integer.parseInt(street.substring(1)) - 1);
        }

        addString.add(street + " " + (house));
        distances.add(new ArrayList<>());

        for (int i = 0; i < distances.size() - 1; ++i) {

            int dist = findDist(i, addString.size() - 1);
            distances.get(i).add(dist);
            distances.getLast().add(dist);
        }

        distances.getLast().add(0);
    }

    private static Integer findDist(int first, int second) {
        return Math.abs(addH.get(first) - addH.get(second)) + Math.abs(addV.get(first) - addV.get(second));
    }

}
