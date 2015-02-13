---
title: "Majors Datasetup"
author: "Vishesh Gupta"
tags: ["majors"]
summary: "From Stanford's registrar's office - my first mini data project. The main interesting things here would be to look at the changes in major selection over time, and the differences in majors by gender. I'm mostly interested in the undergrad majors."
created: "2015 January 21 16:35:29"
---

## Majors Datasetup {.post-title}

### Where to find the data

All the data I'm using can be publicly found at Stanford's registrar [website][registrar]. When you click to previous years, don't be fooled because there isn't a link back - the registrar's office apparently doesn't update the older pages with the newer years links to data.... how strange.

There's actually a (what is probably much better) data set [here][cds] (Stanford's common data set), but it doesn't have a split by gender. There are also other pages with other kinds of data, and you can see the trends, but only the registrar seems to have the gender based data available, which the portion of this project I had the most fun messing with.

Politically Correct Disclaimer:

Not because I think there's an ideal way the majors should be distributed, but it's fun to analyze how it has been distributed/changed since 2004 (which is the earliest data you can get on this page.)

### Scraping

Scraping is a pain in the butt on this one - the data is divided by school, so for each school I pasted the html source into a [html table to csv convertor](http://www.convertcsv.com/html-table-to-csv.htm) and then formatted it into a mathematica table, then spliced all the rows together. This process took me half an hour to get right the first time, so I have a lot of pain left to look forward to if I'm going to really scrape all the years' data here.

As of this post, I did only the 2013-2014 year.




[cds]: http://ucomm.stanford.edu/cds/2014
[registrar]: https://studentaffairs.stanford.edu/registrar/everyone/enrollment-stats_13-14
