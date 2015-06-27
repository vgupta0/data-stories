--- 
title: "Tech Coefficients"
author: "Vishesh"
tags: ["majors"]
summary: "Discussing the rather arbitrary measure I created for evaluating the techiness of the major. There's a better discussion in Fuzzie Techie Divide"
created: "2015-05-12 17:37:49"
--- 

## Tech Coefficients {.post-title}

First, you should read [Fuzzy Techie Divide](/#/post/majors/fuzzy-techie-divide) for a look at what a *real* metric for the techniess of a major would look like. It's really, really intensive, and I'm sure the hours of work spent on some assignment will have lots of strange distributions, and in the end you still have to rely on arbitrary constants for how techie particular kinds of assignments or work is. These arbitrary constants would need to be hopefully a little less arbitrary by applying some framework to them, but in the end, it's just slightly better guesswork. 

So, I thought I'd take a stab at giving each major for Stanford undergrads a techiness score, by evaluating the **presence or absence** of techiness in a major (0-1 scale for each of STEM). This means that there are a lot of majors with a score of 0. I'm not doing an analysis of the polarization of stanford majors, which would require having an opposite metric for the humanities (the 4 axes of a humanities major, anyone?) mostly because I don't really know how to come up with a metric for the humanities. 

Here is a table of the actual scores, sorted by norm. This way you can scan it and ask "is X major more/less techy than Y major?". 

| Major                                     | S   | T   | E   | M   | Norm     | 
|-------------------------------------------|-----|-----|-----|-----|----------| 
| Electrical Engineering                    | 0.7 | 1   | 1   | 1   | 1.86815  | 
| Computer Science                          | 0.3 | 1   | 1   | 0.9 | 1.70294  | 
| Civil Engineering                         | 0.5 | 0.8 | 1   | 0.8 | 1.5906   | 
| Chemical Engineering                      | 0.7 | 0.8 | 1   | 0.6 | 1.57797  | 
| Engineering                               | 0.5 | 0.8 | 1   | 0.7 | 1.54272  | 
| Materials Science and Engineering         | 0.8 | 0.7 | 1   | 0.5 | 1.54272  | 
| Mathematical and Computational Science    | 0   | 0.8 | 0.7 | 1   | 1.45945  | 
| Physics                                   | 1   | 0.3 | 0.2 | 0.9 | 1.39284  | 
| Mechanical Engineering                    | 0.3 | 0.7 | 1   | 0.6 | 1.39284  | 
| Environmental Engineering                 | 0.7 | 0.4 | 1   | 0.3 | 1.31909  | 
| Environmental Systems Engineering         | 0.7 | 0.4 | 1   | 0.3 | 1.31909  | 
| Chemistry                                 | 1   | 0.4 | 0.2 | 0.6 | 1.249    | 
| Energy Resources Engineering              | 0.3 | 0.5 | 1   | 0.4 | 1.22474  | 
| Mathematics                               | 0   | 0.6 | 0.2 | 1   | 1.18322  | 
| Petroleum Engineering                     | 0.3 | 0.5 | 1   | 0.2 | 1.17473  | 
| Symbolic Systems                          | 0.3 | 0.7 | 0.6 | 0.6 | 1.14018  | 
| Industrial Engineering                    | 0.2 | 0.3 | 1   | 0.4 | 1.13578  | 
| Earth Systems                             | 0.8 | 0.4 | 0.5 | 0.3 | 1.06771  | 
| Biological Sciences                       | 1   | 0.3 | 0   | 0.2 | 1.06301  | 
| Biology                                   | 1   | 0.3 | 0   | 0.2 | 1.06301  | 
| Human Biology                             | 1   | 0.2 | 0   | 0.3 | 1.06301  | 
| Management Science and Engineering        | 0.3 | 0.6 | 0.4 | 0.7 | 1.04881  | 
| Statistics                                | 0   | 0.3 | 0.1 | 1   | 1.04881  | 
| Linguistics                               | 0.4 | 0.6 | 0.2 | 0.6 | 0.959166 | 
| Geophysics                                | 0.9 | 0   | 0   | 0.3 | 0.948683 | 
| Geological and Environmental Sciences     | 0.7 | 0.4 | 0.2 | 0.3 | 0.883176 | 
| Psychology                                | 0.8 | 0.2 | 0   | 0.1 | 0.830662 | 
| Economics                                 | 0.4 | 0.3 | 0.2 | 0.6 | 0.806226 | 
| Science and Technology and Society        | 0.4 | 0.4 | 0.2 | 0   | 0.6      | 
| Science, Technology, and Society          | 0.4 | 0.4 | 0.2 | 0   | 0.6      | 
| Science Technology and Society            | 0.4 | 0.4 | 0.2 | 0   | 0.6      | 
| Sociology                                 | 0.5 | 0.1 | 0   | 0.3 | 0.591608 | 
| Archaeology                               | 0.2 | 0.3 | 0.2 | 0   | 0.412311 | 
| Anthropological Sciences                  | 0.4 | 0.1 | 0   | 0   | 0.412311 | 
| Anthropology                              | 0.4 | 0   | 0   | 0   | 0.4      | 
| Music                                     | 0.3 | 0.2 | 0.1 | 0.1 | 0.387298 | 
| Urban Studies                             | 0.3 | 0   | 0   | 0.2 | 0.360555 | 
| Cultural and Social Anthropology          | 0.3 | 0   | 0   | 0   | 0.3      | 
| Communication                             | 0.1 | 0.2 | 0   | 0   | 0.223607 | 
| Comparative Studies in Race and Ethnicity | 0.2 | 0   | 0   | 0   | 0.2      | 
| International Relations                   | 0.2 | 0   | 0   | 0   | 0.2      | 
| Philosophy                                | 0   | 0   | 0   | 0.2 | 0.2      | 
| African and African American Studies      | 0.1 | 0   | 0   | 0   | 0.1      | 
| American Studies                          | 0.1 | 0   | 0   | 0   | 0.1      | 
| Art                                       | 0   | 0.1 | 0   | 0   | 0.1      | 
| Asian American Studies                    | 0.1 | 0   | 0   | 0   | 0.1      | 
| Chicana and Chicano Studies               | 0.1 | 0   | 0   | 0   | 0.1      | 
| Chicana/o Studies                         | 0.1 | 0   | 0   | 0   | 0.1      | 
| Drama                                     | 0.1 | 0   | 0   | 0   | 0.1      | 
| East Asian Studies                        | 0.1 | 0   | 0   | 0   | 0.1      | 
| History                                   | 0.1 | 0   | 0   | 0   | 0.1      | 
| Philosophy and Religious Studies          | 0   | 0   | 0   | 0.1 | 0.1      | 
| Art History                               | 0   | 0   | 0   | 0   | 0        | 
| Art Practice                              | 0   | 0   | 0   | 0   | 0        | 
| Chinese                                   | 0   | 0   | 0   | 0   | 0        | 
| Classics                                  | 0   | 0   | 0   | 0   | 0        | 
| Comparative Literature                    | 0   | 0   | 0   | 0   | 0        | 
| English                                   | 0   | 0   | 0   | 0   | 0        | 
| English and French Literature             | 0   | 0   | 0   | 0   | 0        | 
| English and German Literatures            | 0   | 0   | 0   | 0   | 0        | 
| English and Italian Literature            | 0   | 0   | 0   | 0   | 0        | 
| English and Spanish Literature            | 0   | 0   | 0   | 0   | 0        | 
| Feminist, Gender, and Sexuality Studies   | 0   | 0   | 0   | 0   | 0        | 
| Feminist Studies                          | 0   | 0   | 0   | 0   | 0        | 
| Film and Media Studies                    | 0   | 0   | 0   | 0   | 0        | 
| French                                    | 0   | 0   | 0   | 0   | 0        | 
| German Studies                            | 0   | 0   | 0   | 0   | 0        | 
| Humanities                                | 0   | 0   | 0   | 0   | 0        | 
| Iberian & Latin American Cultures         | 0   | 0   | 0   | 0   | 0        | 
| Individually Designed Major               | 0   | 0   | 0   | 0   | 0        | 
| International Policy Studies              | 0   | 0   | 0   | 0   | 0        | 
| Italian                                   | 0   | 0   | 0   | 0   | 0        | 
| Japanese                                  | 0   | 0   | 0   | 0   | 0        | 
| Native American Studies                   | 0   | 0   | 0   | 0   | 0        | 
| Political Science                         | 0   | 0   | 0   | 0   | 0        | 
| Public Policy                             | 0   | 0   | 0   | 0   | 0        | 
| Religious Studies                         | 0   | 0   | 0   | 0   | 0        | 
| Slavic Languages and Literatures          | 0   | 0   | 0   | 0   | 0        | 
| Spanish                                   | 0   | 0   | 0   | 0   | 0        | 


I'm sure there are some mistakes here, since I definitely don't know the ins and outs of more than a few of the majors at stanford. A lot of these are just my perceptions. Ok, numbers are boring to look at - let's see how good I did at creating a smooth gradient of techiness:

![tech scores plot](/img/majors_techscoresplot.png)

Eh, not bad. There are some definite plateaus here and of course the trailing end 
is close to zero but I think overall this isn't a bad distribution to run a first pass score calculation on. 

Before I get there though, let's just color a line plot of all the majors by how techie majors are - the red things are non-tech and the blue things are tech. 

![plot of major enrollment colored by tech coefficient score - left side axis is percentage of declared majors.](/img/majors_coloredenrollment.png)

The large blue line that keeps increasing is CS. We can see HumBio (the dominant major before CS took over) was actually slowing down its ascent even at 11% of major enrollment, but CS is ready to push 15% and shows no signs of stopping (well there's a slight lowering of the acceleration in the last couple years, but still very steep upwards curve going on). So this picture makes it seem like CS is really something unique in terms of how fast its growing and how it shows no signs of stopping. There is one caveat here - technical majors might be declaring earlier in recent years than they have in past years (note that if technical majors just declare earlier on principal, then this is a consistent effect and therefore not a cause of the increase in technically declared majors), which could skew these results. It would be much more accurate to look at diplomas handed out, but I couldn't find that.

Now, the combined tech score that I'm calculating for each year at Stanford is just a weighted average, so (tech score * # of people in major for all majors)/(total # of declared majors). Since it's just a number, for reference I'm also going to calculate the average (mean) tech score (which is ~0.26003) times the number of declared majors to give a sense of how much higher the tech score is than if we had a purely even class distribution (there are problems here - if there are numerically more engineering majors to choose from, then the "mean" is still heavily weighted towards lots of engineers. Stanford's major breakdown is just very even this way and choosing the mean coeff score is pretty representative. The median is 0.1, so that's another option). 

There is one more thing we need to get an idea of how "techie" Stanford is - the maximum far end of technical things. I looked up MIT's enrollment stats (and I don't think MIT is really as technical as it gets, they are actually fairly well rounded) and they loosely have a 675 engineering, 289 science, 34 management, 45 humanities, and 17 architecture breakdown. Now, MIT actually has a large core curriculum (5 math 5 physics 2 chem 1 bio I think?) so I think it's fair to say that for a gross upper end estimate it's okay to use the Engineering, Physics, MS&E, 0, and 0 as the coefficients respectively. 

That gives us $1/2 (675 * 1.54 + 289 * 1.39 + 34 * 1.04) = 738.265$. Ok, the problem here is that I forgot to normalize by the number of people in the class - for MIT it's 1060, so the score is 0.696 (the tech coeff for a pretty technical school). The 1/2 comes from normalizing the norms (|{1,1,1,1}| = 2).


![stanford's techiness plotted over time - stanford (blue), mean techiness coeff (red), MIT coeff (yellow)](/img/majors_techscores.png)

As you can see in the plot, Stanford's tech score is actually ~0.5 right now. The total percentage of engineering students is 20% of the class, whereas for MIT it's 65% of the class. 

First, in order to *really* say anything here, we'd need similar data from a lot of other colleges to know where Stanford actually stands. Is MIT at 2 STDEVs? 3? 10? I can't really tell with this data. So we can say that Stanford is 'half way to being an engineering school' but without understanding the landscape of other colleges, we can't really say that that's true. Also, the way normal distributions work is that the first halfway is easy to slip down (till the inflection point, the slope increases), and then it becomes harder and harder to get to the MIT point. Regardless, the key missing piece of information is where the inflection point is on this graph. For that I would need data from other ivys, etc etc. 

Also, this analysis can say how technical the majors are, but it can't say how technically oriented the mindset is. For example, STS is definitely not as technical as, say, CS is, but it still involves a lot of thinking about technology. Also, a lot of those majors can end up as PMs or PMMs in companies, which can be fairly technical jobs. At the very least, it's a job in the tech industry, which arguably makes you a tech person whether or not your major was in that discipline. 

So, conclusions - stanford isn't bad compared to an actual technical school, so those sensationalist articles like "Startup U" saying "who even needs the humanities?" need to chill out. A school like MIT is where 'no one needs humanities' is at, and Stanford's admission policies still have admittance for non-technically oriented people, and its commitment to a liberal education means that if things get much worse than this, they'll definitely step in and implement some changes. However, there's a much hairier analysis left to be done about where the mindset of the campus is, and that might be much more technical than the major distribution gives it credit for.

Also, I think there's a larger discussion (getting on my soapbox here, leaving the world of data) that higher (and secondary) education need to have about literacy in relation to any particular field. I think that it's pretty much required at this point to have your primary area of study AND be literate in a variety of fields. For example, I've wanted to be literate in chemistry, aerospace engineering, political science, and communications (to name a few), but there doesn't seem to be a lot of ways to get that information without taking the formal courses for the major. Eg, to take Organic Chemistry (which I'd really like to learn), I need to take Chem 31 A/B + Chem 33 + Chem 35, and I just don't have space for that or the time to compete with premeds over grades. 

However, it is possible to a larger extent to take a one-off humanities course and gain a lot from it if taught properly and inclusively to those who have little background in the subject. I've experienced this with a storytelling class, and with ethics classes. I also want to take some sociology/psychology and CSRE and well, quite a lot more things. But ultimately what this means is that if I were someone with a wide variety of interests, it would be safer to hedge on the technical side, since that takes more investment to gain even literacy in (simply due to the way that college courses are structured) technical disciplines, rather than hedge on the humanities side. Eg. 106A is really not making you literate about Computer Science or programming in in of itself.




























