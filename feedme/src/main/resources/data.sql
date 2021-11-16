-- DUMMY USER DATA
-- BOTH PASSWODS ARE 1234567890 ( RELYING ON 12 BCRYPT ITERATIONS )
-- ID: 1
INSERT INTO
  users (username, email, password, admin)
VALUES
  (
    'admin',
    'bla@bla.bla',
    '$2b$12$BB1tJYM6EUAaQr1YhslmHOKJkuQrBAxiPqw66pC.PsCtm/pte6uBi',
    true
  );

-- ID: 2
INSERT INTO
  users (username, email, password, admin)
VALUES
  (
    'johndoe',
    'john@bla.bla',
    '$2b$12$BB1tJYM6EUAaQr1YhslmHOKJkuQrBAxiPqw66pC.PsCtm/pte6uBi',
    false
  );

-- DUMMY INGREDIENT DATA
-- ID: 1
INSERT INTO
  ingredients (name)
VALUES
  ('chicken');

-- ID: 2
INSERT INTO
  ingredients (name)
VALUES
  ('chicken broth');

-- ID: 3
INSERT INTO
  ingredients (name)
VALUES
  ('turkey');

-- ID: 4
INSERT INTO
  ingredients (name)
VALUES
  ('bacon');

-- ID: 5
INSERT INTO
  ingredients (name)
VALUES
  ('ground beef');

-- ID: 6
INSERT INTO
  ingredients (name)
VALUES
  ('ham');

-- ID: 7
INSERT INTO
  ingredients (name)
VALUES
  ('sausage');

-- ID: 8
INSERT INTO
  ingredients (name)
VALUES
  ('beef tenderloin');

-- ID: 9
INSERT INTO
  ingredients (name)
VALUES
  ('salmon');

-- ID: 10
INSERT INTO
  ingredients (name)
VALUES
  ('cod');

-- ID: 11
INSERT INTO
  ingredients (name)
VALUES
  ('char');

-- ID: 12
INSERT INTO
  ingredients (name)
VALUES
  ('tuna');

-- ID: 13
INSERT INTO
  ingredients (name)
VALUES
  ('shrimp');

-- ID: 14
INSERT INTO
  ingredients (name)
VALUES
  ('eggs');

-- ID: 15
INSERT INTO
  ingredients (name)
VALUES
  ('egg yolks');

-- ID: 16
INSERT INTO
  ingredients (name)
VALUES
  ('egg whites');

-- ID: 17
INSERT INTO
  ingredients (name)
VALUES
  ('milk');

-- ID: 18
INSERT INTO
  ingredients (name)
VALUES
  ('cheese');

-- ID: 19
INSERT INTO
  ingredients (name)
VALUES
  ('parmesan');

-- ID: 20
INSERT INTO
  ingredients (name)
VALUES
  ('mozzarella');

-- ID: 21
INSERT INTO
  ingredients (name)
VALUES
  ('cream cheese');

-- ID: 22
INSERT INTO
  ingredients (name)
VALUES
  ('butter');

-- ID: 23
INSERT INTO
  ingredients (name)
VALUES
  ('olive oil');

-- ID: 24
INSERT INTO
  ingredients (name)
VALUES
  ('sesame oil');

-- ID: 25
INSERT INTO
  ingredients (name)
VALUES
  ('penne pasta');

-- ID: 26
INSERT INTO
  ingredients (name)
VALUES
  ('fusili pasta');

-- ID: 27
INSERT INTO
  ingredients (name)
VALUES
  ('fettuccine pasta');

-- ID: 28
INSERT INTO
  ingredients (name)
VALUES
  ('ravioli pasta');

-- ID: 29
INSERT INTO
  ingredients (name)
VALUES
  ('tortellini pasta');

-- ID: 30
INSERT INTO
  ingredients (name)
VALUES
  ('flour');

-- ID: 31
INSERT INTO
  ingredients (name)
VALUES
  ('sugar');

-- ID: 32
INSERT INTO
  ingredients (name)
VALUES
  ('brown sugar');

-- ID: 33
INSERT INTO
  ingredients (name)
