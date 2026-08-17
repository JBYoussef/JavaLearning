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
        Scanner         numbers;
        String          line;
        long            st_half_weeks_values = 0;
        long            nd_half_weeks_values = 0;
        long            total_val = 0;
        int             num;
        int             n_weeks = 0;
        int             n_tests;
        int             limit;

        try
        {
            while (++n_weeks < 19)
            {
                System.out.print("-> ");
                line = scanner.nextLine();

                if (line.equals("42"))
                    break ;

                if (weekNumber( n_weeks, line ) < 0)
                    terminate(scanner);

                System.out.print("-> ");
                line = scanner.nextLine();
                numbers = new Scanner(line);
                n_tests = 0;
                total_val = 0;
                while (numbers.hasNextInt())
                {
                    ++n_tests;
                    num = numbers.nextInt();
                    if (num < 1 || num > 9 || n_tests > 5)
                    {
                        numbers.close();
                        terminate(scanner);
                    }
                    total_val += num;
                }
                if (n_tests != 5)
                    terminate(scanner);
                total_val /= 5;
                if (n_weeks < 10)
                    st_half_weeks_values |= (total_val << (4 * (n_weeks - 1)));
                else
                    nd_half_weeks_values |= (total_val << (4 * (n_weeks - 10)));
            }
            scanner.close();

            for (int result = 1; result < n_weeks; result++)
            {
                limit = 0b1111;
                System.out.print("Week " + result + " ");
                if (result < 10)
                    limit &= (st_half_weeks_values >> (4 * (result - 1)));
                else
                    limit &= (nd_half_weeks_values >> (4 * (result - 10)));
                while (--limit > 0)
                    System.out.print("=");
                System.out.println(">");
            }
        }
        catch (Exception e)
        {
            terminate(scanner);
        }
    }
}