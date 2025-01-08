import java.util.*;

public class BattleshipGame {
    private Grid player1Board;
    private Grid player2Board;
    private Grid player1BlindBoard;
    private Grid player2BlindBoard;

    private Scanner scanner;

    public BattleshipGame() {
        player1Board = new Grid();
        player2Board = new Grid();
        player1BlindBoard = new Grid("?");
        player2BlindBoard = new Grid("?");
        scanner = new Scanner(System.in);
    }


    public void placeBoat(Boat boat, int player) {
        Grid grid = (player == 1) ? player1Board : player2Board;
        boolean check = false;
        while (!check) {
            System.out.println(boat.getName() + " (Size: " + boat.getSize() + ")");
            System.out.println("Nhập tọa độ bắt đầu (vidu: A1):");
            String position = scanner.next().toUpperCase();

            System.out.println("Nhập chiều đặt tàu (H: ngang, V: Dọc):");
            String direction = scanner.next().toUpperCase();
            while (!direction.equals("H") && !direction.equals("V")) {
                System.out.println("Chiều đặt tàu không hợp lệ! Vui lòng nhập lại (H hoặc V).");
                direction = scanner.next().toUpperCase();
            }

            int x = position.charAt(0) - 'A';
            int y = Integer.parseInt(position.substring(1)) - 1;

            check = grid.addBoat(boat, x, y, direction.equals("H"));
            if (check) {
                System.out.println(boat.getName() + " đã được đặt thành công!\n");
            } else {
                System.out.println("Vị trí không hợp lệ, thử lại!\n");
            }
        }
    }

    public boolean shootAt(int player, int x, int y) {
        Grid targetGrid = (player == 1) ? player2Board : player1Board;
        if (targetGrid.shootAt(x, y) == true)
        {
            if (player == 1) player2BlindBoard.setBoard(x,y,"X");
            if (player == 2) player1BlindBoard.setBoard(x,y,"X");
            return true;
        }
        else
        {
            if (player == 1) player2BlindBoard.setBoard(x,y,"M");
            if (player == 2) player1BlindBoard.setBoard(x,y,"M");
            return false;
        }
    }

    public void printBoard(int player) {
        System.out.println("Bảng cuar người chơi " + player + ":");
        Grid grid = (player == 1) ? player1Board : player2Board;
        grid.printBoard();
    }

    public void printBlindBoard(int player) {
        if(player==1)
        System.out.println("Bảng xương mù của người chơi 2:");
        else System.out.println("Bảng xương mù của người chơi 1:");
        Grid grid = (player == 1) ? player2BlindBoard : player1BlindBoard;
        grid.printBoard();
    }

    public boolean checkGameOver() {
        if (player1Board.isGameOver()) {
            System.out.println("================================");
            System.out.println("   Người chơi 2 chiến thắng!");
            return true;
        }
        if (player2Board.isGameOver()) {
            System.out.println("================================");
            System.out.println("   Người chơi 1 chiến thắng!");
            return true;
        }
        return false;
    }
}
