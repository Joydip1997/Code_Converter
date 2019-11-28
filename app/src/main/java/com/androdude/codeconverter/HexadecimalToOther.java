package com.androdude.codeconverter;

public class HexadecimalToOther {

    int hexadecimalToDecimal(String hexVal)
    {
        int len = hexVal.length();

        // Initializing base value to 1, i.e 16^0
        int base = 1;

        int dec_val = 0;

        // Extracting characters as digits from last character
        for (int i=len-1; i>=0; i--)
        {
            // if character lies in '0'-'9', converting
            // it to integral 0-9 by subtracting 48 from
            // ASCII value
            if (hexVal.charAt(i) >= '0' && hexVal.charAt(i) <= '9')
            {
                dec_val += (hexVal.charAt(i) - 48)*base;

                // incrementing base by power
                base = base * 16;
            }

            // if character lies in 'A'-'F' , converting
            // it to integral 10 - 15 by subtracting 55
            // from ASCII value
            else if (hexVal.charAt(i) >= 'A' && hexVal.charAt(i) <= 'F')
            {
                dec_val += (hexVal.charAt(i) - 55)*base;

                // incrementing base by power
                base = base*16;
            }
        }
        return dec_val;
    }

    public boolean isHexadecimal(String s)
    {
        boolean flag = false;
        int i =0;

        while(i<s.length())
        {
            if (s.charAt(i) == '0' || s.charAt(i) == '1' || s.charAt(i) == '2' || s.charAt(i) == '3' || s.charAt(i) == '4' || s.charAt(i) == '5' || s.charAt(i) == '6' || s.charAt(i) == '7' || s.charAt(i) == '8' || s.charAt(i) == '9' ||  s.charAt(i) == 'A' || s.charAt(i) == 'B'|| s.charAt(i) == 'C'|| s.charAt(i) == 'D' || s.charAt(i) == 'E' || s.charAt(i) == 'F' )
            {
                flag = true;

            }
            else
            {
                flag = false;
                break;
            }

            i++;
        }
        return flag;
    }
}
