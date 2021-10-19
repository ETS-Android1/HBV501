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
  ('cheese');

-- ID: 3
INSERT INTO
  ingredients (name)
VALUES
  ('eggs');

-- ID: 4
INSERT INTO
  ingredients (name)
VALUES
  ('butter');

-- ID: 5
INSERT INTO
  ingredients (name)
VALUES
  ('milk');

-- ID: 6
INSERT INTO
  ingredients (name)
VALUES
  ('flour');

-- ID: 7
INSERT INTO
  ingredients (name)
VALUES
  ('sugar');

-- ID: 8
INSERT INTO
  ingredients (name)
VALUES
  ('olive oil');

-- ID: 9
INSERT INTO
  ingredients (name)
VALUES
  ('bacon');

-- ID: 10
INSERT INTO
  ingredients (name)
VALUES
  ('ground beef');

-- ID: 11
INSERT INTO
  ingredients (name)
VALUES
  ('ham');

-- ID: 12
INSERT INTO
  ingredients (name)
VALUES
  ('sausage');

-- ID: 13
INSERT INTO
  ingredients (name)
VALUES
  ('turkey');

-- ID: 14
INSERT INTO
  ingredients (name)
VALUES
  ('beef tenderloin');

-- ID: 15
INSERT INTO
  ingredients (name)
VALUES
  ('chocolate');

-- ID: 16
INSERT INTO
  ingredients (name)
VALUES
  ('penne');

-- ID: 17
INSERT INTO
  ingredients (name)
VALUES
  ('fusili');

-- ID: 18
INSERT INTO
  ingredients (name)
VALUES
  ('fettuccine');

-- ID: 19
INSERT INTO
  ingredients (name)
VALUES
  ('ravioli');

-- ID: 20
INSERT INTO
  ingredients (name)
VALUES
  ('tortellini');

-- ID: 21
INSERT INTO
  ingredients (name)
VALUES
  ('salmon');

-- ID: 22
INSERT INTO
  ingredients (name)
VALUES
  ('cod');

-- ID: 23
INSERT INTO
  ingredients (name)
VALUES
  ('char');

-- ID: 24
INSERT INTO
  ingredients (name)
VALUES
  ('tuna');

-- ID: 25
INSERT INTO
  ingredients (name)
VALUES
  ('shrimp');

-- ID: 26
INSERT INTO
  ingredients (name)
VALUES
  ('coffee');

-- ID: 27
INSERT INTO
  ingredients (name)
VALUES
  ('parmesan');

-- ID: 28
INSERT INTO
  ingredients (name)
VALUES
  ('pesto');

-- ID: 29
INSERT INTO
  ingredients (name)
VALUES
  ('tomato');

-- ID: 30
INSERT INTO
  ingredients (name)
VALUES
  ('panko');

-- ID: 31
INSERT INTO
  ingredients (name)
VALUES
  ('mozzarella');

-- ID: 32
INSERT INTO
  ingredients (name)
VALUES
  ('onion');

-- ID: 33
INSERT INTO
  ingredients (name)
VALUES
  ('garlic');

-- ID: 34
INSERT INTO
  ingredients (name)
VALUES
  ('parsley');

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
    'Chicken and Broccoly Stir-Fry',
    'Bla',
    'Do things',
    'Get image',
    468.0,
    17.2,
    27.2,
    38.9
  );

-- DUMMY RECIPE INGREDIENTS
INSERT INTO
  recipes_ingredients (recipe_id, ingredient_id)
VALUES
  (1, 1);

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
    'Bla',
    'Do things',
    'Get image',
    311.0,
    38.0,
    11.0,
    13.0
  );

-- DUMMY RECIPE INGREDIENTS
INSERT INTO
  recipes_ingredients (recipe_id, ingredient_id)
VALUES
  (2, 20);

INSERT INTO
  recipes_ingredients (recipe_id, ingredient_id)
VALUES
  (2, 28);

INSERT INTO
  recipes_ingredients (recipe_id, ingredient_id)
VALUES
  (2, 29);

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
    'Bla',
    'Do things',
    'Get image',
    478.0,
    35.0,
    22.0,
    36.0
  );

-- DUMMY RECIPE INGREDIENTS
INSERT INTO
  recipes_ingredients (recipe_id, ingredient_id)
VALUES
  (3, 30);

INSERT INTO
  recipes_ingredients (recipe_id, ingredient_id)
