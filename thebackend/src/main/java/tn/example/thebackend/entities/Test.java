package tn.example.thebackend.entities;

import java.util.Arrays;

public class Test {

    public boolean isAnagram(String s, String t) {
        //
        String [] a= s.split("");
        String b = Arrays.stream(a).sorted().toString();

        String [] c= t.split("");
        String d = Arrays.stream(c).sorted().toString();
        //
        StringBuilder ss = new StringBuilder(s);
        StringBuilder tt = new StringBuilder(t);

        char him;
        if (s.length() != t.length())
            return false;
        for (int i = 0 ; i< ss.length() ; i ++)
        {
            him = ss.charAt(i);
            ss.setCharAt(i, '-');

            for (int j = 0 ; j< tt.length() ; j ++)
            {
                if(him == tt.charAt(j))
                    tt.setCharAt(j, '-');
                    break;
            }
        }
        for (int i = 0 ; i< ss.length() ; i ++)
            if(ss.charAt(i) != '-' )
                return false;

        return true;
    }
}
