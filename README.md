# Data-Stories

The repo container for the website of my blog, Data Stories.

This repo contains both the web code and the raw analysis (mostly done in mathematica).

`web/` is the web code, it contains the (fully static) webpage of the blog.
`parse-metadata` is one of the deployment scripts that takes the .json files from each project folder along with the metadata on the top of each file in each posts/ folder and generates a json object that is the skeleton of the metadata of the website. usage: `parse-metadata > web/metadata.json`. That's it, it takes no arguments and dumps out the website structure that can be put into a file and loaded when the server runs.

`techniques/` is a special project folder that actually just has posts about the various analysis techniques I use and some discussion of what code goes into making it happen (mostly mathematica at work, though).

All the other folders are various projects that I'm working/have worked on.
