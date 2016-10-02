# Contributing
This document outlines how to contribute content to this website. It does not discuss contributing to the GNS.

Because this site is based on Jekyll, many of the pages are written in Markdown, allowing anyone with a basic understanding of Markdown or with a [reference guide](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet) on hand to make changes.

## Methods of editing
There are two ways to edit the content:

* Clone/fork this repo
  * Cloning/fork the repository and make your changes. If you wish to preview the changes locally you must follow the  instructions in README.md on building the site locally.
* Github's web interface
  * This method is ideal for those who want to make single file changes or want to avoid setting up a build environment to make changes. With the Github interface changes can be previewed in a simple Markdown format, rather than within the full context of the site.
	* To edit a file navigate to its corresponding page on Github and click the edit button, a grey pen, just above where the contents of the file are displayed on the right half of the page. Using the Github editor you can preview changes in a simple Markdown previewer. If you are not a contributer to the repository Github will create a fork where your changes will be stored.
	* NOTE: there may be a slight delay between finishing your changes on the web interface and when Github finishes rebuilding the site. This should take only a few seconds.

## Editing
Most files of interest will be in the `documentation` folder. If you have trouble finding a page, see the [Intra-site links](#intra-site-links) section.

It is recommended that you use existing pages and a guide like [this one](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet) when formatting pages. Always be sure to preview your changes before saving them, whether you are on Github or working locally. If you are migrating content from a Github wiki page note that some modifiers such as `<tt>` do not translate to valid Markdown, so always check.

### Intra-site links
To create a link to other pages on the site use this format: `{{ site.baseurl }}/my_folder(s)/my_page/` where `my_folder(s)` is the folder(s) that the file is located in (i.e. `documentation`) and `my_page` is the file name, without the extension. Thus to link to a page called "Other Page" write `[other page link text]({{ site.baseurl }}/other_page_file_path/)`. If you make an index.md or index.html file in a folder you can access that page at the URL corresponding to its folder (i.e. `documentation` or `my_page`).

### Single sourcing
Content can be single sourced using the `include` tag. Add the content as an HTML file to the `_includes` directory and write `{% include filename.html %}` in the HTML or Markdown file where you want the content to appear. Includes can be sorted into subfolders: `{% include folder/file.html %}`. Unfortunately including Markdown is [not simple](https://stackoverflow.com/questions/7226076/in-jekyll-is-there-a-concise-way-to-render-a-markdown-partial) but can be done with: `{% capture file %}{% include file.md %}{% endcapture %}
{{ file | markdownify }}`

## Adding new pages
There are two steps to creating a page. The first is to create the file that holds the content of the page. The second is to add a link to the page in the sidebar.

### 1. Create the Markdown file
In the appropriate folder (i.e. documentation) create the markdown file, with extension `.md`. In order for a page to be rendered on the site it must contain a [YAML Front Matter block](https://jekyllrb.com/docs/frontmatter/), a set a values enclosed by triple hyphens.

Once the Front Matter block has been added you can populate the file with Markdown content and it will rendered on the site.

#### YAML front matter block
Here is an example Front Matter block:
```
---
title: "My Example Page"
sidebar: documentation_sidebar
toc: true
---
```
* title: The title rendered at the top of the page. The quotation marks are optional but let us include special characters like colons.
* sidebar: The sidebar that will appear on this page. If no sidebar is specified then the page will render without one. Currently there is only one sidebar: documentation_sidebar
* toc: Set to false to remove the table of contents generated at the beginning of the page. NOTE: Header 1 (single #) elements are not included in the TOC, only Header 2 and below.

### 2. Update the sidebar
Add the page to the sidebar file so that a link will be rendered in the sidebar. This file can be found at `_data/sidebars/documentation_sidebar.json`. You must include a title and and URL field for the sidebar item to be properly rendered. Use the same format as described to `Intra-site links` section, with the exception that you don't need to include `{{ site.baseurl }}`. The existing content of the sidebar file should serve as a good example of how to add content.
