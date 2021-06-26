package com.company;

import java.util.Random;

public class Main {
    static int n =20;
    static int  MAX_SIZE_STACK=500;
    static int top=-1;
    static Position stack[]=new Position[MAX_SIZE_STACK];
    static String mark[][] =new String[n][n];
    static String array[][] = new String[n + 2][n + 2];
    static Offsets move[] = new Offsets[8];
    /**********************************************************************/
    static void addstack(Position position){
      if(Main.top >=MAX_SIZE_STACK-1){
          System.out.println("stack is full!");
      }
        stack[++Main.top]=position;
    }
    /**********************************************************************/
    static Position deletestack(){
        if(top==-1){
            System.out.println("stack is empty!");
        }
        return stack[Main.top--];
    }

    /*********************************************************************/
    static void print_maze(String array[][]) {
        for (int i = 0; i < n + 2; i++) {
            for (int j = 0; j < n + 2; j++) {
                System.out.print(array[i][j]);
            }
            System.out.print("\n");
        }
    }

    /***************************************************************/
    static void random_wall(String array[][]) {
        int x;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (!array[i][j].equals("0") && !array[i][j].equals("0") && !array[i][j].equals("@") && !array[i][j].equals("#")) {
                    Random random = new Random();
                    x = random.nextInt(2);
                    array[i][j] = String.valueOf(x);
                }
            }
        }

    }

    /************************************************************************/
    static void path(int row, int col, Offsets move[], String array[][]) throws InterruptedException {

        int x, next_row, next_col;
       do {
           Random random = new Random();
            x = random.nextInt(8);//0
            next_row = row + move[x].vert;//1
            next_col = col + move[x].horiz;//0
            System.out.println("value:"+ array[next_row][next_col]);
           Thread.sleep(10);
       }while ( array[next_row][next_col].equals("0") || array[next_row][next_col].equals("1") || array[next_row][next_col].equals("@"));

        if (!array[next_row][next_col].equals("#")) {

            array[next_row][next_col] = "0";
            System.out.println();
            print_maze(array);

            path(next_row, next_col, move, array);

        } else if (array[next_row][next_col].equals("#")) {
            System.out.println("**************************");
            random_wall(array);
        }
    }

    /*******************************************************************/
    static void maze_game() throws InterruptedException {
        int row,col,next_row,next_col,dir;
        boolean found =false;
        Position position =new Position();

        position.row=1;
        position.col=1;
        position.dir=1;
        addstack(position);//0
        while(top>-1 && !found){
            position=deletestack();

            row=position.row;
            col=position.col;
            dir=position.dir;
            array[row][col] = "0" ;

            Thread.sleep(10);
            print_maze(array);
            System.out.println();
            while (dir<8 && !found){
                next_row =row + move[dir].vert;
                next_col =col + move[dir].horiz;
                if(array[next_row][next_col]=="#"){
                    found=true;
                }
                else if(array[next_row][next_col].equals("0")){

                    Thread.sleep(10);
                    print_maze(array);
                    System.out.println();
                    position.row=row;
                    position.col=col;
                    position.dir=(dir+1);
                    addstack(position);
                    array[row][col] = "@" ;
                    row=next_row;
                    col=next_col;
                    dir=0;
                }
                else{
                    dir++;
                }
            }
        }
        if (found){
            print_maze(array);
            System.out.println("****************success!*************");
        }
        else {
            System.out.println("The maze does not have a path...");
        }
    }
    /*******************************************************************/
    public static void main(String[] args) throws InterruptedException {
        Offsets offsets1 = new Offsets(-1, 0);
        move[0] = offsets1;

        Offsets offsets2 = new Offsets(-1, 1);
        move[1] = offsets2;

        Offsets offsets3 = new Offsets(0, 1);
        move[2] = offsets3;

        Offsets offsets4 = new Offsets(1, 1);
        move[3] = offsets4;

        Offsets offsets5 = new Offsets(1, 0);
        move[4] = offsets5;

        Offsets offsets6 = new Offsets(1, -1);
        move[5] = offsets6;

        Offsets offsets7 = new Offsets(0, -1);
        move[6] = offsets7;

        Offsets offsets8 = new Offsets(-1, -1);
        move[7] = offsets8;

/*********************************************************************/
        for (int i = 0; i < n + 2; i++) {
            array[0][i] = "1";
            array[i][0] = "1";
            array[n + 1][i] = "1";
            array[i][n + 1] = "1";
        }


        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                array[i][j] = "*";
            }
        }
        array[1][1] = "@";
        array[n-3][n-5] = "#";
/******************Wall & start pos & end pos**********************/

        int row = 1, col = 1;
        path(row, col, move, array);
        print_maze(array);
        System.out.println("****************************");
        Thread.sleep(2000);
        System.out.println("start Game :)))");

/***********************************************************************/
        maze_game();

    }
}




