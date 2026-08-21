import java.util.Scanner;

class   Program
{
    static void printRecursive( final char[] toplistNum, final char[] allOcurr )
    {
        int     greatest = 0;
        int     occurrence;
        int     result;
        boolean[]  check = new boolean[10];

        for (int k = 0; k < 10 && allOcurr[toplistNum[k]] != '\0'; k++)
        {
            occurrence = allOcurr[toplistNum[k]]; 
            if (k == 0)
                greatest = occurrence;

            result = (occurrence * 10) / greatest;
            if (result < (10 - k))
            {
                k = 0;
                System.out.println("");
                continue ;
            }
            if (!check[k])
                System.out.print(occurrence + " ");
            else
                System.out.print("#");
        }
        for (int k = 0; k < 10 && allOcurr[toplistNum[k]] != '\0'; k++)
            System.out.print(toplistNum[k] + " ");
    }
    public static void  main( final String[] args )
    {
        final Scanner   scanner = new Scanner(System.in);
        final char[]    allOcurr = new char[65535];
        final char[]    toplistNum = new char[10];
        char[]          iter;
        String          input;
        char            prev_letter;
        char            curr_letter;
        char            n_ocurr_on_lst;
        char            curr_letter_on_lst;
        char            n_ocurr_on_input;
        char            curr_letter_on_input;

        try
        {
            System.out.println("->");
            input = scanner.nextLine();
            scanner.close();
            iter = input.toCharArray();
            for (int i = 0; i < iter.length; i++)
            {
                curr_letter_on_input = iter[i];
                n_ocurr_on_input = allOcurr[curr_letter_on_input];
                if (n_ocurr_on_input < 999)
                    n_ocurr_on_input = ++allOcurr[curr_letter_on_input];
                
                for (int j = 0; j < 10; j++)
                {
                    curr_letter_on_lst = toplistNum[j];
                    n_ocurr_on_lst = allOcurr[curr_letter_on_lst];
                    if (n_ocurr_on_input > n_ocurr_on_lst || (n_ocurr_on_input == n_ocurr_on_lst && curr_letter_on_input > curr_letter_on_lst))
                    {
                        prev_letter = curr_letter_on_lst;
                        for (int k = j + 1; k < 10 && allOcurr[toplistNum[k]] != '\0'; k++)
                        {
                            curr_letter = toplistNum[k];
                            toplistNum[k] = prev_letter;
                            prev_letter = curr_letter;
                        }
                        toplistNum[j] = curr_letter_on_input;
                    }
                }
            }
            System.out.println("");
            printRecursive(toplistNum, allOcurr);
        }
        catch ( Exception e )
        {
            scanner.close();
            System.err.println("Exception caught on program: " + e.getMessage());
            System.exit(-1);
        }
    }
}