VALUES
  (3, 1);

INSERT INTO
  recipes_ingredients (recipe_id, ingredient_id)
VALUES
  (3, 27);

INSERT INTO
  recipes_ingredients (recipe_id, ingredient_id)
VALUES
  (3, 3);

INSERT INTO
  recipes_ingredients (recipe_id, ingredient_id)
VALUES
  (3, 6);

INSERT INTO
  recipes_ingredients (recipe_id, ingredient_id)
VALUES
  (3, 31);

INSERT INTO
  recipes_ingredients (recipe_id, ingredient_id)
VALUES
  (3, 8);

INSERT INTO
  recipes_ingredients (recipe_id, ingredient_id)
VALUES
  (3, 32);

INSERT INTO
  recipes_ingredients (recipe_id, ingredient_id)
VALUES
  (3, 33);

INSERT INTO
  recipes_ingredients (recipe_id, ingredient_id)
VALUES
  (3, 29);

INSERT INTO
  recipes_ingredients (recipe_id, ingredient_id)
VALUES
  (3, 34);

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
    'Season each tomato slice with sugar, salt, and pepper. Beat milk and eggs together in a bowl. Pour flour into another bowl. 
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
  recipes_ingredients (recipe_id, ingredient_id)
VALUES
  (4, 29);

INSERT INTO
  recipes_ingredients (recipe_id, ingredient_id)
VALUES
  (4, 7);

INSERT INTO
  recipes_ingredients (recipe_id, ingredient_id)
VALUES
  (4, 5);

INSERT INTO
  recipes_ingredients (recipe_id, ingredient_id)
VALUES
  (4, 3);

INSERT INTO
  recipes_ingredients (recipe_id, ingredient_id)
VALUES
  (4, 6);

INSERT INTO
  recipes_ingredients (recipe_id, ingredient_id)
VALUES
  (4, 4);

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
    'Bla',
    'Preheat the oven to 350°F (180°C) and grease a 9-inch (23 cm) spring form. In a medium-size saucepan place on low heat, melt the chocolate with the milk. Add the butter and allow to melt, stirring as needed. Turn off the heat, then add the sugar and the yolks, one at a time, stirring between each addition. Sift in the flour and stir well.
In a medium-sized bowl, whisk the whites with a pinch of salt until soft peaks form. Carefully incorporate to the chocolate mixture in two or three additions. Pour into the prepared spring form and bake for about 30-35 minutes.',
    'Get image',
    265.2,
    21.5,
    20.1,
    3.0
  );

INSERT INTO
  recipes_ingredients (recipe_id, ingredient_id)
VALUES
  (5, 15);

INSERT INTO
  recipes_ingredients (recipe_id, ingredient_id)
VALUES
  (5, 5);

INSERT INTO
  recipes_ingredients (recipe_id, ingredient_id)
VALUES
  (5, 4);

INSERT INTO
  recipes_ingredients (recipe_id, ingredient_id)
VALUES
  (5, 7);

INSERT INTO
  recipes_ingredients (recipe_id, ingredient_id)
VALUES
  (5, 6);

INSERT INTO
  recipes_ingredients (recipe_id, ingredient_id)
VALUES
  (5, 3);

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
    'Pesto Macaroni and Cheese',
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
  recipes_ingredients (recipe_id, ingredient_id)
VALUES
  (6, 5);
INSERT INTO
  recipes_ingredients (recipe_id, ingredient_id)
VALUES
  (6, 20);
INSERT INTO
  recipes_ingredients (recipe_id, ingredient_id)
VALUES
  (6, 28);
INSERT INTO
  recipes_ingredients (recipe_id, ingredient_id)
VALUES
  (6, 2);
INSERT INTO
  recipes_ingredients (recipe_id, ingredient_id)
VALUES
  (6, 29);
INSERT INTO
  recipes_ingredients (recipe_id, ingredient_id)
VALUES
  (6, 27);

-- DUMMY STORED USER RECIPES
INSERT INTO
  users_recipes (recipe_id, user_id)
VALUES
  (1, 2);

-- DUMMY RECIPE COMMENTS
INSERT INTO
  comments (body, recipe_id, user_id)
VALUES
  ('Dry', 1, 2);

-- DUMMY RECIPE REVIEWS
INSERT INTO
  reviews (rating, recipe_id, user_id)
VALUES
  (3, 1, 2);