package com.example.anagram;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AnagramController {

    @GetMapping("/api/anagram/{a}&{b}")
    public Boolean isAnagram(@PathVariable String a, @PathVariable String b){

        List<Character> dictA = new ArrayList<Character>();
        List<Character> dictB = new ArrayList<Character>();

        System.out.print(a);
        a = a.replaceAll(" ","");
        System.out.println(" -> "+a);

        System.out.print(b);
        b = b.replaceAll(" ", "");
        System.out.println(" -> "+b);

        a.chars().forEach(c -> dictA.add(Character.valueOf((char)c)));
        b.chars().forEach(c -> dictB.add(Character.valueOf((char)c)));

        for(int i=0; i<a.length() ; i++){
            for(int j=0; j<b.length(); j++){
                if(dictA.get(i)==dictB.get(j) && dictA.get(i)!=null){
                    System.out.println(dictA.toString());
                    System.out.println(dictB.toString());
                    System.out.println(" -"+dictA.get(i)+" & -"+dictB.get(j));
                    dictA.set(i,null);
                    dictB.set(j,null);
                    break;
                }
            }
        }

        while(dictA.remove(null));
        while(dictB.remove(null));

        if (dictA.isEmpty() && dictB.isEmpty()){
            return true;
        }
        else {
            return false;
        }
    }
}
