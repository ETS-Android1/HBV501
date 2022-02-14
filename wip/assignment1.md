---
geometry: margin=20mm
classoption: table
header-includes: |
    \usepackage{fancyhdr}
    \pagestyle{fancy}
    \rowcolors{2}{gray!10}{gray!25}
    \lhead{Assignment 1: Project Plan and Requirements}
    \chead{}
    \rhead{HBV601}
...

# Team 16

* Jaan Jaerving - KT: 310191-3539
* Snorri Steinn Stefánsson Thors - KT: 300895-2639
* Steinunn Ósk Axelsdóttir - KT: 210688-2699
* Valbjörn Jón Valbjörnsson - KT: 100594-2779 **( Product Owner )**

# 1. Project vision

## Vision statement

* For people looking for a healthier, more involved lifestyle
* who need to use their ingredients,
* the Natural Ingredient System
* is an information system
* that will provide a wealth of cooking recipes to people that are looking to broaden their diet.
  * The system will provide a variety of cooking recipes that can be accessed right at your fingertips.
  * This system will provide ingredient filtering of recipes to save world food waste.
* Unlike current Icelandic eating habits,
* our product will simplify the decision making process and provide an easy opportunity for people to use
the ingredients they already have.

### 1.1 Background ( What triggered this )

* Based on recent studies it appears that roughly 60% of all web users use the internet on their smartphones. Of that 60% we can also see that 90% of that time is spent using apps rather than the browser itself. This indicates that there is a large market we could reach by providing a smartphone application. Additionally this would make it easier to access recipes while cooking.

### 1.4 Success metrics

* Measure our application downloads and engagement and use that to springboard into bigger advertisement offers

### 2.2 Scope of initial release

* The service should have a reasonably sized bank of recipes that can be filtered by desired ingredients.

### 2.3 Scope of subsequent releases

* Creator tier to allow user recipes
* Additional login methods such as Google Credentials or Facebook
* Voice integration for search features
* Recommender system for registered users

### 3.3 Deployment considerations

* A relatively new android smartphone ( Android 6.0 ) is required for guaranteed application support.
* The application requires an internet connection to run and fetch information.
* It will initially be available on the Google Play store.

\pagebreak

# 2. Product backlog

## User Stories

1. As a user it would be comfortable to have recipes initially shown in brief format so that I can quickly flip through them.

2. As a user I would like to be able to quickly search recipes by keywords. This would make it easier to find the dishes I want in a mobile environment.

3. As a user I would like to have the ability to filter recipes by their ingredients. This will enable me to use the ingredients I already have at home.

4. As fitness coach I want the ability to filter recipes based on the nutritional requirements of my clients diet. This will let me quickly put together meal plans for my work.

5. As a user I want to be able to give recipes I have used a rating. This way I can let others know which recipes are worth trying.

6. As a user I would like to be able to order recipes by their rating grade. This way I can be sure that I will be using a good recipe.

7. As a user I would like to be able to sort recipes in order of their macro nutrients. This will let me pick healthier versions of the meals I am looking for.

8. As a user I want to be able to create an account so that I can more easily interact with the app.

9. As a user I want the app to remember me so that I don't have to waste time entering my details every time.

10. As a user I want to be able to store my favorite recipes. That way I can easily find them later.

11. As a user I want to be able to share recipes to my linked social media accounts. This will allow me to let friends know what I'm cooking.

12. As an ad provider I want my products to be shown on relevant recipes. That way interested people can buy my product.


# 3. User Story estimates ( hours )

* Sprint* indicates potential overflow of expected case.

| **Feature** | **Sprint** | **Priority** | **Best Case** | **Most Likely Case** | **Worst Case** | **Expected Case** |
| -           | -          | -            | -             | -                    | -              | -                 |
| 1.          | 1          | 10           | 3.5           | 4                    | 5              | 4.1               |
| 2.          | 1          | 10           | 10            | 12                   | 14             | 12                |
| 3.          | 1          | 10           | 6             | 7                    | 9              | 7.2               |
| 4.          | 2          | 20           | 4             | 5                    | 5.5            | 4.9               |
| 5.          | 2          | 20           | 3.5           | 4                    | 5              | 4.1               |
| 6.          | 2          | 20           | 5             | 6                    | 7.5            | 6.1               |
| 7.          | 2*         | 20           | 10            | 11                   | 13             | 11.2              |
| 8.          | 3          | 30           | 6             | 7                    | 8              | 7                 |
| 9.          | 3          | 30           | 2             | 2.5                  | 3              | 2.5               |
| 10.         | 3          | 30           | 5             | 6                    | 7.5            | 6.1               |
| 11.         | 3*         | 30           | 8             | 9                    | 11             | 9.2               |
| 12.         | 4          | 40           | 10            | 20                   | 30             | 20                |
| **Total**   |            |              |               |                      |                | **94.4**          |

\pagebreak

# 4. Project schedule

* Sprint 4 and related milestones are tentative.

| **Week** | **From**   | **To**     | **Project**  | **Return Assignment** | **Presentation** | **Sprint**    | **Milestone**                   |
| -        | -          | -          | -            | -                     | -                | -             | -                               |
| 1        | 2022.01.14 | 2022.01.16 | Requirements |                       |                  |               |                                 |
| 2        | 2022.01.17 | 2022.01.23 | Requirements |                       |                  |               |                                 |
| 3        | 2022.01.24 | 2022.01.30 | Requirements |                       |                  |               |                                 |
| 4        | 2022.01.31 | 2022.02.06 | Requirements | ret. #1               |                  |               |                                 |
| 5        | 2022.02.07 | 2022.02.13 | Design       |                       | expl. #1         | 1             |                                 |
| 6        | 2022.02.14 | 2022.02.20 | Design       |                       |                  | 1             |                                 |
| 7        | 2022.02.21 | 2022.02.27 | Design       | ret. #2               |                  | 1             | Core architecture               |
|          |            |            |              |                       |                  |               | Design Model                    |
|          |            |            |              |                       |                  |               | Keyword Search                  |
|          |            |            |              |                       |                  |               | Initial Filters                 |
| 8        | 2022.02.28 | 2022.03.06 | Programming  |                       | expl. #2         | 2             |                                 |
| 9        | 2022.03.07 | 2022.03.13 | Programming  |                       |                  | 2             |                                 |
| 10       | 2022.03.14 | 2022.03.20 | Programming  | code review           |                  | 2             | Additional Filters              |
|          |            |            |              |                       |                  |               | Rating System                   |
|          |            |            |              |                       |                  |               | Sorting System                  |
| 11       | 2022.03.21 | 2022.03.27 | Code Review  | ret. #3               |                  | 3             |                                 |
| 12       | 2022.03.28 | 2022.04.03 | Programming  |                       | expl. #3         | 3             |                                 |
| 13       | 2022.04.04 | 2022.04.10 | Programming  |                       |                  | 3             | Login/Register                  |
|          |            |            |              |                       |                  |               | Recipe Storage                  |
|          |            |            |              |                       |                  |               | Social Media                    |
| 14       | 2022.04.11 | 2022.04.17 | Programming  |                       |                  | 4*            |                                 |
| 15       | 2022.04.18 | 2022.04.24 | Presentation | ret. #4               | expl. #4         |               | Ads*                            |