VALUES
  ('powdered sugar');

-- ID: 34
INSERT INTO
  ingredients (name)
VALUES
  ('vanilla');

-- ID: 35
INSERT INTO
  ingredients (name)
VALUES
  ('cocoa');

-- ID: 36
INSERT INTO
  ingredients (name)
VALUES
  ('yeast');

-- ID: 37
INSERT INTO
  ingredients (name)
VALUES
  ('baking soda');

-- ID: 38
INSERT INTO
  ingredients (name)
VALUES
  ('baking powder');

-- ID: 39
INSERT INTO
  ingredients (name)
VALUES
  ('corn starch');

-- ID: 40
INSERT INTO
  ingredients (name)
VALUES
  ('baking chocolate');

-- ID: 41
INSERT INTO
  ingredients (name)
VALUES
  ('cinnamon');

-- ID: 42
INSERT INTO
  ingredients (name)
VALUES
  ('salt');

-- ID: 43
INSERT INTO
  ingredients (name)
VALUES
  ('black pepper');

-- ID: 44
INSERT INTO
  ingredients (name)
VALUES
  ('tomato');

-- ID: 45
INSERT INTO
  ingredients (name)
VALUES
  ('scallions');

-- ID: 46
INSERT INTO
  ingredients (name)
VALUES
  ('garlic');

-- ID: 47
INSERT INTO
  ingredients (name)
VALUES
  ('parsley');

-- ID: 48
INSERT INTO
  ingredients (name)
VALUES
  ('bell pepper');

-- ID: 49
INSERT INTO
  ingredients (name)
VALUES
  ('celery');

-- ID: 50
INSERT INTO
  ingredients (name)
VALUES
  ('onion');

-- ID: 51
INSERT INTO
  ingredients (name)
VALUES
  ('radishes');

-- ID: 52
INSERT INTO
  ingredients (name)
VALUES
  ('ginger');

-- ID: 53
INSERT INTO
  ingredients (name)
VALUES
  ('mushroom');

-- ID: 54
INSERT INTO
  ingredients (name)
VALUES
  ('zucchini');

-- ID: 55
INSERT INTO
  ingredients (name)
VALUES
  ('jalapeño pepper');

-- ID: 56
INSERT INTO
  ingredients (name)
VALUES
  ('avocados');

-- ID: 57
INSERT INTO
  ingredients (name)
VALUES
  ('broccoli');

-- ID: 58
INSERT INTO
  ingredients (name)
VALUES
  ('panko bread crumbs');

-- ID: 59
INSERT INTO
  ingredients (name)
VALUES
  ('soy sauce');

-- ID: 60
INSERT INTO
  ingredients (name)
VALUES
  ('coffee');

-- ID: 61
INSERT INTO
  ingredients (name)
VALUES
  ('pesto');

-- ID: 62
INSERT INTO
  ingredients (name)
VALUES
  ('tomato puree');

-- ID: 63
INSERT INTO
  ingredients (name)
VALUES
  ('gnocci pasta');

  -- ID: 64
INSERT INTO
  ingredients (name)
VALUES
  ('butternut pumpkin');


-- DUMMY RECIPE DATA
-- ID: 1
INSERT INTO
  recipes (
    name,
    description,
    instructions,
    image,
    calories,
    carbs,
    fats,
    proteins
  )
