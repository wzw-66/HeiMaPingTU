package test;

import java.util.Random;

public class test {
    public static void main(String[] args) {
        int[] arr1 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        int temp = 0;
        Random r = new Random();
        for (int i = 0; i < arr1.length; i++) {
            int index = r.nextInt(16);
            temp = arr1[i];
            arr1[i] = arr1[index];
            arr1[index] = temp;
        }
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + " ");
        }
        System.out.println();
        int[][] arr2 = new int[4][4];
        for (int i = 0; i < 16; i++) {
            arr2[i / 4][i % 4] = arr1[i];
        }
        for (int i = 0; i < arr2.length; i++) {
            for (int j = 0; j < arr2[i].length; j++) {
                System.out.print(arr2[i][j] + " ");
            }
            System.out.println();
        }
    }
}
