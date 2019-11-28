package com.androdude.codeconverter;

public class OctalToOther {

    long convertOctalToBinary(int octalNumber)
    {
        int decimalNumber = 0, i = 0;
        long binaryNumber = 0;
        while(octalNumber != 0)
        {
            decimalNumber += (octalNumber % 10) * Math.pow(8, i);
            ++i;
            octalNumber/=10;
        }
        i = 1;
        while (decimalNumber != 0)
        {
            binaryNumber += (decimalNumber % 2) * i;
            decimalNumber /= 2;
            i *= 10;
        }
        return binaryNumber;
    }

    int octalToDecimal(int n)
    {
        int num = n;
        int dec_value = 0;

        // Initializing base value to 1, i.e 8^0
        int base = 1;

        int temp = num;
        while (temp > 0) {
            // Extracting last digit
            int last_digit = temp % 10;
            temp = temp / 10;

            // Multiplying last digit with appropriate
            // base value and adding it to dec_value
            dec_value += last_digit * base;

            base = base * 8;
        }
        return dec_value;
    }


    public boolean isOctal(String s)
    {
        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) == '0' || s.charAt(i) == '1' || s.charAt(i) == '2' || s.charAt(i) == '3' || s.charAt(i) == '4' || s.charAt(i) == '5' || s.charAt(i) == '6' || s.charAt(i) == '7' )

                return true;

        return false;
    }
}
