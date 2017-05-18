INSERT INTO user_types(id, user_type) SELECT 1, 'property_manager' WHERE NOT EXISTS(SELECT 1 FROM user_types WHERE id=1)
INSERT INTO user_types(id, user_type) SELECT 2, 'tenant' WHERE NOT EXISTS(SELECT 1 FROM user_types WHERE id=2)
INSERT INTO user_types(id, user_type) SELECT 3, 'maintenance_engineer' WHERE NOT EXISTS(SELECT 1 FROM user_types WHERE id=3)

/* Password = test1234 */
INSERT INTO users(id, username, password, email, user_type_id) SELECT 1, 'manager1', '$2a$10$bnC26zz//2cavYoSCrlHdecWF8tkGfPodlHcYwlACBBwJvcEf0p2G', 'manager1@test.ie', 1 WHERE NOT EXISTS(SELECT 1 from users WHERE id=1)
INSERT INTO users(id, username, password, email, user_type_id) SELECT 2, 'manager2', '$2a$10$bnC26zz//2cavYoSCrlHdecWF8tkGfPodlHcYwlACBBwJvcEf0p2G', 'manager2@test.ie', 1 WHERE NOT EXISTS(SELECT 1 from users WHERE id=2)
INSERT INTO users(id, username, password, email, user_type_id) SELECT 3, 'manager3', '$2a$10$bnC26zz//2cavYoSCrlHdecWF8tkGfPodlHcYwlACBBwJvcEf0p2G', 'manager3@test.ie', 1 WHERE NOT EXISTS(SELECT 1 from users WHERE id=3)
INSERT INTO users(id, username, password, email, user_type_id) SELECT 4, 'manager4', '$2a$10$bnC26zz//2cavYoSCrlHdecWF8tkGfPodlHcYwlACBBwJvcEf0p2G', 'manager4@test.ie', 1 WHERE NOT EXISTS(SELECT 1 from users WHERE id=4)
INSERT INTO users(id, username, password, email, user_type_id) SELECT 5, 'manager5', '$2a$10$bnC26zz//2cavYoSCrlHdecWF8tkGfPodlHcYwlACBBwJvcEf0p2G', 'manager5@test.ie', 1 WHERE NOT EXISTS(SELECT 1 from users WHERE id=5)

INSERT INTO users(id, username, password, email, user_type_id) SELECT 6, 'tenant1', '$2a$10$bnC26zz//2cavYoSCrlHdecWF8tkGfPodlHcYwlACBBwJvcEf0p2G', 'tenant1@test.ie', 2 WHERE NOT EXISTS(SELECT 1 from users WHERE id=6)
INSERT INTO users(id, username, password, email, user_type_id) SELECT 7, 'tenant2', '$2a$10$bnC26zz//2cavYoSCrlHdecWF8tkGfPodlHcYwlACBBwJvcEf0p2G', 'tenant2@test.ie', 2 WHERE NOT EXISTS(SELECT 1 from users WHERE id=7)
INSERT INTO users(id, username, password, email, user_type_id) SELECT 8, 'tenant3', '$2a$10$bnC26zz//2cavYoSCrlHdecWF8tkGfPodlHcYwlACBBwJvcEf0p2G', 'tenant3@test.ie', 2 WHERE NOT EXISTS(SELECT 1 from users WHERE id=8)
INSERT INTO users(id, username, password, email, user_type_id) SELECT 9, 'tenant4', '$2a$10$bnC26zz//2cavYoSCrlHdecWF8tkGfPodlHcYwlACBBwJvcEf0p2G', 'tenant4@test.ie', 2 WHERE NOT EXISTS(SELECT 1 from users WHERE id=9)
INSERT INTO users(id, username, password, email, user_type_id) SELECT 10, 'tenant5', '$2a$10$bnC26zz//2cavYoSCrlHdecWF8tkGfPodlHcYwlACBBwJvcEf0p2G', 'tenant5@test.ie', 2 WHERE NOT EXISTS(SELECT 1 from users WHERE id=10)

