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
  ('Chicken');

-- ID: 2
INSERT INTO
  ingredients (name)
VALUES
  ('Cheese');

-- ID: 3
INSERT INTO
  ingredients (name)
VALUES
  ('Eggs');

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