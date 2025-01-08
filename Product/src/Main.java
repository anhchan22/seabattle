import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while(true) {
            GameSystem game = new GameSystem();
            System.out.println("=============================");
            System.out.println("  1. Bắt đầu trò chơi mới");
            System.out.println("  2. Thoát");
            System.out.println("=============================");

            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    game.GameSystem();
                    break;
                case 2:
                    return ;
            }
        }
    }
}
