import java.util.Scanner;

class   Program
{
    public static void  main( String[] args )
    {
        final Scanner   scanner = new Scanner();
        final char[]    allOcurr = new char[65535];
        final char[]    toplistNum = new char[10];
        char[]          iter;
        String          input;
        char            n_ocurr_on_lst;
        char            letter_on_lst;
        char            n_ocurr_on_input;
        char            curr_letter_on_input;

        try
        {
            System.out.println("->");
            input = scanner.nextLine();
            scanner.close();
            iter = input.toCharArray();
            for (int i = 0; i < input.length(); i++)
            {
                curr_letter_on_input = iter[i];
                n_ocurr_on_input = allOcurr[curr_letter_on_input];
                if (n_ocurr_on_input < 999)
                    n_ocurr_on_input = ++allOcurr[curr_letter_on_input];
                
                for (int j = 0; j < 10; j++)
                {
                    n_ocurr_on_lst = allOcurr[toplistNum[j]];
                    letter_on_lst = toplistNum[j];
                    if (n_ocurr_on_input >= allOcurr[n_ocurr_on_lst])
                    {
                        if (n_ocurr_on_input == allOcurr[n_ocurr_on_lst])
                        for (int k = j; k < 10; k++)
                        {

                        }
                    }

                }
            }
        }
        catch ( Exception e )
        {
            scanner.close();
            System.err.println("Exception caught on program: " + e.getMessage());
            System.exit(-1);
        }
    }
}