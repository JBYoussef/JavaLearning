class    Program
{
    public static void  main( String[] args )
    {
        int digits = 479598;
        int sum = 0;

        sum += digits % 10;
        digits /= 10;

        sum += digits % 10;
        digits /= 10;

        sum += digits % 10;
        digits /= 10;

        sum += digits % 10;
        digits /= 10;

        sum += digits % 10;
        digits /= 10;

        sum += digits % 10;
        digits /= 10;

        System.out.println(sum);
    }
}