VALUES
  (
    'Chicken and Broccoli Stir-Fry',
    'An easy and delicious stir-fry anyone could make to impress their loved ones! Great with rice or by itself.',
    '1. Make the Sauce: Combine broth, soy sauce, brown sugar, corn starch, sesame oil, ginger, galic and black pepper, and stir to dissolve the sugar. 
    The warm broth will help the sugar to dissolve easily.
    2. Cook Chicken: Cut chicken into small 3/4″ bite-sized pieces and season lightly with black pepper. Heat a large heavy skillet over medium-high heat with 1 Tbsp oil. 
    Add chicken and cook 1 minute undisturbed then stir fry 5 minutes until golden and just cooked through. Remove chicken to a bowl.
    3. Sautee Veggies: In the same skillet over medium-high heat, add olive oil, cut broccoli florets, sliced onion and mushrooms, and stir fry for about 3 minutes 
    until mushrooms are softened and then reduce heat.
    4. Add Stir Fry Sauce: Give the sauce a quick stir in case there was settling and add it to the skillet. 
    Simmer the sauce over medium/low heat for 3-4 minutes until thickened and garlic and ginger have mellowed in flavor. 
    To thin the sauce, add water as needed and you can add more soy sauce to taste.
    5. Return Chicken to the Pan and stir until heated through then serve over white rice garnished with sesame seeds if desired.',
    'Get image',
    325.0,
    21.0,
    14.0,
    31.0
  );

-- DUMMY RECIPE INGREDIENTS
INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (1, 1, 1, 'lb');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (1, 23, 2, 'tbsp');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (1, 57, 5, 'cups');
  
INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (1, 50, 0.25, 'cups');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (1, 53, 0.5, 'lb');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (1, 2, 0.67, 'cups');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (1, 59, 3, 'tbsp');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (1, 32, 2, 'tbsp');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (1, 39, 1, 'tbsp');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (1, 52, 1, 'tbsp');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (1, 46, 1, 'tsp');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (1, 43, 0.25, 'tsp');

-- ID: 2
INSERT INTO
  recipes (
    name,
    description,
    instructions,
    image,
    calories,
    carbs,
    fats,
    proteins
  )
VALUES
  (
    'Pesto Tortellini Salad',
    'Only takes 10 minutes to create this awesomeness',
    '1. Bring a large pot of water a rolling boil. Add in the tortellini. Do NOT cook tortellini according to package instructions. 
    Instead, boil for exactly 1 and 1/2 minutes. Then drain and rinse with cold water.
    2. Place drained tortellini in a large bowl. Add in the pesto, chopped tomatoes and onion, and ripped up mozzarella. Toss to coat.
    3. Serve at once, or cover and place in the fridge until needed.',
    'Get image',
    385.2,
    53.6,
    14.3,
    12.0
  );

-- DUMMY RECIPE INGREDIENTS
INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (2, 29, 1, '20 ounce bag');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (2, 61, 1, 'cup');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (2, 44, 1.5, 'cups');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (2, 20, 12, 'ounces');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (2, 50, 0.25, 'cups');


-- ID: 3
INSERT INTO
  recipes (
    name,
    description,
    instructions,
    image,
    calories,
    carbs,
    fats,
    proteins
  )
VALUES
  (
    'Chicken Parmesan',
    'The best chicken parmesan with a deliciously crispy coating, smothered in a rich homemade tomato sauce and melted mozzarella cheese!',
    'For The Chicken:
    1. Preheat oven 430°F | 220°C. Lightly grease an oven tray (or baking dish) with non stick cooking oil spray; set aside.
    2. Whisk together eggs, garlic, parsley, salt and pepper in a shallow dish. Add chicken into the egg, rotating to evenly coat each fillet in the mixture. Cover with plastic wrap and allow to marinate for at least 15 minutes (or overnight night if time allows for a deeper flavour).
    3. When chicken is ready for cooking, mix bread crumbs, Parmesan cheese and garlic powder together in a separate shallow bowl. Dip chicken into the breadcrumb mixture to evenly coat.
    4. Heat oil in a large skillet over medium-high heat until hot and shimmering. Fry chicken until golden and crispy, (about 4-5 minutes each side).
    5. Place chicken on prepared baking tray / dish and top each breast with about 1/3 cup of sauce (sauce recipe below). Top each chicken breast with 2-3 slices of mozzarella cheese and about 2 tablespoons parmesan cheese. Sprinkle with basil or parsley. 
    6. Bake for 15-20 minutes, or until cheese is bubbling and melted, and the chicken is completely cooked through.
    For The Sauce:
    1. Heat oil in a medium-sized pot. Fry onion until transparent (about 3 minutes), then add the garlic until fragrant (about 30 seconds).
    2. Add the tomato puree, salt and pepper to taste, Italian herbs and sugar (If using). Cover with lid to simmer for about 8 minutes, or until sauce has thickened slightly. Taste test and adjust salt and pepper, if needed.',
    'Get image',
    478.0,
    35.0,
    22.0,
    36.0
  );

