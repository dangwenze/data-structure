package sparsearray;

import java.util.Arrays;

/**
 * 稀疏数组
 */
public class SparseArray {

    public static void main(String[] args){
        // 定义一个二维数组，数组值为1表示黑子，2表示白子
        int[][] chessAr = new int[10][10];
        chessAr[2][2] = 1;
        chessAr[5][6] = 1;
        chessAr[2][3] = 2;
        chessAr[3][3] = 1;
        chessAr[7][2] = 2;
        chessAr[8][6] = 1;
        for(int[] a : chessAr){
            System.out.println(Arrays.toString(a));
        }
        // 计算二维数组元素个数
        int size = 0;
        for (int i = 0;i<chessAr.length;i++){
            for(int j=0;j<chessAr[i].length;j++){
                if (chessAr[i][j] == 1 || chessAr[i][j] == 2){
                    size++;
                }
            }
        }
        System.out.println();
        System.out.println();
        System.out.println();

        // 定义一个稀疏数组
        int[][] sparseArray = new int[size+1][3];

        sparseArray[0][0] = 10;
        sparseArray[0][1] = 10;
        sparseArray[0][2] = size;

        for(int[] a : sparseArray){
            System.out.println(Arrays.toString(a));
        }

        // 稀疏数组记录二维数据信息
        int flag = 1;
        for (int i = 0; i < chessAr.length; i++) {
            for (int j = 0; j < chessAr[i].length; j++) {
                if (chessAr[i][j] == 1 || chessAr[i][j] == 2) {
                    sparseArray[flag][0] = i;
                    sparseArray[flag][1] = j;
                    sparseArray[flag][2] = chessAr[i][j];
                    flag++;
                }
            }
        }
        System.out.println();
        System.out.println();
        System.out.println();
        for(int[] a : sparseArray){
            System.out.println(Arrays.toString(a));
        }

        // 定义一个空二维数组
        int[][] chessArr2 = new int[sparseArray[0][0]][sparseArray[0][1]];
        // 二维数据根据稀疏数组记录填充
        for(int i = 1 ;i < sparseArray.length ; i++){
            chessArr2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        System.out.println();
        System.out.println();
        System.out.println();
        for(int[] a : chessArr2){
            System.out.println(Arrays.toString(a));
        }
    }

}
