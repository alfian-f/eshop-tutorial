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

## Module 3
#### Reflection
1. In this code, I implemented the `SRP`, `OCP`, `ISP`, and `DIP` principles.  For the models, each class, such as Car and Product, sticks to doing just one thing.  There's also the Item interface that broke down the item capabilities into simple components. By using this Item interface, it allows for easy expansion without changing existing parts. In the repository section, `CarRepository` and `ProductRepository` classes each exclusively manage the persistence of car and product entities, this uses the `SRP` principle. The `DIP`  is partially demonstrated through the use of interfaces. Specifically, the `ICarRepository` and `IProductRepository` interfaces serve as abstractions that higher-level modules depend on, rather than concrete implementations.


2. The advantages of applying SOLID principles is that when a new model, say a `Food`, is introduced, it can implement the existing `Item` interface or extend its behavior without the need to edit or alter the other code that relies on the interface. This promotes a more modular and maintainable code structure, allowing for seamless additions of new features or models without disrupting the functionality of the existing system.


3. Not following SOLID principles can be a problem when different developers work on the app. If one part of the app relies too much on another part, it can cause problems or errors in that other part.

## Module 4
#### Reflection
1. Percival (2017) proposed self-reflective questions
* Do I have **enough functional tests** to reassure myself that my application really **works, from the point of view of the user**?

No, there are no functional tests in this series. All tests simulate the program without confirming user experience since I haven't implemented functional tests yet.

* Am I testing all the **edge cases thoroughly**?

I think the tests should thoroughly cover all parts of the code. In the jacoco test report, you could see that the code coverage for the recent implemented tutorial features have around 94-100% code coverage.

* Do I have tests that check whether **all my components fit together** properly? Could some integrated tests do this, or are functional tests enough?

I think some unit tests can cover all class functionalities in a single test. However, to ensure the feature works properly, creating the controller and functional tests may be necessary.

* Are my tests giving me the confidence to refactor my code, fearlessly and frequently?

Yes, I think my code can be further improved. Before refactoring, it's less organized, and the purpose of each test is not clearly defined compared to after refactoring.

* Are my tests helping me to drive out a good design? If I have a lot of integration tests but less unit tests, do I need to make more unit tests to get better feedback on my code design?

I've tested every aspect of the Order feature. This comprehensive testing provides valuable feedback on my code design, helping me quickly identify parts causing errors when changes are made.

* Are my feedback cycles as fast as I would like them? When do I get warned about bugs, and is there any practical way to make that happen sooner?

Since using TDD, test cases are created first, it helped me visualize the layout of each test and the expected functionality of the model, service, or repository. Unlike before implementing TDD, bug warnings come when creating the test.

* Is there some way that I could write faster integration tests that would get me feedback quicker?

I haven't implemented an integration test in this module.

* Can I run a subset of the full test suite when I need to?

Yes, all tests are separated into different layers. If I want to test only the service layer of the Order features, I can just run the service tests.

* Am I spending too much time waiting for tests to run, and thus less time in a productive flow state?


No, not really. All the test should finish within a few seconds. For larger projects, I might let CI/CD handle the tests, and I can check the test results later on GitHub while continuing to work.

2. F.I.R.S.T implementation
* **F**ast
  Yes, it only took a few seconds to run all the tests.

* **I**solated
  Yes, while in one test they can use multiple methods, those test would not affect the others(test).

* **R**epeatable
  Yes, as the test are not influenced by external factors.

* **S**elf-Validating
  Yes, each test has an assert case, it is used to validate expected outputs.

* **T**horoughly/Timely
  Yes, as we can see in the jacoco reports, the recently created tests have around 94-100% code coverage.