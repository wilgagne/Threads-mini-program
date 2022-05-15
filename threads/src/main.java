import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class main {
    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        System.out.println("WELCOME TO GUESS THE THREADS");
        boolean play = true;
        while (play) {
            System.out.println("How many threads would you like to create?");
            int n = Integer.parseInt(reader.readLine());
            System.out.println("Guess the order of the threads\n" +
                    "Enter your guess in the following format: 12435\n" +
                    "__________________________________________________");
            String guess = reader.readLine();
            int[] arr = new int[n];
            Threads[] threadArr = new Threads[n];
            for (int i = 1; i < n + 1; i++) {
                Threads subOne = new Threads(i, arr);
                threadArr[i - 1] = subOne;
                subOne.start();
                arr = subOne.arr;
            }
            for (int i = 1; i < n + 1; i++) {
                threadArr[i - 1].join();
            }
            System.out.println("The order is " + Arrays.toString(arr) + "\n");

            int k = 0;
            for (int i : arr) {
                k = 10 * k + i;
            }

            if (guess.equals(String.valueOf(k))) {
                System.out.println("You guessed it right :D\n");
            } else {
                System.out.println("Your guess was wrong :(");
            }
            System.out.println("Play again? answer Y or N");
            String answer = reader.readLine();
            if (!answer.equals("Y")) {
                play = false;
            }
        }
    }
}

class Threads extends Thread {
    int i;
    int[] arr;

    public Threads(int i, int[] arr) {
        this.i = i;
        this.arr = arr;
    }

    @Override
    public void run() {
        System.out.println("I am thread #" + i);
        int ii = 0;
        while (arr[ii] != 0){
            ii++;
        }
        arr[ii] = i;
    }

}