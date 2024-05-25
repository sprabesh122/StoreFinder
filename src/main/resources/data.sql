-- Insert Locations
INSERT INTO location (latitude, longitude, address) VALUES
(28.7041, 77.1025, 'Delhi, India'),
(19.0760, 72.8777, 'Mumbai, India');

-- Insert Users
INSERT INTO "user" (username, password, email, role, registration_date, location_id) VALUES
('user1', 'password1', 'user1@example.com', 'ROLE_USER', '2023-01-01T00:00:00', 1),
('user2', 'password2', 'user2@example.com', 'ROLE_USER', '2023-01-01T00:00:00', 2);

-- Insert Stores
INSERT INTO store (name, address, contact_details, operating_hours, description, logo_image_url, location_id, user_id) VALUES
('Store 1', 'Address 1, City, India', '1234567890', '9 AM - 9 PM', 'Description 1', 'http://example.com/logo1.png', 1, 1),
('Store 2', 'Address 2, City, India', '1234567891', '9 AM - 9 PM', 'Description 2', 'http://example.com/logo2.png', 2, 2);
