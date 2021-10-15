--DUMMY USER DATA
-- ID: 1
INSERT INTO
  users (username, email, password, admin)
VALUES
  ('admin', 'bla@bla.bla', '$2b$12$BB1tJYM6EUAaQr1YhslmHOKJkuQrBAxiPqw66pC.PsCtm/pte6uBi', true);

-- ID: 2
INSERT INTO
  users (username, email, password, admin)
VALUES
  ('johndoe', 'john@bla.bla', '$2b$12$BB1tJYM6EUAaQr1YhslmHOKJkuQrBAxiPqw66pC.PsCtm/pte6uBi', false);

--DUMMY INGREDIENT DATA
-- ID: 1
INSERT INTO ingredients (name) VALUES ('Chicken');
-- ID: 2
INSERT INTO ingredients (name) VALUES ('Cheese');
-- ID: 3
INSERT INTO ingredients (name) VALUES ('Eggs');

--DUMMY RECIPE DATA
-- ID: 1
INSERT INTO
    recipes (name, instructions, image, calories, carbs, fats, proteins)
VALUES
    ('Chicken and Broccoly Stir-Fry', 'Do things', 'Get image', 468.0, 17.2, 27.2, 38.9);

--DUMMY RECIPE INGREDIENTS
-- ID: 1
INSERT INTO recipes_ingredients (recipe_id, ingredient_id) VALUES (1, 1);

--DUMMY RECIPE COMMENTS
-- ID: 1
INSERT INTO comments (body, recipe_id) VALUES ('Dry', 1);

--DUMMY RECIPE REVIEWS
-- ID: 1
INSERT INTO reviews (rating, recipe_id) VALUES (3, 1);