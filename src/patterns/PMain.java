package patterns;

public class PMain {

    public static void main(String[] args) {
        char[][] printedBox = createPattern('E');
        printPattern(printedBox);
    }

    /**
     * Print the following pattern for any given char
     * here in this case input char is 'E'
     *         A
     *       B   B
     *     C       C
     *   D           D
     * E               E
     * E               E
     *   D           D
     *     C       C
     *       B   B
     *         A
     *
     *if input is 'A' return only single character A
     * @param c
     * @return patternBox 2D array
     */

    private static char[][] createPattern(char c) {

        /**
         * The idea is to visualize the pattern as a 2D array box
         * and fill each row x col position with either space or relevant char
         * starting from 0,0 position in array box;
         */
        if(c == 'A') {
            return new char[][]{{'A'}};
        }
        int len = c - 'A';
        int totalRows = 2 * (len) + 2;
        int totalCols = 2 * (len) + 1;
        int currentRow = 0;
        int currentCol = 0;
        int left = len;
        int right = len;

        //left and right pointer is used to keep track of positions needed to
        //be filled with characters and not space
        //only the cells marked with left/right pointers will
        //have characters and rest will have spaces

        char[][] patternBox = new char[totalRows][totalCols];
        char currentChar = 'A';

        while (currentRow < totalRows) {
            //Iterate every col in current row
            while(currentCol < totalCols) {
               if(currentCol == left || currentCol == right) {
                   patternBox[currentRow][currentCol] = currentChar;
               }else {
                   patternBox[currentRow][currentCol] = ' ';
               }
               currentCol++;
            }

            //here we skipped row which is equal to len
            //because in given pattern middle rows are identical
            //so to avoid any changes in left/right pointers
            //we skipped that row for any changes
            if(currentRow < len) {
               left--;
               right++;
               currentChar++;
            }else if(currentRow >= len+1){
               left++;
               right--;
               currentChar--;
            }
            //iterate to next row and reset col to 0;
            currentRow++;
            currentCol = 0;

        }

        return patternBox;
    }

    private static void printPattern(char[][] box) {
        int rows = box.length;
        int cols = box[0].length;

        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++) {
                System.out.print(box[i][j] + " ");
            }
            System.out.println();
        }
    }
}
