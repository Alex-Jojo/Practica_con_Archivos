import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Comprobar {
    public static void main(String[] args) {
        try(FileInputStream file = new FileInputStream("c.mat");
            DataInputStream data = new DataInputStream(file)){
            int firstByte = data.readInt();
            int secondByte = data.readInt();
            System.out.println(firstByte+"\t"+secondByte);
            double[][] matriz = new double[firstByte][secondByte];
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[0].length; j++) {
                    if(data.available()>=8){
                        matriz[i][j] = data.readDouble();
                    }
                    System.out.print(matriz[i][j]+"\t");
                }
                System.out.println();
            }
        }catch(IOException exception){
            System.out.println("no");
            exception.printStackTrace();
        }
    }
}
