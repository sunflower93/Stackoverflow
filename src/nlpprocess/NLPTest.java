package nlpprocess;

import edu.stanford.nlp.ling.CoreAnnotations.*;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

/**
 * Created by ShaoBin on 2017/3/3.
 */
public class NLPTest {
    public static void main(String[] args) {
        NLPTest test = new NLPTest();
        test.run();
    }
    public void run() {
        Properties properties = new Properties();
        properties.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);

        HashSet<String> stopwords = new HashSet<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("D:\\WorkSpace\\Stackoverflow\\source\\stop words sort.txt"));
            String line = reader.readLine();
            while (line != null) {
                stopwords.add(line);
                line = reader.readLine();
            }

//            Connection conn = new MyDatabaseConnection().getConnection();
//            conn.setAutoCommit(false);
//            String sql = "SELECT body FROM javadata LIMIT 0,1";
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            ResultSet resultSet = pstmt.executeQuery();
//            resultSet.next();
//            String text = resultSet.getString("body");
//            text = "50 Human machine interface for lab abc computer applications\n" +
//                    "A survey of user opinion of computer system response time\n" +
//                    "The EPS user interface management system\n" +
//                    "System and human system engineering testing of 4 EPS\n" +
//                    "Relation of user perceived response time to error measurement\n" +
//                    "The generation of random binary unordered trees\n" +
//                    "The intersection graph of paths in trees\n" +
//                    "Graph minors IV Widths of trees and well quasi ordering\n" +
//                    "Graph minors A survey";
//            Annotation document = new Annotation(text);
//            pipeline.annotate(document);

            BufferedReader[] readers = new BufferedReader[10];
            FileWriter[] writers = new FileWriter[10];
            for (int i = 0; i < 10; i++) {
                readers[i] = new BufferedReader(new FileReader("L:\\mydata\\java\\javaq&a" + i + ".txt"));
                writers[i] = new FileWriter(new File("L:\\mydata\\java\\javaq&a_lemma" + i + ".txt"), true);

                String text = readers[i].readLine();
                while (text != null) {
                    Annotation document = new Annotation(text);
                    pipeline.annotate(document);

                    List<CoreMap> sentences = document.get(SentencesAnnotation.class);
                    List<String> words = new ArrayList<>();
                    List<String> nerTags = new ArrayList<>();
                    List<String> lemmaTags = new ArrayList<>();

                    for (CoreMap sentence : sentences) {
                        for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
                            String word = token.get(TextAnnotation.class);
                            words.add(word);
                            String ne = token.get(NamedEntityTagAnnotation.class);
                            nerTags.add(ne);
                            String lemma = token.get(LemmaAnnotation.class);
                            if (!stopwords.contains(lemma) && ne.compareTo("NUMBER") != 0) {
                                lemmaTags.add(lemma);
                            }
                        }
                    }
//                    System.out.println(words.size() + "\n" + nerTags.size());
                    for (String s : lemmaTags) {
                        writers[i].write(s + " ");
                    }
                    writers[i].flush();
                    text = readers[i].readLine();
                }
                writers[i].close();
                System.out.println(i);
            }


//            List<CoreMap> sentences = document.get(SentencesAnnotation.class);
//            List<String> words = new ArrayList<>();
//            List<String> posTags = new ArrayList<>();
//            List<String> nerTags = new ArrayList<>();
//            List<String> lemmaTags = new ArrayList<>();

//            for (CoreMap sentence : sentences) {
//                for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
//                    String word = token.get(TextAnnotation.class);
//                    words.add(word);
//                    String pos = token.get(PartOfSpeechAnnotation.class);
//                    posTags.add(pos);
//                    String ne = token.get(NamedEntityTagAnnotation.class);
//                    nerTags.add(ne);
//                    String lemma = token.get(LemmaAnnotation.class);
//                    if (!stopwords.contains(lemma) && ne.compareTo("NUMBER") != 0) {
//                        lemmaTags.add(lemma);
//                    }
//                }
//            }

//            System.out.println(words.toString());
//            System.out.println(posTags.toString());
//            System.out.println(nerTags.toString());
//            System.out.println(lemmaTags.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
