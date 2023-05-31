# Brief description of the cryptocurrency
## Why is the voting system implemented in this system.
Let's pretend that you
1. A US citizen and you are 25 years old.
2. Live in Texas
3. Work on a chicken farm
4. And you are on social security.

It turned out to be quite a complex character.
Now see the Board of Shareholders consists of 1500 accounts with the most votes.
You know that kfc is on the Board of Shareholders and this company proposes laws that
help increase her sales, which in turn turns out to help your
farm to sell more chicken. Thus, the Board of Shareholders represents the interests
this corporation and your interests by improving the quality of the economy. Because of this, the Board of Shareholders is not elected,
and appointed by the system 1500 with the most shares.

But as a Texas resident, you also want infrastructure to develop in your area.
Thus, you participate in the voting and elect your representatives to the Board of Directors,
how is this electoral system specially designed in such a way that the Board of Directors
there are persons who represent mainly regions.

But you may have conservative views, then your interests in this aspect are already represented
factions of your choice.

You are also on social security, and for example, you yourself can vote for
a law that will reduce the price of a specific group of medicines.

Thus the Board of Directors and Fractions are elected and represent your interests at the regional
level and your views. The economic interests are represented by the Board of Shareholders, who are not elected,
but are key players in this economy.
And you can represent specific interests yourself by voting pointwise for this or that law.

It uses a different mechanism of the electoral system and a different way of voting in order to
represent your interests at different levels.


## Brief description of the cryptocurrency

This cryptocurrency is a whole eco-system that allows not only to exchange
monetary units, but also in this system an electoral system is implemented,
allowing you to elect your representatives who will promote your interests in this network.
It also allows you to create rules (laws) for which your representatives will vote, and all
network members will instantly be able to see what decisions have been made.

The system also implements direct democracy, which allows you to directly vote for the rules of the network (laws).


Also, this coin implements the Demurrage mechanism, 0.1% every six months, a digital dollar and
0.2% for a digital share.
Demurrage is a service fee, this mechanism is used in many countries,
and it's called a negative rate, but the most direct application was
The first practical application of Gesell's views was an experiment in 1932 in the Austrian town of Wörgl with a population of 3,000 people.

This measure provides frequent course corrections, which allows this System to be as
resilient to the crisis and sustainably maintain its course.

Why do we need an electoral system?
I will give a real example from cryptocurrencies and you will understand why you need it.
1. Bitcoin. Bitcoin is still debating whether to increase the block size or not.
   This task cannot be solved within the framework of bitcoin, since there is no voting mechanism in bitcoin.
   You cannot make a single decision in bitcoin because there is no legitimate decision making mechanism.
2. Imagine that you have purchased mining equipment, and tomorrow the development team without
   your consent will cancel the mining, and go to the POS. Do you think this is impossible? Look at ethereum.
3. You want to buy goods with cryptocurrency, but you know that if you transfer money to a scammer,
   then you won't be able to get your money back. How are you going to find out which of the sellers of goods is
   deceiver? In this system, you can see the current package of laws, where inside there will be a list
   all online stores you can trust.
4. If you have a legal commercial dispute, you are citizens of different countries, how will you resolve this dispute?
   This system already has elected judges who will resolve contentious issues within the framework of the rules of this network.
   There are two types of currencies in the system - digital dollars and digital stocks,
   576 blocks can be mined per day, each block gives 200 coins
   each type.
   In this package they are
   ***src/main/java/International_Trade_Union/utils/UtilsBlock.java***

Difficulty is determined by the formula and is corrected every half a day.
````
  /**get complexity*/
     private static int getAdjustedDifficulty(Block latestBlock, List<Block> blocks, long BLOCK_GENERATION_INTERVAL, int DIFFICULTY_ADJUSTMENT_INTERVAL){
         Block prevAdjustmentBlock = blocks.get(blocks.size() - DIFFICULTY_ADJUSTMENT_INTERVAL);

         long timeExpected = BLOCK_GENERATION_INTERVAL * DIFFICULTY_ADJUSTMENT_INTERVAL;
         long timeTaken = latestBlock.getTimestamp().getTime() - prevAdjustmentBlock.getTimestamp().getTime();

         if(timeTaken < timeExpected / 2){

             return prevAdjustmentBlock.getHashCompexity() + 1;
         }else if(timeTaken > timeExpected * 2){

             return prevAdjustmentBlock.getHashCompexity() - 1;
         } else {
             return prevAdjustmentBlock.getHashCompexity();
         }
     }
````

````
    /**determines the difficulty, since half a day adjusts the difficulty. There are 576 blocks per day.
  * each block is mined in approximately 2.3 minutes*/
     public static int difficulty(List<Block> blocks, long BLOCK_GENERATION_INTERVAL, int DIFFICULTY_ADJUSTMENT_INTERVAL ){

         //seconds how often blocks are created
         int difficulty = 1;
         Block latestBlock = blocks.get(blocks.size() -1);
         if(latestBlock.getIndex() != 0 && latestBlock.getIndex() % DIFFICULTY_ADJUSTMENT_INTERVAL == 0){
         difficulty = getAdjustedDifficulty(latestBlock, blocks, BLOCK_GENERATION_INTERVAL, DIFFICULTY_ADJUSTMENT_INTERVAL);
         System.out.println("difficulty: change dificulty: " + difficulty);
         }
         else {
         difficulty = latestBlock.getHashCompexity();
         }

         return difficulty == 0? 1: difficulty;
         }

````

What positions are there?
- Board of Directors [BOARD_OF_DIRECTORS]
- Board of Shareholders [BOARD_OF_SHAREHOLDERS]
- Council of Corporate Judges [CORPORATE_COUNCIL_OF_REFEREES]
- General Executive Director [GENERAL_EXECUTIVE_DIRECTOR]
- High Judge [HIGH_JUDGE]
- Fraction [FRACTION]
[back to home](./documentationEng.md)