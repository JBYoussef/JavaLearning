import java.util.Scanner;

class   Program
{
    static void terminate( Scanner scanner )
    {
        System.err.println("IllegalArgument");
        scanner.close();
        System.exit(-1);
    }

    static int weekNum( int id, String line )
    {
        switch (id)
        {
            case 1:
            {
                if (line.equals("Week 1"))
                    return (1);
                break ;
            }
            case 2:
            {
                if (line.equals("Week 2"))
                    return (2);
                break ;
            }
            case 3:
            {
                if (line.equals("Week 3"))
                    return (3);
                break ;
            }
            case 4:
            {
                if (line.equals("Week 4"))
                    return (4);
                break ;
            }
            case 5:
            {
                if (line.equals("Week 5"))
                    return (5);
                break ;
            }
            case 6:
            {
                if (line.equals("Week 6"))
                    return (6);
                break ;
            }
            case 7:
            {
                if (line.equals("Week 7"))
                    return (7);
                break ;
            }
            case 8:
            {
                if (line.equals("Week 8"))
                    return (8);
                break ;
            }
            case 9:
            {
                if (line.equals("Week 9"))
                    return (9);
                break ;
            }
            case 10:
            {
                if (line.equals("Week 10"))
                    return (10);
                break ;
            }
            case 11:
            {
                if (line.equals("Week 11"))
                    return (11);
                break ;
            }
            case 12:
            {
                if (line.equals("Week 12"))
                    return (12);
                break ;
            }
            case 13:
            {
                if (line.equals("Week 13"))
                    return (13);
                break ;
            }
            case 14:
            {
                if (line.equals("Week 14"))
                    return (14);
                break ;
            }
            case 15:
            {
                if (line.equals("Week 15"))
                    return (15);
                break ;
            }
            case 16:
            {
                if (line.equals("Week 16"))
                    return (16);
                break ;
            }
            case 17:
            {
                if (line.equals("Week 17"))
                    return (17);
                break ;
            }
            case 18:
            {
                if (line.equals("Week 18"))
                    return (18);
                break ;
            }
            default:
                return (0);
        }
        return (0);
    }

    public static void  main( String[] args )
    {
        final Scanner   scanner = new Scanner(System.in);
        Scanner         numbers;
        String          line;
        long            st_half_weeks_values = 0;
        long            nd_half_weeks_values = 0;
        long            min_val;
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
                if (weekNum(n_weeks, line) == 0)
                    terminate(scanner);

                System.out.print("-> ");
                line = scanner.nextLine();
                numbers = new Scanner(line);
                n_tests = 0;
                min_val = 10;
                while (numbers.hasNextInt())
                {
                    ++n_tests;
                    num = numbers.nextInt();
                    if (num < 1 || num > 9 || n_tests > 5)
                    {
                        numbers.close();
                        terminate(scanner);
                    }
                    if (num < min_val)
                        min_val = num;
                }
                if (n_tests != 5)
                    terminate(scanner);
                if (n_weeks < 10)
                    st_half_weeks_values |= (min_val << (4 * (n_weeks - 1)));
                else
                    nd_half_weeks_values |= (min_val << (4 * (n_weeks - 10)));
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
                while (limit-- > 0)
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