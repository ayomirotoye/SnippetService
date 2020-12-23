# SnippetService
An MVP service to demo a snippet server 

Assumptions
1. Snippets must be unique

Design decisions
1. The Use of Dependency Injection 
2. Using a ConcurrentHashMap for repository purpose

Production concerns addressed
1. Guard against duplicate entries by ensuring unique name for snippets
2. Also ensuring that the snippet names v=can not contain spaces

why I chose the technology used
I used Java because its my core programming language
I used Spring because it provides the necessary tools to set up and develop a Ready to use Web service or Application.
It also provides a robust framework for developing scalable applications

Error handling approach
Use of try blocks in order to catch exceptions when thrown

Proposed Refacctorings
1. Expired Snippets should be archived
2. Adding a logging capability to write errors and important info to files
3. Adding a security layer 
4. Introducing a DB layer
