--- 
title: "Exploring Colors"
author: "Vishesh"
tags: ["colors"]
summary: "Talking about some of the steps in analyzing and grouping the colors - what are the natural groups here? How do we best discover them? Ends with a discussion of next steps - better color distance metrics!"
created: "2015-02-13 10:08:08"
--- 

## Exploring Colors {.post-title}

Now that we know the data and we know the space, the first thing to do is figure out what the dataset looks like. 

The first interesting plot here is a chromaticity plot (basically, where are the colors that were discussed in terms of the color space?)

![plot of colors in cielab space](/img/cielabspaceplot.png) 

Here we can see that there is a fan structure - each original color group makes a "blade" on this graph. That's pretty interesting - it seems like each group is defined mostly by the fact that all its colors lie on the same approximate hue angle. The neutral colors get kinda squashed this way though and you can't really see what they look like. There are some 3D plots I made to look at that, but it's pretty much impossible to get a good shot of it (most of the intuition is found by dragging the thing around). Of course, you can't get a good feel for lightness here since the tans and whites are buried under the blacks and so on. 

As expected, there's a lot more space in the blue/green areas of the plot (remember, this is CIELAB, so the blue region should be much bigger than it is right now, and right now it has even density with the rest of the plot). 

The second interesting thing to note is that we can already see that our eyes want very different groups of these colors than the imposed blue, green, etc. Specifically, the colors at the way edges (large saturation) and very bright (large lightness) both form their own groups, and the center clump (the neutral hue 
line) needs to be divided separately in its own right.

### Minimum Spanning Tree

One quick way to layout a 2+D space that you can't 'see' on a screen well is to take a minimum spanning tree of the points in the space and then display the weighted edges. There is (or will be) more about how I do some of these things in the techniques section. Here is the minimum tree for the colors space (annotated with some of the interesting groups):

![graph of colors arranged by minimum distance](/img/mstcolors.svg)

So from what we see here, there are quite a few more groups than the original layout might suggest - for example blue becomes blue/deep blue/cyan and orange has two main arms - red orange and regular orange, yellow has bright/golden etc etc. Peach is separated from orange/pink, and purple is really three different groups. There are some "mistakes" here that 
are a product of the fact that we have a discrete number of colors and therefore sometimes
the shortest distance in this space isn't what we expect. To the left of cyan, there are a 
couple of green colors next to a gray one that should probably go along with the rest of the
greens, but in CIELUV space they don't. I also tried making this network in CIELAB but it did a really poor job with all the blues. 

In order to group based on the graph, take the distance matrix and group based on some threshold value between the minimum distance and the maximum distance in the spanning tree. Alternatively you can take communities out of a graph of some arbitrary threshold. 

Honestly though, not that promising. For one, it's hard to have proper resolution - there's a clump and some fringes, but we want "even" sized groups and because there is always a tight community, we simply get a large connected component growing and some fringe nodes. So that's not going to work. 

It's a similar problem even within the bounds set by the MST because the dendrogram is really skewed, so you get an ever increasing connected component, rather than many connected components growing. 


The main problem with the graph-community approach is that it's not computing the distances between groups - it's doing distances between individual nodes and linking together based on the shortest distance to ANY node in the group. In hierarchical clustering, this is the same as single linkage clustering, which leads to really skewed and not very useful dendrograms. 

Below are some plots for the various dendrograms:

<div class="pure-g" style="margin: 0 auto; max-width: 80%;">
<div class="pure-u-1-2 pure-u-md-1-3"><a href="/img/colordendrogram_single.png"><img class="pure-img pure-img-reponsive" src="/img/colordendrogram_single.png"/></a><h5>Single Linkage Clustering</h5></div>
<div class="pure-u-1-2 pure-u-md-1-3"><a href="/img/colordendrogram_complete.png"><img class="pure-img pure-img-responsive" src="/img/colordendrogram_complete.png"/></a><h5>Complete Linkage Clustering</h5></div>
<div class="pure-u-1-2 pure-u-md-1-3"><a href="/img/colordendrogram_ward.png"><img class="pure-img pure-img-responsive" src="/img/colordendrogram_ward.png"/></a><h5>Ward Linkage Clustering</h5></div>
</div>



The single linkage clustering (or the graph threshold thing) are both really skewed - there's honestly only one connected component here (except at a very small level, much smaller than we'd like there to be) and splitting these clusters won't make even groups. The complete linkage is better in that we have some clearly defined groups, but it's still not really balanced to the level that the ward dendrogram is. That's why I generally choose ward's method when I'm trying to group things. 

Now that that's settled, let's take a look at the results of a proper hierarchical clustering algorithm used on the color spaces. 


![hierarchical color groups](/img/colorgroups.png)

So here are the final groups, arranged by size. Of coure, we're still seeing a couple large groups (tan/yellow and white/light grey) but the beauty of hierarchical clustering is that we can now selectively split the larger groups down into smaller ones. Consider a pair of functions like: 

```mathematica
CondClusterSplit[clist_, cond_] := 
  Map[If[And[Head@# === Cluster, cond@#], ClusterSplit[#, 2], {#}] &, clist];
SizeSplit[size_] := Flatten[#, 1] &@CondClusterSplit[#, #[[4]] + #[[5]] > size &] &
```

The first one takes a list of clusters and only splits the ones that match cond into two separate groups. SizeSplit is a partial implementation of that function for the condition of having the clusters be larger than some size. so `SizeSplit[10]` returns a function that when applied to a list of clusters, will split the ones that are larger than some size. 

With this in place, we can now split the larger clusters. I did `SizeSplit[10]@SizeSplit[15]&` (so two splits) since I wanted groups of less than 10 (of which there are around 20).

And we get 

![selectively split hierarchical cluster groups](/img/colorgroups_split.png)

IMHO this looks really good and matches our intuition of the groups from the original MST of the space. We've split up the space into approx even size groups and each of the groups seems to have similarly colored elements.

We can do two steps better here. The first is to take CIELUV space instead of CIELAB space, which has much better hue matching. 

![luv space clustring ](/img/colorgroups_luv.png)

Note the improvements here in the yellow region - the group that had both bright yellow and bright green is gone, and the major pink/purple group in CIELAB space has been split into bluer purples and redder pinks properly.

The second major improvement is to actually do some more [reading](http://en.wikipedia.org/wiki/Color_difference) and learn that both CIELAB and CIELUV have issues in the way the represent the blue spectrum of color, and that really there are some pretty crazy functions the CIE defined to describe the perceptual distance between two colors (by rotating into CIELCh space first and then doing some transformations to correct for perceptual disuniformity).

See the (upcoming) post on color difference to see the improvements made by updating the color distance function. For now, going to call it a day with the CIELUV groupings.  



















