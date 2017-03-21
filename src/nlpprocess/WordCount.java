package nlpprocess;

import java.util.*;

/**
 * Created by ShaoBin on 2017/3/11.
 */
public class WordCount {

    //待处理的文本
    private String[] texts;
    //所有的单词及次数
    private Map<String, Integer> allWords;
    //每一条数据中单词数量
    private List<Integer> taskWords;
    //每一条数据中每个单词的数量
    private List<Map<String, Integer>> taskWordCount;


    public WordCount(String[] texts) {
        this.texts = texts;
        allWords = new HashMap<>();
        taskWords = new ArrayList<>();
        taskWordCount = new ArrayList<>();
    }

    public Map<String, Integer> getAllWords() {
        return allWords;
    }

    public void setAllWords(Map<String, Integer> allWords) {
        this.allWords = allWords;
    }

    public List<Integer> getTaskWords() {
        return taskWords;
    }

    public void setTaskWords(List<Integer> taskWords) {
        this.taskWords = taskWords;
    }

    public List<Map<String, Integer>> getTaskWordCount() {
        return taskWordCount;
    }

    public void setTaskWordCount(List<Map<String, Integer>> taskWordCount) {
        this.taskWordCount = taskWordCount;
    }

    public int getWordSize() {
        return allWords.size();
    }

//    public List<String>[] getWordsFromText() {         //lucene process
//        Analyzer analyzer = new StandardAnalyzer();
//        TokenStream tokenStream = null;
//        List<String>[] words = new List[texts.length];
//        try {
//            for (int i = 0; i < texts.length; i++) {
//                List<String> word = new ArrayList<>();
//                tokenStream = analyzer.tokenStream(null, texts[i]);
//                tokenStream.reset();
//                while (tokenStream.incrementToken()) {
//                    CharTermAttribute ch = tokenStream.addAttribute(CharTermAttribute.class);
//                    word.add(ch.toString());
//                }
//                words[i] = word;
//                tokenStream.end();
//                tokenStream.close();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return words;
//    }

    public void init(int start) {
        List<String>[] words = new List[10000];             //***************************
//        words = getWordsFromText();                      //get all words.
        List<String> word;
        for (int i = 0; i < texts.length; i++) {
            word = words[i];
            taskWords.add(word.size());
            Map<String, Integer> map = new HashMap<>();
            if (word != null) {
                for (String s : word) {
                    if (map.containsKey(s)) {
                        map.put(s, map.get(s) + 1);
                    } else {
                        map.put(s, 1);
//                        if (allWords.containsKey(s)) {
//                            allWords.put(s, allWords.get(s) + 1);
//                        } else {
//                            allWords.put(s, 1);
//                        }
                    }
                }
                if (i < start) {
                    List<Map.Entry<String, Integer>> list = new ArrayList<>();
                    list.addAll(map.entrySet());
                    Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
                        @Override
                        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                            return o2.getValue() - o1.getValue();
                        }
                    });
                    for (int j = 0; j < 10 && j < list.size(); j++) {
                        if (allWords.containsKey(list.get(j).getKey())) {
                            allWords.put(list.get(j).getKey(), allWords.get(list.get(j).getKey()) + 1);
                        } else {
                            allWords.put(list.get(j).getKey(), 1);
                        }
                    }
                }
            }
            taskWordCount.add(map);
        }
    }

    public double[] getTf(int index) {
        double[] tf = new double[allWords.size()];
        int k = 0;
        Map<String, Integer> map;
        for (Map.Entry<String, Integer> entry : allWords.entrySet()) {
            map = taskWordCount.get(index);
            if (map.containsKey(entry.getKey())) {
//                tf[k++] = 1.0 * map.get(entry.getKey()) / taskWords.get(index);
                tf[k++] = 1 + Math.log(map.get(entry.getKey()));
            } else {
                //tf[k++] = 0;
                tf[k++] = 1;
            }
        }
        return tf;
    }

    public double[] getIdf() {
        double[] idf = new double[allWords.size()];
        int index = 0, num;
        for (Map.Entry<String, Integer> entry : allWords.entrySet()) {
            num = entry.getValue();
            idf[index++] = Math.log(1.0 * taskWords.size() / num);
        }
        return idf;
    }

    public List<double[]> getTfIdf() {
        List<double[]> tfIdf = new ArrayList<>();
        double[] idf = getIdf();
        for (int i = 0; i < texts.length; i++) {
            double[] tf = getTf(i);
            for (int j = 0; j < tf.length; j++) {
                tf[j] *= idf[j];
            }
            tfIdf.add(tf);
        }
        return tfIdf;
    }

}
