public class SchoolgirlSolver {
    static char schoolGirlList [] = new char[15];
    public static void main(String [] args) {
        //A bit of setup

        String [] [] table = new String[7][5];
        //filling schoolGirlList and Sunday order
        int counter = -1;
        table [0][0] = "";
        for(int i = 0; i< 15; i++) {
            if(i%3==0) {
                counter++;
                table[0][counter] = "";
            }
            schoolGirlList[i] = (char)(i + 65);
            table[0][counter] += schoolGirlList[i];
            //System.out.println(table[0][counter]);
        }
        //Setting days 2-7 first three rows to ABC
        for (int day = 1; day < table.length; day ++) {
            table[day][0] = "A";
            table[day][1] = "B";
            table[day][2] = "C";
        }
        // Solving the actual problem
        placeGirl( table, 1, 0);
        finish(table);
    }
    static void placeGirl( String [] [] table, int day, int row) {
        //System.out.println("Calling placeGirl");
        if(table[6][4] != null && table[6][4].length() > 2){
            System.out.println("We finished!");
            finish(table);
           System.exit(0);
            return;
        }

        for (int i=day; i< table.length; i++) {
            for (int j = row; j< table[i].length; j++) {
                for (int k = 0; k< schoolGirlList.length; k++) {
                    char girl = (char)(k + 65);
                    if (table[i][j] == null) table[i][j] = "";

                    if(girlCanBePlaced(girl, table, i, j)) {
                    table[i][j] += schoolGirlList[k];
                     //If we are on the last row, the row is filled we increase the day and start at row 0
                     if(row == 4 && table[i][j].length() == 3) {
                         //day++;
                         //row = -1;
                         //k = -1;
                         placeGirl(table, day + 1,0 );
                     } else if(table[i][j].length() == 3) {
                     //If the row is full increase the row but keep the day
                         //row++;
                         placeGirl(table, day,row + 1 );
                     } else {
                            //Fill in next slot
                         placeGirl(table, day,row );
                     }





                    //If we get here it means we've back tracken and have to remove the last entry
                       // System.out.println("Checking to see if this line gets printed please dont");
                        //System.out.printf("I'm at the end of the check! I have girl %s in row %d on day %d the row is currently %s\n", girl,j, i, table[i][j]);
                        //Backtracking removing last entry
                        boolean full = table[i][j].length() == 3;
                        table[i][j] = table[i][j].substring(0, table[i][j].length() - 1);
                        //We have to reset the day if it is full
                        if(full) {
                           // day--;
                            //row--;
                        }
                        if(k == 14) return;
                     }
                    if(k == 14) return;
                }
            }
        }
    }

    static boolean girlCanBePlaced(char girl, String [] [] table, int day, int row) {
        //TODO
        String currentRow = table[day][row];
        //System.out.printf("Now checking if I can place girl %s in row %d on day %d \n", girl,row, day);
        //Making sure table generated is correct
        //finish(table);
        //if(table[day][row] == null || table[day][row].length() == 0) return true;
        if(table[day][row].length() == 3) return false;

        if(table[day][row].contains(""+girl)){
            return false;
        }
        // Checks to see if current girl's value is greater than the previous row on the same day
        if(row > 0 && (int) girl < (int)(table[day][row - 1].charAt(0))) return false;

        //Checks to see if current girl's value is less than the previous if so return false
        if (table[day][row].length() > 0 && (int) girl < currentRow.charAt(currentRow.length() - 1)) return false;

        //Checks to see if the current girl is M, N, or O and is being placed anywhere that is not the last place
        // of the row
        if((int) girl >= 77 && currentRow.length() != 2) return false;

        //Checks to see if girl has been placed already
        for (int i = 0; i< row; i++) {
            if(table[day][i] == null) return false;
            if (table[day][i].contains(""+girl)) return false;
        }

        //Checks to see if girl has shared the same row with another girl
        //Case 1: only one girl in the row so far
        if(table[day][row].length() == 1) {
            String otherGirl = table[day][row].substring(table[day][row].length() -1);
            for (int i = 0; i < table.length; i++) {
                for (int j = 0; j < table[i].length; j++) {
                    //if(table[i][j] == null)  table[i][j] = "";
                    if(table[i][j].contains(""+girl) && table[i][j].contains(otherGirl) ) return false;
                    if (i == day && j == row) return true;
                }
            }
            //Case 2: two girls are in the row and we have to check if the current girl has been placed with
            // either of them before
        } else if (table[day][row].length() == 2) {
            String secondGirl = table[day][row].substring(table[day][row].length() -1);
            String firstGirl = table[day][row].substring(0,1);
            for (int i = 0; i < table.length; i++) {
                for (int j = 0; j < table[i].length; j++) {
                    //if(table[i][j] == null)  table[i][j] = "";
                    if((table[i][j].contains(""+girl) && table[i][j].contains(firstGirl)) || (table[i][j].contains(""+girl) && table[i][j].contains(secondGirl)) ) return false;
                    if (i == day && j == row) return true;
                }
            }
        }
//        if(table[day][row].length() > 0) {
//            String lastGirl = table[day][row].substring(table[day][row].length() -1);
//            String girlOrder1 = lastGirl + girl;
//            String girlOrder2 = girl + lastGirl;
//            //Check if girl being placed has already been placed with the last girl
//            for (int i = 0; i < table.length; i++) {
//                for (int j = 0; j < table[i].length; j++) {
//                    //if(table[i][j] == null)  table[i][j] = "";
//                    if(table[i][j].contains(girlOrder1) ||table[i][j].contains(girlOrder2) ) return false;
//                    if (i == day && j == row) return true;
//                }
//            }
//        }
        //System.out.println("I am printing true");
        return true;
    }
    static void finish(String [] [] table) {
        for (int i  = 0; i < 7; i++) {
            System.out.println(i);
            for (int j = 0; j< 5; j++) {
                System.out.println(table[i][j]);

                //System.out.println(j);
            }
        }
    }
}