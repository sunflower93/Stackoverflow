import java.io.*;
/**
 * Created by ShaoBin on 2016/10/8.
 */
public class Preprocess {
    public static void first_seg() {
        try {
            File posts_file = new File("L:/Stackoverflow/stackoverflow.com-Posts/Posts.xml");
            BufferedReader posts_reader = new BufferedReader(new FileReader(posts_file));
            String line = posts_reader.readLine();
            File questions = new File("L:/Stackoverflow/stackoverflow.com-Posts/questions.xml");
            if (!questions.exists()){
                questions.createNewFile();
            }
            File answers = new File("L:/Stackoverflow/stackoverflow.com-Posts/answers.xml");
            if (!answers.exists()){
                answers.createNewFile();
            }
            long sum = 0;
            long sum_questions = 0;
            long sum_answers = 0;
            long sum_lose = 0;
            while (line != null) {
                sum++;
                if (line.charAt(2) == '<') {
                    String []temp = line.substring(2).split(" " , 10);
                    if (temp[2].charAt(12) == '1') {
                        FileWriter questions_writer = new FileWriter(questions , true);
                        questions_writer.write(line.substring(2) + "\n");
                        questions_writer.close();
                        sum_questions++;
                    }
                    else if (temp[2].charAt(12) == '2') {
                        FileWriter answers_writer = new FileWriter(answers , true);
                        answers_writer.write(line.substring(2) + "\n");
                        answers_writer.close();
                        sum_answers++;
                    }
                    else {
                        FileWriter out = new FileWriter("L:/Stackoverflow/stackoverflow.com-Posts/out.xml" , true);
                        out.write(line + "\n");
                        out.close();
                        sum_lose++;
                    }
                }
                line = posts_reader.readLine();
            }
            System.out.println("sum:" + sum + "questions:" + sum_questions + "\nanswers:" + sum_answers + "\nlose:" + sum_lose);
            posts_reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void file_write(String line, File file) throws IOException {
        FileWriter file_writer = new FileWriter(file , true);
        file_writer.write(line + "\n");
        file_writer.close();
    }

    public static void second_seg() {
        try {
            File read_file = new File("L:/Stackoverflow/stackoverflow.com-Posts/out.xml");
            BufferedReader read_reader = new BufferedReader(new FileReader(read_file));
            String line = read_reader.readLine();
            File []files = new File[6];
            for (int i = 0; i < 6; i++) {
                files[i] = new File("L:/Stackoverflow/stackoverflow.com-Posts/type" + (i + 3) + ".xml");
                if (!files[i].exists()){
                    files[i].createNewFile();
                }
            }
            int sum, sum3 ,sum4, sum5, sum6, sum7, sum8;
            sum = sum3 = sum4 = sum5 = sum6 = sum7 = sum8 = 0;
            while (line != null) {
                sum++;
                System.out.println(sum);
                String []temp = line.substring(2).split(" " , 10);
                switch (temp[2].charAt(12)) {
                    case '3' :
                        file_write((line.substring(2) + "\n") , files[0]);
                        sum3++;
                        break;
                    case '4' :
                        file_write((line.substring(2) + "\n") , files[1]);
                        sum4++;
                        break;
                    case '5' :
                        file_write((line.substring(2) + "\n") , files[2]);
                        sum5++;
                        break;
                    case '6' :
                        file_write((line.substring(2) + "\n") , files[3]);
                        sum6++;
                        break;
                    case '7' :
                        file_write((line.substring(2) + "\n") , files[4]);
                        sum7++;
                        break;
                    case '8' :
                        file_write((line.substring(2) + "\n") , files[5]);
                        sum8++;
                        break;
                }
                line = read_reader.readLine();
            }
            read_reader.close();
            System.out.println("sum:" + sum + "\nType3:" + sum3 + "\nType4:" + sum4 + "\nType5:" + sum5 + "\nType6:" + sum6 + "\nType7:" + sum7 + "\nType8:" + sum8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            File posts_file = new File("L:/Stackoverflow/stackoverflow.com-Posts/Posts.xml");
            BufferedReader posts_reader = new BufferedReader(new FileReader(posts_file));
            String line = posts_reader.readLine();
            long sum , sum1 , sum2;
            int sum3 ,sum4, sum5, sum6, sum7, sum8;
            sum = sum1 = sum2 = 0;
            sum3 = sum4 = sum5 = sum6 = sum7 = sum8 = 0;
            while (line != null) {
                if (line.charAt(2) == '<') {
                    sum++;
                    if (sum % 10000 == 0) {
                        System.out.println(sum);
                    }
                    String []temp = line.substring(2).split(" " , 10);
                    switch (temp[2].charAt(12)) {
                        case '1' :sum1++;break;
                        case '2' :sum2++;break;
                        case '3' :sum3++;break;
                        case '4' :sum4++;break;
                        case '5' :sum5++;break;
                        case '6' :sum6++;break;
                        case '7' :sum7++;break;
                        case '8' :sum8++;break;
                    }
                }
                line = posts_reader.readLine();
            }
            File write_file = new File("D:\\WorkSpace\\Stackoverflow_processing\\source\\post_count.txt");
            if (!write_file.exists()) {
                write_file.createNewFile();
            }
            FileWriter writer = new FileWriter(write_file);
            writer.write("sum:" + sum + "questions:" + sum1 + "\nanswers:" + sum2 + "\ntype3:" + sum3 + "\ntype4:" + sum4 + "\ntype5:" + sum5 + "\ntype6:" + sum6 + "\ntype7:" + sum7 + "\ntype8:" + sum8);
            posts_reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
