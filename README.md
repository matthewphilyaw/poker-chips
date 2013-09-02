poker-chips takes in a line of stdin in and prints out stats about the given
setup specified for one player. Only shows what should be distributed.

the format is as follows
```
[buy in] [denoms] [high denom count] [step between denoms]\n

buy in           - the amount per player to join game
denoms           - is a list of denominations of chips to be used in the form 
                   of value:value:...:value
high denom count - number of high denom chips, the rest of the chip counts
                   are based off this.
step             - step is used in conjunction with high denom count to produce
                   the rest of the chip counts. the count goes up by the step
                   value. so if there are 10 high denom chips, and the step
                   is 5, then the next highest chip would have a count of 
                   15 and so on and so forth.
```
example:
```
echo "10 1:5:10:20:50 10 10" | lein trampoline run | expand --tabs=20

---------------------------------------------------------------poker-chips
 General
--------------------------------------------------------------------------
 buy in($):         10
 point value($):    0.0068965517

--------------------------------------------------------------------------
 Totals
--------------------------------------------------------------------------
 chips:             150
 points:            1450

--------------------------------------------------------------------------
 break down by chip
--------------------------------------------------------------------------
 denom              count               value($)            total-value($)
 1                  50                  0.007               0.345
 5                  40                  0.034               1.379
 10                 30                  0.069               2.069
 20                 20                  0.138               2.759
 50                 10                  0.345               3.448
```
as you can see it takes those simple rules, figures out the other chips
based on the step, then totals up the points for setup, and then breaks
down the value of the chip by denomination and then totals the value for 
that denomination for the initial setup. This isn't intended to track or
keep up with points between rounds just the initial setup for players.

I make no attemp to insert the tabs my self, 
probably should, but didn't. I would pipe the output to expand

Built around lein, so the easiest way to run it is form the root of the repo
with the following (test-input.txt is in the root of the repo)
```
# note trampoline is used here because I think stdout is swallowed otherwise
cat test-input.txt | lein trampoline run | expand --tabs=20
```

I have more ideas for this, it was just a quick pass. I'd like to expand
the rules I digest from stdin to offer more flexibility. 