-- DUMMY RECIPE INGREDIENTS
INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (3, 14, 2, 'large');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (3, 46, 1, 'tbsp');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (3, 47, 2, 'tbsp');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (3, 1, 550, 'g');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (3, 58, 1.5, 'cup');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (3, 19, 0.5, 'cup');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (3, 23, 0.5, 'cup');

-- INSERT INTO
--   ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
-- VALUES
--   (3, 23, 1, 'tbsp');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (3, 50, 1, 'large');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (3, 62, 14, 'ounces');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (3, 20, 8, 'ounces');

-- ID: 4
INSERT INTO
  recipes (
    name,
    description,
    instructions,
    image,
    calories,
    carbs,
    fats,
    proteins
  )
VALUES
  (
    'Fried Green Tomatoes',
    'Fried Green Tomatoes... no description necessary',
    'Slice tomatoes 1/3 inch thick. Season each tomato slice with sugar, salt, and pepper. Beat milk and eggs together in a bowl. Pour flour into another bowl. 
    Gently press each tomato slice into flour to coat; shake off the excess flour. 
    Dip tomato slices into egg mixture, then press again into flour. 
    Place breaded tomato slices onto a plate while breading the rest; do not stack. 
    Melt 1/2 cup butter in a large pan over medium heat. Place tomato slices in melted butter and cook until golden brown on one side, 5 to 7 minutes. 
    Flip and cook on the other side until golden brown. Add more butter as need while frying',
    'Get image',
    621.0,
    36.2,
    49.6,
    9.8
  );

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (4, 44, 2, 'large');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (4, 31, 2, 'tsp');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (4, 42, 0.5, 'tsp');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (4, 43, 0.25, 'tsp');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (4, 17, 0.5, 'cup');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (4, 14, 2, 'large');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (4, 30, 1.25, 'cups');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (4, 22, 1, 'cup');

-- ID: 5
INSERT INTO
  recipes (
    name,
    description,
    instructions,
    image,
    calories,
    carbs,
    fats,
    proteins
  )
VALUES
  (
    'French Chocolate Cake',
    'The best chocolate cake you will ever eat',
    'Preheat the oven to 350°F (180°C) and grease a 9-inch (23 cm) spring form. 
    In a medium-size saucepan place on low heat, melt the chocolate with the milk. 
    Add the butter and allow to melt, stirring as needed. 
    Turn off the heat, then add the sugar and the yolks, one at a time, 
    stirring between each addition. Sift in the flour and stir well.
    In a medium-sized bowl, whisk the whites with a pinch of salt until soft peaks form. 
    Carefully incorporate to the chocolate mixture in two or three additions. 
    Pour into the prepared spring form and bake for about 30-35 minutes.',
    'Get image',
    265.2,
    21.5,
    20.1,
    3.0
  );

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (5, 40, 125, 'g');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (5, 17, 3, 'tbsp');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (5, 22, 125, 'g');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (5, 31, 125, 'g');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (5, 30, 2, 'tbsp');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (5, 15, 2, '');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (5, 16, 2, '');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (5, 42, 1, 'pinch');

-- ID: 6
INSERT INTO
  recipes (
    name,
    description,
    instructions,
    image,
    calories,
    carbs,
    fats,
    proteins
  )
