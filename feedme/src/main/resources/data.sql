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

-- DUMMY RECIPE INGREDIENTS
INSERT INTO
  recipes_ingredients (recipe_id, ingredient_id)
VALUES
  (2, 28);

-- DUMMY RECIPE INGREDIENTS
INSERT INTO
  recipes_ingredients (recipe_id, ingredient_id)
VALUES
  (2, 29);

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