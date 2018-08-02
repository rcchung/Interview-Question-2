import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class InterviewSolution {
    /**
     * Iterate through each line of input.
     */
    public static void main(String[] args) throws IOException {
      InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
      BufferedReader in = new BufferedReader(reader);
      String line;
      while ((line = in.readLine()) != null) {
        String[] input = line.split(",");
        String nLength = input[0];
        String wordToCheck = input[1];
        String[] parsedText = formatInput().split(" ");
        Map<String,Integer> wordCountMap = new TreeMap<>();
        int totalWordCount = 0;
        for(int i = 0; i < parsedText.length; i++) {
          if(parsedText[i].equals(wordToCheck)){
            String nextWord = parsedText[i+1];
            int wordCount = wordCountMap.getOrDefault(nextWord,0)+1;
            wordCountMap.put(nextWord,wordCount);
            totalWordCount++;
          }
        }
        printResult(wordCountMap,totalWordCount);
      }
    }

    public static String formatInput(){
      String standardInput = "Mary had a little lamb its fleece was white as snow; \n" +
        "And everywhere that Mary went, the lamb was sure to go. \n" +
        "It followed her to school one day, which was against the rule; \n" +
        "It made the children laugh and play, to see a lamb at school. \n" +
        "And so the teacher turned it out, but still it lingered near, \n" +
        "And waited patiently about till Mary did appear. \n" +
        "\"Why does the lamb love Mary so?\" the eager children cry;\"Why, Mary loves the lamb, you know\" the teacher did reply.\"";
      return standardInput.replaceAll("[!;.,\"?\n]","");
    }

    public static void printResult(Map<String,Integer> map, Integer totalWordCount){
      Map<String, Integer> sorted= sortByValue(map);
      StringBuilder sb = new StringBuilder();
      for(String key: sorted.keySet()){
        float rate = (float)map.get(key)/(float)totalWordCount;
        sb.append(String.format(key+ ",%.3f;",rate));
      }
      String result = sb.toString();
      System.out.println(result.substring(0,result.length()-1));
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
      List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
      Collections.sort(list,
        new Comparator<Map.Entry<K,V>>() {
          @Override
          public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
            return e2.getValue().compareTo(e1.getValue());
          }
        }
      );
      Map<K, V> result = new LinkedHashMap<>();
      for (Map.Entry<K, V> entry : list) {
        result.put(entry.getKey(), entry.getValue());
      }
      return result;
      }
}

