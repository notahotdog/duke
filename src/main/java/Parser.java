import java.util.Scanner;

public class Parser {


    String firstWord = null;
    String restWord = null;
    String wholeSentence = null;


    Parser(){

    }


    //takes in input and assignes them to fWord and null accordingly
    public void inputString(){
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        wholeSentence = str;
        int noWords = Duke.countWordsUsingStringTokenizer(str);
        if (noWords != 1) {
            String inpSentence[] = str.split(" ", 2);
            firstWord = inpSentence[0];
            restWord = inpSentence[1];
        } else {
            firstWord = str;
            restWord = null;
        }
    }



}
