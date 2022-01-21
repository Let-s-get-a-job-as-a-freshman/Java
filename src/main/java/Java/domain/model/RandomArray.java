package Java.domain.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class RandomArray {

    Scanner sc = new Scanner(System.in);
    int bonusNumber = 0;
    int bonusCheck = 0;

    public int[] selectNumber() {
        Set<Integer> set = new HashSet<>();
        int randomNum;
        while (set.size() != 6) {
            randomNum = (int) (Math.random()*45 + 1);
            set.add(randomNum);
        }

        return set.stream().sorted().mapToInt(Integer::intValue).toArray();
    }

    public void manualSelectNumberSet(int manualCount, Scanner sc, int[][] arr) {
        for (int i = 0; i < manualCount; i++) {
            System.out.println("번호 6개를 입력해주세요.");
            for (int j = 0; j < 6; j++) {
                arr[i][j] = sc.nextInt();
                if (arr[i][j] < 1 || arr[i][j] > 45){
                    System.out.println("유효한 숫자를 입력해주세요");
                    j--;
                }
                for (int k = 0; k < j; k++) {
                    if (arr[i][j] == arr[i][k]) {
                        System.out.println("중복입니다.");
                        j--;
                    }
                }
            }
            Arrays.sort(arr[i]);
        }
    }

    public int getBonusNumber() {
        selectNumber();
        int randomNum = 0;
        
        Set<Integer> set = new HashSet<>();
        while (set.size() != 7) {
            randomNum = (int) (Math.random()*45 + 1);
            set.add(randomNum);
        }
        return randomNum;
    }

    public int[] getNumberArray(int count) {
        return selectNumber();
    }

    public String getCounting(int[] winningNumber, int[] selectNumber) {
        Set<Integer> set = new HashSet<>();
        int count = 0;
        for (int i : winningNumber) {
            set.add(i);
        }
        for (int i : selectNumber) {
            if (set.contains(i)) {
                count++;
            }
        }

        return getRanking(count);
    }

    public String getCounting(int[] winningNumber, int[][] selectNumber) {
        HashSet<Integer> set = new HashSet<>();
        int count = 0;
        for (int i : winningNumber) {
            set.add(i);
        }
        for (int i = 0; i < selectNumber.length; i++) {
            for (int j = 0; j < selectNumber[0].length; j++) {
                if (set.contains(selectNumber[i][j])) {
                    count++;
                }
                if (selectNumber[i][j] == bonusNumber) {
                    bonusCheck = 1;
                }
            }
        }

        return getRanking(count);
    }

    private String getRanking(int count) {
        String message;

        if (bonusCheck == 0) {
            switch (count) {
                case 6:
                    message = "1등 : 당첨금 1,000,000 원";
                    break;
                case 5:
                    message = "3등 : 당첨금 100,000 원";
                    break;
                case 4:
                    message = "4등 : 당첨금 50,000 원";
                    break;
                case 3:
                    message = "5등 : 당첨금 10,000 원";
                    break;
                default:
                    message = "낙첨되었습니다.";
                    break;
            }
        } else if (bonusCheck == 1 && count == 5) {
            message = "2등 : 당첨금 500,000 원";
        } else {
            message = "낙첨되었습니다.";
        }

        return message;
    }


}
