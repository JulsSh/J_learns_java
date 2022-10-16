package ru.stqa.pft.rest.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {
  public static void main(String[] args ){
    String [] langs = {"Java","C#", "php","python"} ;
    List<String> languages = Arrays.asList("Java","C#", "php","python");


    for(Object l: languages){
      System.out.println("i d like to learn "+ l);
   }


  }

}
