package exam;


public class GameScoreCalculator {
    public static int calculateMinSegmentsToPlay(int[] segments) {
        int minSegments = segments.length;
        int player1Score = 0;
        int player2Score = 0;

        for (int i = 0; i < segments.length; i++) {
            // Simulate Player 1 playing i segments
            player1Score = 0;
            for (int j = 0; j < i; j++) {
                if (segments[j] == 1) {
                    player1Score++;
                } else {
                    player1Score--;
                }
            }

            // Simulate Player 2 playing the remaining segments
            for (int j = i; j < segments.length; j++) {
                if (segments[j] == 1) {
                    player2Score++;
                } else {
                    player2Score--;
                }
            }

            // Update the minimum segments if Player 1's score is greater
            if (player1Score > player2Score && i < minSegments) {
                minSegments = i;
            }
        }

        return minSegments;
    }

    public static void main(String[] args) {
        int[] segments = {1, 1, 0, 1};
        int minSegmentsToPlay = calculateMinSegmentsToPlay(segments);
        System.out.println("Minimum segments Player 1 should play: " + minSegmentsToPlay);
    }
}

