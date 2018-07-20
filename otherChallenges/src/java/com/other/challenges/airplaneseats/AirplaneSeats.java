package com.other.challenges.airplaneseats;

import java.util.Optional;

import static com.other.challenges.airplaneseats.SeatType.FREE;
import static com.other.challenges.airplaneseats.SeatType.OCCUPIED;

public class AirplaneSeats {
    private SeatType[][] seatTypes;

    /**
     * Initialize seatTypes layout.
     * E.g.:
     *  false false null false false false false null false false
     *  false false null false false false false null false false
     *  false false null false false false false null false false
     *
     * @param row fow format as an array of 'false' and 'null'. Where: 'false' means seat and "null" means empty space.
     * @param rowCount the number of rows
     */
    public AirplaneSeats(SeatType[] row, int rowCount) {
        seatTypes = new SeatType[rowCount][row.length];
        for (int i = 0; i < rowCount; i++) {
            seatTypes[i] = row.clone();
        }
    }

    public SeatType[][] getSeatTypes() {
        return seatTypes;
    }

    /**
     * Adds group of passengers by finding a sequence of free seatTypes for entire group. Groups of 4 can also be split in 2 groups of 2.
     * @param groupSize the size of the group to distribute.
     */
    public void addPassengers(int groupSize) {
        for (SeatType[] row : seatTypes) {
            Optional<Integer> firstPosOfGroupSeat = findSeatSequenceInRow(row, groupSize);
            if (firstPosOfGroupSeat.isPresent()) {
                occupySeatSequenceInRow(groupSize, row, firstPosOfGroupSeat.get());
                return;
            }
        }
        throw new IllegalStateException("Can't add group of " + groupSize + " to current seatTypes layout.");
    }

    private void occupySeatSequenceInRow(int groupSize, SeatType[] row, int startPosition) {
        for (int groupPos = 0; groupPos < groupSize; groupPos++) {
            if (row[startPosition + groupPos] != FREE) throw new IllegalStateException("Can't occupy a non free seat."); // defensive
            row[startPosition + groupPos] = OCCUPIED;
        }
    }

    private Optional<Integer> findSeatSequenceInRow(SeatType[] row, int groupSize) {
        for (int i = 0; i < row.length - groupSize + 1; i++) {
            for (int groupPos = 0; groupPos < groupSize; groupPos++) {
                if (row[i + groupPos] != FREE) break;
                if (groupPos == groupSize-1) return Optional.of(i);
            }
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AirplaneSeats:\n");
        for (SeatType[] row : seatTypes) {
            for (SeatType s : row) {
                sb.append(s + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
