#VOTE_FRACTION
This voting system is used only for factions.
First, 100 factions are selected that have become legitimate.
Then all the votes given to 100 selected factions are summed up.
After that, the share of each fraction from the total amount is determined.
votes cast for this faction.
The number of votes of each faction is equal to its percentage shares.
Thus, if a faction has 23% of the votes of all votes, out of
100 factions, then her vote is equal to 23%.

Then every time a faction votes for laws,
that start with LIBER (VoteEnum.YES) or (VoteEnum.NO).
This law counts all the votes given *** for ***
and *** against ***, after which it is subtracted from *** for *** - *** against ***.
This result is displayed as a percentage.

````
  //faction vote
     public double voteFractions(Map<String, Double> fractions){
         double yes = 0;
         double no = 0;
         double sum = fractions.entrySet().stream()
                 .map(t->t.getValue())
                 .collect(Collectors.toList())
                 .stream().reduce(0.0, Double::sum);

         for (String s : YES) {
             if (fractions.containsKey(s)) {
                 yes += (fractions.get(s)/sum) * Seting.HUNDRED_PERCENT;
             }

         }
         for (String s : NO) {
             if (fractions.containsKey(s)) {
                 no += (fractions.get(s)/sum) * Seting.HUNDRED_PERCENT;
             }

         }
         return yes - no;

     }

````

[Return to main page](../documentationEng/documentationEng.md)