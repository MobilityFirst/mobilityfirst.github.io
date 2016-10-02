# GNS Website

This is the website for the [Global Name Service (GNS)](https://github.com/MobilityFirst/GNS). It is based on version 5 of the [Jekyll Documentation Theme](http://idratherbewriting.com/documentation-theme-jekyll/)  ([Github](https://github.com/tomjohnson1492/documentation-theme-jekyll)). It is modified to suit the needs of this project and removes unneeded functionality.

## Build the site

1. Install dependencies
  * gem (Ruby Gems)
  * nodejs and npm
  * bundler
2. Clone the repository
3. Run `bundler install` from the cli in the root directory of the project
4. (Optional) Run `bundler update` to check for updated gems
5. Run `bundler exec jekyll serve` to start the local web server
6. View your local site at http://localhost:4000/

As you save modified files Jekyll will continue to rebuild the site, allowing you to refresh the webpage to see them. Note changes to `_config.yml` require a restart of the webserver.

## Editing content
See [CONTRIBUTE.md](https://github.com/MobilityFirst/mobilityfirst.github.io/blob/master/CONTRIBUTE.md) for details regarding how to edit and add content to the site. This document and existing content should give you a good idea of what to do.

Jekyll was chosen so that a contributer only need to know Markdown (or have a [reference](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet) handy) to make content. Content is separated from styling which allows contributers to easily create and update content without worrying about proper. If you wish to change more than the just the content of the site you should be familiar with [Jekyll](https://jekyllrb.com/).
