---
title: "Color Spaces"
author: "Vishesh Gupta"
tags: ["colors", "metrics"]
summary: "Colors can be understood in many different forms and various spaces have been invented over the years to show the 'natural perception' of colors by the human eye. Here is a discussion of various color spaces and why I chose CIELAB as the main space to work in (and CIELUV for aggregation, but we'll get to that later)."
created: "2015-02-12 07:24:00"
---

## Color Spaces {.post-title}

Color and it's definitions have a really interesting history - color opponent theory is the current prevailing scheme for how the brain interprets colors. There are innumerable color spaces, such as RGB (hex codes), HSB (hue saturation brightness), HSL (hue saturation lightness), HSV (hue saturation value) (and yes these three are different), CMYK (subtractive for print purposes), etc, etc.

However, when it comes to analyzing color, we have to use the "real" space defined by the CIE called CIELAB space (there's also CIEL\*A\*b\* CIELUV and CIELCh space, so there's really no consensus on any 1 space here either). LAB is Lightness, A which is supposed to model the magenta/red-green scale, and B, which models the presence or absence of short wavelengths (blue-yellow scale).

The left picture here has the space of CIELAB shown. Of course, we can't actually see all those colors on a computer because sRGB's small gamut only covers part of our eye's total response ability (so a picture really isn't the real thing). The pictures here are therefore an interpretation.

<div class="pure-g" style="margin: 0 auto; max-width: 80%;">
<div class="pure-u-1-2 pure-u-md-1-2"><img class="pure-img pure-img-reponsive"
    src="http://upload.wikimedia.org/wikipedia/commons/b/ba/PlanckianLocus.png"/> </div>
<div class="pure-u-1-2 pure-u-md-1-2"><img class="pure-img pure-img-responsive"
      src="http://upload.wikimedia.org/wikipedia/commons/8/83/CIE_1976_UCS.png"/></div>
</div>

The picture on the right shows off a second color space, which is CIELUV. This space was invented to actually correct the perceptual problems in the CIELAB space (you can see that the blue region is expanded to make the chromaticity perception even across the space - indeed CIELUV is called the 'uniform chromaticity' space).

The eye's real response function is quite uneven and therefore hard to model with these sorts of diagrams. There's yet another set of spaces called CIELCh (for CIELAB) and CIELCHuv for CIELUV that represent the same spaces mapped to cylindrical coordinates (which some corrections, of course), which will become relevant in my discussions on color distance (how to tell how far apart two colors are).

The space itself is pretty good measure of color distance - you can take the [l a b] or [l u v] vector and do a regular euclidean norm and that's supposed to have even perception.



[githubcolors]: http://dudleystorey.github.io/thenewdefaults/


















