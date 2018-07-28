import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] boardSize = scanner.nextLine().split(" ");
        int boardX = Integer.parseInt(boardSize[0]);
        int boardY = Integer.parseInt(boardSize[1]);

        MarsRover marsRover = new MarsRover(boardX, boardY);

        while(scanner.hasNext()) {
            String[] currentPosition = scanner.nextLine().split(" ");

            int currentX = Integer.parseInt(currentPosition[0]);
            int currentY = Integer.parseInt(currentPosition[1]);
            char orientation = currentPosition[2].charAt(0);

            marsRover.setCurrentPosition(currentX, currentY, orientation);

            String movements = scanner.nextLine();

            marsRover.performActions(movements);

            System.out.println(marsRover.getCurrentPosition());
        }

    }
}
