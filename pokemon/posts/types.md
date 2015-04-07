--- 
title: "Types"
author: "Vishesh"
tags: ["pokemon"]
summary: "In which I investigate the act of rating pokemon types and also look at which types are similar to each other. Then repeat the process with 2-type combinations."
created: "2015-03-29 00:41:30"
--- 

## Types {.post-title}
Pokemon have types. This is an interesting phenomenon since the types determine 
which kinds of moves get an extra boost (offensive typing) and 
what types of attacks the pokemon is weak to (defensive typing). 

However, in trying to classify pokemon, types are an interesting hurdle - on one hand
they are discrete quantities, but on the other hand, we know that there are some
similarities between different types - for example, we know that Ghost type and Dark
type attacks are similar in nature. 

So the "type" attribute is actually a complex *loosely-ordered* axis on which to 
compare pokemon. The actual interaction between the current 18 types of pokemon are
determined by the pokemon type chart:

![pokemon type chart](http://img.pokemondb.net/images/typechart.png) 

The first question we can ask is how these types relate to each other offensively and 
defensively and then how they should be ranked - which types are the best offensively
and defensively?

Let's actually tackle the second question first. There are four possibilities when
you use attack type X on pokemon type Y - super effective (o), neutral ( ), not very 
effective (-), and immune (x). The offensive effectiveness of each type can be 
expressed as a linear combination of how many types it's good against (super effective)
and bad against, and ineffective against. 
So we generate the counts and then apply the following weights: 
[2, 1, -2, -2]\*[#o,# ,#-,#x] (offensive) and [-1.25, 0, 1, 1.25] defensive.
and we get the following tables (left offensive, right defensive).

![type effectiveness rankings](/img/pokemon_rankings_singletype.png)

First, some things are obvious - steel is a massively OP defensive type, and grass and bug are
massively bad attack types (seriously 7 resistances!?). Of course, this doesn't handle
the case of multiple attack types together, which severely changes the landscape - 
for example, fighting is one of the worst attack types but it actually has 5 types
it's SE against, so pairing it with something is probably going to end well. 
However, no fighting type move is going to be ultra spammable any time soon. 
Ice is also a horrendously bad defensive type (score of -4 and rock's score is -2.25).

Also offensively, the only thing interesting is that some types are bad. Aside from those,
the scores of the other types are all between 10 (Ice) and 14 (Ghost) (and the lowest is 0, bug and grass). 

Now that we know this, another question we might want to ask is which types are 
similar offensively and defensively (for example, what's the most similar to grass, so
that we can use that type instead of grass to attack something!

Of course, each of these types is actually QUITE different! "similarity" is a loose term.

![singletype dendrogram](/img/pokemon_dendrogram_singletype.png)


You can tell by the height of the dendrogram that some types are wierder than others. 
Ground and Fighting, for example, have the highest bars and therefore took the largest
distance to integrate with the others. Defensively, the similarities look like this:

![singletype defensive dendrogram](/img/pokemon_dendrogram_defense.png)

Now defensively, we can see that Steel is very very different from the other types. 

There is a question of convergence, since this is a game we're playing. For example,
Steel is the best defensive type, but it's weak to Ground, which is one of the best 
offensive types. So maybe we should be weighting the types not just based on the effect
but also the score on the opposing side (eg, if steel is only weak to fire/ground/fighting then
those types are much more important offensively.). Of course, it can be even more complicated
when you consider the availability of certain types of pokemon (so if there's very very few ghost type pokemon, beating them isn't necessary).
The best way to compute that would be to use type * usage percentage (% of times you see it in a battle).
However, this is a good first step. 

Now, pokemon actually have up to two types, not 1! So we really want to analyze the defensive prowess 
of each two-type combination. With 2 types, we actually have a few more relations - 
something can be 4x SE or 1/4x NVE too! So the weights are {-2, -1.5, 0, 1.5, 1.75}.
Basically my intuition is that neutral is a zero either way, 4x weakness sucks really bad
(so -2) and it sucks much worse than it rocks to have an immunity. 2x weakness seems about 
equal to a 1/4x NVE type. 

| Rank |  Type |  4x |  2x |  1x |  1/2x |  1/4x |  0x |  Score |  # of Pkmn with type|
|------|-------|-----|-----|-----|-------|-------|-----|--------|----|
| 1 |  Fairy-Steel |  0 |  2 |  5 |  8 |  1 |  2 |  10. |  3 |
| 2 |  Flying-Steel |  0 |  2 |  6 |  6 |  2 |  2 |  9.5 |  1 |
| 3 |  Electric-Steel |  1 |  2 |  3 |  9 |  2 |  1 |  8.75 |  1 |
| 4 |  Ghost-Steel |  0 |  4 |  2 |  8 |  1 |  3 |  8.75 |  2 |
| 5 |  Poison-Steel |  1 |  1 |  6 |  6 |  3 |  1 |  8.75 |  0 |
| 6 |  Normal-Steel |  1 |  2 |  3 |  10 |  0 |  2 |  8.5 |  0 |
| 7 |  Bug-Steel |  1 |  0 |  8 |  7 |  1 |  1 |  8.25 |  7 |
| 8 |  Dragon-Steel |  0 |  2 |  6 |  8 |  1 |  1 |  8.25 |  1 |
| 9 |  Fire-Steel |  1 |  2 |  5 |  4 |  5 |  1 |  8.25 |  1 |
| 10 |  Steel-Water |  0 |  3 |  4 |  8 |  2 |  1 |  8.25 |  1 |
| 11 |  Grass-Steel |  1 |  1 |  6 |  8 |  1 |  1 |  7.75 |  1 |
| 12 |  Dark-Steel |  1 |  2 |  4 |  9 |  0 |  2 |  7.5 |  1 |
| 13 |  Steel |  0 |  3 |  4 |  10 |  0 |  1 |  7.25 |  4 |
| 14 |  Fighting-Steel |  0 |  3 |  6 |  6 |  2 |  1 |  6.25 |  3 |
| 15 |  Ground-Steel |  0 |  4 |  4 |  7 |  1 |  2 |  6. |  2 |
| 16 |  Ghost-Normal |  0 |  1 |  12 |  2 |  0 |  3 |  5.75 |  0 |
| 17 |  Psychic-Steel |  0 |  4 |  4 |  8 |  1 |  1 |  5.25 |  4 |
| 18 |  Rock-Steel |  2 |  1 |  6 |  6 |  2 |  1 |  5.25 |  3 |
| 19 |  Dark-Ghost |  0 |  1 |  13 |  1 |  0 |  3 |  4.75 |  3 |
| 20 |  Ice-Steel |  2 |  1 |  6 |  7 |  1 |  1 |  4.75 |  0 |
| 21 |  Electric-Poison |  1 |  1 |  8 |  8 |  0 |  0 |  4.5 |  0 |
| 22 |  Dark-Poison |  0 |  1 |  12 |  4 |  0 |  1 |  4.25 |  2 |
| 23 |  Electric-Ghost |  0 |  3 |  8 |  5 |  0 |  2 |  4. |  1 |
| 24 |  Electric-Fairy |  0 |  2 |  10 |  5 |  0 |  1 |  3.75 |  1 |
| 25 |  Electric-Flying |  0 |  2 |  10 |  5 |  0 |  1 |  3.75 |  5 |
| 26 |  Fairy-Ghost |  0 |  2 |  12 |  0 |  1 |  3 |  3.75 |  0 |
| 27 |  Fire-Flying |  1 |  2 |  8 |  4 |  2 |  1 |  3.75 |  5 |
| 28 |  Ground-Water |  1 |  0 |  12 |  4 |  0 |  1 |  3.75 |  6 |
| 29 |  Electric-Fire |  1 |  2 |  7 |  7 |  1 |  0 |  3.5 |  1 |
| 30 |  Fire-Ghost |  0 |  5 |  4 |  6 |  1 |  2 |  3.5 |  1 |
| 31 |  Ghost-Water |  0 |  4 |  6 |  6 |  0 |  2 |  3.5 |  1 |
| 32 |  Poison-Water |  0 |  3 |  7 |  8 |  0 |  0 |  3.5 |  2 |
| 33 |  Fairy-Fire |  0 |  4 |  6 |  6 |  1 |  1 |  3.25 |  0 |
| 34 |  Fairy-Poison |  0 |  3 |  9 |  3 |  2 |  1 |  3.25 |  0 |
| 35 |  Fairy-Water |  0 |  3 |  8 |  6 |  0 |  1 |  3.25 |  1 |
| 36 |  Fire-Ground |  1 |  1 |  10 |  5 |  0 |  1 |  3.25 |  3 |
| 37 |  Flying-Water |  1 |  1 |  10 |  5 |  0 |  1 |  3.25 |  4 |
| 38 |  Fire-Poison |  1 |  3 |  6 |  5 |  3 |  0 |  3. |  0 |
| 39 |  Flying-Ground |  1 |  1 |  11 |  3 |  0 |  2 |  3. |  3 |
| 40 |  Dragon-Fairy |  0 |  4 |  6 |  7 |  0 |  1 |  2.75 |  1 |
| 41 |  Normal-Poison |  0 |  2 |  11 |  4 |  0 |  1 |  2.75 |  0 |
| 42 |  Electric-Water |  0 |  2 |  11 |  4 |  1 |  0 |  2.5 |  2 |
| 43 |  Fairy-Normal |  0 |  2 |  12 |  2 |  0 |  2 |  2.5 |  2 |
| 44 |  Ghost-Poison |  0 |  4 |  8 |  2 |  2 |  2 |  2.5 |  2 |
| 45 |  Ghost |  0 |  2 |  12 |  2 |  0 |  2 |  2.5 |  5 |
| 46 |  Flying-Poison |  0 |  4 |  8 |  2 |  3 |  1 |  2.25 |  1 |
| 47 |  Dragon-Ghost |  0 |  5 |  5 |  6 |  0 |  2 |  2. |  2 |
| 48 |  Fire-Water |  0 |  3 |  10 |  2 |  3 |  0 |  2. |  0 |
| 49 |  Poison |  0 |  2 |  11 |  5 |  0 |  0 |  2. |  6 |
| 50 |  Dark-Fire |  0 |  4 |  7 |  6 |  0 |  1 |  1.75 |  2 |
| 51 |  Electric-Normal |  0 |  2 |  12 |  3 |  0 |  1 |  1.75 |  1 |
| 52 |  Fire-Normal |  0 |  4 |  7 |  6 |  0 |  1 |  1.75 |  1 |
| 53 |  Fairy |  0 |  2 |  12 |  3 |  0 |  1 |  1.75 |  7 |
| 54 |  Bug-Fire |  1 |  2 |  9 |  5 |  1 |  0 |  1.5 |  1 |
| 55 |  Dark-Fairy |  0 |  3 |  11 |  1 |  1 |  2 |  1.5 |  0 |
| 56 |  Dragon-Fire |  0 |  3 |  10 |  3 |  2 |  0 |  1.5 |  2 |
| 57 |  Dragon-Poison |  0 |  4 |  7 |  6 |  1 |  0 |  1.5 |  1 |
| 58 |  Fairy-Ground |  0 |  4 |  8 |  4 |  0 |  2 |  1.5 |  0 |
| 59 |  Fighting-Poison |  1 |  2 |  9 |  5 |  1 |  0 |  1.5 |  1 |
| 60 |  Ghost-Psychic |  2 |  0 |  12 |  2 |  0 |  2 |  1.5 |  0 |
| 61 |  Electric |  0 |  1 |  14 |  3 |  0 |  0 |  1.5 |  14 |
| 62 |  Fire |  0 |  3 |  9 |  6 |  0 |  0 |  1.5 |  11 |
| 63 |  Flying-Ghost |  0 |  5 |  7 |  2 |  1 |  3 |  1.25 |  1 |
| 64 |  Ghost-Ground |  0 |  5 |  7 |  2 |  1 |  3 |  1.25 |  1 |
| 65 |  Ground-Poison |  0 |  4 |  8 |  4 |  1 |  1 |  1.25 |  2 |
| 66 |  Normal-Water |  0 |  3 |  10 |  4 |  0 |  1 |  1.25 |  1 |
| 67 |  Bug-Electric |  0 |  2 |  12 |  4 |  0 |  0 |  1. |  1 |
| 68 |  Dragon-Water |  0 |  2 |  13 |  1 |  2 |  0 |  1. |  2 |
| 69 |  Fairy-Flying |  0 |  5 |  7 |  2 |  2 |  2 |  1. |  1 |
| 70 |  Fighting-Ghost |  0 |  4 |  9 |  2 |  1 |  2 |  1. |  0 |
| 71 |  Flying-Normal |  0 |  3 |  11 |  2 |  0 |  2 |  1. |  11 |
| 72 |  Water |  0 |  2 |  12 |  4 |  0 |  0 |  1. |  26 |
| 73 |  Dark-Electric |  0 |  4 |  8 |  5 |  0 |  1 |  0.75 |  0 |
| 74 |  Dragon-Flying |  1 |  3 |  8 |  4 |  1 |  1 |  0.75 |  7 |
| 75 |  Bug-Water |  0 |  3 |  10 |  5 |  0 |  0 |  0.5 |  0 |
| 76 |  Dark-Flying |  0 |  4 |  9 |  3 |  0 |  2 |  0.5 |  3 |
| 77 |  Dragon-Electric |  0 |  4 |  8 |  5 |  1 |  0 |  0.5 |  2 |
| 78 |  Electric-Fighting |  0 |  3 |  10 |  5 |  0 |  0 |  0.5 |  0 |
| 79 |  Fighting-Fire |  0 |  4 |  8 |  5 |  1 |  0 |  0.5 |  4 |
| 80 |  Fire-Rock |  2 |  2 |  7 |  6 |  1 |  0 |  0.5 |  1 |
| 81 |  Dark-Fighting |  1 |  2 |  11 |  2 |  1 |  1 |  0.25 |  2 |
| 82 |  Dark-Water |  0 |  5 |  6 |  6 |  0 |  1 |  0.25 |  5 |
| 83 |  Fairy-Rock |  1 |  3 |  8 |  5 |  0 |  1 |  0.25 |  3 |
| 84 |  Flying |  0 |  3 |  11 |  3 |  0 |  1 |  0.25 |  2 |
| 85 |  Normal |  0 |  1 |  16 |  0 |  0 |  1 |  0.25 |  35 |
| 86 |  Bug-Ghost |  0 |  5 |  7 |  4 |  0 |  2 |  0. |  1 |
| 87 |  Bug-Poison |  0 |  4 |  9 |  3 |  2 |  0 |  0. |  5 |
| 88 |  Fire-Grass |  0 |  3 |  11 |  3 |  1 |  0 |  0. |  0 |
| 89 |  Ghost-Grass |  0 |  5 |  7 |  4 |  0 |  2 |  0. |  5 |
| 90 |  Poison-Psychic |  0 |  3 |  11 |  3 |  1 |  0 |  0. |  0 |
| 91 |  Poison-Rock |  1 |  3 |  8 |  5 |  1 |  0 |  0. |  0 |
| 92 |  Bug-Fairy |  0 |  5 |  7 |  4 |  1 |  1 |  -0.25 |  0 |
| 93 |  Dark-Dragon |  1 |  4 |  6 |  6 |  0 |  1 |  -0.25 |  1 |
| 94 |  Dragon-Ground |  1 |  2 |  11 |  3 |  0 |  1 |  -0.25 |  4 |
| 95 |  Dragon-Normal |  0 |  4 |  9 |  4 |  0 |  1 |  -0.25 |  0 |
| 96 |  Electric-Ground |  0 |  4 |  9 |  4 |  0 |  1 |  -0.25 |  1 |
| 97 |  Fairy-Grass |  1 |  4 |  6 |  6 |  0 |  1 |  -0.25 |  1 |
| 98 |  Fairy-Psychic |  0 |  3 |  12 |  1 |  1 |  1 |  -0.25 |  3 |
| 99 |  Normal-Psychic |  0 |  2 |  14 |  1 |  0 |  1 |  -0.25 |  1 |
| 100 |  Dark-Normal |  1 |  2 |  12 |  1 |  0 |  2 |  -0.5 |  0 |
| 101 |  Fighting-Water |  0 |  5 |  6 |  7 |  0 |  0 |  -0.5 |  2 |
| 102 |  Fire-Psychic |  0 |  5 |  6 |  7 |  0 |  0 |  -0.5 |  3 |
| 103 |  Grass-Poison |  0 |  4 |  9 |  4 |  1 |  0 |  -0.5 |  6 |
| 104 |  Ground-Normal |  0 |  4 |  10 |  2 |  0 |  2 |  -0.5 |  1 |
| 105 |  Dragon |  0 |  3 |  11 |  4 |  0 |  0 |  -0.5 |  3 |
| 106 |  Bug-Normal |  0 |  3 |  12 |  2 |  0 |  1 |  -0.75 |  0 |
| 107 |  Fairy-Fighting |  0 |  5 |  8 |  2 |  2 |  1 |  -0.75 |  0 |
| 108 |  Flying-Rock |  0 |  5 |  7 |  5 |  0 |  1 |  -0.75 |  3 |
| 109 |  Dark |  0 |  3 |  12 |  2 |  0 |  1 |  -0.75 |  7 |
| 110 |  Ground |  0 |  3 |  12 |  2 |  0 |  1 |  -0.75 |  6 |
| 111 |  Dragon-Fighting |  1 |  4 |  6 |  7 |  0 |  0 |  -1. |  0 |
| 112 |  Electric-Psychic |  0 |  4 |  9 |  5 |  0 |  0 |  -1. |  0 |
| 113 |  Electric-Rock |  1 |  3 |  9 |  4 |  1 |  0 |  -1. |  0 |
| 114 |  Ghost-Ice |  0 |  5 |  8 |  3 |  0 |  2 |  -1. |  1 |
| 115 |  Ghost-Rock |  0 |  6 |  6 |  3 |  1 |  2 |  -1. |  0 |
| 116 |  Grass-Water |  0 |  3 |  12 |  2 |  1 |  0 |  -1. |  1 |
| 117 |  Rock-Water |  1 |  3 |  9 |  4 |  1 |  0 |  -1. |  6 |
| 118 |  Bug-Ground |  0 |  4 |  10 |  3 |  0 |  1 |  -1.25 |  1 |
| 119 |  Fighting-Flying |  0 |  5 |  8 |  3 |  1 |  1 |  -1.25 |  1 |
| 120 |  Fighting-Normal |  0 |  4 |  10 |  3 |  0 |  1 |  -1.25 |  1 |
| 121 |  Bug-Fighting |  1 |  3 |  9 |  5 |  0 |  0 |  -1.5 |  2 |
| 122 |  Dark-Ground |  0 |  6 |  6 |  4 |  0 |  2 |  -1.5 |  1 |
| 123 |  Electric-Grass |  0 |  4 |  10 |  3 |  1 |  0 |  -1.5 |  1 |
| 124 |  Psychic-Water |  0 |  5 |  7 |  6 |  0 |  0 |  -1.5 |  4 |
| 125 |  Bug |  0 |  3 |  12 |  3 |  0 |  0 |  -1.5 |  5 |
| 126 |  Fighting |  0 |  3 |  12 |  3 |  0 |  0 |  -1.5 |  10 |
| 127 |  Bug-Dark |  0 |  5 |  8 |  4 |  0 |  1 |  -1.75 |  0 |
| 128 |  Dark-Psychic |  1 |  1 |  15 |  0 |  0 |  1 |  -1.75 |  1 |
| 129 |  Fairy-Ice |  1 |  3 |  10 |  3 |  0 |  1 |  -1.75 |  0 |
| 130 |  Bug-Dragon |  0 |  5 |  8 |  4 |  1 |  0 |  -2. |  0 |
| 131 |  Fire-Ice |  1 |  3 |  10 |  3 |  1 |  0 |  -2. |  0 |
| 132 |  Bug-Flying |  1 |  4 |  9 |  1 |  2 |  1 |  -2.25 |  11 |
| 133 |  Flying-Psychic |  0 |  5 |  9 |  2 |  1 |  1 |  -2.25 |  4 |
| 134 |  Normal-Rock |  1 |  4 |  8 |  4 |  0 |  1 |  -2.25 |  0 |
| 135 |  Bug-Rock |  0 |  3 |  13 |  2 |  0 |  0 |  -2.5 |  3 |
| 136 |  Fighting-Psychic |  0 |  3 |  13 |  2 |  0 |  0 |  -2.5 |  6 |
| 137 |  Ice-Poison |  0 |  5 |  8 |  5 |  0 |  0 |  -2.5 |  0 |
| 138 |  Psychic |  0 |  3 |  13 |  2 |  0 |  0 |  -2.5 |  25 |
| 139 |  Fighting-Ground |  0 |  6 |  7 |  3 |  1 |  1 |  -2.75 |  0 |
| 140 |  Flying-Grass |  1 |  4 |  9 |  2 |  1 |  1 |  -2.75 |  3 |
| 141 |  Flying-Ice |  1 |  3 |  11 |  2 |  0 |  1 |  -2.75 |  2 |
| 142 |  Grass-Ground |  1 |  3 |  11 |  2 |  0 |  1 |  -2.75 |  1 |
| 143 |  Ground-Rock |  2 |  4 |  6 |  4 |  1 |  1 |  -2.75 |  2 |
| 144 |  Dragon-Psychic |  0 |  6 |  6 |  6 |  0 |  0 |  -3. |  4 |
| 145 |  Electric-Ice |  0 |  4 |  11 |  3 |  0 |  0 |  -3. |  1 |
| 146 |  Dark-Grass |  1 |  6 |  4 |  6 |  0 |  1 |  -3.25 |  2 |
| 147 |  Dark-Rock |  1 |  6 |  4 |  6 |  0 |  1 |  -3.25 |  2 |
| 148 |  Grass-Normal |  0 |  6 |  7 |  4 |  0 |  1 |  -3.25 |  1 |
| 149 |  Ground-Psychic |  0 |  6 |  7 |  4 |  0 |  1 |  -3.25 |  1 |
| 150 |  Dragon-Rock |  0 |  6 |  7 |  4 |  1 |  0 |  -3.5 |  1 |
| 151 |  Fighting-Grass |  1 |  5 |  6 |  6 |  0 |  0 |  -3.5 |  3 |
| 152 |  Ice-Water |  0 |  4 |  12 |  1 |  1 |  0 |  -3.5 |  4 |
| 153 |  Grass |  0 |  5 |  9 |  4 |  0 |  0 |  -3.5 |  14 |
| 154 |  Rock |  0 |  5 |  9 |  4 |  0 |  0 |  -3.5 |  4 |
| 155 |  Ice-Normal |  1 |  3 |  12 |  1 |  0 |  1 |  -3.75 |  0 |
| 156 |  Bug-Grass |  2 |  4 |  7 |  3 |  2 |  0 |  -4. |  3 |
| 157 |  Bug-Ice |  2 |  2 |  11 |  3 |  0 |  0 |  -4. |  0 |
| 158 |  Dragon-Grass |  1 |  5 |  8 |  1 |  3 |  0 |  -4. |  1 |
| 159 |  Grass-Rock |  0 |  4 |  12 |  2 |  0 |  0 |  -4. |  1 |
| 160 |  Bug-Psychic |  0 |  6 |  8 |  3 |  1 |  0 |  -4.5 |  0 |
| 161 |  Dragon-Ice |  0 |  5 |  10 |  3 |  0 |  0 |  -4.5 |  3 |
| 162 |  Fighting-Rock |  0 |  7 |  5 |  6 |  0 |  0 |  -4.5 |  1 |
| 163 |  Dark-Ice |  1 |  5 |  8 |  3 |  0 |  1 |  -4.75 |  1 |
| 164 |  Ground-Ice |  0 |  5 |  11 |  1 |  0 |  1 |  -4.75 |  1 |
| 165 |  Grass-Psychic |  1 |  6 |  5 |  6 |  0 |  0 |  -5. |  2 |
| 166 |  Ice |  0 |  4 |  13 |  1 |  0 |  0 |  -5. |  8 |
| 167 |  Psychic-Rock |  0 |  7 |  6 |  5 |  0 |  0 |  -5.5 |  2 |
| 168 |  Fighting-Ice |  0 |  6 |  9 |  3 |  0 |  0 |  -6. |  0 |
| 169 |  Ice-Rock |  2 |  4 |  8 |  4 |  0 |  0 |  -6. |  1 |
| 170 |  Grass-Ice |  1 |  6 |  7 |  4 |  0 |  0 |  -7. |  2 |
| 171 |  Ice-Psychic |  0 |  6 |  10 |  2 |  0 |  0 |  -7. |  1 |



This is a lot of information - a better way to think about it is to look at 
the scores plotted. First, note - the mean is 0.51, median is zero, max is 10, min is -7,
and STDEV is 3.53728. 

![rank by score](/img/pokemon_rankbyscore.png)

Here we can see that the first 20ish types are all steel combos which have insane
defensive potential. Then there's a nice sort of gradual linear curve down to the 
150th rank, after which things get bad very quickly. 

That's for the ranking - we have 171 types and we can also ask how they look 
similarity wise. First, here's an MST of what's going on:

![2 type minimum spanning tree](/img/pokemon_mst_doubletype.png)

It looks like for the most part that a majority of the types form clusters around
themselves, and there are a select very few types that exist in between those
spaces. It makes a lot of sense then, that when we do an agglomeration and split
it into 30 clusters, we get something like this:

![2 type clusters](/img/pokemon_groups_doubletype.png)

Fairy/Steel/Grass/Dragon are pretty stuborn types and they really cluster well. 
The others are more mixed. 

That's all for now - the next thing to try is categorizing pokemon by their base
stats!






































