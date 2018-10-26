import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {

    /*
    This is a simple but powerful wiki scraper that extracts all the written part of a wikipedia page.
     */

        //main loop that controls the program.
        //mainLoop is the number of current file so its important to change it to the next file in line
        //the second variable is the end number of files you are trying to achieve in this loop
        for (int mainLoop = 1340; mainLoop < 2000; ++mainLoop) {
            //simple timestamp to know how long time does a iteration takes.
            long startTime = System.currentTimeMillis();
            try {
                //main writer please configure the path to the folder and chose a name to save the file.
                BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\maxx\\Desktop\\wikiDatas\\wikiData" + mainLoop + ".txt", true));
                /*
                Loop that creates the files containing the information, 49 wikipedia pages seems a right amount
                to not overload the file of information. note that eventually the more pages you scrap it will
                begin to encounter duplicated pages so the files will end up with less information.
                 */
                for (int forloop = 0; forloop < 50; ++forloop) {

                    //this section gathers all previous wikipedia urls we have visited and stores them in a list
                    //to so we can confirm is we have already used this page or not.
                    //important to chose path and file name. Do remember to always use the same file.
                    FileReader confirmUrlReader = new FileReader("C:\\Users\\maxx\\Desktop\\wikiUrls.txt");
                    BufferedReader confirmUrlBuffer = new BufferedReader(confirmUrlReader);
                    List<String> confirmUrls = new ArrayList();
                    String confirmUrl = null;

                    while ((confirmUrl = confirmUrlBuffer.readLine()) != null) {
                         confirmUrls.add(confirmUrl);
                    }

                    confirmUrlBuffer.close();

                    //Open a slump wikipedia page.
                    Document wikiHtml = Jsoup.connect("https://tools.wmflabs.org/slumpartikel").get();
                    BufferedWriter htmlWriter = new BufferedWriter(new FileWriter("C:\\Users\\maxx\\Desktop\\wikiUrls.txt", true));
                    //selects all text in the page
                    Elements body = wikiHtml.select("p");
                    //Gathers the current wikipedia url we are in.
                    Elements wikiUrlSelect = wikiHtml.select("link[rel~=canonical]");
                    String wikiUrl = wikiUrlSelect.attr("href");
                    //confirms if we have visit this page before or not,
                    if (confirmUrls.contains(wikiUrl)) {
                        System.out.println("this alredy exists");
                    } else {
                        //apends all the info in a file
                        writer.append(System.getProperty("line.separator"));
                        writer.append(System.getProperty("line.separator"));
                        writer.append(body.text());
                        htmlWriter.append(System.getProperty("line.separator"));
                        htmlWriter.append(wikiUrl);
                        System.out.println(forloop);
                        htmlWriter.close();
                    }
                }

                writer.close();
                //ends and prints the time stamp.
                long endTime = System.currentTimeMillis();
                long duration = endTime - startTime;
                System.out.println(duration / 1000L + " sec");
            } catch (IOException var15) {
                var15.printStackTrace();
            }
        }
    }
}