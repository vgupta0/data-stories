# Data-Stories

The repo container for the website of my blog, Data Stories.

This repo contains both the web code and the raw analysis (mostly done in mathematica).

## File Structure

* `web/` is the web code, it contains the (fully static) webpage of the blog.
* `parse-metadata` is one of the deployment scripts that takes the .json files from each project folder along with the metadata on the top of each file in each posts/ folder and generates a json object that is the skeleton of the metadata of the website. usage: `parse-metadata`. That's it, it takes no arguments and dumps out the website structure that can be put into a file and loaded when the server runs.

* `techniques/` is a special project folder that actually just has posts about the various analysis techniques I use and some discussion of what code goes into making it happen (mostly mathematica at work, though).

* All the other folders are various projects that I'm working/have worked on. Each of these folders has a [folder-name].json file that contains the metadata about that project, an images/ folder that contains the images I took of various graphs/charts, and a posts/ folder that contains the markdown files with the posts. The other files are all for the data analysis. There are various scripts that take the images/metadata/posts respectively and "deploy" them by putting them in the appropriate spot in the web folder.

Markdown is rendered using pandoc (which supports a nice extension allowing for a yaml metatdata block on top of each file).
