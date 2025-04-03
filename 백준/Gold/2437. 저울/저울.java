import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        int[] numbers = new int[num];

        for (int i = 0; i < num; i++) {
            numbers[i] = sc.nextInt();
        }

        Arrays.sort(numbers);

        if(numbers[0] != 1){
            System.out.println(1);
            return;
        }

        int sum= numbers[0];

        for(int i=1; i<numbers.length; i++){
            if(sum+1 < numbers[i]){
                System.out.println(sum+1);
                return;
            }
            sum += numbers[i];
        }
        
       System.out.println(sum+1);
    }
}
