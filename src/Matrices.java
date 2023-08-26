import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Matrices {
    public static void main(String[] args) {
        String outputFile = "c.mat";

        try (FileInputStream inputA = new FileInputStream("a.mat");
             FileInputStream inputB = new FileInputStream("b.mat");
             DataInputStream dataInputA = new DataInputStream(inputA);
             DataInputStream dataInputB = new DataInputStream(inputB);
             FileOutputStream outputC = new FileOutputStream(outputFile);
             DataOutputStream dataOutputC = new DataOutputStream(outputC)){

            int firstByteA = dataInputA.read();
            int secondByteA = dataInputA.read();
            int firstByteB =dataInputB.read();
            int secondByteB = dataInputB.read();

            double[][] matrizA = new double[firstByteA][secondByteA];
            double[][] matrizB = new double[firstByteB][secondByteB];

            for (int i = 0; i < matrizA.length; i++) {
                for (int j = 0; j < matrizA[0].length; j++) {
                    if(dataInputA.available()>=8 && dataInputB.available()>=8){
                        matrizA[i][j] = dataInputA.readDouble();
                        matrizB[i][j] = dataInputB.readDouble();
                    }
                }
            }
            double[][] matrizC = new double[firstByteA][secondByteB];
            if(secondByteA==firstByteB){
                for (int i = 0; i < matrizC.length; i++) {
                    for (int j = 0; j < matrizC[0].length; j++) {
                        double comp = 0.0;
                        for (int k = 0; k < secondByteA; k++) {
                            comp += matrizA[i][k] * matrizB[k][j];
                        }
                        matrizC[i][j] = comp;
                    }
                }
            }else System.out.println("No se puede realizar la multplicación de la matriz");

            dataOutputC.writeInt(firstByteA);
            dataOutputC.writeInt(secondByteB);
            for (int i = 0; i < matrizC.length; i++) {
                for (int j = 0; j < matrizC[0].length; j++) {
                    dataOutputC.writeDouble(matrizC[i][j]);
                }
            }
            System.out.println("Archivo guardado con éxito.");


            /*
            Código para ver el contenido de la matriz A, la matriz B y la matriz C
            for (int i = 0; i < matrizA.length; i++) {
                for (int j = 0; j < matrizA[0].length; j++) {
                    System.out.print(matrizA[i][j]+"\t");
                }
                System.out.println();
            }
            for (int i = 0; i < matrizB.length; i++) {
                for (int j = 0; j < matrizB[0].length; j++) {
                    System.out.print(matrizB[i][j]+"\t");
                }
                System.out.println();
            }
            for (int i = 0; i < matrizC.length; i++) {
                for (int j = 0; j < matrizC[0].length; j++) {
                    System.out.print(matrizC[i][j]+"\t");
                }
                System.out.println();
            }
            */
        }catch(IOException e){
            System.out.println("El archivo no se encontró:");
            e.printStackTrace();
        }
    }
}
