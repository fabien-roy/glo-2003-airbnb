package ca.ulaval.glo2003;

import static spark.Spark.get;

public class Main {

  public static void main(String[] args) {
    get("/hello", (req, res) -> "Hello World");
  }

  public int test(String arg) {
    if (arg.equals("hello")) {
      return 3;
    } else {
      return 1;
    }
  }
}
