package de.leonm.adventofcode20.days;

import de.leonm.adventofcode20.models.BoardingPass;
import java.io.IOException;
import java.util.List;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;

public class Day05 extends Day {
  List<String> input;
  List<BoardingPass> boardingPasses;

  /**
   * Constructor for Day05.
   * Reads the input and also creates a list of boarding passes based on input.
   */
  public Day05() {
    try {
      input = reader.getStringListFromFile("src/de/leonm/adventofcode20/input/day05.txt");
      boardingPasses = input.stream().map(BoardingPass::new).collect(Collectors.toList());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Solves part one of day05.
   * Iterates through all the boardingpasses and retrieves their seat id,
   * will then retrieve the maximum utilizing the streaming api.
   *
   * @return the highest seatId as integer
   */
  public OptionalInt partOne() {
    /*
     * Some Tests
    BoardingPass testPass = new BoardingPass("BFFFBBFRRR");
    System.out.println(testPass); // should be 70, 7, 567
    testPass = new BoardingPass("FFFBBBFRRR");
    System.out.println(testPass); // should be 14, 7, 119
    testPass = new BoardingPass("BBFFBBFRLL");
    System.out.println(testPass); // should be 102, 4,
     */

    return boardingPasses.stream().mapToInt(BoardingPass::getSeatId).max();
  }

  /**
   * Solves part two of day05.
   * Creates a Set of the existing seat ids and iterates through it.
   * Checks for every current element curId that curId + 2 exist and curId + 1 do not exist.
   * If the condition holds true, we break the loop and return curId + 1 as our seat id.
   *
   * @return our seatId as integer
   */
  public OptionalInt partTwo() {
    Set<Integer> seatIds = boardingPasses.stream().map(BoardingPass::getSeatId).collect(Collectors.toSet());
    return seatIds.stream()
      .filter(id -> !seatIds.contains(id + 1) && seatIds.contains(id + 2))
      .mapToInt(id -> id + 1)
      .findFirst();
  }

  @Override
  public void printSolutions() {
    partOne().ifPresent(solution -> System.out.printf("Part one: %s" , solution));
    partTwo().ifPresent(solution -> System.out.printf("Part two: %s" , solution));
  }
}