VALUES
  (
    'One Pot Pesto Macaroni and Cheese',
    'An easy, healthier version of your normal Mac and cheese that everyone loves',
    'Pour the pasta into a large skillet (about 12 inches in diameter). Pour 3 cups cold water into the pan; the water should cover the pasta.
    Turn the stove burner to high and set a timer for 10 minutes. Cook on high, stirring frequently, until most of the water is absorbed, about 10 minutes.
    Reduce heat to medium, add milk, and simmer for an additional 3 minutes,
    stirring constantly so the milk doesnt burn. Remove from the heat, add the salt, pesto, and 2 cups grated cheddar cheese,
    stirring until the cheese is melted. Preheat the broiler.
    Sprinkle the top of the pasta with the remaining ½ cup cheddar cheese and place the sliced tomatoes on top of the cheese.
    Sprinkle the tomatoes with parmesan cheese. Place under the broiler for a minute or two, until the parmesan cheese melts and turns golden brown. Serve immediately.',
    'Get image',
    504.0,
    49.0,
    23.0,
    23.0
  );

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (6, 27, 12, 'oz');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (6, 17, 1.5, 'cups');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (6, 42, 0.5, 'tsp');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (6, 61, 0.25, 'cup');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (6, 18, 2.5, 'cups');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (6, 44, 3, 'medium');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (6, 19, 0.25, 'cup');

-- ID: 7
INSERT INTO
  recipes (
    name,
    description,
    instructions,
    image,
    calories,
    carbs,
    fats,
    proteins
  )
VALUES
  (
    'Cinnamon Buns',
    'The best cinnamon buns you will ever try',
    '1. Warm milk to around 43 degrees C (microwaving it for 40-45 seconds). It should be like warm bath water. Transfer warm milk to the bowl of an electric mixer and sprinkle yeast on top. Add in sugar, egg, egg yolk and melted butter. Mix until well combined. Next stir in flour and salt with a wooden spoon until a dough begins to form.
    2. Place dough hook on stand mixer and knead dough on medium speed for 8 minutes. Dough should form into a nice ball and be slightly sticky. If it is TOO sticky (meaning it is sticking to the bottom of the mixer, add in 2 tablespoons more bread flour.) If you don’t want to use an electric mixer, you can use your hands to knead the dough for 8-10 minutes on a well-floured surface.
    3. Transfer dough ball to a well-oiled bowl, cover with plastic wrap and a warm towel. Allow dough to rise for 1 hour to 1 ½ hours, or until doubled in size. This may more or less time depending the humidity and temperature in your home.
    4. After dough has doubled in size, transfer dough to a well-floured surface and roll out into a 14x9 inch rectangle. Spread softened butter over dough, leaving a ¼ inch margin at the far side of the dough.
    5. In a small bowl, mix together brown sugar and cinnamon. Use your hands to sprinkle mixture over the buttered dough, then rub the brown sugar mixture into the butter.
    6. Tightly roll dough up, starting from the 9-inch side and place seam side down making sure to seal the edges of the dough as best you can. You will probably need to cut off about an inch off the ends of the dough as the ends won’t be as full of cinnamon sugar as we’d want it to be.
    7. Cut into 1 inch sections with a serrated knife or floss. You should get 9 large pieces.
    8. Place cinnamon rolls in a greased 9x9 inch baking pan or round 9 inch cake pan. (I also recommend lining the pan with parchment paper as well, in case any of the filling ends up leaking out.) Cover with plastic wrap and a warm towel and let rise again for 30-45 minutes.
    9. Preheat oven to 350 degrees F. Remove plastic wrap and towel and bake cinnamon rolls for 20-25 minutes or until just slightly golden brown on the edges. You want to underbake them a little so they stay soft in the middle, that’s why we want them just slightly golden brown. Allow them to cool for 5-10 minutes before frosting. Makes 9 cinnamon rolls.
    10. To make the frosting: In the bowl of an electric mixer, combine cream cheese, butter, powdered sugar and vanilla extract. Beat until smooth and fluffy. Spread over cinnamon rolls and serve immediately. Enjoy!',
    'Get image',
    436.0,
    50.0,
    24.0,
    4.7
  );

-- DUMMY RECIPE INGREDIENTS
INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (7, 17, 0.75, 'cups');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (7, 36, 2.25, 'tsp');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (7, 31, 0.25, 'cups');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (7, 14, 1, 'large');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (7, 28, 15, '');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (7, 22, 0.25, 'cup');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (7, 30, 3, 'cups');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (7, 42, 0.75, 'tsp');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (7, 32, 0.67, 'cup');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (7, 41, 1.5, 'tbsp');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (7, 21, 4, 'ounces');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (7, 33, 0.75, 'cup');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (7, 34, 0.5, 'tsp');


