package cmu.edu.perceptron;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Reader {

  public ArrayList<DataSet> readDataFile(String filename) {

    // BufferedReader rowData;
    ArrayList<DataSet> featherData = new ArrayList<DataSet>();
    String line;

    try {
      BufferedReader rowData = new BufferedReader(new FileReader(filename));
      // String line = rowData.readLine();
      String splitBy = ",";

      while ((line = rowData.readLine()) != null) {
        String[] lines = line.split(splitBy);
        int[] feature = new int[34];
        String result = null;
        int y = 0;
        DataSet tmp = new DataSet(feature, result);

        if (lines[0].equals("x")) {
          feature[0]++;
          feature[3]++;
          feature[6]++;
          feature[16] = 1;
        }

        if (lines[0].equals("o")) {
          feature[8]++;
          feature[11]++;
          feature[14]++;
          feature[25] = 1;
        }

        if (lines[1].equals("x")) {
          feature[0]++;
          feature[4]++;
          feature[17] = 1;
        }

        if (lines[1].equals("o")) {
          feature[8]++;
          feature[12]++;
          feature[26] = 1;
        }

        if (lines[2].equals("x")) {
          feature[0]++;
          feature[5]++;
          feature[7]++;
          feature[18] = 1;
        }

        if (lines[2].equals("o")) {
          feature[8]++;
          feature[13]++;
          feature[15]++;
          feature[27] = 1;
        }

        if (lines[3].equals("x")) {
          feature[1]++;
          feature[3]++;
          feature[19] = 1;
        }

        if (lines[3].equals("o")) {
          feature[9]++;
          feature[11]++;
          feature[28] = 1;
        }

        if (lines[4].equals("x")) {
          feature[1]++;
          feature[4]++;
          feature[6]++;
          feature[7]++;
          feature[20] = 1;
        }

        if (lines[4].equals("o")) {
          feature[9]++;
          feature[12]++;
          feature[14]++;
          feature[15]++;
          feature[29] = 1;
        }

        if (lines[5].equals("x")) {
          feature[1]++;
          feature[5]++;
          feature[21] = 1;
        }

        if (lines[5].equals("o")) {
          feature[9]++;
          feature[13]++;
          feature[30] = 1;
        }

        if (lines[6].equals("x")) {
          feature[2]++;
          feature[3]++;
          feature[7]++;
          feature[22] = 1;
        }

        if (lines[6].equals("o")) {
          feature[10]++;
          feature[11]++;
          feature[15]++;
          feature[31] = 1;
        }

        if (lines[7].equals("x")) {
          feature[2]++;
          feature[4]++;
          feature[23] = 1;
        }

        if (lines[7].equals("o")) {
          feature[10]++;
          feature[12]++;
          feature[32] = 1;
        }

        if (lines[8].equals("x")) {
          feature[2]++;
          feature[5]++;
          feature[6]++;
          feature[24] = 1;
        }

        if (lines[8].equals("o")) {
          feature[10]++;
          feature[13]++;
          feature[14]++;
          feature[33] = 1;
        }

        result = lines[lines.length - 1];
        if (result.equals("positive")) {
          y = 1;
        } else {
          y = -1;
        }
        tmp.setFeature(feature);
        tmp.setResult(result);
        tmp.setY(y);
        featherData.add(tmp);

      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return featherData;
  }

  public ArrayList<DataSet> splitTestData(String filename) {
    ArrayList<DataSet> featureData = readDataFile(filename);

    ArrayList<DataSet> testData = new ArrayList<DataSet>();

    int n = featureData.size();
    double ratio = 0.1;
    // int tn = (int) (0.1 * n);
    Random r = new Random();
    Map<Integer, List<DataSet>> map = new HashMap<Integer, List<DataSet>>();
    for (DataSet d : featureData) {
      if (!map.containsKey(d.getY()))
        map.put(d.getY(), new ArrayList<DataSet>());
      map.get(d.getY()).add(d);
    }

    for (Integer y : map.keySet()) {
      List<DataSet> list = map.get(y);

      int count = 0;
      while (count < ratio * list.size()) {
        DataSet temp = list.get(r.nextInt(list.size()));
        if (!testData.contains(temp)) {
          testData.add(temp);
          count++;
        }
        // testData.add(list.get(i));
      }
    }

    // while (testData.size() < tn) {
    // DataSet temp = featureData.get(r.nextInt(n));
    // if (!testData.contains(temp)) {
    // testData.add(temp);
    // }
    // }
    // System.out.println(tn+"AA");
    // System.out.println(testData.size()+"BB");

    return testData;
  }

  public ArrayList<DataSet> splitTrainData(String filename) {

    ArrayList<DataSet> featureData = readDataFile(filename);
    ArrayList<DataSet> testData = splitTestData(filename);

    ArrayList<DataSet> trainData = new ArrayList<DataSet>();
    int n = featureData.size();
    for (int i = 0; i < n; i++) {
      if (!testData.contains(featureData.get(i))) {
        trainData.add(featureData.get(i));
      }
    }

    return trainData;
  }

}
