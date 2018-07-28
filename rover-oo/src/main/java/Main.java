import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] plateauCoordinates = scanner.nextLine().split(" ");
        int boundaryX = Integer.parseInt(plateauCoordinates[0]);
        int boundaryY = Integer.parseInt(plateauCoordinates[1]);

        while(scanner.hasNext()) {
            String[] roverPosition = scanner.nextLine().split(" ");
            int roverX = Integer.parseInt(roverPosition[0]);
            int roverY = Integer.parseInt(roverPosition[1]);
            char roverOrientation = roverPosition[2].charAt(0);

            String roverMovements = scanner.nextLine();

            MarsRoverFacade marsRoverFacade = new MarsRoverFacade(boundaryX, boundaryY);
            marsRoverFacade.setRover(roverX, roverY, roverOrientation);
            marsRoverFacade.moveRover(roverMovements);

            System.out.println(marsRoverFacade.toString());
        }
    }
}
