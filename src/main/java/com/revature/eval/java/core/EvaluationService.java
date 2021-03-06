package com.revature.eval.java.core;
import java.util.*;
import java.time.*;
import java.time.temporal.*;

public class EvaluationService {
  
	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		// TODO Write an implementation for this method declaration
		String stringRev = "";
		for(int i=string.length()-1; i>=0; i--) {
			stringRev += string.charAt(i);
		}
		return stringRev;
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		// TODO Write an implementation for this method declaration
    String[] words = phrase.split("[ -]");
    String acronym = "";
    for(String s : words) {
      acronym += s.charAt(0);
    }
		return acronym.toUpperCase();
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			return (sideOne == sideTwo) && (sideTwo == sideThree);
		}


		public boolean isIsosceles() {
			return (sideOne == sideTwo) || (sideOne == sideThree);
		}

		public boolean isScalene() {
			return (sideOne != sideTwo) && (sideTwo != sideThree);
		}

	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
    int score = 0;
    HashMap<Character, Integer> scoreCodes = new HashMap<Character, Integer>();
    for(int i=65; i!=91; i++) {
      Character c = new Character((char) i); 
      switch (c) {
        case 'A': case 'E': case 'I': case 'O': case 'U': case 'L': case 'N': case 'R': case 'S': case 'T': 
          scoreCodes.put(c, 1);
          break;
        case 'D': case 'G': 
          scoreCodes.put(c, 2);
          break;
        case 'B': case 'C': case 'M':  case 'P':  
          scoreCodes.put(c,3);
          break;
        case 'F': case 'H': case 'V': case 'W': case 'Y': 
          scoreCodes.put(c,4);
          break;
        case 'K': 
          scoreCodes.put(c,5);
          break;
        case 'J': case 'X': 
          scoreCodes.put(c,8);
          break;
        case 'Q': case 'Z':
          scoreCodes.put(c,10);
          break;
      }
    }
    for(char c : string.toUpperCase().toCharArray()) {
      score += scoreCodes.get(c);
    }
		return score;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) throws IllegalArgumentException {
    StringBuffer stringBuf = new StringBuffer(string);
    StringBuffer num = new StringBuffer();
    if(stringBuf.charAt(0) == '+') stringBuf.replace(0,1,"");
    string = string.replaceAll("[^0-9]", "");
    if(string.length() != 10) {
      throw new IllegalArgumentException();
    }
    for(char c : stringBuf.toString().toCharArray()) {
      if(Character.isDigit(c))
        num.append(c);
    }
		return num.toString();
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		// TODO Write an implementation for this method declaration
    string = string.replaceAll("[^a-zA-Z]", " ");
    string = string.replaceAll(" [^a-zA-Z]", " ");
    String[] words = string.split(" ");
    HashMap<String, Integer> wc = new HashMap<String, Integer>();
    for(String w : words) {
      if(wc.containsKey(w)) {
        int occ = wc.get(w);
        wc.put(w, ++occ);
      }
      else {
        wc.put(w,1);
      }
    }    
    return wc;
  }

  /**
   * 7. Implement a binary search algorithm.
   * 
   * Searching a sorted collection is a common task. A dictionary is a sorted list
   * of word definitions. Given a word, one can find its definition. A telephone
   * book is a sorted list of people's names, addresses, and telephone numbers.
   * Knowing someone's name allows one to quickly find their telephone number and
   * address.
   * 
   * If the list to be searched contains more than a few items (a dozen, say) a
   * binary search will require far fewer comparisons than a linear search, but it
   * imposes the requirement that the list be sorted.
   * 
   * In computer science, a binary search or half-interval search algorithm finds
   * the position of a specified input value (the search "key") within an array
   * sorted by key value.
   * 
   * In each step, the algorithm compares the search key value with the key value
   * of the middle element of the array.
   * 
   * If the keys match, then a matching element has been found and its index, or
   * position, is returned.
   * 
   * Otherwise, if the search key is less than the middle element's key, then the
   * algorithm repeats its action on the sub-array to the left of the middle
   * element or, if the search key is greater, on the sub-array to the right.
   * 
   * If the remaining array to be searched is empty, then the key cannot be found
   * in the array and a special "not found" indication is returned.
   * 
   * A binary search halves the number of items to check with each iteration, so
   * locating an item (or determining its absence) takes logarithmic time. A
   * binary search is a dichotomic divide and conquer search algorithm.
   * 
   */
  static class BinarySearch<T> {
    private List<T> sortedList;
    
    public int indexOf(T target) {
      int limit_down=0;
      int limit_up = sortedList.size()-1;
      int curIdx = ((limit_down + limit_up)/2) + 1;
      T curVal = sortedList.get(curIdx);

      while(curVal != target) {
        Integer iCurVal = Integer.parseInt(curVal.toString());
        if(iCurVal.compareTo(new Integer(target.toString())) > 0) {
          limit_up = curIdx; 
          curIdx = ((limit_down + limit_up)/2) ;
        }
        else if (iCurVal.compareTo(new Integer(target.toString())) < 0) {
          limit_down = curIdx;
          curIdx = ((limit_down + limit_up)/2) ;
        }
        else {
          System.out.println("HEYO");
          System.exit(2);
        }
      }
      return curIdx;
    }

    public BinarySearch(List<T> sortedList) {
      super();
      this.sortedList = sortedList;
    }

    public List<T> getSortedList() {
      return sortedList;
    }

    public void setSortedList(List<T> sortedList) {
      this.sortedList = sortedList;
    }

  }

  /**
   * 8. Implement a program that translates from English to Pig Latin.
   * 
   * Pig Latin is a made-up children's language that's intended to be confusing.
   * It obeys a few simple rules (below), but when it's spoken quickly it's really
   * difficult for non-children (and non-native speakers) to understand.
   * 
   * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
   * the word. Rule 2: If a word begins with a consonant sound, move it to the end
   * of the word, and then add an "ay" sound to the end of the word. There are a
   * few more rules for edge cases, and there are regional variants too.
   * 
   * See http://en.wikipedia.org/wiki/Pig_latin for more details.
   * 
   * @param string
   * @return
   */
  public String toPigLatin(String string) {
    String[] words = string.split(" ");
    StringBuffer trans = new StringBuffer();
    for(String w : words) {
      StringBuffer wBuf = new StringBuffer(w); //convert word to StringBuffer

      String firstChar = wBuf.substring(0,1);
      String suffix;
      String nextChar;
      switch (firstChar.toUpperCase()) {
        case "A": case "E": case "I": case "O": case "U":
          suffix = "ay";
          trans.append(wBuf);
          break;
        case "S":
          nextChar = wBuf.substring(1,3);
          
          if(nextChar.toUpperCase().equals("CH")) {
            firstChar += nextChar; //if char after q == u, treat as a unit
            suffix = firstChar + "ay";
            trans.append(wBuf.delete(0,3));
          }
          else {
            suffix = firstChar;
            trans.append(wBuf.delete(0,1));
          }
          break;
        case "T":
          nextChar = wBuf.substring(1,2);
          if(nextChar.toUpperCase().equals("H")) {
            firstChar += nextChar; //if char after q == u, treat as a unit
            suffix = firstChar + "ay";
            trans.append(wBuf.delete(0,2));
          }
          else {
            suffix = firstChar;
            trans.append(wBuf.delete(0,1));
          }
          break;

        case "Q":
          nextChar = wBuf.substring(1,2);
          if(nextChar.toUpperCase().equals("U")) {
            firstChar += nextChar; //if char after q == u, treat as a unit
            suffix = firstChar + "ay";
            trans.append(wBuf.delete(0,2));
          }
          else {
            suffix = firstChar;
            trans.append(wBuf.delete(0,1));
          }
          break;
        default:
          suffix = firstChar + "ay";
          trans.append(wBuf.deleteCharAt(0));
      }
      trans.append(suffix);
      trans.append(" ");

    }

    return trans.toString().trim();
  }

  /**
   * 9. An Armstrong number is a number that is the sum of its own digits each
   * raised to the power of the number of digits.
   * 
   * For example:
   * 
   * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
   * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
   * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
   * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
   * a number is an Armstrong number.
   * 
   * @param input
   * @return
   */
  public boolean isArmstrongNumber(int input) {
    ArrayList<Double> digits = new ArrayList<Double>();
    String num = Integer.toString(input);
    int pow = num.length();
    double sum = 0;
    for(String s : num.split("")) {
      digits.add(Double.parseDouble(s));
      sum += Math.pow(Double.parseDouble(s), pow);
    }

    return (int)sum == input;

  }

  /**
   * 10. Compute the prime factors of a given natural number.
   * 
   * A prime number is only evenly divisible by itself and 1.
   * 
   * Note that 1 is not a prime number.
   * 
   * @param l
   * @return
   */
  public List<Long> calculatePrimeFactorsOf(long l) {
    ArrayList<Long> factors = new ArrayList<Long>();
    return divide(l, 2, factors);
  }
  public  ArrayList<Long> divide(long a, long b, ArrayList<Long> factors) {     

    if(b == a) {     
      //print(b);     
      factors.add(b);     
      return factors;     
    }     
    else if(a%b==0) {     
      factors.add(b);     
      return divide(a/b, b, factors);     
    }     
    else {     
      return divide(a, b+1, factors);     
    }     
  }   
  /**
   * 11. Create an implementation of the rotational cipher, also sometimes called
   * the Caesar cipher.
   * 
   * The Caesar cipher is a simple shift cipher that relies on transposing all the
   * letters in the alphabet using an integer key between 0 and 26. Using a key of
   * 0 or 26 will always yield the same output due to modular arithmetic. The
   * letter is shifted for as many values as the value of the key.
   * 
   * The general notation for rotational ciphers is ROT + <key>. The most commonly
   * used rotational cipher is ROT13.
   * 
   * A ROT13 on the Latin alphabet would be as follows:
   * 
   * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
   * stronger than the Atbash cipher because it has 27 possible keys, and 25
   * usable keys.
   * 
   * Ciphertext is written out in the same formatting as the input including
   * spaces and punctuation.
   * 
   * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
   * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
   * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
   * quick brown fox jumps over the lazy dog.
   */
  static class RotationalCipher {
    private int key;

    public RotationalCipher(int key) {
      super();
      this.key = key;
    }

    public String rotate(String string) {
      // TODO Write an implementation for this method declaration
      //
      char[] chars = string.toCharArray();
      String cipher = "";
      for(char c : chars) {
        char cc = c;
        if(Character.isLetter(c)) {
          if(Character.isUpperCase(c)) {
            int code = (int)c - 65;
            cc = (char)(((code+key) % 26) + 65);
          }
          else {
            int code = (int)c - 97;
            cc = (char)(((code+key) % 26) + 97);
          }
        }
        cipher = cipher.concat(Character.toString(cc));
      }
      return cipher;
    }

  }


  /**
   * 12. Given a number n, determine what the nth prime is.
   * 
   * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
   * that the 6th prime is 13.
   * 
   * If your language provides methods in the standard library to deal with prime
   * numbers, pretend they don't exist and implement them yourself.
   * 
   * @param i
   * @return
   */
  public int calculateNthPrime(int i) {
    ArrayList<Integer> primes = new ArrayList<Integer>();

    if(i == 1) return 2;
    if(i < 1) throw new IllegalArgumentException();

    primes.add(2);
    primes.add(3);
    int temp = 3;
    while(primes.size() < i) {
      if(isPrime(temp, primes))
      {
        primes.add(temp);
        temp = primes.get(primes.size()-1);
      }
      temp += 2;
    }
    return primes.get(primes.size()-1);
  }

  static boolean isPrime(int n, List<Integer> primes) {
    for(int p : primes) {
      if(n % p == 0) {
        return false;
      }
    }
    return true;
  }


  /**
   * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
   * system created in the Middle East.
   * 
   * The Atbash cipher is a simple substitution cipher that relies on transposing
   * all the letters in the alphabet such that the resulting alphabet is
   * backwards. The first letter is replaced with the last letter, the second with
   * the second-last, and so on.
   * 
   * An Atbash cipher for the Latin alphabet would be as follows:
   * 
   * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
   * very weak cipher because it only has one possible key, and it is a simple
   * monoalphabetic substitution cipher. However, this may not have been an issue
   * in the cipher's time.
   * 
   * Ciphertext is written out in groups of fixed length, the traditional group
   * size being 5 letters, and punctuation is excluded. This is to make it harder
   * to guess things based on word boundaries.
   * 
   * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
   * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
   *
   */
  static class AtbashCipher {
    public static HashMap<Character, Character> key = new HashMap<Character, Character>();
    static {
      key.put('a', 'z');
      key.put('b', 'y');
      key.put('c', 'x');
      key.put('d', 'w');
      key.put('e', 'v');
      key.put('f', 'u');
      key.put('g', 't');
      key.put('h', 's');
      key.put('i', 'r');
      key.put('j', 'q');
      key.put('k', 'p');
      key.put('l', 'o');
      key.put('m', 'n');
      key.put('n', 'm');
      key.put('o', 'l');
      key.put('p', 'k');
      key.put('q', 'j');
      key.put('r', 'i');
      key.put('s', 'h');
      key.put('t', 'g');
      key.put('u', 'f');
      key.put('v', 'e');
      key.put('w', 'd');
      key.put('x', 'c');
      key.put('y', 'b');
      key.put('z', 'a');
    }
    public static HashMap<Character, Character> decodeKey = new HashMap<Character, Character>();
    static {
      decodeKey.put('z','a') ;
      decodeKey.put('y','b') ;
      decodeKey.put('x','c') ;
      decodeKey.put('w','d') ;
      decodeKey.put('v','e') ;
      decodeKey.put('u','f') ;
      decodeKey.put('t','g') ;
      decodeKey.put('s','h') ;
      decodeKey.put('r','i') ;
      decodeKey.put('q','j') ;
      decodeKey.put('p','k') ;
      decodeKey.put('o','l') ;
      decodeKey.put('n','m') ;
      decodeKey.put('m','n') ;
      decodeKey.put('l','o') ;
      decodeKey.put('k','p') ;
      decodeKey.put('j','q') ;
      decodeKey.put('i','r') ;
      decodeKey.put('h','s') ;
      decodeKey.put('g','t') ;
      decodeKey.put('f','u') ;
      decodeKey.put('e','v') ;
      decodeKey.put('d','w') ;
      decodeKey.put('c','x') ;
      decodeKey.put('b','y') ;
      decodeKey.put('a','z') ;
    }
    /**
     * Question 13
     * 
     * @param string
     * @return
     */
    public static String encode(String string) {
      string = string.toLowerCase().replaceAll("[^a-z0-9]", "");
      String encoding = "";
      for(char c : string.toCharArray()) {
        char cipher = c;
        if(key.containsKey(c)) {
          cipher = key.get(c);
          encoding += Character.toString(cipher);
        }
        else {
          encoding += Character.toString(c);
        }
      }

      String temp = encoding;
      encoding = "";
      for(int i=0; i<temp.length(); i++)
      {
        if(i % 5 == 0) 
          encoding += " ";
        encoding += Character.toString(temp.charAt(i));
      }
      return encoding.trim();
    }


    /**
     * Question 14
     * 
     * @param string
     * @return
     */
    public static String decode(String string) {
      //constraints: must be lower case, white space after every 5 characters
      String msg = "";
      for(char c : string.toCharArray()) {
        if(c == ' ') //skip spaces
          continue;
        if(decodeKey.containsKey(c))
          msg += decodeKey.get(c);
        else
          msg += c;
      }

      return msg;
    }
  }

  /**
   * 15. The ISBN-10 verification process is used to validate book identification
   * numbers. These normally contain dashes and look like: 3-598-21508-8
   * 
   * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
   * a digit or an X only). In the case the check character is an X, this
   * represents the value '10'. These may be communicated with or without hyphens,
   * and can be checked for their validity by the following formula:
   * 
   * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
   * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
   * otherwise it is invalid.
   * 
   * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
   * and get:
   * 
   * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
   * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
   * 
   * @param string
   * @return
   */
  public boolean isValidIsbn(String string) {
    // TODO Write an implementation for this method declaration
    ArrayList<Integer> number = new ArrayList<Integer>();

    for(String s : string.split("")) {
      try {
        number.add(Integer.parseInt(s));
      }
      catch(NumberFormatException e) {
        if(!(s.matches("[X-]"))) {
          return false;
        }
        if(s.matches("X"))
          number.add(10);
      }
    }
    int sum = 0;
    for(int i=0, j=10; i<number.size(); i++, j--) {
      sum += number.get(i)*j;
    }
    return sum % 11 == 0;

  }

  /**
   * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
   * gramma, "every letter") is a sentence using every letter of the alphabet at
   * least once. The best known English pangram is:
   * 
   * The quick brown fox jumps over the lazy dog.
   * 
   * The alphabet used consists of ASCII letters a to z, inclusive, and is case
   * insensitive. Input will not contain non-ASCII symbols.
   * 
   * @param string
   * @return
   */
  public boolean isPangram(String string) {
    // TODO Write an implementation for this method declaration
    char[] chars = string.toLowerCase().toCharArray();
    HashSet<Character> letters = new HashSet<Character>();
    for(char c : chars) {
      if(Character.isLetter(c)) letters.add(c); 
    }
    if(letters.size() == 26) return true;
    return false;
  }

  /**
   * 17. Calculate the moment when someone has lived for 10^9 seconds.
   * 
   * A gigasecond is 109 (1,000,000,000) seconds.
   * 
   * @param given
   * @return
   */
  public Temporal getGigasecondDate(Temporal given) {
    Duration dur = Duration.ofSeconds(1000000000);
    if(given instanceof LocalDateTime)
      return given.plus(dur);
    LocalDateTime givenDateTime = LocalDateTime.of((LocalDate)given, LocalTime.of(0,0,0));
    givenDateTime = givenDateTime.plus(dur);
    return givenDateTime;
  }

  /**
   * 18. Given a number, find the sum of all the unique multiples of particular
   * numbers up to but not including that number.
   * 
   * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
   * get 3, 5, 6, 9, 10, 12, 15, and 18.
   * 
   * The sum of these multiples is 78.
   * 
   * @param i
   * @param set
   * @return
   */
  public int getSumOfMultiples(int i, int[] set) {
    HashSet<Integer> multiples = new HashSet<Integer>();
    for(int m : set) {
      int n = m; // save value of m
      do {
        multiples.add(n);
        n += m;
      }
      while(n < i);
    }
    int sum =0;
    for(int m : multiples) {
      sum += m;
    }
    return sum;
  }


  /**
   * 19. Given a number determine whether or not it is valid per the Luhn formula.
   * 
   * The Luhn algorithm is a simple checksum formula used to validate a variety of
   * identification numbers, such as credit card numbers and Canadian Social
   * Insurance Numbers.
   * 
   * The task is to check if a given string is valid.
   * 
   * Validating a Number Strings of length 1 or less are not valid. Spaces are
   * allowed in the input, but they should be stripped before checking. All other
   * non-digit characters are disallowed.
   * 
   * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
   * the Luhn algorithm is to double every second digit, starting from the right.
   * We will be doubling
   * 
   * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
   * then subtract 9 from the product. The results of our doubling:
   * 
   * 8569 2478 0383 3437 Then sum all of the digits:
   * 
   * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
   * then the number is valid. This number is valid!
   * 
   * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
   * digits, starting from the right
   * 
   * 7253 2262 5312 0539 Sum the digits
   * 
   * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
   * this number is not valid.
   * 
   * @param string
   * @return
   */
  public boolean isLuhnValid(String string) {
    // TODO Write an implementation for this method declaration
    ArrayList<Integer> number = new ArrayList<Integer>();

    for(String s : string.split("")) {
      try {
        number.add(Integer.parseInt(s));
      }
      catch(NumberFormatException e) {
        if(!s.equals(" ")) return false;
        continue;
      }
    }
    //check for invalid chars
    
    //reverse order to start operation from right
    Collections.reverse(number);
    for(int i=0; i<number.size(); i++)
    {
      if(i%2==1)
      {
        int temp = number.get(i);
        temp *=2;
        if(temp > 9) temp -= 9;
        number.set(i,temp);
      }
    }

    //sum all digits
    int sum = 0;
    for(int i : number) {
      sum += i;
    }
    if(sum % 10 == 0) return true;
   return false;
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
    String[] tokens = string.split("[ ?]");
    //map operation strings to lambdas
    HashMap<String, IntegerMath> operations = new HashMap<String, IntegerMath>();
    IntegerMath addition = (a,b) -> a+b;
    IntegerMath subtraction = (a,b) -> a-b;
    IntegerMath multiplication = (a,b) -> a*b;
    IntegerMath division = (a,b) -> a/b;
    String operator = "";

    operations.put("plus", addition);
    operations.put("minus", subtraction);
    operations.put("multiplied", multiplication);
    operations.put("divided", division);
    ArrayList<Integer> ops = new ArrayList<Integer>();
    for(String t : tokens) {
      try {
        ops.add(Integer.parseInt(t));
      }
      catch(NumberFormatException e) {
       if(operations.containsKey(t))
       {
         operator = t;
       }
      }
    }
    int res = operateBinary(ops.get(0), ops.get(1), operations.get(operator));
    return res;

	}

  interface IntegerMath {
    int operation(Integer a, Integer b); 
  }

  public static int operateBinary(Integer a, Integer b, IntegerMath op) {
    return op.operation(a,b);
  }
}
