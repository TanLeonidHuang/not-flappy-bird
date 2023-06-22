package GameProgram;

import java.io.*;
import java.util.Map;

public class LBFileHandler {

    private final String LBFileName = "LeaderBoard.txt";

    public LBFileHandler() {
    }

    public void readLBFile(Leaderboard lb) {

        try {
            FileReader reader = new FileReader(LBFileName);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            String[] l;

            while ((line = bufferedReader.readLine()) != null){
                l = line.split(",");
                lb.addNewScore(l[0], Integer. parseInt(l[1]));
            }
            bufferedReader.close();
            reader.close();

        } catch (IOException e) {
                e.printStackTrace();
            }

    }

    public void saveLBFile (Leaderboard lb) {

        try {
            FileWriter writer = new FileWriter(LBFileName);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            Map<String, Integer> scoreMap = lb.getScoreMap();

            for (Map.Entry<String, Integer> entry : scoreMap.entrySet()) {
                bufferedWriter.write(entry.getKey() + "," + entry.getValue());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}