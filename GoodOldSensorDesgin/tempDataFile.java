/*Read CSV File to TempMap*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class tempDataFile {

    public static void main(String[] args) throws FileNotFoundException {
    	
        Scanner scanner = new Scanner(new File("C:/Users/Savanh/git/Senior-Design/tempData.csv"));
        scanner.useDelimiter(",");
        while(scanner.hasNext()){
            System.out.print(scanner.next()+" | ");
            
        }
        scanner.close();
    }

}