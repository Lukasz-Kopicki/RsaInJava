import java.lang.reflect.Array;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.lang.Math;

public class MainRSa {
    public static long NWD_1(long pierwsza, long druga)
    {
        while (pierwsza != druga) // dopóki dwie liczby nie są sobie równe
        {
            if (pierwsza > druga)  // sprawdzamy, która z nich jest większa
            {
                pierwsza = pierwsza - druga; // odejmujemy mniejszą liczbę
            }                               // od większej
            else
            {
                druga = druga - pierwsza;
            }
        }
        return pierwsza;
    }
    public static long generujLiczbePierwsza(long liczbaPodanaPrzezUsera)
    {
        for( long j=liczbaPodanaPrzezUsera; j>2;j--) {
            if(isPrime(j)){
                return j;
            }
        }
        return 0;
    }
    public static long WyliczE(long phi)
    {
        long i;
        for( i=phi-1;i>2;i-- )
        {
            if(isPrime(i)){
                if(NWD_1(phi,i)==1){
                    return i;
                }
                break;
            }
        }
        return 0;
    }
    public static long GenerujD(long e, long phi)
    {
        for(long d=1;;d++)
        {
            if((e*d-1)%phi==0)
            {
                return d;
            }
        }
    }
    static boolean isPrime(long n)
    {
        if (n <= 1)
            return false;
        for (long i = 2; i < n; i++)
            if (n % i == 0) {
                return false;
            }
            else{
                // System.out.println("pierwsza");
            }
        return true;
    }
    public static long ObliczamyN(long p, long q)
    {
        long n;
        return n=p*q;
    }
    public static long zwracamyPhi(long p, long q)
    {
        long phi;
        return phi=(p-1)*(q-1);
    }

    public static long powe(long base, long power) {
        if (power == 0)
            return 1;
        if (power % 2 == 1)
            return powe(base, power - 1) * base;
        else {
            long a = powe(base, power / 2);
            return a * a;
        }
    }

    public static long powerN(long number, long power
    ) {
        if(power == 0) return 1;
        long result = number;

        while(power > 1) {
            result*=number;
            power--;
        }

        return (long)result;
    }

    public static BigInteger powerBig(long number, int power)
    {
        BigInteger b1;

        String x = Long.toString(number);

        b1 = new BigInteger(x);

        BigInteger result = b1.pow(power);

        return result;
    };

    public static BigInteger powerBigg(BigInteger number, int power)
    {

        BigInteger result = number.pow(power);
        return result;
    };


    public static long generujInt(long m,long e, long n)
    {

        long c;


        System.out.print(c=powe(m,e)%n);


        return c;
    }

  /*  public static String Message(String m)
    {
        byte[] ascii =m.getBytes(StandardCharsets.US_ASCII);
        String asciiString = Arrays.toString(ascii);

    }*/

  public static BigInteger[] KonwersjaCZLongNaBigInteger(byte[] tablice)
  {
      BigInteger[] tab=new BigInteger[tablice.length];


      for(int i=0;i<tablice.length;i++)
      {
          tab[i]= BigInteger.valueOf(tablice[i]);
      }
      return tab;
  }

    public static BigInteger[] generujC(String m,int e, long n)
    {
        byte[] ascii =m.getBytes(StandardCharsets.US_ASCII);
        BigInteger[] tab=new BigInteger[ascii.length];
        tab=KonwersjaCZLongNaBigInteger(ascii);

        BigInteger nn=BigInteger.valueOf(n);
        long[] c= new long[ascii.length];
      /*  System.out.println("------------------------------------------------");
        System.out.println("------------------------------------------------");
        System.out.print(""+ascii);*/
        System.out.println("------------------------------------------------");
        System.out.println("------------------------------------------------");
        System.out.println(" Tablica zaszyfrowana:");
        System.out.println(" ");
        for(int i=0;i<ascii.length;i++)
        {
            tab[i]=powerBig(ascii[i],e).mod(nn);
            System.out.print(" "+tab[i]);
        }


        System.out.println(" ");
        return tab;
    }

    public static BigInteger  deszyfrowanieObliczM(long c,int d,long n)
    {
        BigInteger m;
        BigInteger b1;
        String x = Long.toString(n);
        b1 = new BigInteger(x);
        m=powerBig(c,d).mod(b1);
        return m;
    }

    public static void deszyfrowanieObliczMAsci(BigInteger c[],int d,long n)
    {
        char[] tab=new char[c.length];

        BigInteger x=BigInteger.valueOf(n);
        System.out.println("--------------------------");
        BigInteger m;
        for(int i=0;i<c.length;i++) {

            m=powerBigg(c[i],d).mod(x);
           // tab[i]=(char)m;
            tab[i]=(char)(m.intValue());
            //System.out.print(" "+m);
            //System.out.println("");
            System.out.print(tab[i]);
        }
    }
    public static void main(String[] args) {

        long p=generujLiczbePierwsza(112);
        System.out.print("Liczba p "+p);
        System.out.println("-------------------------------------");
        System.out.println("-------------------------------------");
        long q=generujLiczbePierwsza(129);
        System.out.print("Liczba q "+q);
        System.out.println("-------------------------------------");
        System.out.println("-------------------------------------");
        long n=ObliczamyN(p,q);
        System.out.print("Liczba n "+n);
        System.out.println("-------------------------------------");
        System.out.println("-------------------------------------");
        long phi=zwracamyPhi(p,q);
        System.out.print("Liczba phi "+phi);
        System.out.println("-------------------------------------");
        System.out.println("-------------------------------------");
        long e=WyliczE(phi);
        System.out.print("Liczba e "+e);
        System.out.println("-------------------------------------");
        System.out.println("-------------------------------------");
        long d=GenerujD(e,phi);

        int x= (int) d;

        System.out.println("Liczba d "+d);
        System.out.println("-------------------------------------");
        System.out.println("-------------------------------------");
        System.out.print(" Para e: "+e+" i n:"+n+" tworza klucz publiczny");
        System.out.println("-------------------------------------");
        System.out.println("-------------------------------------");

        System.out.print("Para d: "+d+" i n:"+n+" tworza klucz prywatny");

        System.out.println("-------------------------------------");
        System.out.println("-------------------------------------");

        BigInteger[] tab=new BigInteger[50];

        int ee=(int)e;

       tab=generujC("fajnie by bylo jak bysmy wrocili juz na uczelnie!!",ee,n);

        System.out.println("-------------------------------------");
        System.out.println("-------------------------------------");
        deszyfrowanieObliczMAsci(tab,x,n);
    }
}
