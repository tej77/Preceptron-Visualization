package cmu.edu.perceptron;

import java.util.ArrayList;
import java.util.Random;

public class PerceptronOnline {

  public static void main(String[] args) {
    Reader reader = new Reader();

    ArrayList<DataSet> train = reader.splitTrainData("tictactoe.csv");
    ArrayList<DataSet> test = reader.splitTestData("tictactoe.csv");

    PerceptronOnline p = new PerceptronOnline();

    Double[] weight = p.getWeight(train);
    double precision = p.doTest(test, weight);

    System.out.println("online precision: " + precision);

  }

  public Double[] getWeight(ArrayList<DataSet> train) {
    double alpha = 0.0001;
    Random rand = new Random();

    Double[] w = new Double[train.get(0).getFeature().length];
    for (int i = 0; i < w.length; i++) {
      w[i] = rand.nextDouble();
    }
//    int loop = 100;
//    while (--loop >= 0) {
//      double[] deltaW = new double[w.length];
      double hit = 0.0;
      for (int i = 0; i < train.size(); i++) {
        double h = 0;
        // DataSet doc = train.get(i);
        DataSet doc = train.get(i / 2 + (i % 2) * (train.size() / 2));

        for (int k = 0; k < doc.getFeature().length; k++) {
          h += doc.getFeature()[k] * w[k];
        }
        // System.out.println(">>" + h);
        for (int j = 0; j < doc.getFeature().length; j++) {
          if (h * doc.getY() < 0)
            w[j] += alpha * (doc.getY() - h) * doc.getFeature()[j];
          // else
          // deltaW[j] -= alpha * (doc.getY() - h) * doc.getFeature()[j];
        }
        // System.out.println(deltaW[0] + "\t" + deltaW[1]);
        if (h * doc.getY() > 0) {
          hit++;
        }
      }
//      System.out.println(deltaW[0] + "\t" + deltaW[1] + "\t" + (hit / train.size()));
//      for (int i = 0; i < w.length; i++) {
//        w[i] += deltaW[i];
//      }
      // if (loop % 100 == 0) {
      // doTest(train, w);
      // }
//    }
    return w;
  }

  public double doTest(ArrayList<DataSet> test, Double[] w) {
    double pre = 0;
    int pos = 0;
    int neg = 0;
    int cp = 0;
    int cn = 0;

    for (int i = 0; i < test.size(); i++) {
      pre = 0.0;
      for (int j = 0; j < w.length; j++) {
        pre += test.get(i).getFeature()[j] * w[j];
      }
      if (pre > 0) {
        test.get(i).setPredict(1);
      } else {
        test.get(i).setPredict(-1);
      }
    }

    for (int i = 0; i < test.size(); i++) {
      switch (test.get(i).getY()) {
        case 1:
          pos++;
          if (test.get(i).getY() == test.get(i).getPredict()) {
            cp++;
          }
          break;

        case -1:
          neg++;
          if (test.get(i).getY() == test.get(i).getPredict()) {
            cn++;
          }
          break;

        default:
          break;
      }
    }

    double precision = ((double) (cn + cp)) / (pos + neg);
    // System.out.println(test.size());
    System.out.println("Acu/Pre  Positive Negative");
    System.out.println("Postive  " + cp + "     " + (pos - cp));
    System.out.println("Negative " + (neg - cn) + "     " + cn);
    return precision;
  }

}
