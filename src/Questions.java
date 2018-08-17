import java.util.*;
public class Questions {
  public static void main(String[] args) {
    final int SIZE = 5;
    ArrayList<String> questions = new ArrayList<String>(
       Arrays.asList("How can Rey beat Kylo Ren when she's only been training in the force for a few days and Kylo Ren had years of training under Luke Skywalker?", "Why did Holdo Sacrifice herself? Wouldn't it be important for high ranking officers to live?", "How does a space ship run out of gas in space? Wouldn't it just use its own inertia to move itself? was it using a generator? Why can't it use solar power?", "If the bombers are so fragile that they blow up from the slightest touch, why do they fly so close to each other? Why do they require a button to release the bombs? Why do they have a WW2 style bomb releasing mechanism in space?", "Why would Poe think Luke was distracting them so they can escape? I think Luke would've let them know if that was his intention. What was he actually trying to do by force projecting?", "Why did Yoda appear as crazy Yoda?", "Why didn't Snoke know Kylo wanted to kill him?", "Why does Luke go fishing in the most over the top way imaginable?", "How was Rose able to crash into fin laterally when she was behind him the whole time?", "Why would you put some unknown substance in your mouth to see what it was made of?", "Why did bb8 say there were no exits when there were?", "Why does bb8 have a mechanism for inserting coins like a slot machine?", "Why Snoke call Kylo Ren in the previous movie to finish his training yet only berates him for being beaten by a girl?", "How did Rey actually escape Snoke's ship as it was being destroyed?", "Why is DJ in a prison cell if he can escape so easily?", "Why did Holdo wait to kamikaze into the dreadnought only until half the resistance were shot down?", "How could Leia survive the vacuum of space? Did she have some sort of force bubble? Why was there frost accumulating on her face?"
         )
    );
    ArrayList<String> people = new ArrayList<String>(
       Arrays.asList("Petrarch", "Augustine", "Dante", "Aristotle", "Maria Moliner")
     ); 


    Random r = new Random();
    Scanner wait = new Scanner(System.in); //waits for any key press to get next question
    //System.out.println(r.nextInt(SIZE));
    while(!questions.isEmpty() && !people.isEmpty()) {
      int qRandom = r.nextInt(questions.size()); //retrieve by random index from lists
      int pRandom = r.nextInt(people.size()); 

      System.out.println(questions.remove(qRandom));
      System.out.println(people.remove(pRandom));
      wait.nextLine();
    }
    System.out.println("done");
  }
}
