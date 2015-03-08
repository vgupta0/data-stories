---
title: "Color Distance 2000"
author: "Vishesh"
tags: ["colors"]
summary: "Where I use a better distance function to correct for issues in the blue region of the data set, and seeing the differences."
created: "2015-03-05 10:45:56"
---

## Color Distance 2000 {.post-title}

The newest and best color distance metric is called CIEDE2000 and it's supposed to resolve a lot of perceptual uniformity issues, especially around the blue region. This is very helpful for trying to add new blue colors, for example, if we want to add the N most different colors to the set of colors we already have, we should use this new distance metric.

The actual definitions of the distance function are given in this [paper](http://www.ece.rochester.edu/~gsharma/ciede2000/ciede2000noteCRNA.pdf) by the CIE.

Let's look at the differences between the 2000 and 1994 definitions of color distance. First, the MST:

![ciede94 mst](/img/mstcolors.svg)

![ciede2000 mst](/img/mstcolors00.svg)

I haven't annotated the new mst, mostly because the color groups are largely the same, just in different places. Some differences - the red group is now attatched to pink/peach, all of which are closer to the yellow/orange region of the chart than they are the blue region of the chart. Violet is now next to pink and blue/cyan groups are combined. The deep blue group is now attatched between black group and purple/magenta groups. The black group seems to have been split into a warm black (next to the red sections) and a cool grey/black region near the blues. The mistakes with the grey-greens are corrected now and the greens are properly split and progress from yellow downwards rather than being two arms off of the one tan node.

Overall everything looks much smoother perceptually - the old one was nice in the sense that you could define the groups very precisely but this is a much better MST in that you can see the actual progression of the colors.


There are also differences in the groups that were made in the [Exploring Colors](#/post/colors/exploring-colors) post. Here is a direct comparison

![luv ciede94 space clustring](/img/colorgroups_luv94.png)

![ciede2000 space clustering](/img/colorgroups_2000.png)

These groups are computed slightly differently. I have 4 size based splits instead of 3 to better deal with the tighter grouping in ciede2000 space. Again, I'd say that the new color space is an improvement. The LUV space 94 distance clustering has issues with the color green and groups the blues together very wierdly (aqua/sapphire same as ocean green? I don't think so) The 2000 colorspace accurately resolves a lot of those discrepancies. I guess there's a reason they made this new distance function after all!


A fun question to ask is whether or not cidede2000 (which is a complex space involving all sorts of trig functions and rotation operations) is defineable in any kind of reduced space.
Using the method described in this [post](http://math.stackexchange.com/questions/156161/finding-the-coordinates-of-points-from-distance-matrix/423898#423898) I computed the matrix M and took the rank, which turned out to be 37. Sad face - all those trig functions induce nonlinearity, I guess. Moreover, the matrix is not positive semidefinite, so there's no way to embed these points. Oh well.

Still, we can see that the 2000 definition of color distance is an improvement over the 1994 definition, and let's see how it applies when we add more blues!



















