---
title: "Adding Blue Colors"
author: "Vishesh"
tags: ["colors"]
summary: "Here I attempt to find some more blue and green colors to add to the previous set of colors we already have to correct the warm color imbalance."
created: "2015-03-05 11:55:41"
---

## Adding Blue Colors {.post-title}


Another interesting aspect of this data set is that there are far less cool colors (below the $y = -x$ line) than there are warm colors. Looking at a picture of the MST makes this very clear (see post on "[Exploring the Color Space](#/post/colors/exploring-colors)"). This is compounded by the fact that the human eye is 2x as sensitive to medium wavelengths as compared to long/short wavelengths. You would think that merits having a few more greens.

Of course, me picking random blues and greens to add because I think they look nice would be a disaster. So where should these colors come from? I figured that the best solution would be to take all the warm colors and flip them to their cool counterparts. Another option would be to take all the warm colors and compute the LCH definitions, but flip the hue and not the chroma/lightness. For now, I stuck with the simpler defintion of just completely flipping the colors around.

I did the same grouping scheme as on the other colors with the new blue/greens, and here are the colors grouped:

![blue groups](/img/colorgroups_blue.png)

Seems like a great selection. Of course, we don't just want to add all of thse - some are very similar and it would be a waste to have both colors. So how do we resolve this issue? Well, the best way to do it would be to simply pick the "most different" color in this new set and add it to the original set of colors, then do that process on repeat, recalculating what the farthest is each time we add a new color.

So initially, we compute the distance between all the new colors and all the old colors, then take the min each step, giving us a list (sorted) like this (this is only a portion of the list):

![next colors](/img/nextcolorlist.png)

Then add the head of that list to the new set, and compute the distance ($O(N)$ time) on all the rest of the elements and replace the values if necessary, then resort the list ($O(NlgN)$ time). If you want to add k new colors this is an $O(kN\log N)$ time operation. Probably not the most efficient thing in the world, but hey, it's only 200 elements so who cares (I guess you can compute the max while you update everything, so really it's $O(kN)$).

If you do that and take the first 30 colors that get added to the set, they are:

![new colors](/img/newcolors.png)

Of course, to really be part of the color set they need names. Any ideas?

Here's the mathematica code for computing the initial set (`MinDistElem`) and computing the next colors to add (`NMostDifferent`):

```mathematica
MinDistElems[d_, l1_, l2_] :=
 Transpose[{d /@ l1}~Join~
    Transpose[{d@Part[l2, Ordering[#1, 1][[1]] ], Min@#1} & /@
      Outer[, l1, l2, 1]]] // SortBy[#, -#[[3]] &] &

NMostDifferent[d_, initial_, n_] :=
 Module[{i, next = {}, closest = initial},
  For[i = 1, i <= n, i++,
   AppendTo[ next, {"",  "#" <> StringJoin[IntegerString[#, 16, 2] &@Round@# & /@
                     (255*ColorConvert[#[[1, 1]], "LAB" -> "RGB"])]} &@closest];
   closest =
    Map[With[{distance = d @@ {#[[1]], closest[[1, 1]]}},
        If[distance < #[[3]], {#[[1]], closest[[1, 1]], distance}, #]] &,
        closest[[2 ;;]]] // SortBy[#, -#[[3]] &] &;
   ];
  Return[{next, closest}];
 ]
```






