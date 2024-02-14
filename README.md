# eshop - advprog
[![Continuous Integration (CI)](https://github.com/alfian-f/eshop-tutorial/actions/workflows/ci.yml/badge.svg?branch=main)](https://github.com/alfian-f/eshop-tutorial/actions/workflows/ci.yml)
[![pmd](https://github.com/alfian-f/eshop-tutorial/actions/workflows/pmd.yml/badge.svg)](https://github.com/alfian-f/eshop-tutorial/actions/workflows/pmd.yml)
[![Scorecard supply-chain security](https://github.com/alfian-f/eshop-tutorial/actions/workflows/scorecard.yml/badge.svg)](https://github.com/alfian-f/eshop-tutorial/actions/workflows/scorecard.yml)

Alfian Fadhlurrahman | 2206821683 | Tutorial - Advanced Programming


[deployed app]: https://eshop-advprog-alfian-f.koyeb.app/product/list
[deployed app]

## Module 1
#### Reflection 1
1. I added two new features – the option to edit and delete products. When working on these features, I kept the code clean by using clear and easy-to-understand names for variables and functions. I also followed a simple and organized development approach by creating separate branches for each feature using Git Flow. If I were to improve my code, I would probably focus more on secure coding. I don't think I really do much about secure coding practices, and adding comments for better clarity is also something I plan to do in the future.

#### Reflection 2 
1. I feel confident that the feature that I just added can run smoothly. For each function or method, it's essential to have corresponding unit tests that cover various scenarios like valid inputs, edge cases, and errors. The aim is to ensure sufficient test coverage to catch potential issues. 

	While code coverage is a useful metric indicating how much code is tested, achieving 100% coverage doesn't guarantee a bug-free application. It simply means every line is executed at least once, but thorough test case design is essential to cover all possible paths and scenarios.


2. it's crucial to maintain code cleanliness. The cleanliness of the code ensures readability and maintainability and reduces the likelihood of bugs appearing. However, duplication of setup procedures and instance variables across multiple test suites can impact code quality. We can extract common setup logic and instance variables into a separate method or class to resolve the duplication of setup procedures.

## Module 2
#### Reflection
1. The quality issues that I fixed are Unused imports & Unnecessary modifiers. For unused imports issue, instead of using ```import org.springframework.web.bind.annotation.*;```, I only use necessary import like RequestMapping, GetMapping, PostMapping, ModelAttribute, and PathVariable. For the unnecessary modifiers issue, I removed all the public modifiers in ```ProductService.java```.


2. My code setup on GitHub is like having automated checks (tools like Scorecard and PMD) that examine the code as soon as I push it. Additionally, I've automated the process of deploying my code through Koyeb, using a Dockerfile. This means my changes are tested and put into action automatically, without needing manual steps. This efficient approach not only speeds up my development process but also ensures that my software runs smoothly when it's live, making my overall software delivery process more effective and dependable.
