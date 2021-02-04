package com.rodzyn.sza4;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Encoder implements PasswordEncoder {

    private Map<String, String> dataBasePassword = new HashMap<>();


    public Encoder() {
        dataBasePassword.put("Mateusz", encode("Mateusz123"));
        dataBasePassword.put("Dariusz", encode("123AbcD"));

        System.out.println(dataBasePassword.get("Mateusz"));



        System.out.println(matches("Mateusz123", "Mateusz"));
        System.out.println(matches("123AbcD", "Dariusz"));
        System.out.println(matches("Mateusz1", "Mateusz1"));
    }

    @Override
    public String encode(CharSequence password) {
        double sum = 0;

        char[] characters = password.toString().toCharArray();

        List<Integer> listOfCharacters = new ArrayList<>();
        for(int j = 0; j < 2; j++){
            for (int i = 0; i < characters.length; i++){
                listOfCharacters.add( (int) characters[i]);
            }
        }

        for(int i = 0; i < listOfCharacters.size(); i++ ){

            int j = i;
            if((i+1)%4!=0){
                j = (i+1)%4;
            }else{
                j = 4;
            }
            sum = sum + Math.pow(j, 2) * listOfCharacters.get(i);
        }
        String suma = String.valueOf(sum);
        return suma;

    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return encode(charSequence).equals(dataBasePassword.get(s));
    }
}


