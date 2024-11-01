import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task_1 {

    static Scanner sc = new Scanner(System.in);

    static String firmaStreet;
    static Integer firmaHouse;

    static public void main(String[] args) {

        final int N = sc.nextInt();
        final int M = sc.nextInt();

        final int K = sc.nextInt();

        firmaStreet = sc.next();
        firmaHouse = sc.nextInt();

        int mileage = getMileage(K);

        System.out.println(mileage * 100);
    }

    private static int getMileage(int K) {

        List<Integer> firmaAdd = convertStringToMatrix(firmaStreet, firmaHouse);
        List<Integer> lastAdd = new ArrayList<>(firmaAdd);

        int mileage = 0;

        for (int i = 0; i < K; ++i) {
            String clientStreet = sc.next();
            Integer clientHouse = sc.nextInt();
            List<Integer> clientAdd = convertStringToMatrix(clientStreet, clientHouse);
            mileage += Math.abs(lastAdd.get(0) - clientAdd.get(0)) + Math.abs(lastAdd.get(1) - clientAdd.get(1));
            lastAdd = new ArrayList<>(clientAdd);
        }

        mileage += Math.abs(lastAdd.get(0) - firmaAdd.get(0)) + Math.abs(lastAdd.get(1) - firmaAdd.get(1));

        return mileage;
    }

    private static List<Integer> convertStringToMatrix(String street, Integer house) {

        List<Integer> matrix = new ArrayList<>();

        if (street.charAt(0) == 'H') {
            matrix.add(Integer.parseInt(street.substring(1)) - 1);
            matrix.add((house + 1)  / 4);
        } else {
            matrix.add((house + 1)  / 4);
            matrix.add(Integer.parseInt(street.substring(1)) - 1);
        }

        return matrix;
    }

}
