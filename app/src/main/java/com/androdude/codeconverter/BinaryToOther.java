package com.androdude.codeconverter;

public class BinaryToOther {

    int binaryToDecimal(int n)
    {
        int num = n;
        int dec_value = 0;

        // Initializing base
        // value to 1, i.e 2^0
        int base = 1;

        int temp = num;
        while (temp > 0) {
            int last_digit = temp % 10;
            temp = temp / 10;

            dec_value += last_digit * base;

            base = base * 2;
        }

        return dec_value;
    }

    int convertBinarytoOctal(long binaryNumber)
    {
        int octalNumber = 0, decimalNumber = 0, i = 0;
        while(binaryNumber != 0)
        {
            decimalNumber += (binaryNumber % 10) * Math.pow(2, i);
            ++i;
            binaryNumber /= 10;
        }
        i = 1;
        while (decimalNumber != 0)
        {
            octalNumber += (decimalNumber % 8) * i;
            decimalNumber /= 8;
            i *= 10;
        }
        return octalNumber;
    }

    String binaryTohexadecimal(int num)
    {
        int binary_number, hexadecimal_number = 0, i = 1, remainder;


        binary_number = num;
        while (binary_number != 0)
        {
            remainder = binary_number % 10;
            hexadecimal_number = hexadecimal_number + remainder * i;
            i = i * 2;
            binary_number = binary_number / 10;
        }
        switch (hexadecimal_number)
        {
            case 10 :
                return "A";
            case 11 :
                return "B";
            case 12 :
                return "C";
            case 13 :
                return "D";
            case 14 :
                return "E";
            case 15 :
                return "F";
            default:
                return String.valueOf(hexadecimal_number);

        }

    }

    public Boolean isBinary(String s)
    {
        Boolean flag = false;
        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i) == '0' || s.charAt(i) == '1' )
                flag = true;
            else
            {
                flag = false;
                break;
            }
        }
        return flag;
    }

}
