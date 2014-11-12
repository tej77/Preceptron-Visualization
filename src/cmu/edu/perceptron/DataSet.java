package cmu.edu.perceptron;

public class DataSet {
  private String result;
  private int[] feature;
  private int y;
  private int predict;
  
  public DataSet(){}
  
  public DataSet(int[] feature, String result){
    this.setFeature(feature);
    this.setResult(result);
  }

  public int[] getFeature() {
    return feature;
  }

  public void setFeature(int[] feature) {
    this.feature = feature;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getPredict() {
    return predict;
  }

  public void setPredict(int predict) {
    this.predict = predict;
  }
  
  
}
