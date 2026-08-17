import java.util.Scanner;

class   Program
{
    static void terminate( Scanner scanner )
    {
        System.err.println("IllegalArgument");
        scanner.close();
        System.exit(-1);
    }

    public static void  main( String[] args )
    {
        final Scanner   scanner = new Scanner(System.in);
        long            num;
        long            iter = 2;
        int             steps = 0;
        boolean         prime = true;

        try
        {
            System.out.print("-> ");
            num = scanner.nextLong( );
            if (num < 2)
                terminate(scanner);
            while (iter < num / 2)
            {
                steps++;
                if (num % iter == 0)
                {
                    prime = false;
                    break ;
                }
                iter++;
            }
            System.out.println( prime + " " + steps );
            scanner.close();
        }
        catch (Exception e)
        {
            terminate(scanner);
        }
    }
}