INSERT INTO users(id, username, password, email, user_type_id) SELECT 11, 'engineer1', '$2a$10$bnC26zz//2cavYoSCrlHdecWF8tkGfPodlHcYwlACBBwJvcEf0p2G', 'engineer1@test.ie', 3 WHERE NOT EXISTS(SELECT 1 from users WHERE id=11)
INSERT INTO users(id, username, password, email, user_type_id) SELECT 12, 'engineer2', '$2a$10$bnC26zz//2cavYoSCrlHdecWF8tkGfPodlHcYwlACBBwJvcEf0p2G', 'engineer2@test.ie', 3 WHERE NOT EXISTS(SELECT 1 from users WHERE id=12)
INSERT INTO users(id, username, password, email, user_type_id) SELECT 13, 'engineer3', '$2a$10$bnC26zz//2cavYoSCrlHdecWF8tkGfPodlHcYwlACBBwJvcEf0p2G', 'engineer3@test.ie', 3 WHERE NOT EXISTS(SELECT 1 from users WHERE id=13)
INSERT INTO users(id, username, password, email, user_type_id) SELECT 14, 'engineer4', '$2a$10$bnC26zz//2cavYoSCrlHdecWF8tkGfPodlHcYwlACBBwJvcEf0p2G', 'engineer4@test.ie', 3 WHERE NOT EXISTS(SELECT 1 from users WHERE id=14)
INSERT INTO users(id, username, password, email, user_type_id) SELECT 15, 'engineer5', '$2a$10$bnC26zz//2cavYoSCrlHdecWF8tkGfPodlHcYwlACBBwJvcEf0p2G', 'engineer5@test.ie', 3 WHERE NOT EXISTS(SELECT 1 from users WHERE id=15)


INSERT INTO user_roles(app_user_id, role) SELECT 1, 'MEMBER' WHERE NOT EXISTS(SELECT 1 FROM user_roles WHERE app_user_id=1)
INSERT INTO user_roles(app_user_id, role) SELECT 2, 'MEMBER' WHERE NOT EXISTS(SELECT 1 FROM user_roles WHERE app_user_id=2)
INSERT INTO user_roles(app_user_id, role) SELECT 3, 'MEMBER' WHERE NOT EXISTS(SELECT 1 FROM user_roles WHERE app_user_id=3)
INSERT INTO user_roles(app_user_id, role) SELECT 4, 'MEMBER' WHERE NOT EXISTS(SELECT 1 FROM user_roles WHERE app_user_id=4)
INSERT INTO user_roles(app_user_id, role) SELECT 5, 'MEMBER' WHERE NOT EXISTS(SELECT 1 FROM user_roles WHERE app_user_id=5)

INSERT INTO user_roles(app_user_id, role) SELECT 6, 'MEMBER' WHERE NOT EXISTS(SELECT 1 FROM user_roles WHERE app_user_id=6)
INSERT INTO user_roles(app_user_id, role) SELECT 7, 'MEMBER' WHERE NOT EXISTS(SELECT 1 FROM user_roles WHERE app_user_id=7)
INSERT INTO user_roles(app_user_id, role) SELECT 8, 'MEMBER' WHERE NOT EXISTS(SELECT 1 FROM user_roles WHERE app_user_id=8)
INSERT INTO user_roles(app_user_id, role) SELECT 9, 'MEMBER' WHERE NOT EXISTS(SELECT 1 FROM user_roles WHERE app_user_id=9)
INSERT INTO user_roles(app_user_id, role) SELECT 10, 'MEMBER' WHERE NOT EXISTS(SELECT 1 FROM user_roles WHERE app_user_id=10)

INSERT INTO user_roles(app_user_id, role) SELECT 11, 'MEMBER' WHERE NOT EXISTS(SELECT 1 FROM user_roles WHERE app_user_id=11)
INSERT INTO user_roles(app_user_id, role) SELECT 12, 'MEMBER' WHERE NOT EXISTS(SELECT 1 FROM user_roles WHERE app_user_id=12)
INSERT INTO user_roles(app_user_id, role) SELECT 13, 'MEMBER' WHERE NOT EXISTS(SELECT 1 FROM user_roles WHERE app_user_id=13)
INSERT INTO user_roles(app_user_id, role) SELECT 14, 'MEMBER' WHERE NOT EXISTS(SELECT 1 FROM user_roles WHERE app_user_id=14)
INSERT INTO user_roles(app_user_id, role) SELECT 15, 'MEMBER' WHERE NOT EXISTS(SELECT 1 FROM user_roles WHERE app_user_id=15)