-- ID: 8
INSERT INTO
  recipes (
    name,
    description,
    instructions,
    image,
    calories,
    carbs,
    fats,
    proteins
  )
VALUES
  (
    'Gnocchi cacio e pepe',
    'Enjoy a comforting bowl of gnocchi with parmesan and black pepper. 
    It is simple but utterly delicious – and the best part is, it only takes seven minutes to make!',
    '1. Cook the gnocchi in a large pan of lightly salted, boiling water. 
    Drain and reserve 200ml of the cooking water.
    2. Heat the butter in a large frying pan. Add the gnocchi, cheese and pepper as well as 
    150ml of the cooking water, raise the heat a little and stir vigorously until melted and 
    the gnocchi is well coated. Pour in more of the reserved water if you like it saucier. 
    Season with a little salt. Transfer the gnocchi to bowls and serve with a mixed salad, if you like.',
    'Get image',
    424.0,
    41.0,
    21.0,
    15.0
  );

-- DUMMY RECIPE INGREDIENTS
INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (8, 63, 300, 'g');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (8, 22, 2, 'tbsp');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (8, 19, 60, 'g');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (8, 43, 2, 'tsp');


-- ID: 9
INSERT INTO
  recipes (
    name,
    description,
    instructions,
    image,
    calories,
    carbs,
    fats,
    proteins
  )
VALUES
  (
    'Pesto chicken and pumpkin pasta',
    '30 minutes is all you need to make this speedy, 6-ingredient recipe for 4. 
    It is simple but utterly delicious – and the best part is, it only takes seven minutes to make!',
    '1. Cook the pasta following packet directions or until al dente. Drain well. 
    Meanwhile, cook the pumpkin (peeled, seeded and cut into 3cm pieces) in a steamer over a saucepan of boiling water for 10-15 mins or until tender.
    2. While pumpkin is cooking, heat oil in a large frying pan over high heat. 
    Cook chicken, in batches, for 5 mins or until browned and almost cooked through. 
    Transfer to a heatproof bowl. Cook chopped onion in frying pan, stirring, for 3 mins or until onion is tender.
    3. Return the chicken to the pan with pasta and pesto. 
    Cook, stirring, for 5 mins or until well combined and chicken is cooked through. 
    Add the pumpkin and toss to combine. Season.',
    'Get image',
    775.0,
    75.0,
    33.0,
    40.0
  );

-- DUMMY RECIPE INGREDIENTS
INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (9, 25, 375, 'g');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (9, 64, 600, 'g');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (9, 23, 1, 'tbsp');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (9, 1, 500, 'g');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (9, 50, 1, '');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (9, 61, 150, 'g');

-- ID: 10
INSERT INTO
  recipes (
    name,
    description,
    instructions,
    image,
    calories,
    carbs,
    fats,
    proteins
  )
VALUES
  (
    'Avocado Fries',
    'These Avocado Fries are easy to make, taste delicious and are perfect for the whole family. ',
    ' Pre-heat the oven to 200C / 400F.
    1. Start by halving an avocado, remove the stone and then slice each half lengthways, just to the skin, to form “fries”. Use a large spoon to gently scoop out the slices. Alternately, you can peel back the skin of the avocado if you find that this would be easier. 
    2. To coat the avocado start by rolling each slice in the flour.
    3. Then dip into the beaten egg.
    4. Finally, roll in the breadcrumbs.
    5. Place each coated slice on a baking tray and lightly spray/drizzle with olive oil. Bake for approx. 15-20 mins at 200C / 400F.',
    'Get image',
    290.0,
    26.0,
    19.0,
    8.0
  );

-- DUMMY RECIPE INGREDIENTS
INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (10, 56, 2, 'whole');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (10, 30, 30, 'g');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (10, 14, 2, 'large');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (10, 58, 1, 'cup');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (10, 23, 1, 'tbsp');


