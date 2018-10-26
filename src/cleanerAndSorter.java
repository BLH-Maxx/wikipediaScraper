import java.io.*;
import java.util.Arrays;
import java.util.List;

public class cleanerAndSorter {

    public static void main(String[] args) throws IOException {

        /*
        Main loop of the program that takes the files containing wikipedia pages and cleans them up
        sorts them and creates a nice structured list of words in alphabet order.
         */
        //mainLoop is the first file from where is reaching and the second variable is the amount of files to cheek.
        for (int mainLoop = 0; mainLoop < 50; ++mainLoop){

            //set up path and name of the files to fix
            FileReader wikiFile = new FileReader("C:\\Users\\maxx\\Desktop\\wikiDatas\\wikiData" + mainLoop + ".txt");
            BufferedReader reader = new BufferedReader(wikiFile);

            //Strings and store words and paragraphs for the files cleaned
            String line;
            String processedLine="";
            StringBuilder builder = new StringBuilder();
            String newLine="";
            //best loop i could think of to eliminate words that arent swidish, do note that this just eliminate words
            //in finish or other languages except the ones containing regular alphabeth and swidish "öäå" plus it
            //lowercase all words. and it stores it in one single string.
            while ((line = reader.readLine()) != null) {
                processedLine = line.replaceAll("[^A-Za-zöäåÄÖÅ ]+", "").toLowerCase();
                builder.append(processedLine);
            }


            newLine = builder.toString();
            //Splits the one string into an array
            String[] splitedWord = newLine.split(" ");
            //sorts array in alphabeth order
            Arrays.sort(splitedWord);
            //convert the array into a list. ( this is a must so we could eliminate the blank spaces. )
            List<String> words = Arrays.asList(splitedWord);
            //just print out info
            System.out.println(words.size());
            System.out.println(Arrays.toString(splitedWord));

            //creates a new file where to store the the clean information in order
            BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\maxx\\Desktop\\orderedFiles\\orderedWords" + mainLoop + ".txt", true));

            //hardcoded lookup to eliminate blank spaces and other things that are not words.
            for ( int loop = 0; loop < words.size(); ++loop){
                if (words.get(loop).contains("a") || words.get(loop).contains("b") || words.get(loop).contains("c") ||
                        words.get(loop).contains("d") || words.get(loop).contains("e") || words.get(loop).contains("f") ||
                        words.get(loop).contains("g") || words.get(loop).contains("h") || words.get(loop).contains("i") ||
                        words.get(loop).contains("j") || words.get(loop).contains("k") || words.get(loop).contains("l") ||
                        words.get(loop).contains("m") || words.get(loop).contains("n") || words.get(loop).contains("o") ||
                        words.get(loop).contains("p") || words.get(loop).contains("q") || words.get(loop).contains("r") ||
                        words.get(loop).contains("s") || words.get(loop).contains("t") || words.get(loop).contains("u") ||
                        words.get(loop).contains("v") || words.get(loop).contains("x") || words.get(loop).contains("y") ||
                        words.get(loop).contains("z") || words.get(loop).contains("å") || words.get(loop).contains("ä") ||
                        words.get(loop).contains("ö")
                        ){
                    System.out.println(words.get(loop));
                    //writes the words in order to the new file
                    writer.append(words.get(loop));
                    writer.append(System.getProperty("line.separator"));
                }
            }
            writer.close();

        }

    }
}


