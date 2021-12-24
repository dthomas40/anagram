package com.example.anagram;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AnagramController {

    @GetMapping("/api/anagram/list")
    public List<List<String>> listAnagrams(@RequestParam List<String> words){
        System.out.println(words.toString());

        List<List<String>> anagrams = new ArrayList<List<String>>();
        List<Boolean> grouped = new ArrayList<Boolean>(Collections.nCopies(words.size(), Boolean.FALSE));

        for(int i=0; i<words.size(); i++){
            if(grouped.get(i)==Boolean.FALSE) {
                anagrams.add(new ArrayList<String>(Collections.singleton(words.get(i))));
                for (int j = i + 1; j < words.size(); j++) {
                    System.out.println(i + " : " + j);
                    if (isAnagram(words.get(i), words.get(j))) {
                        anagrams.get(anagrams.size()-1).add(words.get(j));
                        grouped.set(j, Boolean.TRUE);
                        System.out.println(grouped.toString());
                    }
                }
                grouped.set(i, Boolean.TRUE);
                System.out.println(grouped.toString());
            }

        }

        return anagrams;
    }

    @GetMapping("/api/anagram/{a}&{b}")
    public Boolean isAnagram(@PathVariable String a, @PathVariable String b){

        List<Character> dictA = a.chars()
                .map(Character::toUpperCase)
                .filter(c -> !Character.isSpaceChar(c))
                .mapToObj(c -> Character.valueOf((char) c))
                .collect(Collectors.toList());

        List<Character> dictB = b.chars()
                .map(Character::toUpperCase)
                .filter(c -> !Character.isSpaceChar(c))
                .mapToObj(c -> Character.valueOf((char) c))
                .collect(Collectors.toList());

        for(int i=0; i<dictA.size() ; i++){
            for(int j=0; j< dictB.size(); j++){
                if(dictA.get(i)==dictB.get(j) && dictA.get(i)!=null){
//                    System.out.println(dictA.toString());
//                    System.out.println(dictB.toString());
//                    System.out.println(" -"+dictA.get(i)+" & -"+dictB.get(j));
                    dictA.set(i,null);
                    dictB.set(j,null);
                    break;
                }
            }
        }

        final List<Character> dictC = dictA.stream()
                .filter(letter -> letter!=null)
                .collect(Collectors.toList());
        final List<Character> dictD = dictB.stream()
                .filter(letter -> letter!=null)
                .collect(Collectors.toList());

        if (dictC.isEmpty() && dictD.isEmpty()){
            return true;
        }
        else {
            return false;
        }
    }
}
