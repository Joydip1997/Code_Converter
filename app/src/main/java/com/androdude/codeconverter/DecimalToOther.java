package com.androdude.codeconverter;

public class DecimalToOther {
    int decToBinary(int n)
    {
        // array to store binary number
        int[] binaryNum = new int[1000];
        String x = "";

        // counter for binary array
        int i = 0;
        while (n > 0)
        {
            // storing remainder in binary array
            binaryNum[i] = n % 2;
            n = n / 2;
            i++;
        }

        // printing binary array in reverse order
        for (int j = i - 1; j >= 0; j--)
            x = x.concat(String.valueOf(binaryNum[j]));

        return Integer.parseInt(x);
    }

    int toOctal(int decimal){
        int rem; //declaring variable to store remainder
        String octal=""; //declareing variable to store octal
        //declaring array of octal numbers
        char octalchars[]={'0','1','2','3','4','5','6','7'};
        //writing logic of decimal to octal conversion
        while(decimal>0)
        {
            rem=decimal%8;
            octal=octalchars[rem]+octal;
            decimal=decimal/8;
        }
        return Integer.parseInt(octal);
    }

    String convertToHexadecimal(int num)
    {
        int dec_num, rem;
        String hexdec_num="";

        /* hexadecimal number digits */

        char hex[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

        dec_num = num;

        while(dec_num>0)
        {
            rem = dec_num%16;
            hexdec_num = hex[rem] + hexdec_num;
            dec_num = dec_num/16;
        }

        return hexdec_num;
    }




    public boolean isNumber(String s)
    {
        for (int i = 0; i < s.length(); i++)
            if (Character.isDigit(s.charAt(i))
                    == false)
                return false;

        return true;
    }
}