-- ID: 11
INSERT INTO
  recipes (
    name,
    description,
    instructions,
    image,
    calories,
    carbs,
    fats,
    proteins
  )
VALUES
  (
    'Pizza twists',
    'Make sure you make lots because everyone will be coming back for more.',
    '1. Preheat oven to 200°C. Grease a large baking tray. Combine 3/4 cup warm water, yeast, sugar and salt in a jug. Whisk with a fork to dissolve. Cover with plastic wrap. Set aside in a warm place for 5 minutes or until bubbles appear on the surface.
    2. Sift flour into a bowl. Add yeast mixture and half the oil (2 tbsp). Mix to form a soft dough. Turn onto a lightly floured surface. Knead for 10 minutes or until elastic. Place in a lightly greased bowl. Cover with plastic wrap. Stand in a warm place for 25 to 30 minutes or until dough has doubled in size.
    3. Use your fist to punch dough down. Knead on a lightly floured surface until smooth.
    4. Cut dough into 4 portions. Cut each portion into 6 pieces. Roll 1 piece into a 10cm-long sausage shape. Cut sausage shape in half lengthways. Twist strips together, pressing ends to secure. Place on prepared tray. Repeat with remaining dough.
    5. Brush each pizza twist with the rest of the oil. Sprinkle with sea salt. Bake for 15 to 20 minutes or until crisp and golden. Serve warm with your favorite dip',
    'Get image',
    245.0,
    40.0,
    15.0,
    9.0
  );

-- DUMMY RECIPE INGREDIENTS
INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (11, 36, 7, 'g');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (11, 31, 1, 'tsp');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (11, 42, 0.5, 'tsp');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (11, 30, 2, 'cups');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (11, 23, 4, 'tbsp');

-- ID: 12
INSERT INTO
  recipes (
    name,
    description,
    instructions,
    image,
    calories,
    carbs,
    fats,
    proteins
  )
VALUES
  (
    'Avocado Omelet with Herbs',
    'Delicious and healthy!',
    '1. Heat the oil in a non-stick frying pan over medium heat. Sauté the onion for 4 minutes, or until it starts to turn golden brown.
    2. Transfer to a bowl, and beat with the eggs, and salt and pepper to taste.
    3. Heat 1 teaspoon oil in the pan over medium heat, and add half of the egg mixture. Cook for a few minutes, and when it’s firm enough to turn around with a flat spatula, turn it and cook for 30 second on the second side. Transfer to the plate, and repeat the same process with the second half of the egg mixture.
    4. Cover half of each omelet with avocado slices, and fold in half, covering the avocado.
    5. Sprinkle each omelette with ½ tablespoon chopped parsley.',
    'Get image',
    337.0,
    7.0,
    28.0,
    14.0
  );

-- DUMMY RECIPE INGREDIENTS
INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (12, 23, 1, 'tbsp');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (12, 50, 0.5, 'medium');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (12, 14, 4, '');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (12, 56, 1, 'medium');

INSERT INTO
  ingredient_quantity (recipe_id, ingredient_id, quantity, unit)
VALUES
  (12, 47, 1, 'tbsp');

-- DUMMY STORED USER RECIPES
INSERT INTO
  users_recipes (recipe_id, user_id)
VALUES
  (1, 2);

INSERT INTO
  users_recipes (recipe_id, user_id)
VALUES
  (6, 2);

INSERT INTO
  users_recipes (recipe_id, user_id)
VALUES
  (7, 2);

-- DUMMY RECIPE COMMENTS
INSERT INTO
  comments (body, recipe_id, user_id)
VALUES
  ('Dry', 1, 2);

-- DUMMY RECIPE REVIEWS
INSERT INTO
  reviews (recipe_id, user_id, title, subtitle, "date", rating)
VALUES
  (1, 2, 'Bleh', 'Meh', CURRENT_TIMESTAMP, 3);

INSERT INTO
  reviews (recipe_id, user_id, title, subtitle, "date", rating)
VALUES
  (3, 2, 'Nice', 'Nice', CURRENT_TIMESTAMP, 5);