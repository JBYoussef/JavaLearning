import java.util.Scanner;

class   Program
{
    public static void  main( String[] args )
    {
        final Scanner   scanner = new Scanner(System.in);
        int             requests = 0;

        while (true)
        {
            long        num;
            long        iter = 2;
            long        sum = 0;
            boolean     prime = true;

            System.out.print("-> ");
            num = scanner.nextLong( );
            if (num < 2)
            {
                System.err.println("IllegalArgument");
                scanner.close();
                System.exit(-1);
            }
            if (num == 42)
                break ;
            while (num > 0)
            {
                sum += num % 10;
                num /= 10;
            }
            while (iter < sum / 2)
            {
                if (sum % iter == 0)
                {
                    prime = false;
                    break ;
                }
                iter++;
            }
            if (prime)
                requests++;
        }
        System.out.println( "Count of cofee-request - " + requests );
        scanner.close();
    }
}